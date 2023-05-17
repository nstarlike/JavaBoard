package nstarlike.jcw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nstarlike.jcw.model.Comment;
import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.service.CommentService;
import nstarlike.jcw.service.PostService;
import nstarlike.jcw.util.Pagination;

@Controller
@RequestMapping("/post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String PREFIX = "post/";
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.write");
		logger.debug("params=" + params);
		
		model.addAttribute("queryString", makeQueryString(params, false));
		return PREFIX + "write";
	}
	
	@PostMapping("/writeProc")
	public String writeProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.writeProc");
		logger.debug("params=" + params);
		
		try {
			UserPrincipal userPrincipal;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			Post post = new Post();
			post.setWriterId(userPrincipal.getUser().getId());
			post.setTitle(params.get("title"));
			post.setContent(params.get("content"));
			
			int ret = postService.write(post);
			
			if(ret > 0) {
				model.addAttribute("alert", "Registered.");
				model.addAttribute("replace", "/post/list" + makeQueryString(params, false));
			}else {
				model.addAttribute("alert", "Failed to register.");
				model.addAttribute("back", true);
			}
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.list");
		logger.debug("params=" + params);
		
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
		model.addAttribute("queryString", makeQueryString(params, false));
		model.addAttribute("pagination", pagination);
		
		return PREFIX + "list";
	}
	
	@GetMapping("/view")
	public String view(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.view");
		logger.debug("params=" + params);
		
		Post post = postService.getById(Long.valueOf(params.get("id")));
		
		logger.debug("post=" + post);
		
		params.put("postId", params.get("id"));
		List<Comment> commentList = commentService.listAll(params);
		
		logger.debug("commentList=" + commentList);
		
		model.addAttribute("post", post);
		model.addAttribute("listQueryString", makeQueryString(params, false));
		model.addAttribute("queryString", makeQueryString(params, true));
		model.addAttribute("commentList", commentList);
		
		return PREFIX + "view";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.update");
		logger.debug("params=" + params);
		
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
			
			logger.debug("post=" + post);
			
			model.addAttribute("post", post);
			model.addAttribute("queryString", makeQueryString(params, true));
			
			return PREFIX + "/update";
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
			
			return "common/proc";
		}
	}
	
	@PostMapping("/updateProc")
	public String updateProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.updateProc");
		logger.debug("params=" + params);
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			Post retrieved = postService.getById(Long.valueOf(params.get("id")));
			
			if(userPrincipal.getUser().getId() != retrieved.getWriterId()) {
				throw new Exception("You don't have authority.");
			}
			
			Post post = new Post();
			post.setId(retrieved.getId());
			post.setTitle(params.get("title"));
			post.setContent(params.get("content"));
			
			int ret = postService.update(post);
			
			model.addAttribute("alert", "Updated.");
			model.addAttribute("replace", "/post/view" + makeQueryString(params, true));
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@PostMapping("/deleteProc")
	public String deleteProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.deleteProc");
		logger.debug("params=" + params);
		
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
			
			int ret = postService.delete(id);
			
			if(ret > 0) {
				model.addAttribute("alert", "Deleted.");
				model.addAttribute("replace", "/post/list" + makeQueryString(params, false));
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
		logger.debug("start PostController.writeCommentProc");
		logger.debug("params=" + params);
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				throw new Exception("Please login.");
			}
			UserPrincipal userPrincipal = (UserPrincipal)auth.getPrincipal();
			
			Comment comment = new Comment();
			comment.setWriterId(userPrincipal.getUser().getId());
			comment.setPostId(Long.valueOf(params.get("id")));
			comment.setContent(params.get("content"));
			
			int ret = commentService.write(comment);
			
			logger.debug("ret=" + ret);
			
			model.addAttribute("alert", "Registered.");
			model.addAttribute("replace", "/post/view" + makeQueryString(params, true));
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@PostMapping("/deleteCommentProc")
	public String deleteCommentProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start PostController.deleteCommentProc");
		logger.debug("params=" + params);
		
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
			model.addAttribute("replace", "/post/view" + makeQueryString(params, true));
			
		}catch(Exception e) {
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	/*
	 * make query string from parameters
	 */
	private String makeQueryString(Map<String, String> params, boolean includeId) {
		logger.debug("start PostController.makeQueryString");
		logger.debug("params=" + params);
		logger.debug("includeId", includeId);
		
		if(params == null || params.size() <= 0) {
			return "";
		}
		
		List<String> whiteList = new ArrayList<>();
		whiteList.add("pageNo");
		whiteList.add("search");
		whiteList.add("keyword");
		whiteList.add("cPageNo");
		if(includeId) {
			whiteList.add("id");
		}
		
		StringJoiner sj = new StringJoiner("&");
		Set<String> keys = params.keySet();
		for(String key : keys) {
			if(whiteList.contains(key)) {
				sj.add(key + "=" + params.get(key));
			}
		}
		
		if(sj.length() > 0) {
			String queryString = "?" + sj.toString();
			
			logger.debug("queryString=" + queryString);
			
			return queryString;
			
		}else {
			logger.debug("queryString=");
			
			return "";
		}
	}
}
