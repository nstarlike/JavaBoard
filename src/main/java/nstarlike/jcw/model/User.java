package nstarlike.jcw.model;

public class User {
	private Long id;
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String registered;
	private String lastLogged;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	public String getLastLogged() {
		return lastLogged;
	}

	public void setLastLogged(String lastLogged) {
		this.lastLogged = lastLogged;
	}

}
