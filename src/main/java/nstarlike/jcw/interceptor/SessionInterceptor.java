package nstarlike.jcw.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nstarlike.jcw.security.SessionConstants;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//for test
		request.getSession().setAttribute(SessionConstants.USER_ID, 99999999L);
		
		return true;
	}
}
