import java.util.*;

public class test {
	public static void tt(Integer y) {
		System.out.println(y);
		System.out.println(y/2);
	}
	public static void main(String[] args) {
//		System.out.println("����һ������");
//		Scanner in=new Scanner(System.in);//����¼��ֵ
//     	int a = 0;
//		try{
//			a=in.nextInt();
//			System.out.println(5/a);
//		}catch(NullPointerException e){
//			System.out.println("��ָ���쳣");
//			a=1;
//		}catch(InputMismatchException e){
//			System.out.println("�����쳣");
//			a=1;
//		}catch(ArithmeticException e){
//			System.out.println("�����쳣");
//			a=1;
//		}finally{
//			System.out.println("finally���");
//			
//			System.out.println(5/a);
//		}
//		
		
		try{
			tt(0);
		}catch(NullPointerException e){
			System.out.println("��ָ���쳣");
		
		}catch(InputMismatchException e){
			System.out.println("�����쳣");
		
		}catch(ArithmeticException e){
			System.out.println("�����쳣");
		
		}finally{
			System.out.println("finally���");
			
			System.out.println("over");
		}
		
		
		
	}
	
	
	
}
