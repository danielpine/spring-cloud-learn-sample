package com.hlyc.Herofour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
	static String time=new Time().time();
	// ��¼����
		public static void loginMenu() {

			System.out.println("==================================");
			System.out.println("-----------��ӭ����  GameSky��--------");
			System.out.println("------------1.����Ա �� ¼------------");
			System.out.println("------------2.Ӣ  ��  ��  ¼------------");
			System.out.println("------------E.��              ��------------");
			System.out.println("==================================");
			System.out.println("------------�����ߣ�������------------");
			System.out.println("----��ǰʱ�䣺" + time + "----");
			System.out.println("==================================");
		}

		// Ӣ�۲˵�
		public static void heroMenu(String hero) {
			System.out.println("=================================");
//			System.err.println("��ӭ������");
			System.out.println("----------<<��ӭ����>>-�װ���>>" + hero + "<<����");
			System.out.println("------------1.��    ѯ---------------");
			System.out.println("------------2.��    ��---------------");
			System.out.println("------------Q.��    ��---------------");
			System.out.println("------------E.��    ��---------------");
			System.out.println("=================================");
			System.out.println("----��ǰʱ�䣺" + time + "---");
			System.out.println("=================================");
		}

		/**
		 * ��ѯ
		 */
		public static void heroMenu_1() {
			System.out.println("=================================");
			System.out.println("----------------�� ӭ �� ѯ----------");
			System.out.println("------------1.�� ȫ �� Ӣ�� �� Ϣ-------");
			System.out.println("------------2.��    ְ   ҵ   ��   ѯ-------");
			System.out.println("------------3.��ID����������ѯ-------");
			System.out.println("------------4.��ѯ X �ȼ��˺�ֵ-------");
			System.out.println("------------Q.��                         ��-------");
			System.out.println("------------E.��                         ��-------");
			System.out.println("=================================");
		}

		public static void heroMenu_2() {
			System.out.println("=================================");
			System.out.println("-----------��ӭ�������԰�-----------");
			System.out.println("------------1.��        ��-------------");
			System.out.println("------------2.�鿴�ظ�------------");
			System.out.println("------------Q.��        ��------------");
			System.out.println("------------E.��        ��-------------");
			System.out.println("=================================");
		}

		// ����Ա�˵�
		public static void adminMenu() {
			System.out.println("=================================");
			System.out.println(" ------------��ӭ�����Ա��----------");
			System.out.println("------------1.����Ӣ��-------------");
			System.out.println("------------2.ɾ��Ӣ��-------------");
			System.out.println("------------3.�޸�Ӣ��-------------");
			System.out.println("------------4.��ѯӢ��-------------");
			System.out.println("------------5.��������-------------");
			System.out.println("------------Q.��        ��-------------");
			System.out.println("------------E.��        ��-------------");
			System.out.println("=================================");
		}

		public static void adminMenu_1() {
			System.out.println("=================================");
			System.out.println("-------------��ӭ�����Ա��----------");
			System.out.println("------------1.������ְҵ>>>---------");
			System.out.println("------------Q.��              ��-----------");
			System.out.println("=================================");
		}

		public static void adminMenu_2() {
			System.out.println("=================================");
			System.out.println("------------��ӭ�����Ա��-----------");
			System.out.println("-----------1.����Ӣ������------------");
			System.out.println("-----------2.����Ӣ��  ID------------");
			System.out.println("-----------Q.��               ��------------");
			System.out.println("-----------E.��               ��------------");
			System.out.println("=================================");
		}

		public static void adminMenu_3(int i) {
			if (i == 1) {
				System.out.println("=================================");
				System.out.println("-----------��ӭ�����Ա��------------");
				System.out.println("-----------1.ѡ��Ӣ��--------------");
				System.out.println("-----------Q.��        ��--------------");
				System.out.println("-----------E.��        ��--------------");
				System.out.println("=================================");
			}
			if (i == 2) {
				System.out.println("=================================");
				System.out.println("-----------��ӭ�����Ա��------------");
				System.out.println("-----------1.��������--------------");
				System.out.println("-----------2.����ְҵ--------------");
				System.out.println("-----------Q.��        ��--------------");
				System.out.println("-----------E.��        ��--------------");
				System.out.println("=================================");

			}
		}

		public static void adminMenu_4() {
			System.out.println("=================================");
			System.out.println("---------------��ӭ��ѯ------------");
			System.out.println("-----------1.��ȫ�� Ӣ�� ��Ϣ---------");
			System.out.println("-----------2.��         ְ        ҵ---------");
			System.out.println("-----------3.��ID ���� �� ��---------");
			System.out.println("-----------4.��ѯX�ȼ��˺�ֵ---------");
			System.out.println("-----------Q.��                      ��---------");
			System.out.println("-----------E.��                      ��---------");
			System.out.println("=================================");
		}

		public static void adminMenu_5() {
			System.out.println("=================================");
			System.out.println("--------------���԰�---------------");
			System.out.println("-----------1.�鿴����--------------");
			System.out.println("-----------2.�ظ�����--------------");
			System.out.println("-----------3.�������--------------");
			System.out.println("-----------Q.��        ��--------------");
			System.out.println("-----------E.��        ��--------------");
			System.out.println("=================================");
		}
