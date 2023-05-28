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
		return sqlSession.insert(PREFIX + "create", post);
	}

	@Override
	public List<PostMap> readAll(Map<String, String> params) {
		return sqlSession.selectList(PREFIX + "readAll", params);
	}

	@Override
	public Post readById(long id) {
		return sqlSession.selectOne(PREFIX + "readById", id);
	}
	
	@Override
	public List<PostMap> readEntire(Map<String, String> params){
		return sqlSession.selectList(PREFIX + "readEntire", params);
	}

	@Override
	public int update(Post post) {
		return sqlSession.update(PREFIX + "update", post);
	}

	@Override
	public int hit(long id) {
		return sqlSession.update(PREFIX + "hit", id);
	}

	@Override
	public int delete(long id) {
		return sqlSession.delete(PREFIX + "delete", id);
	}

}
