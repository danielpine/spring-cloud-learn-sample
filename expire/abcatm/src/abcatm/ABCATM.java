

package abcatm;
import java.util.Scanner;
//����ABCATM���ʾũ��ATMϵͳ
public class ABCATM {
	private UnionPay card;//����UnionPay������card��ʾ������
//����insertCard����ʵ�ֲ忨��������card��ʼ������ʾ�忨����	
	public void insertCard(UnionPay usercard ){
		if (card == null){
			card=usercard;
		}		
	}
//����outCard����ʵ��ȡ������		
	private void outCard(){
		card=null;
	}
//����showBalance����ʵ�ֲ�ѯ����
	private void showBalance(){
		System.out.println("��ǰ������ǣ�"+card.getBalance());;
	}
//����takeMoneyʵ��ȡ���
	private void takeMoney(){
		Scanner input=new Scanner(System.in);
		System.out.println("������ȡ����Ŀ��");
	}
}
