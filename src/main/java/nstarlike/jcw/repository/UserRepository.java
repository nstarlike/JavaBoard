package nstarlike.jcw.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;

@Repository
public class UserRepository implements UserDao {
	private static final String MAPPER = "nstarlike.jcw.mapper.UserMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<User> readAll(Map<String, String> params) {
		return sqlSession.selectList(MAPPER + "readAll");
	}

	@Override
	public User readById(Long id) {
		return sqlSession.selectOne(MAPPER + "readById");
	}

	@Override
	public User readByLoginId(String loginId) {
		return sqlSession.selectOne(MAPPER + "readByLoginId");
	}

	@Override
	public User readByEmail(String email) {
		return sqlSession.selectOne(MAPPER + "readByEmail");
	}

	@Override
	public int create(User user) {
		return sqlSession.insert(MAPPER + "create", user);
	}

	@Override
	public int update(User user) {
		return sqlSession.update(MAPPER + "update", user);
	}

	@Override
	public int delete(Long id) {
		return sqlSession.delete(MAPPER + "delete", id);
	}

}
