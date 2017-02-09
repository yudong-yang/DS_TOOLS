package cn.com.duiba.ds.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformat {

public static void main(String[] args) {
	
	
	
	String str = "C";
	switch (str) {
	case "A":
	    System.out.println("A");
	    break;
	case "B":
	    System.out.println("B");
	    break;
	case "C":
	    System.out.println("A");
	    break;
	default:
	    System.out.println(0);
	}
		
		/*long s = System.currentTimeMillis();
		long t2=1494145755349L;
		System.out.println("当前时间毫秒值"+s);
		
		String s2=DateToFomat(t2);
		System.out.println(t2+"=="+s2);*/
	}

	public static String DateToFomat(long s) {
		Date date=new Date(s);
		DateFormat format=new SimpleDateFormat("公元yyyy年MM月dd日  HH时mm分ss秒");
		String time=format.format(date);
		return time;
	}
	

}
