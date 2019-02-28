package com.hlyc.ooptest;
/**
 * ����һ��Ա����
 * get��set����������tostring
 * @author Administrator
 *
 */
public class Emp {
	String name;
	int age;
	char gender;
	double salary;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Emp(String name, int age, char gender, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Emp [name=" + name + ", age=" + age + ", gender=" + gender + ", salary=" + salary + "]";
	}
	
	//�޲δ�ӡԱ����Ϣ����
	public  void printEmpInfo() {
		System.out.println("===================");
		System.out.println("name:" + name);
		System.out.println("age:" + age);
		System.out.println("gender:" + gender);
		System.out.println("salary:" + salary);
		System.out.println("===================");
	}
	
}