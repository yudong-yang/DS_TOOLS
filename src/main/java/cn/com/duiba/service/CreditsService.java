package cn.com.duiba.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import cn.com.duiba.ds.tools.sdk.CreditConsumeParams;
import cn.com.duiba.entity.Credits;

@Service
public class CreditsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 插入数据库
	 * 
	 * @param Credits
	 * @throws Exception
	 */

	public void insert(Credits credits) throws Exception {

		try {
			String sql = "insert into credits(userid,credits,ordernum,bizId,type,timestamp,description,facePrice,actualPrice,ip,waitAudit,params) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(
						sql,new Object[] { credits.getUserId(),
									credits.getCredits(),
									credits.getOrderNum(), credits.getBizId(),
									credits.getType(), DateToFomat(credits.getTimestamp()),
									credits.getDescription(),
									credits.getFacePrice(),
									credits.getActualPrice(), credits.getIp(),
									credits.getWaitAudit(), credits.getParams() },
							new int[] { java.sql.Types.VARCHAR,
									java.sql.Types.INTEGER,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR,
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR });
		} catch (Exception e) {
			throw new Exception("订单创建失败，订单号重复");
		}

	}

	public Credits findCreditByUid(final String orderNum) {
		String sqlStr = " SELECT credits, userid, status FROM credits WHERE orderNum =? ";
		final Credits credits = new Credits();
		jdbcTemplate.query(sqlStr, new Object[] { orderNum },
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						credits.setCredits(rs.getLong("credits"));
						credits.setOrderNum(orderNum);
						credits.setUserId(rs.getString("userid"));
						credits.setStatus(rs.getString("status"));
					}
				});
		return credits;
	}

	public Credits ParamToCredits(CreditConsumeParams params, String bizId) {
		Credits credit = new Credits();
		credit.setUserId(params.getUid());
		credit.setOrderNum(params.getOrderNum());
		credit.setCredits(params.getCredits());
		credit.setBizId(bizId);
		credit.setType(params.getType());
		credit.setTimestamp(params.getTimestamp().toString());
		credit.setDescription(params.getDescription());
		credit.setFacePrice(params.getFacePrice());
		credit.setActualPrice(params.getActualPrice());
		credit.setIp(params.getIp());
		credit.setWaitAudit(params.isWaitAudit() + "");
		credit.setParams(params.getParams());
		return credit;
	}

	public String updateOrderStatus(String orderNum, String status) {
		int i = jdbcTemplate.update(
				"update credits set status=? where ordernum =?", new Object[] {
						status, orderNum }, new int[] {
						java.sql.Types.VARCHAR,
						java.sql.Types.VARCHAR });
		if (i == 0) {
			return "订单状态修改失败";
		} else {
			return "订单状态修改成功";
		}
	}

	
	public static String DateToFomat(String s) {
		DateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT+08:00' yyyy", new Locale("ENGLISH", "CHINA"));
		Date date=null;
		try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			return "1970-01-01 00:00:00";
		}
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sdf.format(date);
		return time;
	}

	public void createList(CreditConsumeParams params, String bizId) throws Exception {
		Credits credit =  ParamToCredits(params,bizId);
		insert(credit);	
	}
	
}
