package cn.com.duiba.ds.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformat {

public static void main(String[] args) {
		
		long s = System.currentTimeMillis();
		long t2=1480925865000L;
		System.out.println("当前时间毫秒值"+s);
		
		String s2=DateToFomat(t2);
		System.out.println(t2+"=="+s2);
	}

	public static String DateToFomat(long s) {
		Date date=new Date(s);
		DateFormat format=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	

}
