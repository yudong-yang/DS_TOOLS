package cn.com.duiba.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import cn.com.duiba.entity.AppDemo;

@Service
public class AppDemoService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Value("${duiba.dbhost}")
	private String dbhost;
//	String host = "";

	public List<AppDemo> findByAppCode(String appcode) {
		String sql = "SELECT appcode,appname,appsecret,appkey FROM AppDemo where appcode = ? ";
		return (List<AppDemo>) jdbcTemplate.query(sql, new Object[]{appcode},new RowMapper<AppDemo>() {

			public AppDemo mapRow(ResultSet rs, int rowNum) throws SQLException {
				AppDemo app = new AppDemo();
				app.setAppcode(rs.getString("appcode"));
				app.setAppname(rs.getString("appname"));
				app.setAppsectet(rs.getString("appsecret"));
				app.setAppkey(rs.getString("appkey"));
				return app;
			}

		});
	}
	
/**
 * 插入数据库
 * @param AppDemo
 */
	
	public void insert(AppDemo app) {
		jdbcTemplate
				.update("insert into AppDemo(appcode,appname,appsecret,appkey) values(?,?,?,?)",
						new Object[] { app.getAppcode(), app.getAppname(),
						app.getAppsectet(), app.getAppkey()}, new int[] {
								java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR 
								});
	}
		  
  
	public String urlToString(String code){
		String UrlJSON = "{'creditsurl':'"+dbhost+"/duiba/consume/"
				+code+"','notifyurl':'"+dbhost+"/duiba/notify/"
				+code+"','redirecturl':'"+dbhost+"/duiba/dbredirect/"
				+code+"','virtualurl':'"+dbhost+"/duiba/virtual/"+code+"'}";
	return UrlJSON;
	}
	
}
