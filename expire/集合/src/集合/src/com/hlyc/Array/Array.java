package ����.src.com.hlyc.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * ���ϳ����ǿɱ��
 * List����ģ����ظ���ֵ����Ϊnull
 * ���ͣ� �涨����
 * ArrayList:��С�ɱ������
 * 
 * indexOf(Object o);
 *  lastIndexOf(Object o);
 */
public class Array {
	public static void main(String[] args) {
	  List<String> arr=new ArrayList<String>();
	   arr.add("1");
	   arr.add("2");
	   arr.add(1,"3");
	   List<String> list=new ArrayList<String>();
	   list.add("4");
	   list.add("5");
	   arr.addAll(list);
//	   arr.clear();//�Ƴ�����������Ԫ��
//	   boolean contains = arr.contains("0");//�����Ƿ�����ڼ�����
//	   System.out.println(arr.indexOf("1"));//������һ�γ��ֵ�λ��
//	   System.out.println(arr.lastIndexOf("4"));//���Ҳ������һ�γ��ֵ�λ��
//	  System.out.println(arr.size());
//	   arr.remove("4");//�Ƴ��������Ƴ�λ�ú�Ԫ��
//	   arr.set(1, "a");//�滻����λ���ϵ�ֵ
	   Object[] array = arr.toArray();
	   for(Object s:array){
		   System.out.println(s);
	   }
	   
	  
	  
	}

}
