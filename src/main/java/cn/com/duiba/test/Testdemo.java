package cn.com.duiba.test;

import java.util.Hashtable;


public class Testdemo {

	public static void main(String[] args) {
		String[] A = {"aa","bb","cc","dd","ee"};
		String[] B = {"oo","aa"};
		String[] C = {"ff","bb","ee","hh","cc"};
		 Hashtable<String, String> list = new Hashtable<String, String>();
		
		for (int i = 0; i <A.length; i++) {
			for (int j = 0; j <B.length; j++) {
				if(A[i]==B[j]){
					list.put("["+i+"]["+j+"]","1");
					System.out.println("A的第"+i+"个元素和B的第"+j+"个元素相同值为:"+B[j]);
				}else{list.put("["+i+"]["+j+"]","0");}
			}
		}
		System.out.println(list.size());
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				
				System.out.print(list.get("["+i+"]["+j+"]")+";");
			}
			System.out.println();
		}
			
			
		
	}

}
