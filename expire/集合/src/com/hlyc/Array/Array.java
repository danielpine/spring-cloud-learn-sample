package com.hlyc.Array;

import java.util.ArrayList;

public class Array {
	public static void main(String[] args) {
		/**
		 * 集合长度是可变的
		 */
		ArrayList<String> arr = new ArrayList<String>();

		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		arr.add("sss");
		System.out.println("arr的长度：" + arr.size());
		arr.add(arr.size(), "20");
		for (String s : arr) {
			System.out.println(s);
		}
		System.out.println("arr的长度：" + arr.size());
		arr.set(3, "aaa");
		System.out.println(arr.get(3));
		System.out.println("---"+arr.get(20));
		System.out.println(arr.toArray());
		System.out.println(arr.lastIndexOf("sss"));
		System.out.println("===================");
	//	System.out.println(indexOfMyself("sss"));
		System.out.println(arr.indexOf(null));
		System.out.println("===================");
		arr.trimToSize();
		arr.clear();
		arr.add("3");
		arr.indexOf("");
		
		System.out.println(arr.contains("3"));
		System.out.println(arr.size());
		Object[] array = arr.toArray();// 按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组
		for (Object s : array) {
			System.out.println(s);
		}
		

	}

	/**
	 * 集合长度是可变的
	 * List有序的，可重复，值可以为null
	 * 泛型： 规定类型
	 * ArrayList:大小可变的数组
	 * 
	 * indexOf(Object o);
	 * lastIndexOf(Object o);
	 */

}