//	// ��¼����
//	public static void loginMenu() {
//
//		System.out.println("==================================");
//		System.out.println("-----------��ӭ����  GameSky��--------");
//		System.out.println("------------1.����Ա �� ¼------------");
//		System.out.println("------------2.Ӣ  ��  ��  ¼------------");
//		System.out.println("------------E.��              ��------------");
//		System.out.println("==================================");
//		System.out.println("------------�����ߣ�������------------");
//		System.out.println("----��ǰʱ�䣺" + time + "----");
//		System.out.println("==================================");
//	}
//
//	// Ӣ�۲˵�
//	public static void heroMenu(String hero) {
//		System.out.println("=================================");
//		System.out.println("----------<<��ӭ����>>-�װ��ġ�" + hero + "������");
//		System.out.println("------------1.��ѯ----------------");
//		System.out.println("------------2.����----------------");
//		System.out.println("------------3.����----------------");
//		System.out.println("------------4.�˳�----------------");
//		System.out.println("=================================");
//		System.out.println("----��ǰʱ�䣺" + time + "---");
//		System.out.println("=================================");
//	}
//
//	/**
//	 * ��ѯ
//	 */
//	public static void heroMenu_1() {
//		System.out.println("=================================");
//		System.out.println("---------------��ӭ��ѯ------------");
//		System.out.println("------------1.�� ȫ �� Ӣ�� �� Ϣ------");
//		System.out.println("------------2.��   ְ   ҵ   ��  ѯ-------");
//		System.out.println("------------3.��ID����������ѯ-------");
//		System.out.println("------------4.��ѯX�ȼ��˺�ֵ--------");
//		System.out.println("------------5.��           ��------------");
//		System.out.println("------------6.��           ��------------");
//		System.out.println("=================================");
//	}
//
//	public static void heroMenu_2() {
//		System.out.println("=================================");
//		System.out.println("----------��ӭ�������԰�------------");
//		System.out.println("------------1.��        ��-------------");
//		System.out.println("------------2.�鿴�ظ�------------");
//		System.out.println("------------3.��        ��------------");
//		System.out.println("------------4.��        ��-------------");
//		System.out.println("=================================");
//	}
//
//	// ����Ա�˵�
//	public static void adminMenu() {
//		System.out.println("=================================");
//		System.out.println("-----------��ӭ�����Ա��-----------");
//		System.out.println("------------1.����Ӣ��-------------");
//		System.out.println("------------2.ɾ��Ӣ��-------------");
//		System.out.println("------------3.�޸�Ӣ��-------------");
//		System.out.println("------------4.��ѯӢ��-------------");
//		System.out.println("------------5.��������-------------");
//		System.out.println("------------6.��        ��-------------");
//		System.out.println("------------E.��        ��-------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_1() {
//		System.out.println("=================================");
//		System.out.println("------------��ӭ�����Ա��-----------");
//		System.out.println("------------1.������ְҵ>>>---------");
//		System.out.println("------------Q.��              ��-----------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_2() {
//		System.out.println("=================================");
//		System.out.println("--------------��ӭ�����Ա��---------");
//		System.out.println("-----------1.����Ӣ������-----------");
//		System.out.println("-----------2.����Ӣ��ID------------");
//		System.out.println("-----------3.��            ��------------");
//		System.out.println("-----------4.��            ��------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_3(int i) {
//		if (i == 1) {
//			System.out.println("=================================");
//			System.out.println("------------��ӭ�����Ա��-----------");
//			System.out.println("-----------1.ѡ��Ӣ��--------------");
//			System.out.println("-----------2.��       ��--------------");
//			System.out.println("-----------3.��       ��--------------");
//			System.out.println("=================================");
//		}
//		if (i == 2) {
//			System.out.println("=================================");
//			System.out.println("-------------��ӭ�����Ա��---------");
//			System.out.println("-----------1.��������--------------");
//			System.out.println("-----------2.����ְҵ--------------");
//			System.out.println("-----------3.��       ��--------------");
//			System.out.println("-----------4.��       ��--------------");
//			System.out.println("=================================");
//
//		}
//	}
//
//	public static void adminMenu_4() {
//		System.out.println("=================================");
//		System.out.println("---------------��ӭ��ѯ------------");
//		System.out.println("-----------1.��ȫ�� Ӣ�� ��Ϣ---------");
//		System.out.println("-----------2.��         ְ        ҵ---------");
//		System.out.println("-----------3.��ID ���� �� ��---------");
//		System.out.println("-----------4.��ѯX�ȼ��˺�ֵ---------");
//		System.out.println("-----------5.��         ��--------------");
//		System.out.println("-----------E.��         ��--------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_5() {
//		System.out.println("=================================");
//		System.out.println("--------------���԰�---------------");
//		System.out.println("-----------1.�鿴����--------------");
//		System.out.println("-----------2.�ظ�����--------------");
//		System.out.println("-----------3.�������--------------");
//		System.out.println("-----------4.��        ��--------------");
//		System.out.println("-----------5.��        ��--------------");
//		System.out.println("=================================");
//	}

}
