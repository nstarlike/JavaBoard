package nstarlike.jcw.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nstarlike.jcw.dao.PostDao;
import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;

@Repository
public class PostRepository implements PostDao {
	private static final Logger logger = LoggerFactory.getLogger(PostRepository.class);
	private static final String PREFIX = "nstarlike.jcw.mapper.PostMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int create(Post post) {
		logger.debug("start PostRepository.create");
		logger.debug("post=" + post);
		
		return sqlSession.insert(PREFIX + "create", post);
	}

	@Override
	public List<PostMap> readAll(Map<String, String> params) {
		logger.debug("start PostRepository.readAll");
		logger.debug("params=" + params);
		
		return sqlSession.selectList(PREFIX + "readAll", params);
	}

	@Override
	public Post readById(long id) {
		logger.debug("start PostRepository.readById");
		logger.debug("id=" + id);
		
		return sqlSession.selectOne(PREFIX + "readById", id);
	}

	@Override
	public int update(Post post) {
		logger.debug("start PostRepository.update");
		logger.debug("post=" + post);
		
		return sqlSession.update(PREFIX + "update", post);
	}

	@Override
	public int hit(long id) {
		logger.debug("start PostRepository.hit");
		logger.debug("id=" + id);
		
		return sqlSession.update(PREFIX + "hit", id);
	}

	@Override
	public int delete(long id) {
		logger.debug("start PostRepository.delete");
		logger.debug("id=" + id);
		
		return sqlSession.delete(PREFIX + "delete", id);
	}

}
