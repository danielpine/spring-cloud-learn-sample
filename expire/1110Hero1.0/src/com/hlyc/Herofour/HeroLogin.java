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
//		System.out.println("请输入ID||姓名：");
//		String hero = in.next();
//		if(!new XmlParsing().xmlHeroIdOrNameExists(hero)){flag=true;
//		System.out.println("----用户名或ID不存在!还有" + (chance1 - 1) + "次机会---");// 用户名不存在          
//		break;
//		}else
//		if (new XmlParsing().xmlHeroIdOrNameExists(hero)){
//	for(;;){// 第三级for循环开始处
//		Menu.heroMenu();
//		String choice = in.next();
//		switch(choice){
//		case "1":{
//			for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:for循环
//				//返回88
//				
//				Menu.heroMenu_1();
//				// 20171107
//				
//				String choice4 = in.next();
//				if (choice4.equals("1")) {
//					new XmlParsing().xmlShowAllHeroInfo();
//				} else if (choice4.equals("2")) {
//					System.out.println("请输入：" + "英雄职业：|射手|刺客|法师|坦克/战士|");
//					String heroserch = in.next();
//					if(	!new Judge().judge(heroserch)){
//						
//						System.out.println("职业输入错误!!!\n还有" + (chance_4 - 1) + "次机会");continue;
//						}
//					if (new XmlParsing().xmlHeroSerch_byJob(heroserch))
//						flag = true;
//				} else if (choice4.equals("3")) {
//					System.out.println("请输入：" + "英雄ID");
//					String heroserch = in.next();
//					if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
//						System.out.println("显示完成");
//						break;
//					} else
//						System.out.println("输入错误！");
//				} else if (choice4.equals("4")) {// 查询等级伤害
//					System.out.println("请输入职业：");
//					String herosjob = in.next();
//					System.out.println("请输入等级：");
//					String heroslevel = in.next();
//					System.out.println(herosjob + "在" + heroslevel + "等级伤害为："
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
//				// System.out.println("输入错误!还有" + (chance_4
//				// - 1) + "次机会");
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
//					System.out.println("请输入：ID|xxx|");
//					String id = in.next();
//					if(new XmlParsing().xmlHeroIdOrNameExists(id)){//关键以前误写为hero
//						System.out.println("请输入：留言内容");
//					String m = in.next();
//					if(new XmlParsing().xmlMsgAdd(id, m))
//						System.out.println("留言成功！");
//					heroLogin();
//					break;
//					}else{
//						System.out.println("ID输入错误!还有" + (chance_2_1- 1) + "次机会");
//						flag=true;}
//					}
//				}else if(choice_2.equals("2")){//查看回复
//					if(hero.equals(new XmlParsing().xmlViaNameGetId(hero))/*说明hero为ID*/){ 
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
//				System.out.println("输入错误!还有" + (chance_2- 1) + "次机会");
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
//	}// 第三级for循环结束处
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
