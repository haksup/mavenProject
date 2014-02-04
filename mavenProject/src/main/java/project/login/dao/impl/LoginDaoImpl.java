package project.login.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import project.login.dao.LoginDao;
import project.mapperDao.MapperDao;

@Repository
public class LoginDaoImpl extends MapperDao implements LoginDao{
	
	/** NAMESPACE */
	private static final String NAMESPACE = "login.";

	@Override
	public HashMap<String, String> selectUserCheck(HashMap<String, String> hashMap) {
		return getSqlSession().selectOne(NAMESPACE + "selectUserCheck", hashMap);
	}

}
