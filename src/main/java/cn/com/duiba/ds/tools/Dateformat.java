package cn.com.duiba.ds.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dateformat {

public static void main(String[] args) throws ParseException {
	long s1 = System.currentTimeMillis();
	long t2=1494247765878L;
	System.out.println("当前时间毫秒值"+s1);
	
	String s2=DateToFomat(t2);
	System.out.println(t2+"=="+s2);
	
	/*String str = "C";
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
		*/
		/*long s = System.currentTimeMillis();
		long t2=1494145755349L;
		System.out.println("当前时间毫秒值"+s);
		
		String s2=DateToFomat(t2);
		System.out.println(t2+"=="+s2);*/
	
	
	String s= "Fri Jan 20 16:18:52 GMT+08:00 2017";
	System.out.println(GMTDateToFomat(s));
	
	}

	public static String GMTDateToFomat(String s) throws ParseException {
		DateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);//将GMT字符串类型的日期，转换成DataFormat类型
		Date date =sdf.parse(s);  //将传过来的字符串转换成日期类型
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("sdf==="+date);
		String time= sdf.format(date);
		return time;
	}
	

	/**
	 * Long 时间戳转换成日期格式
	 * @param s
	 * @return
	 */
	public static String DateToFomat(long s)  {
		Date date =new Date(s);  
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time= sdf.format(date);
		return time;
	}
	
}
