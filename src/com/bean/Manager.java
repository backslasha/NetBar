package com.bean;

/**
 * +------------+-----------+--------+-----+--------+----------+ | managerNo |
 * name | gender | age | salary | password |
 * +------------+-----------+--------+-----+--------+----------+ | 3115005078 |
 * 姚海标 | 男 | 22 | 3500 | 123456 |
 * +------------+-----------+--------+-----+--------+----------+
 *
 */
public class Manager {
	long managerNo;
	String password;
	String name;
	String gender;
	String status = "offline";
	public String getStatus() {
		return status;
	}
	public Manager() {
		super();
	}
	public Manager(long managerNo, String password, String name, String gender, int age, int salary) {
		super();
		this.managerNo = managerNo;
		this.password = password;
		this.name = name;
		this.gender = gender;	
		this.age = age;
		this.salary = salary;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	int age;
	int salary;
	public long getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(long managerNo) {
		this.managerNo = managerNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Manager [managerNo=" + managerNo + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + ", salary=" + salary + "]";
	}
	
	
}
