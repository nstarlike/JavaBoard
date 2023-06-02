package nstarlike.jcw.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;

public class OidcAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(OidcAuthenticationSuccessHandler.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.debug("principal=" + authentication.getPrincipal().getClass().getSimpleName());
		
		String email = null;
		if(authentication.getPrincipal().getClass().isAssignableFrom(DefaultOidcUser.class)) {
			DefaultOidcUser oidcUser = (DefaultOidcUser)authentication.getPrincipal();
			email = (String)oidcUser.getAttribute("email");
		}else if(authentication.getPrincipal().getClass().isAssignableFrom(DefaultOAuth2User.class)) {
			DefaultOAuth2User oAuth2User = (DefaultOAuth2User)authentication.getPrincipal();
			email = (String)oAuth2User.getAttribute("email");
		}
		
		if(email != null && !email.isEmpty()) {
			User user = userDao.readByEmail(email);
			if(user != null) {
				Authentication auth = new UsernamePasswordAuthenticationToken(
						userDetailsService.loadUserByUsername(user.getLoginId()), 
						null, 
						AuthorityUtils.createAuthorityList("ROLE_USER")
				);
				SecurityContextHolder.getContext().setAuthentication(auth);
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
