package kr.tjeit.datas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Person {
	private String name;
	private String phoneNum;
	private Calendar createdAt;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	
	public void printMyInfo() {
		System.out.println("�̸� : " + this.name);
		System.out.println("���� : " + this.phoneNum);
		//����Ͻ� : 20181202 23:50
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		System.out.println("����Ͻ� : " + sdf.format(this.createdAt.getTime()));
	}
}
