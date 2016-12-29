package cn.com.duiba.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import cn.com.duiba.entity.Virtual;


@Service
public class VirtualService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	 public Long getCredits(final String virparam) {
	        String sqlStr = " SELECT credits FROM virtual WHERE virparam =? ";
	        final Virtual virtual = new Virtual();
	        jdbcTemplate.query(sqlStr, new Object[]{virparam},
	                new RowCallbackHandler() {
	                    public void processRow(ResultSet rs) throws SQLException {
	                    	virtual.setCredits(rs.getLong("credits"));
	                    	virtual.setVirparam(virparam);
	                    }
	                });
	        return virtual.getCredits();
	    }

}
