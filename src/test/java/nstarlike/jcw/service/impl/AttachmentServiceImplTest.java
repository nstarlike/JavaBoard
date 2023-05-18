package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.model.Attachment;
import nstarlike.jcw.service.AttachmentService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class AttachmentServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImplTest.class);
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Test
	void testAttach() {
		logger.debug("start " + this.getClass().getSimpleName() + "." + new Object() {}.getClass().getEnclosingMethod().getName());
		
		Attachment attachment = new Attachment();
		attachment.setPostId(1);
		attachment.setFilename("testfile.xslx");
		attachment.setFilepath("2023/05/19/" + UUID.randomUUID() + ".xslx");
		
		int ret = attachmentService.attach(attachment);
		
		assertTrue(ret > 0);
	}

	@Test
	void testGetById() {
		logger.debug("start " + this.getClass().getSimpleName() + "." + new Object() {}.getClass().getEnclosingMethod().getName());
		
		long id = 1;
		Attachment attachment = attachmentService.getById(id);
		
		assertNotNull(attachment);
	}

	@Test
	void testGetByPostId() {
		logger.debug("start " + this.getClass().getSimpleName() + "." + new Object() {}.getClass().getEnclosingMethod().getName());
		
		long postId = 1;
		List<Attachment> list = attachmentService.getByPostId(postId);
		
		assertNotNull(list);
	}

	@Test
	void testDelete() {
		logger.debug("start " + this.getClass().getSimpleName() + "." + new Object() {}.getClass().getEnclosingMethod().getName());
		
		long id = 1;
		int ret = attachmentService.delete(id);
		
		assertTrue(ret > 0);
	}

}
