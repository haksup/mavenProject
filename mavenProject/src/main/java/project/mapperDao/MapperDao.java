package project.mapperDao;

import org.mybatis.spring.SqlSessionTemplate;

public class MapperDao {
	private static SqlSessionTemplate sqlSession;

	public static SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public static void setSqlSession(SqlSessionTemplate sqlSession) {
		MapperDao.sqlSession = sqlSession;
	}
	 
	 
}
