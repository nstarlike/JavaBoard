package nstarlike.jcw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class QueryStringBuilder {
	private List<String> whiteList = new ArrayList<>();
	private String idParamName;
	private String pageParamName;
	private String cPageParamName;
	
	public QueryStringBuilder() {}
	
	public QueryStringBuilder(List<String> whiteList, String idParamName, String pageParamName, String cPageParamName) {
		this.whiteList = whiteList;
		this.idParamName = idParamName;
		this.pageParamName = pageParamName;
		this.cPageParamName = cPageParamName;
	}

	public List<String> getWhiteList() {
		return whiteList;
	}
	
	public void setWhiteList(List<String> whiteList) {
		this.whiteList = whiteList;
	}
	
	public String getIdParamName() {
		return idParamName;
	}
	
	public void setIdParamName(String idParamName) {
		this.idParamName = idParamName;
	}
	
	public String getPageParamName() {
		return pageParamName;
	}
	
	public void setPageParamName(String pageParamName) {
		this.pageParamName = pageParamName;
	}
	
	public String getCPageParamName() {
		return cPageParamName;
	}
	
	public void setCPageParamName(String cPageParamName) {
		this.cPageParamName = cPageParamName;
	}
	
	public void add(String whiteEntry) {
		this.whiteList.add(whiteEntry);
	}

	public String build(Map<String, String> params, List<String> excludes, String prefix) {
		StringJoiner sj = new StringJoiner("&");
		
		if(params != null) {
			Set<String> keys = params.keySet();
			for(String key : keys) {
				if(whiteList.contains(key) && (excludes == null || !excludes.contains(key))) {
					sj.add(key + "=" + params.get(key));
				}
			}
		}
		
		String queryString = "";
		
		if(sj.length() > 0) {
			queryString = prefix + sj.toString();
		}
		
		return queryString;
	}
	
	public String build(Map<String, String> params) {
		return build(params, null, null);
	}
	
	public String attach(Map<String, String> params) {
		return build(params, null, "?");
	}
	
	public String add(Map<String, String> params) {
		return build(params, null, "&");
	}
	
	public String attachToGoList(Map<String, String> params) {
		if(this.idParamName == null || this.idParamName.isEmpty()) {
			throw new QueryStringParamNameException("The parameter name for an id is not valid, " + this.idParamName);
		}
		if(this.cPageParamName == null || this.cPageParamName.isEmpty()) {
			throw new QueryStringParamNameException("The parameter name for an comment page number is not valid, " + this.cPageParamName);
		}
		
		List<String> excludes = new ArrayList<>();
		excludes.add(this.idParamName);
		excludes.add(this.cPageParamName);
		return build(params, excludes, "?");
	}
	
	public String addToPage(Map<String, String> params, String pageParamName) {
		if(pageParamName == null || pageParamName.isEmpty()) {
			throw new QueryStringParamNameException("The parameter name for a page number is not valid, " + pageParamName);
		}
		
		List<String> excludes = new ArrayList<>();
		excludes.add(pageParamName);
		return build(params, excludes, "&");
	}
	
	public String addToPage(Map<String, String> params) {
		return addToPage(params, this.pageParamName);
	}
	
	public String addToCommentPage(Map<String, String> params) {
		return addToPage(params, this.cPageParamName);
	}

	@Override
	public String toString() {
		return "QueryStringBuilder [whiteList=" + whiteList + ", idParamName=" + idParamName + ", pageParamName="
				+ pageParamName + "]";
	}
}
