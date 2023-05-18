package nstarlike.jcw.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.model.Attachment;
import nstarlike.jcw.service.AttachmentService;
import nstarlike.jcw.dao.AttachmentDao;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public int attach(Attachment attachment) {
		logger.debug("start AttachmentServiceImp.attach");
		logger.debug("attachment=" + attachment);
		
		return attachmentDao.create(attachment);
	}

	@Override
	public Attachment getById(long id) {
		logger.debug("start AttachmentServiceImpl.getById");
		logger.debug("id=" + id);
		
		return attachmentDao.readById(id);
	}

	@Override
	public List<Attachment> getByPostId(long postId) {
		logger.debug("start AttachmentServiceImpl.getByPostId");
		logger.debug("postId=" + postId);
		
		return attachmentDao.readByPostId(postId);
	}

	@Override
	public int delete(long id) {
		logger.debug("start AttachmentServiceImpl.delete");
		logger.debug("id=" + id);
		
		return attachmentDao.delete(id);
	}

	@Override
	public int deleteByPostId(long postId) {
		logger.debug("start AttachmentServiceImpl.deleteByPostId");
		logger.debug("postId=" + postId);
		
		return attachmentDao.deleteByPostId(postId);
	}
}
