package nstarlike.jcw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nstarlike.jcw.model.Attachment;
import nstarlike.jcw.model.Comment;
import nstarlike.jcw.model.CommentMap;
import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.service.AttachmentService;
import nstarlike.jcw.service.CommentService;
import nstarlike.jcw.service.PostService;
import nstarlike.jcw.util.Pagination;
import nstarlike.jcw.util.QueryStringBuilder;
import nstarlike.jcw.util.Validator;
import nstarlike.jcw.util.ValidatorInvalidException;

@Controller
@RequestMapping("/post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String PREFIX = "post/";
	private static final String ATTACHMENT_ROOT =  "c:" + File.separator + "attachments";
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	private QueryStringBuilder queryStringBuilder;
	
	public PostController() {
		List<String> whiteList = new ArrayList<>();
		whiteList.add("id");
		whiteList.add("pageNo");
		whiteList.add("search");
		whiteList.add("keyword");
		whiteList.add("cPageNo");
		
		queryStringBuilder = new QueryStringBuilder(whiteList, "id", "pageNo", "cPageNo");
		
		logger.debug("queryStringBuilder=" + queryStringBuilder);
	}
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("queryString", queryStringBuilder.attach(params));
		return PREFIX + "write";
	}
	
	@PostMapping("/writeProc")
	public String writeProc(@RequestParam Map<String, String> params, @RequestParam(required=false) MultipartFile[] files, Model model) {
		try {
			UserPrincipal userPrincipal;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			String title = Validator.empty(params.get("title"), "You must enter a title.");
			String content = Validator.empty(params.get("content"), "You must enter a content.");
			
			Post post = new Post();
			post.setWriterId(userPrincipal.getUser().getId());
			post.setTitle(title);
			post.setContent(content);
			
			int ret = postService.write(post);
			
			if(ret <= 0) {
				throw new RuntimeException("Failed to register");
			}
			
			for(MultipartFile file : files) {
				Attachment attachment = new Attachment();
				attachment.setPostId(post.getId());
				attachment.setFilename(file.getOriginalFilename());
				attachment.setFilepath(getFilepath(file.getOriginalFilename()));
				
				int retAttach = attachmentService.attach(attachment);
				
				uploadAttachment(attachment, file);
			}
			
			model.addAttribute("alert", "Registered.");
			model.addAttribute("replace", "/post/list" + params.get("queryString"));
			
		}catch(ValidatorInvalidException e) {
			e.printStackTrace();
			
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam Map<String, String> params, Model model) {
		int pageNo = 1;
		if(params.get("pageNo") != null) {
			pageNo = Integer.valueOf(params.get("pageNo"));
		}
		Pagination pagination = new Pagination(pageNo);
		params.put("startNo", String.valueOf(pagination.getStartNo()));
		params.put("endNo", String.valueOf(pagination.getEndNo()));
		
		List<PostMap> list = postService.listAll(params);
		
		logger.debug("list=" + list);
		
		long total = 0;
		if(list.size() > 0) {
			total = list.get(0).getTotal();
		}
		pagination.calculate(total);
		
		model.addAttribute("list", list);
		model.addAttribute("queryString", queryStringBuilder.attach(params));
		model.addAttribute("listQueryString", queryStringBuilder.add(params));
		model.addAttribute("pageQueryString", queryStringBuilder.addToPage(params));
		model.addAttribute("pagination", pagination);
		
		return PREFIX + "list";
	}
	
	@GetMapping("/view")
	public String view(@RequestParam Map<String, String> params, Model model) {
		Post post = postService.getById(Long.valueOf(params.get("id")));
		
		logger.debug("post=" + post);
		
		List<Attachment> attachmentList = null;
		List<CommentMap> commentList = null;
		Pagination pagination = null;
		if(post != null) {
			attachmentList = attachmentService.getByPostId(post.getId());
			
			int cPageNo = 1;
			if(params.get("cPageNo") != null) {
				cPageNo = Integer.valueOf(params.get("cPageNo"));
			}
			pagination = new Pagination(cPageNo);
			
			params.put("postId", params.get("id"));
			params.put("startNo", String.valueOf(pagination.getStartNo()));
			params.put("endNo", String.valueOf(pagination.getEndNo()));
			commentList = commentService.listAll(params);
			
			logger.debug("commentList=" + commentList);
			
			long total = 0;
			if(commentList.size() > 0) {
				total = commentList.get(0).getTotal();
			}
			pagination.calculate(total);
		}
		
		
		model.addAttribute("post", post);
		model.addAttribute("attachmentList", attachmentList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("queryString", queryStringBuilder.attach(params));
		model.addAttribute("listQueryString", queryStringBuilder.attachToGoList(params));
		model.addAttribute("pageQueryString", queryStringBuilder.addToCommentPage(params));
		model.addAttribute("pagination", pagination);
		
		return PREFIX + "view";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam Map<String, String> params, Model model) {
		try {
			UserPrincipal userPrincipal;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			Post post = postService.getById(Long.valueOf(params.get("id")));
			
			if(post.getWriterId() != userPrincipal.getUser().getId()) {
				throw new Exception("You don't have authority.");
			}
			
			List<Attachment> attachmentList = attachmentService.getByPostId(post.getId());
			
			logger.debug("post=" + post);
			
			model.addAttribute("post", post);
			model.addAttribute("attachmentList", attachmentList);
			model.addAttribute("queryString", queryStringBuilder.attach(params));
			
			return PREFIX + "/update";
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
			
			return "common/proc";
		}
	}
	
	@PostMapping("/updateProc")
	public String updateProc(@RequestParam Map<String, String> params, @RequestParam(value="files", required=false) MultipartFile[] files, 
							Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			String id = Validator.number(params.get("id"), "The post id is invalid.");
			String title = Validator.empty(params.get("title"), "You must enter a title.");
			String content = Validator.empty(params.get("content"), "You must enter a content.");
			
			Post retrieved = postService.getById(Long.valueOf(id));
			
			if(userPrincipal.getUser().getId() != retrieved.getWriterId()) {
				throw new Exception("You don't have authority.");
			}
			
			Post post = new Post();
			post.setId(retrieved.getId());
			post.setTitle(title);
			post.setContent(content);
			
			int ret = postService.update(post);
			
			if(files != null) {
				for(MultipartFile file : files) {
					Attachment attachment = new Attachment();
					attachment.setPostId(post.getId());
					attachment.setFilename(file.getOriginalFilename());
					attachment.setFilepath(getFilepath(file.getOriginalFilename()));
					
					int retAttach = attachmentService.attach(attachment);
					
					uploadAttachment(attachment, file);
				}
			}
			
			if(params.get("deletedAttachmentIds") != null && !params.get("deletedAttachmentIds").isEmpty()) {
				String[] deletedIds = params.get("deletedAttachmentIds").split(",");
				for(String deletedId : deletedIds) {
					long attachId = Long.valueOf(deletedId.trim());
					Attachment attachment = attachmentService.getById(attachId);
					deleteAttachment(attachment);
					int retAttach = attachmentService.delete(attachId);
				}
			}
			
			model.addAttribute("alert", "Updated.");
			model.addAttribute("replace", "/post/view" + params.get("queryString"));
			
		}catch(ValidatorInvalidException e) {
			e.printStackTrace();
			
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@PostMapping("/deleteProc")
	public String deleteProc(@RequestParam Map<String, String> params, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception ("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			long id = Long.valueOf(params.get("id"));
			
			Post post = postService.getById(id);
			if(post.getWriterId() != userPrincipal.getUser().getId()) {
				throw new Exception("You don't have authority.");
			}
			
			List<Attachment> attachList = attachmentService.getByPostId(post.getId());
			
			if(attachList != null) {
				deleteAttachment(attachList);
				int retAttach = attachmentService.deleteByPostId(post.getId());
			}
			
			int ret = postService.delete(id);
			
			if(ret > 0) {
				model.addAttribute("alert", "Deleted.");
				model.addAttribute("replace", "/post/list" + params.get("queryString"));
			}else {
				model.addAttribute("alert", "Failed to delete.");
				model.addAttribute("back", true);
			}
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@PostMapping("/writeCommentProc")
	public String writeCommentProc(@RequestParam Map<String, String> params, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			String id = Validator.number(params.get("id"), "The post id is invalid.");
			String content = Validator.empty(params.get("content"), "You must enter a comment.");
			
			Comment comment = new Comment();
			comment.setWriterId(userPrincipal.getUser().getId());
			comment.setPostId(Long.valueOf(id));
			comment.setContent(content);
			
			int ret = commentService.write(comment);
			
			logger.debug("ret=" + ret);
			
			model.addAttribute("alert", "Registered.");
			model.addAttribute("replace", "/post/view" + params.get("queryString"));
			
		}catch(ValidatorInvalidException e) {
			e.printStackTrace();
			
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@PostMapping("/deleteCommentProc")
	public String deleteCommentProc(@RequestParam Map<String, String> params, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			Comment retrieved = commentService.getById(Long.valueOf(params.get("commentId")));
			if(userPrincipal.getUser().getId() != retrieved.getWriterId()) {
				throw new Exception("You don't have authority.");
			}
			
			int ret = commentService.delete(retrieved.getId());
			
			model.addAttribute("alert", "Deleted.");
			model.addAttribute("replace", "/post/view" + params.get("queryString"));
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@GetMapping("/downloadAttachment")
	public void downloadAttachment(@RequestParam String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Attachment attachment = attachmentService.getById(Long.valueOf(id));
			if(attachment == null) {
				throw new Exception("The attachment data does not exist.");
			}
			
			String filename = new String(attachment.getFilename().getBytes("UTF-8"), "ISO-8859-1");
			String filepath = attachment.getFilepath();
			if(!File.separator.equals("/")) {
				filepath = filepath.replaceAll("/", "\\\\");
			}
			
			Path file = Paths.get(ATTACHMENT_ROOT + File.separator + filepath);
			
			logger.debug("file path=" + file);
			
			if(!Files.exists(file)) {
				throw new Exception("The attachment file does not exists.");
			}
			
			response.setContentType("application/octet-stream");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFilepath(String filename) {
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		Calendar calendar = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String uploadFilename = format.format(calendar.getTime()) + "/" + UUID.randomUUID().toString() + "." + extension;
		return uploadFilename;
	}
	
	private void uploadAttachment(Attachment attachment, MultipartFile multipartFile) {
		try {
			String filepath = attachment.getFilepath();
			if(!File.separator.equals("/")) {
				filepath = filepath.replaceAll("/", "\\\\");
			}
			String path = ATTACHMENT_ROOT + File.separator + filepath;
			String dirPath = path.substring(0, path.lastIndexOf(File.separator));
			logger.debug("path=" + path);
			logger.debug("dirPath=" + dirPath);
			
			File dir = new File(dirPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			File file = new File(path);
			OutputStream os = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(multipartFile.getBytes());
			bos.close();
			
			logger.debug("absolutepath=" + file.getAbsolutePath());
			
		}catch(FileNotFoundException e) {
			logger.debug(e.getMessage());
		}catch(IOException e) {
			logger.debug(e.getMessage());
		}
	}
	
	private void deleteAttachment(List<Attachment> attachments) {
		for(Attachment attachment : attachments) {
			deleteAttachment(attachment);
		}
	}
	
	private void deleteAttachment(Attachment attachment) {
		String filepath = attachment.getFilepath();
		if(!File.separator.equals("/")) {
			filepath = filepath.replaceAll("/", "\\\\");
		}
		File file = new File(ATTACHMENT_ROOT + File.separator + filepath);
		if(file.exists()) {
			boolean deleted = file.delete();
			
			logger.debug("deleted=" + deleted);
		}
		
		logger.debug("path=" + file.getAbsolutePath());
	}
}
