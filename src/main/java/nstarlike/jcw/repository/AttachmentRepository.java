package nstarlike.jcw.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.mybatis.spring.SqlSessionTemplate;

import nstarlike.jcw.dao.AttachmentDao;
import nstarlike.jcw.model.Attachment;

@Repository
public class AttachmentRepository implements AttachmentDao {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentRepository.class);
	private static final String PREFIX = "nstarlike.jcw.mapper.AttachmentMapper.";
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int create(Attachment attachment) {
		logger.debug("start AttachmentRepository.create");
		logger.debug("attachment=" + attachment);
		
		return sqlSession.insert(PREFIX + "create", attachment);
	}

	@Override
	public Attachment readById(long id) {
		logger.debug("start AttachmentRepository.readById");
		logger.debug("id=" + id);
		
		return sqlSession.selectOne(PREFIX + "readById", id);
	}

	@Override
	public List<Attachment> readByPostId(long postId) {
		logger.debug("start AttachmentRepository.readByPostId");
		logger.debug("postId=" + postId);
		
		return sqlSession.selectList(PREFIX + "readByPostId", postId);
	}

	@Override
	public int delete(long id) {
		logger.debug("start AttachmentRepositry.delete");
		logger.debug("id=" + id);
		
		return sqlSession.delete(PREFIX + "delete", id);
	}

}
