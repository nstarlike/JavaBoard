package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;
import nstarlike.jcw.service.PostService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"classpath:aop-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class PostServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImplTest.class);
	
	@Autowired
	private PostService postService;
	
	@Test
	void testWrite() {
		logger.debug("start PostServiceImplTest.testWrite");
		
		Post post = new Post();
		post.setWriterId(2L);
		post.setTitle("title test");
		post.setContent("content test");
		
		int ret = postService.write(post);
		
		logger.debug("ret=" + ret);
		
		assertTrue(ret > 0);
	}

	@Test
	void testListAll() {
		logger.debug("start PostServiceImplTest.testListAll");
		
		Map<String, String> params = new HashMap<>();
		params.put("startNo", "1");
		params.put("endNo", "10");
		
		List<PostMap> list = postService.listAll(params);
		
		assertNotNull(list);
		
		logger.debug("list=" + list);
		
		params = new HashMap<>();
		params.put("startNo", "1");
		params.put("endNo", "10");
		params.put("writerId", "2");
		params.put("title", "title");
		params.put("content", "content");
			
		list = postService.listAll(params);
		
		assertNotNull(list);
		
		logger.debug("list searched by conditions=" + list);
	}

	@Test
	void testGetById() {
		logger.debug("start PostServiceImplTest.testGetById");
		
		long id = 1L;
		
		Post post = postService.getById(id);
		
		assertNotNull(post);
		
		logger.debug("post=" + post);
	}

	@Test
	void testUpdate() {
		logger.debug("start PostServiceImplTest.testUpdate");
		
		Post post = new Post();
		post.setId(1L);
		post.setTitle("updated title");
		post.setContent("updated content");
		
		int ret = postService.update(post);
		
		logger.debug("ret=" + ret);
		
		assertTrue(ret > 0);
	}

	@Test
	void testHit() {
		logger.debug("start PostServiceImplTest.testHit");
		
		long id = 1L;
		
		int ret = postService.hit(id);
		
		logger.debug("ret=" + ret);
		
		assertTrue(ret > 0);
	}

	@Test
	void testDelete() {
		logger.debug("start PostSericeImplTest.testDelete");
		
		long id = 1L;
		
		int ret = postService.delete(id);
		
		logger.debug("ret=" + ret);
		
		assertTrue(ret > 0);
		
	}

}
