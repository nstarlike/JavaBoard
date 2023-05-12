package nstarlike.jcw.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.HandlerInterceptor;

import nstarlike.jcw.security.SessionConstants;

public class SessionInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		logger.debug("start SessionInterceptor.preHandle()");
		
		//for test
		request.getSession().setAttribute(SessionConstants.USER_ID, 2L);
		
		return true;
	}
}
