package toby.chap.beanObject.dao;

import java.util.List;

import toby.chap.enumEx.User;

public interface UserDao {
	public void add(User user1);
	public void update(User user1);
	public List<User> getAll();
}
