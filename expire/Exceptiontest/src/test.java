import java.util.*;

public class test {
	public static void tt(Integer y) {
		System.out.println(y);
		System.out.println(y/2);
	}
	public static void main(String[] args) {
//		System.out.println("输入一个数：");
//		Scanner in=new Scanner(System.in);//键入录入值
//     	int a = 0;
//		try{
//			a=in.nextInt();
//			System.out.println(5/a);
//		}catch(NullPointerException e){
//			System.out.println("空指针异常");
//			a=1;
//		}catch(InputMismatchException e){
//			System.out.println("输入异常");
//			a=1;
//		}catch(ArithmeticException e){
//			System.out.println("运算异常");
//			a=1;
//		}finally{
//			System.out.println("finally语句");
//			
//			System.out.println(5/a);
//		}
//		
		
		try{
			tt(0);
		}catch(NullPointerException e){
			System.out.println("空指针异常");
		
		}catch(InputMismatchException e){
			System.out.println("输入异常");
		
		}catch(ArithmeticException e){
			System.out.println("运算异常");
		
		}finally{
			System.out.println("finally语句");
			
			System.out.println("over");
		}
		
		
		
	}
	
	
	
}
