package toby.chap5.beanObject.dao;

import java.util.List;

import toby.chap5.enumEx.User;

public interface UserDao {
	public void add(User user1);
	public void update(User user1);
	public List<User> getAll();
}
