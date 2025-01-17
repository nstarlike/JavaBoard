package nstarlike.jcw.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;

public class ExcelHelper {
	private List<PostMap> list;
	private File file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public ExcelHelper(List<PostMap> list) {
		this.list = list;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Posts");
	}
	
	public ExcelHelper(File file) throws InvalidFormatException, IOException {
		this.file = file;
		workbook = new XSSFWorkbook(file);
	}
	
	public List<Post> read() {
		List<Post> posts = new ArrayList<>();
		
		sheet = workbook.getSheetAt(0);
		int firstNo = sheet.getFirstRowNum();
		int lastNo = sheet.getLastRowNum();
		for(int i=firstNo; i<lastNo; i++) {
			XSSFRow row = sheet.getRow(i);
			
			Post post = new Post();
			post.setWriterId(Long.valueOf(row.getCell(0).getRawValue()));
			post.setTitle(row.getCell(1).getRawValue());
			post.setContent(row.getCell(2).getRawValue());
			posts.add(post);
		}
		
		return posts;
	}
	
	public void export(OutputStream os) {
		writeHeader();
		writeBody();
		
		try {
			workbook.write(os);
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void writeHeader() {
		XSSFRow row = sheet.createRow(0);
		
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row, 0, "Post ID", style);
		createCell(row, 1, "Writer Name", style);
		createCell(row, 2, "Title", style);
		createCell(row, 3, "Content", style);
		createCell(row, 4, "Written Date", style);
	}
	
	private void writeBody() {
		int rowNo = 1;
		
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		
		for(PostMap post : list) {
			XSSFRow row = sheet.createRow(rowNo++);
			
			int colNo = 0;
			createCell(row, colNo++, post.getId(), style);
			createCell(row, colNo++, post.getWriterName(), style);
			createCell(row, colNo++, post.getTitle(), style);
			createCell(row, colNo++, post.getContent(), style);
			createCell(row, colNo++, post.getWritten(), style);
		}
	}
	
	private void createCell(XSSFRow row, int col, Object val, XSSFCellStyle style) {
		sheet.autoSizeColumn(col);
		
		XSSFCell cell = row.createCell(col);
		if(val instanceof Integer) {
			cell.setCellValue((Integer)val);
		}else if(val instanceof Long) {
			cell.setCellValue((Long)val);
		}else if(val instanceof Float) {
			cell.setCellValue((Float)val);
		}else if(val instanceof Double) {
			cell.setCellValue((Double)val);
		}else if(val instanceof String) {
			cell.setCellValue((String)val);
		}
		cell.setCellStyle(style);
	}
}
