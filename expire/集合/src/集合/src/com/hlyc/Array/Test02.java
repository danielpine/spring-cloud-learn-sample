package 集合.src.com.hlyc.Array;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//假泛型
	//	List<String> list=new ArrayList<String>();
		
		//list.add(123);
		//获取list的字节码信息
		Class clazz = Class.forName("java.util.ArrayList");
		//创建对象
		ArrayList<String> list = (ArrayList) clazz.newInstance();
		//获取add()
		Method method = clazz.getDeclaredMethod("add", Object.class);
		//激活add() 就是调用add方法
		method.invoke(list, 123);
		method.invoke(list, new Date());
		
		int size = list.size();
		System.out.println(size);
		
		String string = list.get(0);
		
		
	}
}
