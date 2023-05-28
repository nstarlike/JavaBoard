package nstarlike.jcw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;
import nstarlike.jcw.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String searchLoginId(User user) throws Exception {
		User retrieved = userDao.readByEmail(user.getEmail());
		if(retrieved == null || !retrieved.getName().equals(user.getName())){
			throw new Exception("Infomation is not correct.");
		}
		
		return retrieved.getLoginId();
	}

	@Override
	public boolean resetPassword(User user) {
		User retrieved = userDao.readByLoginId(user.getLoginId());
		if(retrieved != null && retrieved.getEmail().equals(user.getEmail()) && retrieved.getName().equals(user.getName())) {
			String newPassword = "password2";
			retrieved.setPassword(passwordEncoder.encode(newPassword));
			userDao.update(retrieved);
			
			String to = retrieved.getEmail();
			String subject = "Reset password";
			String text = "Hi, " + retrieved.getName()
					+ "Your password reset because you have requested.\n"
					+ "Your password is " + newPassword + " \n"
					+ "If you did not request password, please visit our site immediately.\n"
					+ "And you can reset password so other can use your account illegally.\n"
					+ "Thank you.";
			sendMail(to, subject, text);
			
			return true;
			
		}else {
			return false;
		}
	}
	
	private void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		mailSender.send(message);
	}

}
