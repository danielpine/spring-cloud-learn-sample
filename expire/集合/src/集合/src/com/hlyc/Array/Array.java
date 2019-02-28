package 集合.src.com.hlyc.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 集合长度是可变的
 * List有序的，可重复，值可以为null
 * 泛型： 规定类型
 * ArrayList:大小可变的数组
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
//	   arr.clear();//移除集合中所有元素
//	   boolean contains = arr.contains("0");//参数是否包含在集合中
//	   System.out.println(arr.indexOf("1"));//参数第一次出现的位置
//	   System.out.println(arr.lastIndexOf("4"));//查找参数最后一次出现的位置
//	  System.out.println(arr.size());
//	   arr.remove("4");//移除，可以移除位置和元素
//	   arr.set(1, "a");//替换参数位置上的值
	   Object[] array = arr.toArray();
	   for(Object s:array){
		   System.out.println(s);
	   }
	   
	  
	  
	}

}
