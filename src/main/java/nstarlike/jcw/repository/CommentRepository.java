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
import nstarlike.jcw.model.CommentMap;

@Repository
public class CommentRepository implements CommentDao {
	private static final Logger logger = LoggerFactory.getLogger(CommentRepository.class);
	private static final String PREFIX = "nstarlike.jcw.mapper.CommentMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int create(Comment comment) {
		return sqlSession.insert(PREFIX + "create", comment);
	}

	@Override
	public List<CommentMap> readAll(Map<String, String> params) {
		return sqlSession.selectList(PREFIX + "readAll", params);
	}
	
	@Override
	public Comment readById(long id) {
		return sqlSession.selectOne(PREFIX + "readById", id);
	}

	@Override
	public int delete(long id) {
		return sqlSession.delete(PREFIX + "delete", id);
	}
	
}
