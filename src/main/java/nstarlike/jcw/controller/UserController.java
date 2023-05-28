package nstarlike.jcw.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nstarlike.jcw.model.User;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.service.UserService;
import nstarlike.jcw.util.Validator;
import nstarlike.jcw.util.ValidatorInvalidException;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private static final String PREFIX = "user/";
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		UserPrincipal userPrincipal = getUserPrincipal();
		User user = userService.getById(userPrincipal.getUser().getId());
		
		logger.debug("user=" + user);
		
		model.addAttribute("user", user);
		return PREFIX + "mypage";
	}
	
	@PostMapping("/updateProc")
	public String updateProc(@RequestParam Map<String, String> form, Model model) {
		try {
			UserPrincipal userPrincipal = getUserPrincipal();
			
			String password = form.get("password");
			if(password != null) {
				password = Validator.password(password);
			}
			String name = Validator.koreanName(form.get("name"));
			String email = Validator.email(form.get("email"));
			
			User user = new User();
			user.setId(userPrincipal.getUser().getId());
			if(password != null && !password.isEmpty()) {
				user.setPassword(passwordEncoder.encode(password));
			}
			user.setName(name);
			user.setEmail(email);
			
			logger.debug("user=" + user);
			
			userService.update(user);
			
			model.addAttribute("alert", "Updated.");
			model.addAttribute("replace", "mypage");
			
		}catch(ValidatorInvalidException e) {
			e.printStackTrace();
			
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		
		return "common/proc";
	}
	
	@GetMapping("/register")
	public String register() {
		return PREFIX + "register";
	}
	
	@PostMapping("/registerProc")
	public String registerProc(@RequestParam Map<String, String> form, Model model) {
		try {
			String loginId = Validator.loginId(form.get("loginId"));
			String password = Validator.password(form.get("password"));
			String name = Validator.koreanName(form.get("name"));
			String email = Validator.email(form.get("email"));
			
			User user = new User();
			user.setLoginId(loginId);
			user.setPassword(passwordEncoder.encode(password));
			user.setName(name);
			user.setEmail(email);
			
			logger.debug("user=" + user);
			
			userService.create(user);
			
			model.addAttribute("alert", "Registered.");
			model.addAttribute("replace", "/login");
			
		}catch(ValidatorInvalidException e) {
			e.printStackTrace();
			
			model.addAttribute("alert", e.getMessage());
			model.addAttribute("back", true);
		}
		return "common/proc";
	}
	
	@PostMapping("/unregisterProc")
	public String unregisterProc(Model model) {
		UserPrincipal userPrincipal = getUserPrincipal();
		userService.delete(userPrincipal.getUser().getId());
		
		model.addAttribute("alert", "Unregistered.");
		model.addAttribute("replace", "/");
		
		return "common/proc";
	}
	
	private UserPrincipal getUserPrincipal() {
		UserPrincipal userPrincipal = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		logger.debug("authentication=" + authentication);
		
		if(authentication != null) {
			userPrincipal = (UserPrincipal)authentication.getPrincipal();
		}
		
		logger.debug("userPrincipal=" + userPrincipal);
		
		return userPrincipal;
	}
}
