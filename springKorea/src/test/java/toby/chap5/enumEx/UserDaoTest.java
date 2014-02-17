package toby.chap5.enumEx;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import toby.chap.beanObject.serivce.TsetUserService;
import toby.chap.beanObject.serivce.UserService;
import toby.chap.enumEx.User1;
import toby.chap.enumEx.Enum.Level;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	UserService userService;
	
	private User1 user1;
	private User1 user2;
	private User1 user3;

	@Before
	public void setUp() throws Exception{
		this.user1 = new User1("gyumee", "박성철", "springno1", Level.BASIC, 1, 0);
		this.user2 = new User1("leegw700", "이길원", "springno2", Level.SILVER, 55, 10);
		this.user3 = new User1("bumjin", "박범진", "springno3", Level.GOLD, 100, 40);
	}
	
	private void checkSameUser(User1 user1, User1 user2){
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	}
	
	//@Test
	public void addAndGet(){
//		User1 userget1 = new User1("gyumee", "박성철", "springno1", Level.BASIC, 1, 0);
//		checkSameUser(userget1, user1);
//		userService.getUserDao().update(userget1);
//		userService.getUserDao().add(user2);
//		userService.getUserDao().add(user3);
	}
	
//	@Test
	public void getAll(){
		//userService.upgradeLevels();
		// userService.upgradeLevels2();
		System.out.println("aaa " + Level.BASIC.intValue());
		System.out.println("aaa " + Level.BASIC.nextLevel());
	}
	
//	@Test
//	public void upgradeAllOrNothing(){
//		userService.upgradeAllOrNothing();
//	}
	
	
}
