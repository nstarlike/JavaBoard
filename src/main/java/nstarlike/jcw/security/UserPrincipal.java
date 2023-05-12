package nstarlike.jcw.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import nstarlike.jcw.model.User;

public class UserPrincipal implements UserDetails {
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(user != null);
		sb.append("user=>" + user.toString() + "\n");
		sb.append("username=" + getUsername() + ", ");
		sb.append("password=" + getPassword() + ", ");
		sb.append("getAuthorities=" + getAuthorities() + ", ");
		sb.append("isAccountNonExpired=" + isAccountNonExpired() + ", ");
		sb.append("isAccountNonLocked=" + isAccountNonLocked() + ", ");
		sb.append("isCredentialsNonExpired=" + isCredentialsNonExpired() + ", ");
		sb.append("isEnabled=" + isEnabled());
		return sb.toString();
	}
}
