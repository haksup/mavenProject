package toby.chap5.beanObject.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import toby.chap5.beanObject.dao.UserDao;
import toby.chap5.enumEx.User;
import toby.chap5.enumEx.Enum.Level;

public class UserDaoImpl implements UserDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void add(User user1){
		this.jdbcTemplate.update(
				"insert into users(id, name, password, lev, login, recommend)" +
				"values(?, ?, ?, ?, ?, ?)", user1.getId(), user1.getName(),
				user1.getPassword(), user1.getLevel().intValue(), user1.getLogin(), user1.getRecommend());
	}
	
	@Override
	public void update(User user1) {
		this.jdbcTemplate.update("update users set name = ?, password = ?, lev = ?, login = ?, " + 
				"recommend = ? where id = ? ", user1.getName(), user1.getPassword(), user1.getLevel().intValue(), 
				user1.getLogin(), user1.getRecommend(), user1.getId());
	}
	
	public List<User> getAll(){
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList("select * from users");
		
		List<User> listUser = new ArrayList<User>();
		for (Map row : rows) {
			User user1 = new User();
			user1.setId((String) row.get("ID"));
			user1.setName((String) row.get("NAME"));
			user1.setPassword((String) row.get("PASSWORD"));
			
			BigDecimal d = (BigDecimal)row.get("LEV");
			user1.setLevel(Level.valueOf(d.intValue()));
			
			d = (BigDecimal)row.get("LOGIN");
			user1.setLogin(d.intValue());
			
			d = (BigDecimal)row.get("RECOMMEND");
			user1.setRecommend(d.intValue());
			listUser.add(user1);
		}
		
		return listUser;
	}
	

}
