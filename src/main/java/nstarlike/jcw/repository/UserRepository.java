package nstarlike.jcw.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;

@Repository
public class UserRepository implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
	
	private static final String MAPPER = "nstarlike.jcw.mapper.UserMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<User> readAll(Map<String, String> params) {
		logger.debug("start UserRepository.readAll()");
		logger.debug("params=" + params);
		
		return sqlSession.selectList(MAPPER + "readAll", params);
	}

	@Override
	public User readById(Long id) {
		logger.debug("start UserRepository.readById()");
		logger.debug("id=" + id);
		
		return sqlSession.selectOne(MAPPER + "readById", id);
	}

	@Override
	public User readByLoginId(String loginId) {
		logger.debug("start UserRepository.readByLoginId()");
		logger.debug("loginId=" + loginId);
		
		return sqlSession.selectOne(MAPPER + "readByLoginId", loginId);
	}

	@Override
	public User readByEmail(String email) {
		logger.debug("start UserRepository.readByEmail()");
		logger.debug("email=" + email);
		
		return sqlSession.selectOne(MAPPER + "readByEmail", email);
	}

	@Override
	public int create(User user) {
		logger.debug("start UserRepository.create()");
		logger.debug("user=" + user);
		
		return sqlSession.insert(MAPPER + "create", user);
	}

	@Override
	public int update(User user) {
		logger.debug("start UserRepository.update()");
		logger.debug("user=" + user);
		
		return sqlSession.update(MAPPER + "update", user);
	}

	@Override
	public int delete(Long id) {
		logger.debug("start UserRepository.delete()");
		logger.debug("id=" + id);
		
		return sqlSession.delete(MAPPER + "delete", id);
	}

}
