package project.login.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.login.dao.LoginDao;
import project.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	public LoginDao loginDao; 
	
	@Override
	public HashMap<String, String> selectUserCheck(HashMap<String, String> hashMap) {
		return loginDao.selectUserCheck(hashMap);
	}

}
