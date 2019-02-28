

public class test3 {
	public static void main(String[] args) throws Myself {
		show(2);
		
	}
	public static void show(int a) throws Myself{
	    if(a>1){
	    	throw new Myself("UU");
	    }
		
	}
	static class Myself extends Exception{
		public Myself(String s) {
			System.out.println("这是一个异常"+s);// TODO Auto-generated constructor stub
		}
		
		}
			
	}
	

