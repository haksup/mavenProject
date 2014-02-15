package toby.chap5.beanObject.serivce;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import toby.chap5.beanObject.dao.UserDao;
import toby.chap5.beanObject.serivce.TsetUserService.TestUserServiceException;
import toby.chap5.enumEx.Enum.Level;
import toby.chap5.enumEx.User1;

public class UserService {
	UserDao userDao;
	private DataSource dataSource;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void upgradeLevels(){
		List<User1> users = userDao.getAll();
		for(User1 user1 : users){
			Boolean changed = null;
			if(user1.getLevel() == Level.BASIC && user1.getLogin() >= 50){
				user1.setLevel(Level.SILVER);
				changed = true;
			}
			else if(user1.getLevel() == Level.SILVER && user1.getRecommend() >= 30){
				user1.setLevel(Level.GOLD);
				changed = true;
			}
			else if(user1.getLevel() == Level.GOLD){ changed = false;}
			else { changed = false;}
			
			if(changed){ userDao.update(user1);}
		}
	}
	
	public void upgradeLevels2(){
		List<User1> users = userDao.getAll();
		for(User1 user1 : users){
			if(canUpgradeLevel(user1)){
				upgradeLevel(user1);
			}
		}
	}
	
	private boolean canUpgradeLevel(User1 user1){
		Level currentLevel = user1.getLevel();
		
		switch(currentLevel){
		case BASIC : return (user1.getLogin() >= 50);
		case SILVER : return (user1.getRecommend() >= 30);
		case GOLD : return false;
		default : throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}
	
	private void upgradeLevel(User1 user1){
		if(user1.getLevel() == Level.BASIC){
			user1.setLevel(Level.SILVER);
		}
		else if(user1.getLevel() == Level.SILVER){
			user1.setLevel(Level.GOLD);
		}
		userDao.update(user1);
	}
	
	public void upgradeAllOrNothing(){
		List<User1> users = userDao.getAll();
		UserService testuserService = new TsetUserService(users.get(1).getId());
		testuserService.setUserDao(this.userDao);
		
		try{
			testuserService.upgradeLevels2();
		}
		catch(TestUserServiceException e){
			
		}
	}
	
	public void transactionUpgrade() throws SQLException{
		TransactionSynchronizationManager.initSynchronization();
		Connection c = DataSourceUtils.getConnection(dataSource);
		c.setAutoCommit(false);
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
