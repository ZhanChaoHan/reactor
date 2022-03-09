package com.jachs.reactor.entity;

/***
 * 
 * @author zhanchaohan
 *
 */
public class User {
	private String name;
	private int age;
	private String pwd;
	
	public User() {
		super();
	}
	public User(String name, int age, String pwd) {
		super();
		this.name = name;
		this.age = age;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
