package nstarlike.jcw.service;

import nstarlike.jcw.model.User;

public interface LoginService {
	public String searchLoginId(User user) throws Exception;
	public boolean resetPassword(User user);
}
