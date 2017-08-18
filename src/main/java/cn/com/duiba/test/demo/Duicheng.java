package cn.com.duiba.test.demo;

public class Duicheng {

	public static void main(String[] args) {
		String s= "aaadssssddd";
		String sub = subString(s);
		System.out.println("===="+sub+"=:"+sub.length());

	}

	private static boolean duicheng(String s) {
		char[] ch = s.toCharArray();
		int min = 0;
		int max = ch.length-1;
		while(min<=max){
			if(ch[max--]!=ch[min++])
				return false;
		}
		return true;
	}
	
	public static String subString(String s){
		String sub = "";
		for(int i = s.length();i>=2;i--)
			for(int j = 0;j<i-1;j++){
				String temp = s.substring(j, i);
				if(duicheng(temp)&&temp.length()>=sub.length())
					sub=temp;							
				}
		return sub;
	}
}
