package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.model.Comment;
import nstarlike.jcw.service.CommentService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class CommentServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);
	
	@Autowired
	private CommentService commentService;
	
	@Test
	void testWrite() {
		logger.debug("start CommentServiceImplTest.testWrite");
		
		Comment comment = new Comment();
		comment.setPostId(1L);
		comment.setWriterId(1L);
		comment.setContent("comment....");
		
		int ret = commentService.write(comment);
		
		assertTrue(ret > 0);
	}

	@Test
	void testListAll() {
		logger.debug("start CommentServiceImplTest.testListAll");
		
		Map<String, String> params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "9");
		
		List<Comment> list = commentService.listAll(params);
		
		assertNotNull(list);
		
		logger.debug("list=" + list);
		
		params.put("content", "comment");
		
		list = commentService.listAll(params);
		
		assertNotNull(list);
		
		logger.debug("searched list=" + list);
	}

	@Test
	void testDelete() {
		logger.debug("start CommentServiceImplTest.testDelete");
		
		long id = 1L;
		int ret = commentService.delete(id);
		
		assertTrue(ret > 0);
	}

}
