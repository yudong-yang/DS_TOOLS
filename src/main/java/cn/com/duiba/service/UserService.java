package cn.com.duiba.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import cn.com.duiba.entity.User;

@Service
public class UserService {
	public static int pagesize = 20;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> getList() {
		String sql = "SELECT userid,username,credits,phone, vip FROM users";
		 		
		return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setCredits(rs.getLong("credits"));
				user.setPhone(rs.getString("phone"));
				user.setVip(rs.getString("vip"));
				return user;
			}

		});
	}
	
	
	public List<User> findByPage(int pageNum) {
		String sql = "SELECT userid,username,credits,phone, vip FROM users limit ?,?";
		 		int start = (pageNum-1)*pagesize;
		return (List<User>) jdbcTemplate.query(sql, new Object[]{start,pagesize},new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setCredits(rs.getLong("credits"));
				user.setPhone(rs.getString("phone"));
				user.setVip(rs.getString("vip"));
				return user;
			}

		});
	}
	
/**
 * 插入数据库
 * @param user
 */
	
	public void insert(User user) {
		jdbcTemplate
				.update("insert into users(userid,username,credits,vip,phone) values(?,?,?,?,?)",
						new Object[] { user.getUserid(), user.getUsername(),
								user.getCredits(), user.getVip(),
								user.getPhone() }, new int[] {
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR,
								java.sql.Types.INTEGER, java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR });
	}
	
	/**
	 * 根据id扣积分
	 */
	public void cutCredit(String Uid,Long credits) {
		jdbcTemplate
				.update("update users set credits=credits-? where userid =?",
						new Object[] {  credits, Uid }, new int[] {
								java.sql.Types.INTEGER, 
								java.sql.Types.VARCHAR});
	}
	
	/**
	 * 根据id扣积分
	 * @throws Exception 
	 */
	public void AddCredit(String Uid,Long credits) throws Exception {
		try {int i=jdbcTemplate
				.update("update users set credits=credits+? where userid =?",
						new Object[] {  credits, Uid }, new int[] {
						java.sql.Types.INTEGER, 
						java.sql.Types.VARCHAR});
		System.out.println("数据库变更==："+i);
			if(i==0){
				throw new Exception("积分增加失败");	
			}
		} catch (Exception e) {
			throw new Exception("订单创建失败，订单号重复");	
		}
	}
	
	public String returnCredit(String Uid,Long credits) {
		int i = jdbcTemplate
				.update("update users set credits=credits+? where userid =?",
						new Object[] {  credits, Uid }, new int[] {
								java.sql.Types.INTEGER, 
								java.sql.Types.VARCHAR});
		if(i==0){
			return "积分返还失败";
		}else{return "积分返还成功";}
	}
	/**
	 * 根据id查积分
	 */
	  public Long GetCreditsByUid(final String userid) {
	        String sqlStr = " SELECT credits FROM Users WHERE userid =? ";
	        final User user = new User();
	        jdbcTemplate.query(sqlStr, new Object[]{userid},
	                new RowCallbackHandler() {
	                    public void processRow(ResultSet rs) throws SQLException {
	                        user.setCredits(rs.getLong("credits"));
	                        user.setUserid(userid);
	                    }
	                });
	        return user.getCredits();
	    }
	  
	  
	  public String deleteByid(String Uid) {
			int flag = jdbcTemplate.update("delete from users where userid =?",
							new Object[] { Uid }, new int[] {
									java.sql.Types.VARCHAR});
			if(flag==0){
			return "删除失败";}
			else{return "删除成功";}
		}
	  
}
