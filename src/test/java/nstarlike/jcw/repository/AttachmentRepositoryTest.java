package nstarlike.jcw.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.dao.AttachmentDao;
import nstarlike.jcw.model.Attachment;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:datasource-context.xml", 
		"classpath:security-context.xml", 
		"classpath:aop-context.xml", 
		"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class AttachmentRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentRepositoryTest.class);
	
	@Autowired
	private AttachmentDao attachmentDao;

	@Test
	void testCreate() {
		logger.debug("start AttachmentRepositoryTest.testCreate");
		
		Attachment attachment = new Attachment();
		attachment.setPostId(1L);
		attachment.setFilename("testfile.xslx");
		attachment.setFilepath("2023/05/19/" + UUID.randomUUID() + ".xslx");
		
		int ret = attachmentDao.create(attachment);
		
		assertTrue(ret > 0);
	}

	@Test
	void testReadById() {
		logger.debug("start AttachmentRepositoryTest.testReadById");
		
		long id = 1;
		Attachment attachment = attachmentDao.readById(id);
		
		assertNotNull(attachment);
		assertEquals(attachment.getId(), id);
	}

	@Test
	void testReadByPostId() {
		logger.debug("start AttachmentRepositoryTest.testReadByPostId");
		
		long postId = 1;
		List<Attachment> list = attachmentDao.readByPostId(postId);
		
		assertNotNull(list);
	}

	@Test
	void testDelete() {
		logger.debug("start AttachmentRepositoryTest.testDelete");
		
		long id = 1;
		int ret = attachmentDao.delete(id);
		
		assertTrue(ret > 0);
	}

}
