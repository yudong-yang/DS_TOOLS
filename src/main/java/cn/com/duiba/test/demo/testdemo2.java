package cn.com.duiba.test.demo;

import java.util.ArrayList;

public class testdemo2 {

	public static void main(String[] args) {

		ArrayList<Person> list = new ArrayList<Person>();
		Person p2 = new Person("333", "6666");
		Person p3 = new Person("333", "6666");
		list.add(new Person("111", "dfsdfsd"));
		list.add(new Person("222", "xiamix"));
		list.add(p2);
		System.out.println(list.size());
		System.out.println(list.remove(p3));
	}

}
