package com.hlyc.ooptest;

public class mxdx {



	public static void main(String[] args) {
		// ����һ��Emp���͵�Ա����ִ�г�ʼ��
		Emp emp1 = new Emp("����", 10, 'Ů', 1000.0);
		Emp emp2 = new Emp("����", 10, 'Ů', 1000.0);
		Emp emp3 = new Emp("����", 10, 'Ů', 1000.0);
		Emp emp4 = new Emp("����", 10, 'Ů', 1000.0);
		emp1.printEmpInfo();
		emp1.setName("����");
		System.out.println(emp1.toString()+'\n'+emp1.getName());		
		emp1.printEmpInfo();
	
	}
}
