package nstarlike.jcw.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.model.User;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.service.PostService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class PostControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(PostControllerTest.class);
	
	private MockMvc mockMvc;
	
	@Mock
	private PostService postService;
	
	@InjectMocks
	private PostController postController;
	
	@BeforeEach
	public void beforeEach() {
		logger.debug("start PostControllerTest.beforeEach");
		
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
		
		setSession();
	}
	
	private void setSession() {
		User user = new User();
		user.setId(2L);
		user.setLoginId("test1");
		user.setName("name1");
		user.setEmail("test1@naver.com");
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		
		when(authentication.getPrincipal()).thenReturn(userPrincipal);
		when(securityContext.getAuthentication()).thenReturn(authentication);
	}
	
	@Test
	void testWrite() throws Exception {
		logger.debug("start PostControllerTest.testWrite");
		
		mockMvc.perform(get("/post/write")).andExpect(status().isOk());
	}

	@Test
	void testWriteProc() throws Exception {
		logger.debug("start PostControllerTest.testWriteProc");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.post("/post/writeProc")
			.queryParam("writerId", "2")
			.queryParam("title", "test title")
			.queryParam("content", "test content");
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	void testList() throws Exception {
		logger.debug("start PostControllerTest.testList");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/post/list")
				.queryParam("pageNo", "1");
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	void testView() throws Exception {
		logger.debug("start PostControllerTest.testView");
		
		mockMvc.perform(get("/post/view").queryParam("id", "1")).andExpect(status().isOk());
	}

	@Test
	void testUpdate() throws Exception {
		logger.debug("start PostControllerTest.testUpdate");
		
		mockMvc.perform(get("/post/update").queryParam("id", "1")).andExpect(status().isOk());
	}

	@Test
	void testUpdateProc() throws Exception {
		logger.debug("start PostControllerTest.testUpdateProc");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/post/updateProc")
				.queryParam("id", "1")
				.queryParam("title", "updated title")
				.queryParam("content", "updated content");
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	void testDeleteProc() throws Exception {
		logger.debug("start PostControllerTest.testDeleteProc");
		
		mockMvc.perform(post("/post/deleteProc").queryParam("id", "1")).andExpect(status().isOk());
	}

}
