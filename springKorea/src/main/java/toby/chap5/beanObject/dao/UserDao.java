package toby.chap5.beanObject.dao;

import java.util.List;

import toby.chap5.enumEx.User1;

public interface UserDao {
	public void add(User1 user1);
	public void update(User1 user1);
	public List<User1> getAll();
}
