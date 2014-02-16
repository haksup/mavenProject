package toby.chap5.beanObject.serivce;

import toby.chap5.beanObject.serivce.impl.UserServiceImpl;
import toby.chap5.enumEx.User;

public class TsetUserService extends UserServiceImpl {
	private String id;
	
	public TsetUserService(String id){
		this.id = id;
	}
	
	protected void upgradeLevel(User user1){
		if(user1.getId().equals(this.id)) throw new TestUserServiceException();
		super.upgradeLevels();
	}
	
	static class TestUserServiceException extends RuntimeException{
		
	}
}
