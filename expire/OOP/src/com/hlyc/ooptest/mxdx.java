package com.hlyc.ooptest;

public class mxdx {



	public static void main(String[] args) {
		// 创建一个Emp类型的员工并执行初始化
		Emp emp1 = new Emp("张三", 10, '女', 1000.0);
		Emp emp2 = new Emp("张三", 10, '女', 1000.0);
		Emp emp3 = new Emp("张三", 10, '女', 1000.0);
		Emp emp4 = new Emp("张三", 10, '女', 1000.0);
		emp1.printEmpInfo();
		emp1.setName("李四");
		System.out.println(emp1.toString()+'\n'+emp1.getName());		
		emp1.printEmpInfo();
	
	}
}
