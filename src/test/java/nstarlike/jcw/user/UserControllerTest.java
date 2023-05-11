package nstarlike.jcw.user;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.controller.UserController;
import nstarlike.jcw.interceptor.SessionInterceptor;
import nstarlike.jcw.model.User;
import nstarlike.jcw.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class UserControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(userController)
				.addInterceptors(new SessionInterceptor())
				.build();
	}
	
	@Test
	void testMypage() throws Exception{
		mockMvc.perform(get("/user/mypage")).andExpect(status().isOk());
	}

	@Test
	void testUpdateProc() throws Exception {
		when(passwordEncoder.encode("password")).thenReturn("password");
		
		mockMvc.perform(post("/user/updateProc")
				.param("id", "2")
				.param("loginId", "mockito1")
				.param("password", "password")
				.param("name", "name1")
				.param("email", "mockito1@naver.com")
			).andExpect(status().isOk());
	}

	@Test
	void testRegister() throws Exception {
		mockMvc.perform(get("/user/register")).andExpect(status().isOk());
	}

	@Test
	void testRegisterProc() throws Exception {
		when(passwordEncoder.encode("password")).thenReturn("password");
		
		mockMvc.perform(post("/user/registerProc")
				.param("loginId", "mockito1")
				.param("password", "password")
				.param("name", "name1")
				.param("email", "mockito1@naver.com")
			).andExpect(status().isOk());
	}

	@Test
	void testUnregisterProc() throws Exception {
		when(userService.delete(2L)).thenReturn(1);
		
		mockMvc.perform(post("/user/unregisterProc")).andExpect(status().isOk());
	}

}
