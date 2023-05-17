package nstarlike.jcw.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.dao.PostDao;
import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;

import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class PostRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(PostRepositoryTest.class);
	
	@Autowired
	private PostDao postDao;
	
	@Test
	void testCreate() {
		logger.debug("start PostRepositoryTest.testCreate");
		
		Post post = new Post();
		post.setWriterId(2);
		post.setTitle("title");
		post.setContent("content");
		
		int ret = postDao.create(post);
		
		logger.debug("ret=" + ret);
		
		assertTrue(ret > 0);
	}

	@Test
	void testReadAll() {
		logger.debug("start PostRepositoryTest.testReadAll");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		
		List<PostMap> list = postDao.readAll(params);
		
		assertNotNull(list);
		
		logger.debug("list=" + list);
		
		params = new HashMap<String, String>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		params.put("title", "title1");
		params.put("content", "content1");
		
		list = postDao.readAll(params);
		
		assertNotNull(list);
		
		logger.debug("list search by condition=" + list);
	}

	@Test
	void testReadById() {
		logger.debug("start PostRepositoryTest.testReadById");
		
		long id = 1L;
		Post post = postDao.readById(id);
		
		assertNotNull(post);
		
		logger.debug("post=" + post);
	}

	@Test
	void testUpdate() {
		logger.debug("start PostRepositoryTest.testUpdate");
		
		long id = 1L;
		String title = "updated title";
		String content = "updated content";
		
		Post post = new Post();
		post.setId(id);
		post.setTitle(title);
		post.setContent(content);
		
		long ret = postDao.update(post);
		
		assertTrue(ret > 0);
		
		Post updated = postDao.readById(id);
		
		assertNotNull(updated);
		assertTrue(updated.getTitle().equals(title));
		assertTrue(updated.getContent().equals(content));
		
		logger.debug("updated=" + updated);
	}

	@Test
	void testHit() {
		logger.debug("start PostRepositoryTest.testHit");
		
		long id = 1L;
		
		int ret = postDao.hit(id);
		
		assertTrue(ret > 0);
	}

	@Test
	void testDelete() {
		logger.debug("start PostRepositoryTest.testDelete");
		
		long id = 1L;
		
		int ret = postDao.delete(id);
		
		assertTrue(ret > 0);
	}

}
