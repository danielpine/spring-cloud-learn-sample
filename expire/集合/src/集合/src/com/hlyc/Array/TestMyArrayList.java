package 集合.src.com.hlyc.Array;

import java.util.Arrays;
import java.util.Date;

public class TestMyArrayList {
	public static void main(String[] args){
	
//		list.add(new Date());
//		list.add(new Date());
//		list.add(new Date());
//		list.add(null);
//		System.out.println(list.size());
//		
//		
//		boolean contains = list.contains("abc");
//		System.out.println(contains);
//		System.out.println(list.indexOf(1));
		
		
	//	list.get(1000);
		
		
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//		}
		
//		int[] arr={1,2,3,4,5,3,2,1,2,43,4};
//		int[] arr2=new int[arr.length];
//		System.arraycopy(arr, 0, arr2, 0, arr.length);
//		System.out.print(Arrays.toString(arr2));
//		
//		for(int i=0;i<arr.length;i++){
//			arr2[i]=arr[i];
//		}
//		Object obj = list.set(1, "ABC");
//		System.out.println(obj);
//		System.out.println("第二个位置："+list.get(1));
		MyArrayList list=new MyArrayList();
		list.add("123");
		list.add("abc");
		for(int i=0;i<8;i++){
			list.add(i+" ");
		}
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		boolean flag = list.remove("1");
		System.out.println(flag);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
