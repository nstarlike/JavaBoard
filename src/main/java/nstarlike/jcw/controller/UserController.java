package nstarlike.jcw.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public String mypage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
		logger.debug("start UserController.mypage()");
		logger.debug("userPrincipal=" + userPrincipal);
		
		User user = userService.getById(userPrincipal.getUser().getId());
		
		logger.debug("user=" + user);
		
		model.addAttribute("user", user);
		return PREFIX + "mypage";
	}
	
	@PostMapping("/updateProc")
	public String updateProc(@RequestParam Map<String, String> form, @AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
		logger.debug("start UserController.updateProc()");
		logger.debug("form=" + form);
		logger.debug("userPrincipal=" + userPrincipal);
		
		String password = form.get("password");
		
		User user = new User();
		user.setId(userPrincipal.getUser().getId());
		if(password != null && !password.isEmpty()) {
			user.setPassword(passwordEncoder.encode(form.get("password")));
		}
		user.setName(form.get("name"));
		user.setEmail(form.get("email"));
		
		logger.debug("user=" + user);
		
		userService.update(user);
		
		model.addAttribute("alert", "Updated.");
		model.addAttribute("replace", "mypage");
		
		return "common/proc";
	}
	
	@GetMapping("/register")
	public String register() {
		logger.debug("start UserController.register()");
		
		return PREFIX + "register";
	}
	
	@PostMapping("/registerProc")
	public String registerProc(@RequestParam Map<String, String> form, Model model) {
		logger.debug("start UserController.registerProc()");
		logger.debug("form=" + form);
		
		User user = new User();
		user.setLoginId(form.get("loginId"));
		user.setPassword(passwordEncoder.encode(form.get("password")));
		user.setName(form.get("name"));
		user.setEmail(form.get("email"));
		
		logger.debug("user=" + user);
		
		userService.create(user);
		
		model.addAttribute("alert", "Registered.");
		model.addAttribute("replace", "/login");
		
		return "common/proc";
	}
	
	@PostMapping("/unregisterProc")
	public String unregisterProc(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
		logger.debug("start UserController.unregisterProc()");
		logger.debug("userPrincipal=" + userPrincipal);
		
		userService.delete(userPrincipal.getUser().getId());
		
		model.addAttribute("alert", "Unregistered.");
		model.addAttribute("replace", "/");
		
		return "common/proc";
	}
}
