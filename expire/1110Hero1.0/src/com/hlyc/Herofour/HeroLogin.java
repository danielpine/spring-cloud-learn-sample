//package com.hlyc.Herofour;
//
//import java.io.IOException;
//import java.lang.invoke.SwitchPoint;
//import java.util.Scanner;
//
//import org.dom4j.DocumentException;
//
//public class HeroLogin {
//	
//public boolean heroLogin_New() throws DocumentException, IOException {
//	Scanner in=new Scanner(System.in);
//	for (int chance1 = 3; chance1 > 0; chance1--) {	
//boolean flag=false;
//	
//		System.out.println("������ID||������");
//		String hero = in.next();
//		if(!new XmlParsing().xmlHeroIdOrNameExists(hero)){flag=true;
//		System.out.println("----�û�����ID������!����" + (chance1 - 1) + "�λ���---");// �û���������          
//		break;
//		}else
//		if (new XmlParsing().xmlHeroIdOrNameExists(hero)){
//	for(;;){// ������forѭ����ʼ��
//		Menu.heroMenu();
//		String choice = in.next();
//		switch(choice){
//		case "1":{
//			for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:forѭ��
//				//����88
//				
//				Menu.heroMenu_1();
//				// 20171107
//				
//				String choice4 = in.next();
//				if (choice4.equals("1")) {
//					new XmlParsing().xmlShowAllHeroInfo();
//				} else if (choice4.equals("2")) {
//					System.out.println("�����룺" + "Ӣ��ְҵ��|����|�̿�|��ʦ|̹��/սʿ|");
//					String heroserch = in.next();
//					if(	!new Judge().judge(heroserch)){
//						
//						System.out.println("ְҵ�������!!!\n����" + (chance_4 - 1) + "�λ���");continue;
//						}
//					if (new XmlParsing().xmlHeroSerch_byJob(heroserch))
//						flag = true;
//				} else if (choice4.equals("3")) {
//					System.out.println("�����룺" + "Ӣ��ID");
//					String heroserch = in.next();
//					if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
//						System.out.println("��ʾ���");
//						break;
//					} else
//						System.out.println("�������");
//				} else if (choice4.equals("4")) {// ��ѯ�ȼ��˺�
//					System.out.println("������ְҵ��");
//					String herosjob = in.next();
//					System.out.println("������ȼ���");
//					String heroslevel = in.next();
//					System.out.println(herosjob + "��" + heroslevel + "�ȼ��˺�Ϊ��"
//							+ new MySystem().heroDamageCalculate(herosjob, heroslevel));
////					for (int i = 0; i < 4; i++) {
////					}
//				} else if (choice4.equals("5")) {
//					flag=true;
//					break;
//					
//				} else if (choice4.equals("6")) {
//					System.exit(0);
//				}
////				 if (flag);
//				// System.out.println("�������!����" + (chance_4
//				// - 1) + "�λ���");
//			}
//		}
//			
//			
//			
//			
//		break;
//		
//		case "2":
//		 {
//			 boolean flag2=false;
//				for(int chance_2 = 3; chance_2 > 0; chance_2--){
//					if(flag2)break;
//					
//				Menu.heroMenu_2();
//				String choice_2 = in.next();
//				if(choice_2.equals("1")){
//					for(int chance_2_1 = 3; chance_2_1 > 0; chance_2_1--){
//					System.out.println("�����룺ID|xxx|");
//					String id = in.next();
//					if(new XmlParsing().xmlHeroIdOrNameExists(id)){//�ؼ���ǰ��дΪhero
//						System.out.println("�����룺��������");
//					String m = in.next();
//					if(new XmlParsing().xmlMsgAdd(id, m))
//						System.out.println("���Գɹ���");
//					heroLogin();
//					break;
//					}else{
//						System.out.println("ID�������!����" + (chance_2_1- 1) + "�λ���");
//						flag=true;}
//					}
//				}else if(choice_2.equals("2")){//�鿴�ظ�
//					if(hero.equals(new XmlParsing().xmlViaNameGetId(hero))/*˵��heroΪID*/){ 
//						String id=hero;
//						new XmlParsing().xmlMsgShowReMessage(id);		
//					}else{
//							String id =new XmlParsing().xmlViaNameGetId(hero);
//					new XmlParsing().xmlMsgShowReMessage(id);	}
//					
//					break;
//				}else
//				if(choice_2.equals("3")){
//					flag=true;
//					break;
//				}else
//				if(choice_2.equals("4")){
//					System.exit(0);
//				}	
//				System.out.println("�������!����" + (chance_2- 1) + "�λ���");
//				}
//			} 
//			
//			
//			break;
//		
//		case "3":
//			
//			main(null);
//			break;
//		case "4":
//			
//			System.exit(0);
//			break;
//		
//		
//		
//		}//switch
//		
//		
//		
//		
//	}// ������forѭ��������
//	
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		}
//	}
//	return false;
//	
//}
//}
