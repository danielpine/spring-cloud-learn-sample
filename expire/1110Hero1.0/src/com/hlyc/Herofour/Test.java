package com.hlyc.Herofour;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class Test {
	public static void main(String[] args) throws DocumentException {
		Menu m = new Menu();
		// m.loginMenu();
		// m.adminMenu();
		// m.adminMenu_1();
		// m.adminMenu_2();
		// m.adminMenu_3();
		// m.adminMenu_4();
		// m.adminMenu_5();
		// m.heroMenu();
		// m.heroMenu_1();
		// m.heroMenu_2();

		// xj.wirtexml(new XmlHero("0070", "孔夫子", "法师", "12", "Q", "W", "E",
		// "R"));
		// xj.xmlDelect("孔夫子"+5);
		// List<Admin> list =xj.xmlAdminParsing() ;
		// @SuppressWarnings("unchecked")
		// List<Hero_ck> list =xj.xmlHeroParsing("ck") ;
		// for (Hero_ck x : list) {
		// System.out.println(x);
		// }

		// for (Admin x : list) {
		// System.out.println(x);
		// }
		// XmlParsing xj=new XmlParsing();
		// System.out.println(xj.xmlAdminExists("admin02"));
		//// new XmlParsing().xmlMsgShowMessage();
		// System.out.println(new XmlParsing().getHeroSizeforAddID());
		// System.out.println(new XmlParsing().idFormat(new
		// XmlParsing().getHeroSizeforAddID()));

		System.out.println(new XmlParsing().xmlViaNameGetJob("盖伦"));
		System.out.println(System.currentTimeMillis());
        System.out.println();
	}


}