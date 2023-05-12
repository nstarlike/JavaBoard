package nstarlike.jcw.datasource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:datasource-context.xml"})
class DataSourceTest {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	void testDataSource() {
		logger.debug("start DataSourceTest.testDataSource()");
		
		try {
			Connection conn = dataSource.getConnection();
			boolean isValid = conn.isValid(10 * 1000);
			
			assertTrue(isValid);
			
			logger.debug("isValid=" + isValid);
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSqlSession() {
		logger.debug("start DataSourceTest.testSqlSession()");
		
		assertNotNull(sqlSession);
	}

}
