package project.login.dao;

import java.util.HashMap;

public interface LoginDao {
	public HashMap<String, String> selectUserCheck(HashMap<String, String> hashMap);
}
