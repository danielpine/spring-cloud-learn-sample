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
				if (heroLogin_New())// �˷���Ϊ��д���� ԭ����heroLogin()����ʵ�ֲ˵����ع���
					break;
			} else if (s.equals("e") || s.equals("E")) {
				System.exit(0);
			} else {
				System.out.println("----ʧ�ܣ���������ȷѡ�����---");
			}
		}
	}

	public static boolean adminLogin() throws DocumentException, IOException {
		for (int chance1 = 3; chance1 > 0; chance1--) {// ��һ��forѭ����ʼ��
			System.out.println("�������û�����");
			String adminname = in.next();
			if (new XmlParsing().xmlAdminExists(adminname)) {
				for (int chance2 = 3; chance2 > 0; chance2--) {// �ڶ���forѭ����ֹ��
					System.out.println("���������룺");
					String pwd = in.next();
					if (new XmlParsing().xmlAdminCheckPwd(adminname, pwd)) {
						for (;;) {// ������forѭ����ʼ��

							Menu.adminMenu();// ����Ա��¼�˵�
							String choice = in.next();
							switch (choice) {
							case "1":// ���
								boolean flag1_1 = false;
								for (int chance2_1_1 = 3; chance2_1_1 > 0; chance2_1_1--) {
									if (flag1_1 || chance2_1_1 == 0)
										break;// ʹ����ѡ�񷵻�
									Menu.adminMenu_1();// ��ְҵ���
									String input_1 = in.next();
									if (input_1.equals("q") || input_1.equals("Q")) {
										flag1_1 = true;
										break;
									} else
										System.out.println("�밴���¸�ʽ����" + "\n|����|�̿�|��ʦ|̹��/սʿ|");

									String addjob = in.next();
									if (!new Judge().judge(addjob)) {

										System.out.println("ְҵ�������!!!\n����" + (chance2_1_1 - 1) + "�λ���");
										continue;
									}
									try {
										if (new XmlParsing().xmlHeroAdd(addjob))
											System.out.println("��ӳɹ�");
										;
									} catch (IOException e) {
										// TODO Auto-generated catch block
										System.out.println("�������");
										e.printStackTrace();
									}

								}
								break;
							case "2":// ɾ��
								boolean flag2 = false;
								for (int chance_2 = 3; chance_2 > 0; chance_2--) {// case"2":forѭ��
									if (flag2)
										break;
									Menu.adminMenu_2();
									// 20171107:δʵ�ְ�ְҵ��ѯ
									String choice2 = in.next();
									boolean flag = false;
									if (choice2.equals("1")) {
										System.out.println("�����룺" + "Ӣ������");//
										String hero = in.next();
										if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
											if (new XmlParsing().xmlHeroDelectByName(hero)) {
												System.out.println("ɾ���ɹ���");
												break;
											}
										} else {
											System.out.println("û�и�Ӣ�ۣ�����");
											System.out.println("������󣡻���" + (chance_2 - 1) + "�λ���");
											continue;
										}
									} else if (choice2.equals("2")) {
										System.out.println("�����룺" + "Ӣ��ID����ʽ|001|");
										String hero = in.next();
										if (new XmlParsing().xmlHeroDelectByID(hero)) {
											System.out.println("ɾ���ɹ���");
											break;
										} else {
											System.out.println("û�и�Ӣ�ۣ�����");
											System.out.println("������󣡻���" + (chance_2 - 1) + "�λ���");
											continue;
										}
									} else if (choice2.equals("3")) {
										flag2 = true;

									} else if (choice2.equals("4") || choice2.equals("E") || choice2.equals("e")) {
										System.exit(0);
									} else if (flag) {
										System.out.println("������󣡻���" + (chance_2 - 1) + "�λ���");

									}
								}
								break;
							case "3":// 2017 11 08 ��覴�//ϵͳ�����ֱ��ɾ��Ӣ��BUG
							{
								for (int chance_3 = 3; chance_3 > 0; chance_3--) {// ����
									Menu.adminMenu_3(1);
									String in_3_1 = in.next();

									if (in_3_1.equals("1"))

									{

										System.out.println("�����룺" + "Ӣ������");//
										String hero = in.next();
										if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {

											System.out.println("û�и�Ӣ�ۣ�����");
											System.out.println("������󣡻���" + (chance_3 - 1) + "�λ���");
											continue;

										}
										String herojob = new XmlParsing().xmlViaNameGetJob(hero);
										System.out.println("ְҵ��" + herojob);

										Menu.adminMenu_3(2);
										String in_3_1_2 = in.next();

										if (in_3_1_2.equals("1")) {
											changeCharacteristic(herojob, hero);
										} // ��������
										else if (in_3_1_2.equals("2")) {// ����ְҵ
											System.out.println("�밴���¸�ʽ�������ְҵ��" + "\n|����|�̿�|��ʦ|̹��/սʿ|");
											String newjob = in.next();
											if (!new Judge().judge(newjob)) {
												System.out.println("ְҵ�������!!!\n����" + (chance_3 - 1) + "�λ���");
												continue;
											}
											String oldid = new XmlParsing().xmlViaNameGetId(hero);
											new XmlParsing().xmlHeroDelect(hero);// remove
											if (new XmlParsing().xmlHeroAddNewJob(newjob, oldid, hero)) {
												System.out.println("���ĳɹ�");
												break;

											}
										} else if (in_3_1_2.equals("3")) {

											break;

										} else if (in_3_1_2.equals("4") || in_3_1_2.equals("E") || in_3_1_2.equals("e"))
											// �˳�
											System.exit(0);

									}
									// if (new
									// XmlParsing().xmlHeroRestor(hero))//ʧ�ܷ���
									else if (in_3_1.equals("2") || in_3_1.equals("q") || in_3_1.equals("Q")/* q���� */) {
										break;// ����
									} else if (in_3_1.equals("3") || in_3_1.equals("E") || in_3_1.equals("e")) {
										System.exit(0);
									}

								}
							}
								break;

							case "4":// ��ѯ

								for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:forѭ��
									Menu.adminMenu_4();
									// 20171107
									boolean flag = false;
									String choice4 = in.next();
									if (choice4.equals("1")) {
										new XmlParsing().xmlShowAllHeroInfo();
									} else if (choice4.equals("2")) {
										System.out.println("�����룺" + "Ӣ��ְҵ��|����|�̿�|��ʦ|̹��/սʿ|");
										String heroserch = in.next();
										if (!new Judge().judge(heroserch)) {

											System.out.println("ְҵ�������!!!\n����" + (chance_4 - 1) + "�λ���");
											continue;
										}
										if (new XmlParsing().xmlHeroSerch_byJob(heroserch))

											break;
									} else if (choice4.equals("3")) {
										System.out.println("�����룺" + "Ӣ��ID||����");
										String heroserch = in.next();
										if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
											System.out.println("��ʾ���");
											break;
										} else {
											System.out.println("�������");
											flag = true;
										}
									} else if (choice4.equals("4")) {// ��ѯ�ȼ��˺�
										System.out.println("������ְҵ��");
										String herosjob = in.next();
										if (!new Judge().judge(herosjob)) {
											System.out.println("ְҵ�������");
											flag = true;
										}
										System.out.println("������ȼ���");
										String heroslevel = in.next();
										System.out.println(herosjob + "��" + heroslevel + "�ȼ��˺�Ϊ��"
												+ heroDamageCalculate(herosjob, heroslevel));
										// for (int i = 0; i < 4; i++) {
										// }
									} else if (choice4.equals("5")) {
										break;
									} else if (choice4.equals("e") || choice4.equals("E") || choice4.equals("e")) {
										System.exit(0);
									}
									if (flag)
										System.out.println("�������!����" + (chance_4 - 1) + "�λ���");
								}
								break;
							case "5":// ��������
								boolean flag = false;
								for (;;) {
									if (flag)
										break;
									Menu.adminMenu_5();// ��������
									String choice5 = in.next();
									switch (choice5) {
									case "1":
										System.out.println("�鿴����");
										new XmlParsing().xmlMsgShowMessage();
										break;
									case "2":
										System.out.println("������ظ�id��������");// 999
										String hero = in.next();
										if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
											System.out.println("û�и�ID�����������Լ�¼������");
											break;// �ظ�ʧ�ܷ���
										}
										String id = new XmlParsing().xmlViaNameGetId(hero);// �Զ���ȡid
										// System.out.println("������ظ�id��xxx");
										// String id = in.next();
										System.out.println("������ظ����ݣ�");
										String s = in.next();
										if (!new XmlParsing().xmlMsgReply(id, s)) {
											System.out.println("������󣡻ظ�ʧ��");
										}
										break;
									case "3":
										System.out.println("�������");
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
										System.out.println("�������");
										break;
									}

								}
								break;
							case "q": // ����
							case "Q": // ����
							case "6":
								main(null);// ����
								break;
							case "e":
							case "E":
								System.exit(0);
								break;

							default:
								System.out.println("----����ʧ�ܣ���������ȷѡ�����---");
								break;
							}
						} // ������forѭ����ֹ��
					} else if (chance2 - 1 == 0) {
						System.out.println("----GOOD BYE----");
						System.exit(0);// �˳�ϵͳ
					}
					System.out.println("�������!����" + (chance2 - 1) + "�λ���");
				} // �ڶ���forѭ����ֹ��
			} else
			// if (comeback) {
			// break;// �˳�ϵͳ
			// }
			if (chance1 - 1 == 0) {
				System.out.println("----GOOD BYE----");
				System.exit(0);// �˳�ϵͳ
			}
			System.out.println("----�û���������!����" + (chance1 - 1) + "�λ���---");// �û���������
		}
		return comeback;
	}// ��һ��forѭ��

	// public static boolean heroLogin() throws DocumentException, IOException {
	// boolean flag = false;
	// for (int chance1 = 3; chance1 > 0; --chance1) {// ��һ��forѭ����ʼ��
	//
	// if (chance1 == 1)
	// break;
	// System.out.println("������ID||������");
	// String hero = in.next();
	// if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
	// flag = true;
	// System.out.println("----�û�����ID������!����" + (chance1 - 1) + "�λ���---");//
	// �û���������
	// break;
	// }
	// if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
	// Menu.heroMenu();
	// String choice = in.next();
	// for (int chance2 = 3; chance2 > 0; chance2--) {
	//
	// if (choice.equals("1")) {
	//
	// for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:forѭ��
	// // ����88
	//
	// Menu.heroMenu_1();
	// // 20171107
	//
	// String choice4 = in.next();
	// if (choice4.equals("1")) {
	// new XmlParsing().xmlShowAllHeroInfo();
	// } else if (choice4.equals("2")) {
	// System.out.println("�����룺" + "Ӣ��ְҵ��|����|�̿�|��ʦ|̹��/սʿ|");
	// String heroserch = in.next();
	// if (!new Judge().judge(heroserch)) {
	//
	// System.out.println("ְҵ�������!!!\n����" + (chance_4 - 1) + "�λ���");
	// continue;
	// }
	// if (new XmlParsing().xmlHeroSerch_byJob(heroserch))
	// flag = true;
	// } else if (choice4.equals("3")) {
	// System.out.println("�����룺" + "Ӣ��ID");
	// String heroserch = in.next();
	// if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
	// System.out.println("��ʾ���");
	// break;
	// } else
	// System.out.println("�������");
	// } else if (choice4.equals("4")) {// ��ѯ�ȼ��˺�
	// System.out.println("������ְҵ��");
	// String herosjob = in.next();
	// System.out.println("������ȼ���");
	// String heroslevel = in.next();
	// System.out.println(herosjob + "��" + heroslevel + "�ȼ��˺�Ϊ��"
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
	// // System.out.println("�������!����" + (chance_4
	// // - 1) + "�λ���");
	// }
	// } else if (choice.equals("2")) {
	// for (int chance_2 = 3; chance_2 > 0; chance_2--) {
	// Menu.heroMenu_2();
	// String choice_2 = in.next();
	// if (choice_2.equals("1")) {
	// for (int chance_2_1 = 3; chance_2_1 > 0; chance_2_1--) {
	// System.out.println("�����룺ID|xxx|");
	// String id = in.next();
	// if (new XmlParsing().xmlHeroIdOrNameExists(id)) {// �ؼ���ǰ��дΪhero
	// System.out.println("�����룺��������");
	// String m = in.next();
	// if (new XmlParsing().xmlMsgAdd(id, m))
	// System.out.println("���Գɹ���");
	// heroLogin();
	// break;
	// } else {
	// System.out.println("ID�������!����" + (chance_2_1 - 1) + "�λ���");
	// return comeback;
	// }
	// }
	// } else if (choice_2.equals("2")) {// �鿴�ظ�
	// if (hero.equals(new XmlParsing().xmlViaNameGetId(hero))/* ˵��heroΪID */) {
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
	// System.out.println("�������!����" + (chance_2 - 1) + "�λ���");
	// }
	// } else if (choice.equals("3")) {
	// flag = true;
	// break;
	// } else if (choice.equals("4")) {
	// System.exit(0);
	// } else
	// System.out.println("----���벻�Ϸ�!����" + (chance2 - 1) + "�λ���---");
	// }
	// }
	//
	// if (flag)
	// ;
	// // System.out.println("----�û�����ID������!����" + (chance1 - 1) +
	// // "�λ���---");// �û���������
	// }
	// return comeback;
	// }

	public static int heroDamageCalculate(String herosjob, String heroslevel) {
		int l = Integer.parseInt(heroslevel);

		switch (herosjob) {
		case "����":

			return l * 3;
		case "�̿�":

			return l * 2;
		case "��ʦ":

			return l * 5;
		case "����/սʿ":

			return l * 4;

		default:
			break;
		}
		{
			// System.out.println("Ӣ�۵ȼ�0-18����лл����");
		}
		return 0;

	}

	public static void changeCharacteristic(String herojob, String hero) throws DocumentException, IOException {
		String d = null;

		if (herojob.equals("����")) {
			System.out.println("������:�ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[7];
			for (int j = 0; j < 7; j++) {
				System.out.println("�����룺" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("���ĳɹ�");
			else
				System.out.println("�������");
			;

		} else if (herojob.equals("�̿�")) {
			System.out.println("������:�ȼ�, ����ֵ, ����,һ����, ������, ������");
			String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "λ��", "һ����", "������", "������"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[7];
			for (int i = 0; i < 7; i++) {
				System.out.println("�����룺" + inck[i]);
				if (i == 3) {
					System.out.println("λ�Ʒ�Χ500-1000");
					Scanner in = new Scanner(System.in);
					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
						// break;//�˳�ѭ����Ȼ����
					} else {
						d = "0";
						System.out.println("�������λ�Ʒ�Χ500-1000ϵͳĬ��Ϊ0");
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
				System.out.println("���ĳɹ�");
			else
				System.out.println("�������");
			;

		}

		else if (herojob.equals("��ʦ")) {
			System.out.println("������:�ȼ�������ֵ��������һ���ܣ������ܣ�������");
			String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "һ����", "������", "������"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[6];
			for (int j = 0; j < 6; j++) {
				System.out.println("�����룺" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("���ĳɹ�");
			else
				System.out.println("�������");
			;

			// break;

		} else if (herojob.equals("̹��/սʿ")) {

			System.out.println("������:�ȼ�������ֵ�������������һ���ܣ������ܣ�������");
			String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					/* 0 1 2 3 4 5 */
			};
			String[] s = new String[7];
			for (int j = 0; j < 7; j++) {
				System.out.println("�����룺" + inck[j]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[j] = str;
			}
			if (new XmlParsing().xmlHeroChangeByCharacteristic(s, hero))
				System.out.println("���ĳɹ�");
			else
				System.out.println("�������");
			;

			// break;
		}
	}

	public static boolean heroLogin_New() throws DocumentException, IOException {
		Scanner in = new Scanner(System.in);
		for (int chance1 = 3; chance1 > 0; chance1--) {
			System.out.println("������ID||������");
			String hero = in.next();
			if (!new XmlParsing().xmlHeroIdOrNameExists(hero)) {
				if (chance1 - 1 == 0) {
					System.out.println("----GOOD BYE----");
					System.exit(0);// �˳�ϵͳ
				}
				System.out.println("----�û�����ID������!����" + (chance1 - 1) + "�λ���---");// �û���������
				continue;
			} else if (new XmlParsing().xmlHeroIdOrNameExists(hero)) {
				boolean flag = false;
				for (;;) {// ������forѭ����ʼ��
					if (flag)
						break;// ����
					Menu.heroMenu(new XmlParsing().xmlViaIdorNameReturnName(hero));// Ӣ�۲˵�&�������Ϊ��¼������
					String choice = in.next();

					switch (choice) {
					case "1": {

						boolean flag_1 = false;

						for (int chance_4 = 3; chance_4 > 0; chance_4--) {// case""4:forѭ��
							// ����88
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
								System.out.println("�����룺" + "Ӣ��ְҵ��|����|�̿�|��ʦ|̹��/սʿ|");
								String heroserch = in.next();
								if (!new Judge().judge(heroserch)) {

									System.out.println("ְҵ�������!!!\n����" + (chance_4 - 1) + "�λ���");
									continue;
								}
								if (new XmlParsing().xmlHeroSerch_byJob(heroserch)) {
									System.out.println("===========================��ʾ���===========================");
									break;
								}
							} else if (choice4.equals("3")) {
								System.out.println("�����룺" + "Ӣ��ID");
								String heroserch = in.next();
								if (new XmlParsing().xmlHeroSerch_byIDorName(heroserch)) {
									System.out.println("===========================��ʾ���===========================");

									break;
								} else {
									System.out.println("�������\n����" + (chance_4 - 1) + "�λ���");
									break;
								}
							} else if (choice4.equals("4")) {// ��ѯ�ȼ��˺�
								System.out.println("������ְҵ��");
								String herosjob = in.next();
								System.out.println("������ȼ���");
								String heroslevel = in.next();
								new MySystem();
								System.out.println(herosjob + "��" + heroslevel + "�ȼ��˺�Ϊ��"
										+ MySystem.heroDamageCalculate(herosjob, heroslevel));
								// for (int i = 0; i < 4; i++) {
								// }
							} else if (choice4.equals("5")
									|| choice4.equals("q")/* 11.10���Ը��ģ�֮ǰ��дΪe */ || choice4.equals("Q")) {

								break;// ����

							} else if (choice4.equals("6") || choice4.equals("E") || choice4.equals("e")) {
								System.exit(0);
							}
							// if (flag_1);
							// System.out.println("�������!����" + (chance_4
							// - 1) + "�λ���");
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
									// System.out.println("�����룺ID|xxx|");
									String id = new XmlParsing().xmlViaNameGetId(hero);// �Զ���ȡid
									if (new XmlParsing().xmlHeroIdOrNameExists(id)) {// �ؼ���ǰ��дΪhero
										System.out.println("�����룺��������");
										String m = in.next();
										if (new XmlParsing().xmlMsgAdd(id, m))
											System.out.println("���Գɹ���");
										break;

									}
									// else {
									//
									// System.out.println("ID�������!����" +
									// (chance_2_1 - 1) + "�λ���");
									//
									// }
								}
							} else if (choice_2.equals("2")) {// �鿴�ظ�
								if (hero.equals(new XmlParsing().xmlViaNameGetId(hero))/* ˵��heroΪID */) {
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
								System.out.println("�������!����" + (chance_2 - 1) + "�λ���");
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
						System.out.println("----ʧ�ܣ���������ȷѡ�����---");
						break;
					}// switch
					if (chance1 - 1 == 0) {
						System.out.println("----GOOD BYE----");
						System.exit(0);// �˳�ϵͳ
					}

					if (flag3)
						System.out.println("�������!����" + (chance1 - 1) + "�λ���");
				} // ������forѭ��������
			}
		}

		return false;
	}
}
