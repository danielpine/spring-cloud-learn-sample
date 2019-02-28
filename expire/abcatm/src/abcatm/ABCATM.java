

package abcatm;
import java.util.Scanner;
//定义ABCATM类表示农行ATM系统
public class ABCATM {
	private UnionPay card;//定义UnionPay类属性card表示银联卡
//定义insertCard方法实现插卡操作，将card初始化即表示插卡操作	
	public void insertCard(UnionPay usercard ){
		if (card == null){
			card=usercard;
		}		
	}
//定义outCard方法实现取卡操作		
	private void outCard(){
		card=null;
	}
//定义showBalance方法实现查询余额功能
	private void showBalance(){
		System.out.println("当前的余额是："+card.getBalance());;
	}
//定义takeMoney实现取款功能
	private void takeMoney(){
		Scanner input=new Scanner(System.in);
		System.out.println("请输入取款数目：");
	}
}
