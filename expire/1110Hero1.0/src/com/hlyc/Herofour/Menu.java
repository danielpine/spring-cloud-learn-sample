package com.hlyc.Herofour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu {
	static String time=new Time().time();
	// 登录界面
		public static void loginMenu() {

			System.out.println("==================================");
			System.out.println("-----------欢迎来到  GameSky！--------");
			System.out.println("------------1.管理员 登 录------------");
			System.out.println("------------2.英  雄  登  录------------");
			System.out.println("------------E.退              出------------");
			System.out.println("==================================");
			System.out.println("------------制作者：杨尚武------------");
			System.out.println("----当前时间：" + time + "----");
			System.out.println("==================================");
		}

		// 英雄菜单
		public static void heroMenu(String hero) {
			System.out.println("=================================");
//			System.err.println("欢迎回来！");
			System.out.println("----------<<欢迎回来>>-亲爱的>>" + hero + "<<大人");
			System.out.println("------------1.查    询---------------");
			System.out.println("------------2.留    言---------------");
			System.out.println("------------Q.返    回---------------");
			System.out.println("------------E.退    出---------------");
			System.out.println("=================================");
			System.out.println("----当前时间：" + time + "---");
			System.out.println("=================================");
		}

		/**
		 * 查询
		 */
		public static void heroMenu_1() {
			System.out.println("=================================");
			System.out.println("----------------欢 迎 查 询----------");
			System.out.println("------------1.查 全 部 英雄 信 息-------");
			System.out.println("------------2.按    职   业   查   询-------");
			System.out.println("------------3.按ID或者姓名查询-------");
			System.out.println("------------4.查询 X 等级伤害值-------");
			System.out.println("------------Q.返                         回-------");
			System.out.println("------------E.退                         出-------");
			System.out.println("=================================");
		}

		public static void heroMenu_2() {
			System.out.println("=================================");
			System.out.println("-----------欢迎来到留言板-----------");
			System.out.println("------------1.输        入-------------");
			System.out.println("------------2.查看回复------------");
			System.out.println("------------Q.返        回------------");
			System.out.println("------------E.退        出-------------");
			System.out.println("=================================");
		}

		// 管理员菜单
		public static void adminMenu() {
			System.out.println("=================================");
			System.out.println(" ------------欢迎你管理员！----------");
			System.out.println("------------1.增加英雄-------------");
			System.out.println("------------2.删除英雄-------------");
			System.out.println("------------3.修改英雄-------------");
			System.out.println("------------4.查询英雄-------------");
			System.out.println("------------5.处理留言-------------");
			System.out.println("------------Q.返        回-------------");
			System.out.println("------------E.退        出-------------");
			System.out.println("=================================");
		}

		public static void adminMenu_1() {
			System.out.println("=================================");
			System.out.println("-------------欢迎你管理员！----------");
			System.out.println("------------1.请输入职业>>>---------");
			System.out.println("------------Q.返              回-----------");
			System.out.println("=================================");
		}

		public static void adminMenu_2() {
			System.out.println("=================================");
			System.out.println("------------欢迎你管理员！-----------");
			System.out.println("-----------1.输入英雄姓名------------");
			System.out.println("-----------2.输入英雄  ID------------");
			System.out.println("-----------Q.返               回------------");
			System.out.println("-----------E.退               出------------");
			System.out.println("=================================");
		}

		public static void adminMenu_3(int i) {
			if (i == 1) {
				System.out.println("=================================");
				System.out.println("-----------欢迎你管理员！------------");
				System.out.println("-----------1.选择英雄--------------");
				System.out.println("-----------Q.返        回--------------");
				System.out.println("-----------E.退        出--------------");
				System.out.println("=================================");
			}
			if (i == 2) {
				System.out.println("=================================");
				System.out.println("-----------欢迎你管理员！------------");
				System.out.println("-----------1.更改属性--------------");
				System.out.println("-----------2.更改职业--------------");
				System.out.println("-----------Q.返        回--------------");
				System.out.println("-----------E.退        出--------------");
				System.out.println("=================================");

			}
		}

		public static void adminMenu_4() {
			System.out.println("=================================");
			System.out.println("---------------欢迎查询------------");
			System.out.println("-----------1.查全部 英雄 信息---------");
			System.out.println("-----------2.按         职        业---------");
			System.out.println("-----------3.按ID 或者 姓 名---------");
			System.out.println("-----------4.查询X等级伤害值---------");
			System.out.println("-----------Q.返                      回---------");
			System.out.println("-----------E.退                      出---------");
			System.out.println("=================================");
		}

		public static void adminMenu_5() {
			System.out.println("=================================");
			System.out.println("--------------留言板---------------");
			System.out.println("-----------1.查看留言--------------");
			System.out.println("-----------2.回复留言--------------");
			System.out.println("-----------3.清空留言--------------");
			System.out.println("-----------Q.返        回--------------");
			System.out.println("-----------E.退        出--------------");
			System.out.println("=================================");
		}
//	// 登录界面
//	public static void loginMenu() {
//
//		System.out.println("==================================");
//		System.out.println("-----------欢迎来到  GameSky！--------");
//		System.out.println("------------1.管理员 登 录------------");
//		System.out.println("------------2.英  雄  登  录------------");
//		System.out.println("------------E.退              出------------");
//		System.out.println("==================================");
//		System.out.println("------------制作者：杨尚武------------");
//		System.out.println("----当前时间：" + time + "----");
//		System.out.println("==================================");
//	}
//
//	// 英雄菜单
//	public static void heroMenu(String hero) {
//		System.out.println("=================================");
//		System.out.println("----------<<欢迎回来>>-亲爱的【" + hero + "】大人");
//		System.out.println("------------1.查询----------------");
//		System.out.println("------------2.留言----------------");
//		System.out.println("------------3.返回----------------");
//		System.out.println("------------4.退出----------------");
//		System.out.println("=================================");
//		System.out.println("----当前时间：" + time + "---");
//		System.out.println("=================================");
//	}
//
//	/**
//	 * 查询
//	 */
//	public static void heroMenu_1() {
//		System.out.println("=================================");
//		System.out.println("---------------欢迎查询------------");
//		System.out.println("------------1.查 全 部 英雄 信 息------");
//		System.out.println("------------2.按   职   业   查  询-------");
//		System.out.println("------------3.按ID或者姓名查询-------");
//		System.out.println("------------4.查询X等级伤害值--------");
//		System.out.println("------------5.返           回------------");
//		System.out.println("------------6.退           出------------");
//		System.out.println("=================================");
//	}
//
//	public static void heroMenu_2() {
//		System.out.println("=================================");
//		System.out.println("----------欢迎来到留言板------------");
//		System.out.println("------------1.输        入-------------");
//		System.out.println("------------2.查看回复------------");
//		System.out.println("------------3.返        回------------");
//		System.out.println("------------4.退        出-------------");
//		System.out.println("=================================");
//	}
//
//	// 管理员菜单
//	public static void adminMenu() {
//		System.out.println("=================================");
//		System.out.println("-----------欢迎你管理员！-----------");
//		System.out.println("------------1.增加英雄-------------");
//		System.out.println("------------2.删除英雄-------------");
//		System.out.println("------------3.修改英雄-------------");
//		System.out.println("------------4.查询英雄-------------");
//		System.out.println("------------5.处理留言-------------");
//		System.out.println("------------6.返        回-------------");
//		System.out.println("------------E.退        出-------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_1() {
//		System.out.println("=================================");
//		System.out.println("------------欢迎你管理员！-----------");
//		System.out.println("------------1.请输入职业>>>---------");
//		System.out.println("------------Q.返              回-----------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_2() {
//		System.out.println("=================================");
//		System.out.println("--------------欢迎你管理员！---------");
//		System.out.println("-----------1.输入英雄姓名-----------");
//		System.out.println("-----------2.输入英雄ID------------");
//		System.out.println("-----------3.返            回------------");
//		System.out.println("-----------4.退            出------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_3(int i) {
//		if (i == 1) {
//			System.out.println("=================================");
//			System.out.println("------------欢迎你管理员！-----------");
//			System.out.println("-----------1.选择英雄--------------");
//			System.out.println("-----------2.返       回--------------");
//			System.out.println("-----------3.退       出--------------");
//			System.out.println("=================================");
//		}
//		if (i == 2) {
//			System.out.println("=================================");
//			System.out.println("-------------欢迎你管理员！---------");
//			System.out.println("-----------1.更改属性--------------");
//			System.out.println("-----------2.更改职业--------------");
//			System.out.println("-----------3.返       回--------------");
//			System.out.println("-----------4.退       出--------------");
//			System.out.println("=================================");
//
//		}
//	}
//
//	public static void adminMenu_4() {
//		System.out.println("=================================");
//		System.out.println("---------------欢迎查询------------");
//		System.out.println("-----------1.查全部 英雄 信息---------");
//		System.out.println("-----------2.按         职        业---------");
//		System.out.println("-----------3.按ID 或者 姓 名---------");
//		System.out.println("-----------4.查询X等级伤害值---------");
//		System.out.println("-----------5.返         回--------------");
//		System.out.println("-----------E.退         出--------------");
//		System.out.println("=================================");
//	}
//
//	public static void adminMenu_5() {
//		System.out.println("=================================");
//		System.out.println("--------------留言板---------------");
//		System.out.println("-----------1.查看留言--------------");
//		System.out.println("-----------2.回复留言--------------");
//		System.out.println("-----------3.清空留言--------------");
//		System.out.println("-----------4.返        回--------------");
//		System.out.println("-----------5.退        出--------------");
//		System.out.println("=================================");
//	}

}
