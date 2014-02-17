package toby.chap.beanObject.serivce.impl;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import toby.chap.beanObject.dao.UserDao;
import toby.chap.beanObject.serivce.UserService;
import toby.chap.enumEx.User;
import toby.chap.enumEx.Enum.Level;

public class UserServiceImpl implements UserService {
	UserDao userDao;
	
	PlatformTransactionManager transactionManager;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

//	public void upgradeLevels(){
//		List<User> users = userDao.getAll();
//		for(User user1 : users){
//			Boolean changed = null;
//			if(user1.getLevel() == Level.BASIC && user1.getLogin() >= 50){
//				user1.setLevel(Level.SILVER);
//				changed = true;
//			}
//			else if(user1.getLevel() == Level.SILVER && user1.getRecommend() >= 30){
//				user1.setLevel(Level.GOLD);
//				changed = true;
//			}
//			else if(user1.getLevel() == Level.GOLD){ changed = false;}
//			else { changed = false;}
//			
//			if(changed){ userDao.update(user1);}
//		}
//	}
//	
//	public void upgradeLevels2(){
//		List<User> users = userDao.getAll();
//		for(User user1 : users){
//			if(canUpgradeLevel(user1)){
//				upgradeLevel(user1);
//			}
//		}
//	}
	
//	public void upgradeAllOrNothing(){
//		List<User> users = userDao.getAll();
//		UserServiceImpl testuserService = new TsetUserService(users.get(1).getId());
//		testuserService.setUserDao(this.userDao);
//		
//		try{
//			testuserService.upgradeLevels2();
//		}
//		catch(Exception e){
//			
//		}
//	}
	
	private boolean canUpgradeLevel(User user){
		Level currentLevel = user.getLevel();
		
		switch(currentLevel){
		case BASIC : return (user.getLogin() >= 50);
		case SILVER : return (user.getRecommend() >= 30);
		case GOLD : return false;
		default : throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}
	
	private void upgradeLevel(User user1){
		if(user1.getLevel() == Level.BASIC){
			user1.setLevel(Level.SILVER);
		}
		else if(user1.getLevel() == Level.SILVER){
			user1.setLevel(Level.GOLD);
		}
		userDao.update(user1);
	}
	
	
	// 6장 내용 시작
	public void transactionUpgrade(){
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			upgradeLevelInternal();
			
			this.transactionManager.commit(status);
		} catch (Exception e) {
			this.transactionManager.rollback(status);
		}
	}
	
	private void upgradeLevelInternal(){
		List<User> users = userDao.getAll();
		for(User user : users){
			if(canUpgradeLevel(user)){
				upgradeLevel(user);
			}
		}
	}
	
	public void upgradeLevels(){
		List<User> users = userDao.getAll();
		for(User user : users){
			if(canUpgradeLevel(user)){
				upgradeLevel(user);
			}
		}
	}
	
}
