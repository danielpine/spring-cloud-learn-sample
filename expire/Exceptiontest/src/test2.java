import java.util.InputMismatchException;
import java.util.Scanner;
public class test2 {
	public static void main(String[] args) {
		try{
			eat();
	}
	catch(InputMismatchException e){
		System.out.println("�����쳣");		
	}	eat();
	    
	}
	public static void eat()  {
		for(;;){
		System.out.println("����һ������");
		Scanner in=new Scanner(System.in);//����¼��ֵ	
		int a=in.nextInt();	
		System.out.println(5/a);
		       }
	}
	
}

		
	