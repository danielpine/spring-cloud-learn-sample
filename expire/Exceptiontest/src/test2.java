import java.util.InputMismatchException;
import java.util.Scanner;
public class test2 {
	public static void main(String[] args) {
		try{
			eat();
	}
	catch(InputMismatchException e){
		System.out.println("输入异常");		
	}	eat();
	    
	}
	public static void eat()  {
		for(;;){
		System.out.println("输入一个数：");
		Scanner in=new Scanner(System.in);//键入录入值	
		int a=in.nextInt();	
		System.out.println(5/a);
		       }
	}
	
}

		
	