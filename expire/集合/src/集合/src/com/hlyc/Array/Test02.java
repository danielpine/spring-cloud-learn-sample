package ����.src.com.hlyc.Array;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//�ٷ���
	//	List<String> list=new ArrayList<String>();
		
		//list.add(123);
		//��ȡlist���ֽ�����Ϣ
		Class clazz = Class.forName("java.util.ArrayList");
		//��������
		ArrayList<String> list = (ArrayList) clazz.newInstance();
		//��ȡadd()
		Method method = clazz.getDeclaredMethod("add", Object.class);
		//����add() ���ǵ���add����
		method.invoke(list, 123);
		method.invoke(list, new Date());
		
		int size = list.size();
		System.out.println(size);
		
		String string = list.get(0);
		
		
	}
}
