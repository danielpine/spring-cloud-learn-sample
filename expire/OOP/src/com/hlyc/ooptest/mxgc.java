package com.hlyc.ooptest;

public class mxgc {
	public static void printEmpInfo(String name,int age,char gender,double salary) {
			System.out.println("-----------------------");
			System.out.println("������"+name);
			System.out.println("���䣺"+age);
			System.out.println("�Ա�"+gender);
			System.out.println("нˮ"+salary);
	}
	public static void main(String[] args) {
		String emp1name="xiaozhang";
		int emp1age=10;
		char emp1gender='Ů';
		double emp1salary=10000;
		printEmpInfo(emp1name, emp1age, emp1gender, emp1salary);
		emp1salary *=120.0/100.0;//����С����нˮ���䣿������
		printEmpInfo(emp1name, emp1age, emp1gender, emp1salary);
	}
}
