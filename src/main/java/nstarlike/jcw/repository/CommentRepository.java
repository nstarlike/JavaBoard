package nstarlike.jcw.repository;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.mybatis.spring.SqlSessionTemplate;

import nstarlike.jcw.dao.CommentDao;
import nstarlike.jcw.model.Comment;

@Repository
public class CommentRepository implements CommentDao {
	private static final Logger logger = LoggerFactory.getLogger(CommentRepository.class);
	private static final String PREFIX = "nstarlike.jcw.mapper.CommentMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int create(Comment comment) {
		logger.debug("start CommentRepository.create");
		logger.debug("comment=" + comment);
		
		return sqlSession.insert(PREFIX + "create", comment);
	}

	@Override
	public List<Comment> readAll(Map<String, String> params) {
		logger.debug("start CommentRepository.readAll");
		logger.debug("params=" + params);
		
		return sqlSession.selectList(PREFIX + "readAll", params);
	}

	@Override
	public int delete(long id) {
		logger.debug("start CommentRepository.delete");
		logger.debug("id=" + id);
		
		return sqlSession.delete(PREFIX + "delete", id);
	}
	
}
