package com.hlyc.ooptest;

public class mxgc {
	public static void printEmpInfo(String name,int age,char gender,double salary) {
			System.out.println("-----------------------");
			System.out.println("姓名："+name);
			System.out.println("年龄："+age);
			System.out.println("性别："+gender);
			System.out.println("薪水"+salary);
	}
	public static void main(String[] args) {
		String emp1name="xiaozhang";
		int emp1age=10;
		char emp1gender='女';
		double emp1salary=10000;
		printEmpInfo(emp1name, emp1age, emp1gender, emp1salary);
		emp1salary *=120.0/100.0;//不加小数点薪水不变？？？？
		printEmpInfo(emp1name, emp1age, emp1gender, emp1salary);
	}
}
