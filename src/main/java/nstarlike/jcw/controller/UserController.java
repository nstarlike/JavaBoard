package nstarlike.jcw.controller;

import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import nstarlike.jcw.model.User;
import nstarlike.jcw.security.SessionConstants;
import nstarlike.jcw.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final String PREFIX = "/user/";
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/mypage")
	public String mypage(HttpSession httpSession, Model model) {
		long userId = (long)httpSession.getAttribute(SessionConstants.USER_ID);
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return PREFIX + "mypage";
	}
	
	@PostMapping("/updateProc")
	public String updateProc(Map<String, Object> form, HttpSession httpSession, Model model) {
		User user = new User();
		user.setId((long)httpSession.getAttribute(SessionConstants.USER_ID));
		user.setLoginId((String)form.get("loginId"));
		user.setPassword(passwordEncoder.encode((String)form.get("password")));
		user.setName((String)form.get("name"));
		user.setEmail((String)form.get("email"));
		
		userService.update(user);
		
		model.addAttribute("alert", "Updated.");
		model.addAttribute("replace", "mypage");
		
		return "common/proc";
	}
	
	@GetMapping("/register")
	public String register() {
		return PREFIX + "register";
	}
	
	@PostMapping("/registerProc")
	public String registerProc(Map<String, Object> form, Model model) {
		User user = new User();
		user.setLoginId((String)form.get("loginId"));
		user.setPassword(passwordEncoder.encode((String)form.get("password")));
		user.setName((String)form.get("name"));
		user.setEmail((String)form.get("email"));
		
		userService.create(user);
		
		model.addAttribute("alert", "Registered.");
		model.addAttribute("replace", "/login");
		
		return "common/proc";
	}
	
	@PostMapping("/unregisterProc")
	public String unregisterProc(HttpSession httpSession, Model model) {
		long userId = (long)httpSession.getAttribute(SessionConstants.USER_ID);
		
		userService.delete(userId);
		
		model.addAttribute("alert", "Unregistered.");
		model.addAttribute("replace", "/");
		
		return "common/proc";
	}
}
