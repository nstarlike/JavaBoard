package nstarlike.jcw.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;

import nstarlike.jcw.dao.CommentDao;
import nstarlike.jcw.model.Comment;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class CommentRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryTest.class);
	
	@Autowired
	private CommentDao commentDao;
	
	@Test
	void testCreate() {
		logger.debug("start CommentRepositoryTest.testCreate");
		
		Comment comment = new Comment();
		comment.setPostId(1L);
		comment.setWriterId(1L);
		comment.setContent("comment");
		
		int ret = commentDao.create(comment);
		
		assertTrue(ret > 0);
	}

	@Test
	void testReadAll() {
		logger.debug("start CommentRepositoryTest.testReadAll");
		
		Map<String, String> params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "9");
		
		List<Comment> list = commentDao.readAll(params);
		
		assertNotNull(list);
		
		logger.debug("list=" + list);
		
		params.put("content", "comment");
		
		list = commentDao.readAll(params);
		
		assertNotNull(list);
		
		logger.debug("searched list=" + list);
	}

	@Test
	void testDelete() {
		logger.debug("start CommentRepositoryTest.testDelete");
		
		long id = 1L;
		int ret = commentDao.delete(id);
		
		assertTrue(ret > 0);
	}
}
