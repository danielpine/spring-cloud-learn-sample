package com.hlyc.Array;

import java.util.ArrayList;

public class Array {
	public static void main(String[] args) {
		/**
		 * ���ϳ����ǿɱ��
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
		System.out.println("arr�ĳ��ȣ�" + arr.size());
		arr.add(arr.size(), "20");
		for (String s : arr) {
			System.out.println(s);
		}
		System.out.println("arr�ĳ��ȣ�" + arr.size());
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
		Object[] array = arr.toArray();// ���ʵ�˳�򣨴ӵ�һ�������һ��Ԫ�أ����ذ������б�������Ԫ�ص�����
		for (Object s : array) {
			System.out.println(s);
		}
		

	}

	/**
	 * ���ϳ����ǿɱ��
	 * List����ģ����ظ���ֵ����Ϊnull
	 * ���ͣ� �涨����
	 * ArrayList:��С�ɱ������
	 * 
	 * indexOf(Object o);
	 * lastIndexOf(Object o);
	 */

}
