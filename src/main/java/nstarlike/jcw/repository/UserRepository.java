package nstarlike.jcw.repository;

import java.util.List;

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
	public List<User> readAll() {
		return sqlSession.selectList(MAPPER + "readAll");
	}

	@Override
	public User readByUserId(String userId) {
		return sqlSession.selectOne(MAPPER + "readByUserId");
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
	public int delete(Long userId) {
		return sqlSession.delete(MAPPER + "delete", userId);
	}

}
