package nstarlike.jcw.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import nstarlike.jcw.model.User;
import nstarlike.jcw.service.impl.UserDetailsServiceImpl;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@GetMapping("/login")
	public String login() {
		logger.debug("start LoginController.login()");
		
		return "login";
	}
	
	@GetMapping("/searchId")
	public String searchId() {
		logger.debug("start LoginController.searchId");
		
		return "searchId";
	}
	
	@PostMapping("/searchIdProc")
	public String searchIdProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start LoginController.searchIdProc");
		logger.debug("params = " + params);
		
		User user = new User();
		user.setName(params.get("name"));
		user.setEmail(params.get("email"));
		
		try {
			String userId = userDetailsService.searchLoginId(user);
			
			logger.debug("userId=" + userId);
			
			model.addAttribute("alert", "Your ID is " + userId);
			model.addAttribute("location", "/login");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			model.addAttribute("alert", "Your infomation is not correct.");
			model.addAttribute("location", "/searchId");
		}
		
		return "common/proc";
	}
	
	@GetMapping("/resetPassword")
	public String resetPassword() {
		logger.debug("start LoginController.resetPassword");
		
		return "resetPassword";
	}
	
	@PostMapping("/resetPasswordProc")
	public String resetPasswordProc(@RequestParam Map<String, String> params, Model model) {
		logger.debug("start LoginController resetPasswordProc");
		logger.debug("params=" + params);
		
		User user = new User();
		user.setLoginId(params.get("loginId"));
		user.setName(params.get("name"));
		user.setEmail(params.get("email"));
		
		//check if the reset password is sent.
		boolean isSent = userDetailsService.resetPassword(user);
		if(isSent) {
			model.addAttribute("alert", "New password is sent to you email.");
			model.addAttribute("location", "/login");
		}else {
			model.addAttribute("alert", "You information is not correct.");
			model.addAttribute("location", "/resetPassword");
		}
		
		return "common/proc";
	}
}
