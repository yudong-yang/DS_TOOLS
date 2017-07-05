package cn.com.duiba.test;

import java.awt.List;


import java.util.ArrayList;

import cn.com.duiba.ds.tools.buildUrl;

import com.alibaba.fastjson.JSONArray;
public class ActivityTimes {

	public static void main(String[] args) {
		
		String uid = "2";
	
		String activityId = "2312146";
		String times = "3";
		String bizId = System.currentTimeMillis()+"";
		String json = "[{'activityId':'2312146','times':3}]";
		String url = buildUrl.getActivityTimes(uid,json,activityId,times, bizId);
		
		String url2 = buildUrl.getActivityTimes2(uid,json,activityId,times, bizId);
		
		String url3 = buildUrl.getActivityTimes3(uid,json,activityId,times, bizId);
		
		System.out.println("生成无json的url==="+url);
		System.out.println("生成json为空的url==="+url2);
		
		System.out.println("生成json不为空的url==="+url3);
	}
}


class Activity{
	private String activityId;
	private String times;
	
	
	public Activity() {
		super();
	}
	public Activity(String activityId, String times) {
		super();
		this.activityId = activityId;
		this.times = times;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
}