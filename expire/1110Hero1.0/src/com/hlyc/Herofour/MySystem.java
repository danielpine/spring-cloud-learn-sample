package com.hlyc.Herofour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.SwitchPoint;
import java.text.BreakIterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class MySystem {
	static boolean comeback = false;
	public static Scanner in = new Scanner(System.in);
	private static boolean flag3;

	public static void main(String[] args) throws DocumentException, IOException {
		for (;;) {
			Menu.loginMenu();
			String s = in.next();
			if (s.equals("1")) {
				if (adminLogin())
					break;
			} else if (s.equals("2")) {
				if (heroLogin_New())// 此方法为重写方法 原方法heroLogin()难以实现菜单返回功能
					break;
			} else if (s.equals("e") || s.equals("E")) {
				System.exit(0);
			} else {
				System.out.println("----失败！请输入正确选项！！！---");
			}
		}
	}

	public static boolean adminLogin() throws DocumentException, IOException {
		for (int chance1 = 3; chance1 > 0; chance1--) {// 第一级for循环开始处
			System.out.println("请输入用户名：");
			String adminname = in.next();
			if (new XmlParsing().xmlAdminExists(adminname)) {
				for (int chance2 = 3; chance2 > 0; chance2--) {// 第二级for循环终止处
					System.out.println("请输入密码：");
					String pwd = in.next();
					if (new XmlParsing().xmlAdminCheckPwd(adminname, pwd)) {
						for (;;) {// 第三级for循环开始处

							Menu.adminMenu();// 管理员登录菜单
							String choice = in.next();
							switch (choice) {
							case "1":// 添加
								boolean flag1_1 = false;
								for (int chance2_1_1 = 3; chance2_1_1 > 0; chance2_1_1--) {
									if (flag1_1 || chance2_1_1 == 0)
										break;// 使用者选择返回
									Menu.adminMenu_1();// 按职业添加
									String input_1 = in.next();
									if (input_1.equals("q") || input_1.equals("Q")) {
										flag1_1 = true;
										break;
									} else
										System.out.println("请按如下格式输入" + "\n|射手|刺客|法师|坦克/战士|");

									String addjob = in.next();
									if (!new Judge().judge(addjob)) {

										System.out.println("职业输入错误!!!\n还有" + (chance2_1_1 - 1) + "次机会");
										continue;
									}
									try {
										if (new XmlParsing().xmlHeroAdd(addjob))
											System.out.println("添加成功");
										;
									} catch (IOException e) {
										// TODO Auto-generated catch block
										System.out.println("输入错误！");
										e.printStackTrace();
									}

								}
								break;
							case "2":// 删除
								boolean flag2 = false;
								for (int chance_2 = 3; chance_2 > 0; chance_2--) {// case"2":for循环
									if (flag2)
										break;
									Menu.adminMenu_2();
									// 20171107:未实现按职业查询
									String choice2 = in.next();
									boolean flag = false;
									if (choice2.equals("1")) {
										System.out.println("请输入：" + "英雄姓名");//
										String hero = in.next();
										if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
											if (new XmlParsing().xmlHeroDelectByName(hero)) {
												System.out.println("删除成功！");
												break;
											}
										} else {
											System.out.println("没有该英雄！！！");
											System.out.println("输入错误！还有" + (chance_2 - 1) + "次机会");
											continue;
										}
									} else if (choice2.equals("2")) {
										System.out.println("请输入：" + "英雄ID：格式|001|");
										String hero = in.next();
										if (new XmlParsing().xmlHeroDelectByID(hero)) {
											System.out.println("删除成功！");
											break;
										} else {
											System.out.println("没有该英雄！！！");
											System.out.println("输入错误！还有" + (chance_2 - 1) + "次机会");
											continue;
										}
									} else if (choice2.equals("3")) {
										flag2 = true;

									} else if (choice2.equals("4") || choice2.equals("E") || choice2.equals("e")) {
										System.exit(0);
									} else if (flag) {
										System.out.println("输入错误！还有" + (chance_2 - 1) + "次机会");

									}
								}
								break;
							case "3":// 2017 11 08 有瑕疵//系统出错会直接删掉英雄BUG
							{
								for (int chance_3 = 3; chance_3 > 0; chance_3--) {// 更改
									Menu.adminMenu_3(1);
									String in_3_1 = in.next();

									if (in_3_1.equals("1"))

									{

										System.out.println("请输入：" + "英雄姓名");//
										String hero = in.next();
										if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {

											System.out.println("没有该英雄！！！");
											System.out.println("输入错误！还有" + (chance_3 - 1) + "次机会");
											continue;

										}
										String herojob = new XmlParsing().xmlViaNameGetJob(hero);
										System.out.println("职业：" + herojob);

										Menu.adminMenu_3(2);
										String in_3_1_2 = in.next();

										if (in_3_1_2.equals("1")) {
											changeCharacteristic(herojob, hero);
										} // 更改属性
										else if (in_3_1_2.equals("2")) {// 更改职业
											System.out.println("请按如下格式输入更改职业：" + "\n|射手|刺客|法师|坦克/战士|");
											String newjob = in.next();
											if (!new Judge().judge(newjob)) {
												System.out.println("职业输入错误!!!\n还有" + (chance_3 - 1) + "次机会");
												continue;
											}
											String oldid = new XmlParsing().xmlViaNameGetId(hero);
											new XmlParsing().xmlHeroDelect(hero);// remove
											if (new XmlParsing().xmlHeroAddNewJob(newjob, oldid, hero)) {
												System.out.println("更改成功");
												break;

											}
										} else if (in_3_1_2.equals("3")) {

											break;

										} else if (in_3_1_2.equals("4") || in_3_1_2.equals("E") || in_3_1_2.equals("e"))
											// 退出
											System.exit(0);

									}
									// if (new
									// XmlParsing().xmlHeroRestor(hero))//失败方法
									else if (in_3_1.equals("2") || in_3_1.equals("q") || in_3_1.equals("Q")/* q返回 */) {
										break;// 返回
									} else if (in_3_1.equals("3") || in_3_1.equals("E") || in_3_1.equals("e")) {
										System.exit(0);
									}

								}
							}
								break;

							case "4":// 查询

								for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:for循环
									Menu.adminMenu_4();
									// 20171107
									boolean flag = false;
									String choice4 = in.next();
									if (choice4.equals("1")) {
										new XmlParsing().xmlShowAllHeroInfo();
									} else if (choice4.equals("2")) {
										System.out.println("请输入：" + "英雄职业：|射手|刺客|法师|坦克/战士|");
										String heroserch = in.next();
										if (!new Judge().judge(heroserch)) {

											System.out.println("职业输入错误!!!\n还有" + (chance_4 - 1) + "次机会");
											continue;
										}
										if (new XmlParsing().xmlHeroSerch_byJob(heroserch))

											break;
									} else if (choice4.equals("3")) {
										System.out.println("请输入：" + "英雄ID||姓名");
										String heroserch = in.next();
										if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
											System.out.println("显示完成");
											break;
										} else {
											System.out.println("输入错误！");
											flag = true;
										}
									} else if (choice4.equals("4")) {// 查询等级伤害
										System.out.println("请输入职业：");
										String herosjob = in.next();
										if (!new Judge().judge(herosjob)) {
											System.out.println("职业输入错误");
											flag = true;
										}
										System.out.println("请输入等级：");
										String heroslevel = in.next();
										System.out.println(herosjob + "在" + heroslevel + "等级伤害为："
												+ heroDamageCalculate(herosjob, heroslevel));
										// for (int i = 0; i < 4; i++) {
										// }
									} else if (choice4.equals("5")) {
										break;
									} else if (choice4.equals("e") || choice4.equals("E") || choice4.equals("e")) {
										System.exit(0);
									}
									if (flag)
										System.out.println("输入错误!还有" + (chance_4 - 1) + "次机会");
								}
								break;
							case "5":// 处理留言
								boolean flag = false;
								for (;;) {
									if (flag)
										break;
									Menu.adminMenu_5();// 处理留言
									String choice5 = in.next();
									switch (choice5) {
									case "1":
										System.out.println("查看留言");
										new XmlParsing().xmlMsgShowMessage();
										break;
									case "2":
										System.out.println("请输入回复id或姓名：");// 999
										String hero = in.next();
										if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
											System.out.println("没有该ID或姓名的留言记录！！！");
											break;// 回复失败返回
										}
										String id = new XmlParsing().xmlViaNameGetId(hero);// 自动获取id
										// System.out.println("请输入回复id：xxx");
										// String id = in.next();
										System.out.println("请输入回复内容：");
										String s = in.next();
										if (!new XmlParsing().xmlMsgReply(id, s)) {
											System.out.println("输入错误！回复失败");
										}
										break;
									case "3":
										System.out.println("清空留言");
										new XmlParsing().xmlMsgDelectAll();
										break;
									case "q":
									case "Q":
									case "4":
										flag = true;
										break;
									case "E":
									case "e":
									case "5":
										System.exit(0);
										break;

									default:
										System.out.println("输入错误");
										break;
									}

								}
								break;
							case "q": // 返回
							case "Q": // 返回
							case "6":
								main(null);// 返回
								break;
							case "e":
							case "E":
								System.exit(0);
								break;

							default:
								System.out.println("----进入失败，请输入正确选项！！！---");
								break;
							}
						} // 第三级for循环终止处
					} else if (chance2 - 1 == 0) {
						System.out.println("----GOOD BYE----");
						System.exit(0);// 退出系统
					}
					System.out.println("密码错误!还有" + (chance2 - 1) + "次机会");
				} // 第二级for循环终止处
			} else
			// if (comeback) {
			// break;// 退出系统
			// }
			if (chance1 - 1 == 0) {
				System.out.println("----GOOD BYE----");
				System.exit(0);// 退出系统
			}
			System.out.println("----用户名不存在!还有" + (chance1 - 1) + "次机会---");// 用户名不存在
		}
		return comeback;
	}// 第一级for循环

	// public static boolean heroLogin() throws DocumentException, IOException {
	// boolean flag = false;
	// for (int chance1 = 3; chance1 > 0; --chance1) {// 第一级for循环开始处
	//
	// if (chance1 == 1)
	// break;
	// System.out.println("请输入ID||姓名：");
	// String hero = in.next();
	// if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
	// flag = true;
	// System.out.println("----用户名或ID不存在!还有" + (chance1 - 1) + "次机会---");//
	// 用户名不存在
	// break;
	// }
	// if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
	// Menu.heroMenu();
	// String choice = in.next();
	// for (int chance2 = 3; chance2 > 0; chance2--) {
	//
	// if (choice.equals("1")) {
	//
	// for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:for循环
	// // 返回88
	//
	// Menu.heroMenu_1();
	// // 20171107
	//
	// String choice4 = in.next();
	// if (choice4.equals("1")) {
	// new XmlParsing().xmlShowAllHeroInfo();
	// } else if (choice4.equals("2")) {
	// System.out.println("请输入：" + "英雄职业：|射手|刺客|法师|坦克/战士|");
	// String heroserch = in.next();
	// if (!new Judge().judge(heroserch)) {
	//
	// System.out.println("职业输入错误!!!\n还有" + (chance_4 - 1) + "次机会");
	// continue;
	// }
	// if (new XmlParsing().xmlHeroSerch_byJob(heroserch))
	// flag = true;
	// } else if (choice4.equals("3")) {
	// System.out.println("请输入：" + "英雄ID");
	// String heroserch = in.next();
	// if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
	// System.out.println("显示完成");
	// break;
	// } else
	// System.out.println("输入错误！");
	// } else if (choice4.equals("4")) {// 查询等级伤害
	// System.out.println("请输入职业：");
	// String herosjob = in.next();
	// System.out.println("请输入等级：");
	// String heroslevel = in.next();
	// System.out.println(herosjob + "在" + heroslevel + "等级伤害为："
	// + heroDamageCalculate(herosjob, heroslevel));
	// // for (int i = 0; i < 4; i++) {
	// // }
	// } else if (choice4.equals("5")) {
	// flag = true;
	// break;
	//
	// } else if (choice4.equals("6")) {
	// System.exit(0);
	// }
	// // if (flag);
	// // System.out.println("输入错误!还有" + (chance_4
	// // - 1) + "次机会");
	// }
	// } else if (choice.equals("2")) {
	// for (int chance_2 = 3; chance_2 > 0; chance_2--) {
	// Menu.heroMenu_2();
	// String choice_2 = in.next();
	// if (choice_2.equals("1")) {
	// for (int chance_2_1 = 3; chance_2_1 > 0; chance_2_1--) {
	// System.out.println("请输入：ID|xxx|");
	// String id = in.next();
	// if (new XmlParsing().xmlHeroIdOrNameExists(id)) {// 关键以前误写为hero
	// System.out.println("请输入：留言内容");
	// String m = in.next();
	// if (new XmlParsing().xmlMsgAdd(id, m))
	// System.out.println("留言成功！");
	// heroLogin();
	// break;
	// } else {
	// System.out.println("ID输入错误!还有" + (chance_2_1 - 1) + "次机会");
	// return comeback;
	// }
	// }
	// } else if (choice_2.equals("2")) {// 查看回复
	// if (hero.equals(new XmlParsing().xmlViaNameGetId(hero))/* 说明hero为ID */) {
	// String id = hero;
	// new XmlParsing().xmlMsgShowReMessage(id);
	// } else {
	// String id = new XmlParsing().xmlViaNameGetId(hero);
	// new XmlParsing().xmlMsgShowReMessage(id);
	// }
	//
	// break;
	// } else if (choice_2.equals("3")) {
	// flag = true;
	// break;
	// } else if (choice_2.equals("4")) {
	// System.exit(0);
	// }
	// System.out.println("输入错误!还有" + (chance_2 - 1) + "次机会");
	// }
	// } else if (choice.equals("3")) {
	// flag = true;
	// break;
	// } else if (choice.equals("4")) {
	// System.exit(0);
	// } else
	// System.out.println("----输入不合法!还有" + (chance2 - 1) + "次机会---");
	// }
	// }
	//
	// if (flag)
	// ;
	// // System.out.println("----用户名或ID不存在!还有" + (chance1 - 1) +
	// // "次机会---");// 用户名不存在
	// }
	// return comeback;
	// }

	public static int heroDamageCalculate(String herosjob, String heroslevel) {
		int l = Integer.parseInt(heroslevel);

		switch (herosjob) {
		case "射手":

			return l * 3;
		case "刺客":

			return l * 2;
		case "法师":

			return l * 5;
		case "塔克/战士":

			return l * 4;

		default:
			break;
		}
		{
			// System.out.println("英雄等级0-18级，谢谢合作");
		}
		return 0;

	}

	public static void changeCharacteristic(String herojob, String hero) throws DocumentException, IOException {
		String d = null;

		if (herojob.equals("射手")) {
			System.out.println("请输入:等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] inck = new String[] { "等级", "生命值", "蓝量", "射程", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[7];
			for (int j = 0; j < 7; j++) {
				System.out.println("请输入：" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("更改成功");
			else
				System.out.println("输入错误");
			;

		} else if (herojob.equals("刺客")) {
			System.out.println("请输入:等级, 生命值, 蓝量,一技能, 二技能, 三技能");
			String[] inck = new String[] { "等级", "生命值", "蓝量", "位移", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[7];
			for (int i = 0; i < 7; i++) {
				System.out.println("请输入：" + inck[i]);
				if (i == 3) {
					System.out.println("位移范围500-1000");
					Scanner in = new Scanner(System.in);
					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
						// break;//退出循环不然出错
					} else {
						d = "0";
						System.out.println("输入错误！位移范围500-1000系统默认为0");
						continue;
					}
				}
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				sck[i] = str;

			}

			sck[3] = d;

			if (new XmlParsing().xmlHeroChangeByCharacteristic(sck, hero))
				System.out.println("更改成功");
			else
				System.out.println("输入错误");
			;

		}

		else if (herojob.equals("法师")) {
			System.out.println("请输入:等级，生命值，蓝量，一技能，二技能，三技能");
			String[] inck = new String[] { "等级", "生命值", "蓝量", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[6];
			for (int j = 0; j < 6; j++) {
				System.out.println("请输入：" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("更改成功");
			else
				System.out.println("输入错误");
			;

			// break;

		} else if (herojob.equals("坦克/战士")) {

			System.out.println("请输入:等级，生命值，蓝量，输出，一技能，二技能，三技能");
			String[] inck = new String[] { "等级", "生命值", "蓝量", "输出", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[7];
			for (int j = 0; j < 7; j++) {
				System.out.println("请输入：" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("更改成功");
			else
				System.out.println("输入错误");
			;

			// break;
		}
	}

	public static boolean heroLogin_New() throws DocumentException, IOException {
		Scanner in = new Scanner(System.in);
		for (int chance1 = 3; chance1 > 0; chance1--) {
			System.out.println("请输入ID||姓名：");
			String hero = in.next();
			if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
				if (chance1 - 1 == 0) {
					System.out.println("----GOOD BYE----");
					System.exit(0);// 退出系统
				}
				System.out.println("----用户名或ID不存在!还有" + (chance1 - 1) + "次机会---");// 用户名不存在
				continue;
			} else if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
				boolean flag = false;
				for (;;) {// 第三级for循环开始处
					if (flag)
						break;// 返回
					Menu.heroMenu(new XmlParsing().xmlViaIdorNameReturnName(hero));// 英雄菜单&传入参数为登录者名字
					String choice = in.next();

					switch (choice) {
					case "1": {

						boolean flag_1 = false;

						for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:for循环
							// 返回88
							if (flag_1)
								break;
							if (chance_4 == 0)
								break;
							Menu.heroMenu_1();
							// 20171107

							String choice4 = in.next();
							if (choice4.equals("1")) {
								new XmlParsing().xmlShowAllHeroInfo();
								break;
							} else if (choice4.equals("2")) {
								System.out.println("请输入：" + "英雄职业：|射手|刺客|法师|坦克/战士|");
								String heroserch = in.next();
								if (!new Judge().judge(heroserch)) {

									System.out.println("职业输入错误!!!\n还有" + (chance_4 - 1) + "次机会");
									continue;
								}
								if (new XmlParsing().xmlHeroSerch_byJob(heroserch)) {
									System.out.println("===========================显示完成===========================");
									break;
								}
							} else if (choice4.equals("3")) {
								System.out.println("请输入：" + "英雄ID");
								String heroserch = in.next();
								if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
									System.out.println("===========================显示完成===========================");

									break;
								} else {
									System.out.println("输入错误！\n还有" + (chance_4 - 1) + "次机会");
									break;
								}
							} else if (choice4.equals("4")) {// 查询等级伤害
								System.out.println("请输入职业：");
								String herosjob = in.next();
								System.out.println("请输入等级：");
								String heroslevel = in.next();
								new MySystem();
								System.out.println(herosjob + "在" + heroslevel + "等级伤害为："
										+ MySystem.heroDamageCalculate(herosjob, heroslevel));
								// for (int i = 0; i < 4; i++) {
								// }
							} else if (choice4.equals("5")
									|| choice4.equals("q")/* 11.10调试更改，之前错写为e */ || choice4.equals("Q")) {

								break;// 返回

							} else if (choice4.equals("6") || choice4.equals("E") || choice4.equals("e")) {
								System.exit(0);
							}
							// if (flag_1);
							// System.out.println("输入错误!还有" + (chance_4
							// - 1) + "次机会");
						}
					}

						break;

					case "2": {
						boolean flag2 = false;
						for (int chance_2 = 3; chance_2 > 0; chance_2--) {
							if (flag2)
								break;
							Menu.heroMenu_2();
							String choice_2 = in.next();
							if (choice_2.equals("1")) {
								for (int chance_2_1 = 3; chance_2_1 > 0; chance_2_1--) {
									// System.out.println("请输入：ID|xxx|");
									String id = new XmlParsing().xmlViaNameGetId(hero);// 自动获取id
									if (new XmlParsing().xmlHeroIdOrNameExists(id)) {// 关键以前误写为hero
										System.out.println("请输入：留言内容");
										String m = in.next();
										if (new XmlParsing().xmlMsgAdd(id, m))
											System.out.println("留言成功！");
										break;

									}
									// else {
									//
									// System.out.println("ID输入错误!还有" +
									// (chance_2_1 - 1) + "次机会");
									//
									// }
								}
							} else if (choice_2.equals("2")) {// 查看回复
								if (hero.equals(new XmlParsing().xmlViaNameGetId(hero))/* 说明hero为ID */) {
									String id = hero;
									new XmlParsing().xmlMsgShowReMessage(id);
								} else {
									String id = new XmlParsing().xmlViaNameGetId(hero);
									new XmlParsing().xmlMsgShowReMessage(id);
								}

								break;
							} else if (choice_2.equals("3")) {

								break;
							} else if (choice_2.equals("4") || choice_2.equals("E") || choice_2.equals("e")) {
								System.exit(0);
							}
							if (flag)
								System.out.println("输入错误!还有" + (chance_2 - 1) + "次机会");
						}
					}

						break;
					case "q":
					case "Q":
					case "3":

						main(null);
						break;
					case "4":
					case "E":
					case "e":
						System.exit(0);
						break;
					default:
						System.out.println("----失败！请输入正确选项！！！---");
						break;
					}// switch
					if (chance1 - 1 == 0) {
						System.out.println("----GOOD BYE----");
						System.exit(0);// 退出系统
					}

					if (flag3)
						System.out.println("密码错误!还有" + (chance1 - 1) + "次机会");
				} // 第三级for循环结束处
			}
		}

		return false;
	}
}
