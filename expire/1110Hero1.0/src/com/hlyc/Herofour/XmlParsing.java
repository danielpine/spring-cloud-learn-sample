package com.hlyc.Herofour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.print.Doc;
import javax.xml.crypto.dsig.XMLValidateContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;

public class XmlParsing {

	File xfile = new File("src\\hero.xml");
	File afile = new File("src\\admin.xml");
	File mfile = new File("src\\message.xml");

	// id=new getHeroSizeforAddID();
	public int getHeroSizeforAddID() throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		Element root = doc.getRootElement();// 解析根节点
		List rootcount = root.elements();
		return rootcount.size();
	}

	/**
	 * 读取文档
	 * 
	 * @param f
	 *            XML文件
	 * @return 生成doc文档
	 * @throws DocumentException
	 */
	public Document doc(File f) throws DocumentException {
		// 调用dom4j输入流获取数据
		SAXReader sax = new SAXReader();
		// 生成java文档
		Document doc = sax.read(f);
		// 返回文档
		return doc;
	}

	/**
	 * 写方法
	 * 
	 * @param doc
	 *            ：要写入的文档
	 * @param f
	 *            ：更改的XML文件
	 * @throws IOException
	 */
	public void out(Document doc, File f) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xw = new XMLWriter(new FileOutputStream(f), format);
		xw.write(doc);
		xw.close();
	}

	/**
	 * 清空留言板
	 * 
	 * @return 是否清空成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgDelectAll() throws DocumentException, IOException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> me = xmlMsgParsing();
		for (int i = rootcount.size() - 1; i >= 0; i--) {
			Element message = (Element) root.elements().get(i);
			root.remove(message);
		}
		out(doc, mfile);
		System.out.println("清空完成");
		return flag;
	}

	/**
	 * 按ID删除某个英雄的的留言内容，————未使用————
	 * 
	 * @param id
	 *            删除谁的留言
	 * @return 是否删除成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgDelect(String id) throws DocumentException, IOException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		for (int i = 0; i < rootcount.size(); i++) {
			if (message.get(i).getId().equals(id)) {
				Element mess = (Element) root.elements().get(i);
				root.remove(mess);
				return flag = true;
			}
		}
		System.out.println("找不到指定id,删除失败！");
		return flag;
	}

	//
	/**
	 * 通过姓名更改属性
	 * 
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroChangeByCharacteristic(String[] s,
			String heroname/* String different */) throws DocumentException, IOException {
		String mark = null;//标签
		Document doc = doc(xfile);// 拿到文档
		Element root = doc.getRootElement();// 解析根节点
		String herojob = xmlViaNameGetJob(heroname);// 通过姓名生成汉语职业
		// 转换为标签名
		if (herojob.equals("射手")) {
			mark = "st";
		} else if (herojob.equals("刺客")) {
			mark = "ck";
		} else if (herojob.equals("法师")) {
			mark = "fs";
		} else if (herojob.equals("坦克/战士")) {
			mark = "tf";
		} else {
			System.out.println("输入错误");
			return false;
		}//可抽取为方法11.10心得
		// 标签名转换完成
		List rootcount = root.elements(mark);
		switch (herojob) {
		case "射手":
			ArrayList<Hero_st> list = xmlHeroParsing(mark);
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// 解析节点
					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("shoot");
					Element damage = hero.element("damage");
					/**
					 * 处理子节点skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "等级", "生命值", "蓝量", "射程", "一技能", "二技能", "三技能"
					level.setText(s[0]);
					red.setText(s[1]);
					blue.setText(s[2]);
					shoot.setText(s[3]);
					damage.setText(l * 3 + "");
					i.setText(s[4]);
					ii.setText(s[5]);
					iii.setText(s[6]);
					out(doc, xfile);
					return true;
				}
			}
			break;
		// break;
		case "刺客":
			ArrayList<Hero_ck/* 1110.10.13.修改之前错写为hero_st */> list_ck = xmlHeroParsing(mark);//
			for (int j = 0; j < list_ck.size(); j++) {
				if (list_ck.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// 解析节点
					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("displacement");
					Element damage = hero.element("damage");
					/**
					 * 处理子节点skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "等级", "生命值", "蓝量", s3","一技能", "二技能", "三技能"
					level.setText(s[0]);
					red.setText(s[1]);
					blue.setText(s[2]);
					shoot.setText(s[3]);
					damage.setText(l * 5 + "");
					i.setText(s[4]);
					ii.setText(s[5]);
					iii.setText(s[6]);
					out(doc, xfile);
					return true;
				}
			}
			return true;
		// break;
		case "法师":
			ArrayList<Hero_fs> list_fs = xmlHeroParsing(mark);
			for (int j = 0; j < list_fs.size(); j++) {
				if (list_fs.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// 解析节点

					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("useblue");
					Element damage = hero.element("damage");
					/**
					 * 处理子节点skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);

					// "等级", "生命值", "蓝量", "一技能", "二技能", "三技能"
					level.setText(s[0]);
					red.setText(s[1]);
					blue.setText(s[2]);
					shoot.setText(l * 2 + "");
					damage.setText(l * 2 + "");
					i.setText(s[3]);
					ii.setText(s[4]);
					iii.setText(s[5]);
					out(doc, xfile);
					return true;
				}
			}
			return true;
		// break;
		case "坦克/战士":
			ArrayList<Hero_tf> list_tf = xmlHeroParsing(mark);
			for (int j = 0; j < list_tf.size(); j++) {
				if (list_tf.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// 解析节点

					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("makedamage");
					Element damage = hero.element("damage");
					/**
					 * 处理子节点skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "等级", "生命值", "蓝量", "射程", "一技能", "二技能", "三技能"
					level.setText(s[0]);
					red.setText(s[1]);
					blue.setText(s[2]);
					shoot.setText(s[3]);
					damage.setText(l * 4 + "");
					i.setText(s[4]);
					ii.setText(s[5]);
					iii.setText(s[6]);
					out(doc, xfile);
					return true;
				}
			}
			return true;
		// break;

		default:
			return false;
		// break;
		}
		return false;

	}

	/**
	 * 留言回复
	 * 
	 * @param id
	 *            回复id
	 * @param s
	 *            回复内容
	 * @return 回复结果
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgReply(String id, String s) throws DocumentException, IOException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		for (int i = 0; i < rootcount.size(); i++) {
			if (message.get(i).getId().equals(id)) {
				Element mess = (Element) root.elements().get(i);
				Element msgreply = mess.element("msgreply");
				msgreply.setText("回复时间：" + time + " 回复内容：" + "\n" + s);
				out(doc, mfile);
				System.out.println("回复成功！");
				return flag = true;
			}
		}
		System.out.println("没有该英雄的留言记录,回复失败！");
		return flag;
	}

	/**
	 * 显示回复
	 * 
	 * @throws DocumentException
	 */
	public boolean xmlMsgShowReMessage(String id) throws DocumentException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;// 用于判断是否有管理员回复
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		if (rootcount.size() == 0)// 处理留言板清空的情况
		{
			System.out.println("留言板很空旷，没有新回复哦！");
			return false;
		}
		List<Message> message = xmlMsgParsing();
		String s = message.get(0).getMsgreply();
		// 1108晚新增显示单条留言方法
		for (int i = 0; i < rootcount.size(); i++) {
			if (message.get(i).getId().equals(id) && !(message.get(i).getMsgreply().equals(""))) {// 新建xml文件中无内容设置为
																									// ""
				System.out.println("管理员回复：\n" + message.get(i).getMsgreply());
				flag = true;
			}
		}
		// 此方法可以显示全部ID下内容，但管理员回复都是在第一条，留言板功能并没有实现多条回复
		// for(Message x :message){if(x.getId().equals(id)){
		// if(x.getMsgreply()!=null){ System.out.println("管理员回复：\n" +
		// x.getMsgreply());
		// flag=true;}
		// }
		// }
		if (!flag) {
			System.out.println("没有新回复哦！");
		}
		return flag;
	}

	/**
	 * 显示留言
	 * 
	 * @throws DocumentException
	 */
	public void xmlMsgShowMessage() throws DocumentException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		if (rootcount.size() == 0) {
			System.out.println("没有新留言哦！");
		} else {
			for (Message x : message) {
				System.out.println("英雄ID:" + x.getId() + "给你留言了" + "\n" + x.getMessage());
			}
		}
	}

	/**
	 * 显示留言详细信息
	 * 
	 * @throws DocumentException
	 */
	public void xmlMsgShowAll() throws DocumentException {
		Document doc = doc(mfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		for (Message x : message) {
			System.out.println(x);
		}
	}

	/**
	 * 通过Id或者姓名固定返回ID
	 * 
	 * @param id
	 *            姓名或者ID
	 * @return 固定返回Name以便调用
	 * @throws DocumentException
	 */
	public String xmlViaIdorNameReturnName(String id) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(id) || cklist.get(ick).getName().equals(id)) {
					// System.out.println(cklist.get(ick));

					return cklist.get(ick).getName();
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(id) || fslist.get(ifs).getName().equals(id)) {
					// System.out.println();
					return fslist.get(ifs).getName();
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(id) || stlist.get(ist).getName().equals(id)) {
					// System.out.println(stlist.get(ist));
					return stlist.get(ist).getName();
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(id) || tflist.get(itf).getName().equals(id)) {
					// System.out.println(tflist.get(itf));
					return tflist.get(itf).getName();

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");// 1110更改，输出红色字体
		}

		return null;
	}

	/**
	 * 通过Id返回姓名
	 * 
	 * @param id
	 *            ID
	 * @return 固定返回Name以便调用
	 * @throws DocumentException
	 */
	public String xmlViaIdGetName(String id) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(id)) {
					// System.out.println(cklist.get(ick));

					return cklist.get(ick).getName();
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(id)) {
					// System.out.println();
					return fslist.get(ifs).getName();
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(id)) {
					// System.out.println(stlist.get(ist));
					return stlist.get(ist).getName();
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(id)) {
					// System.out.println(tflist.get(itf));
					return tflist.get(itf).getName();

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return null;
	}

	/**
	 * 通过姓名返回ID
	 * 
	 * @param name
	 *            姓名
	 * @return 固定返回ID以便调用
	 * @throws DocumentException
	 */
	public String xmlViaNameGetId(String name) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getName().equals(name) || cklist.get(ick).getId().equals(name)) {
					// System.out.println(cklist.get(ick));

					return cklist.get(ick).getId();
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getName().equals(name) || fslist.get(ifs).getId().equals(name)) {
					// System.out.println();
					return fslist.get(ifs).getId();
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getName().equals(name) || stlist.get(ist).getId().equals(name)) {
					// System.out.println(stlist.get(ist));
					return stlist.get(ist).getId();
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getName().equals(name) || tflist.get(itf).getId().equals(name)) {
					// System.out.println(tflist.get(itf));
					return tflist.get(itf).getId();

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return null;
	}

	/**
	 * 通过姓名返回职业
	 * 
	 * @param name
	 *            姓名
	 * @return 职业
	 * @throws DocumentException
	 */
	public String xmlViaNameGetJob(String name) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getName().equals(name)) {
					// System.out.println(cklist.get(ick));

					return cklist.get(ick).getJob();
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getName().equals(name)) {
					// System.out.println();
					return fslist.get(ifs).getJob();
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getName().equals(name)) {
					// System.out.println(stlist.get(ist));
					return stlist.get(ist).getJob();
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getName().equals(name)) {
					// System.out.println(tflist.get(itf));
					return tflist.get(itf).getJob();

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！返回null");
		}

		return null;
	}

	/**
	 * 英雄添加留言
	 * 
	 * @param id
	 *            英雄id
	 * @param m
	 *            留言内容
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgAdd(String id, String m) throws DocumentException, IOException {
		Document doc = doc(mfile);
		Element root = doc.getRootElement();
		Element message = DocumentHelper.createElement("message");
		Element idm = DocumentHelper.createElement("id");
		Element msg = DocumentHelper.createElement("msg");
		Element msgreply = DocumentHelper.createElement("msgreply");
		// 赋值
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		String name = xmlViaIdGetName(id);
		idm.setText(id);
		msg.setText("姓名:" + name + " 时间：" + time + " 内容：" + "||>" + m + "<||");
		root.add(message);
		message.add(idm);
		message.add(msg);
		message.add(msgreply);
		out(doc, mfile);
		return true;
	}

	/**
	 * 留言板解析 按hero.xml的id添加留言
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public ArrayList<Message> xmlMsgParsing() throws DocumentException {
		Document doc = doc(mfile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		ArrayList<Message> list = new ArrayList<Message>();
		for (int i = 0; i < rootcount.size(); i++) {
			Element message = (Element) root.elements("message").get(i);
			Element id = message.element("id");
			Element msg = message.element("msg");
			Element msgreply = message.element("msgreply");
			list.add(new Message(id.getText(), msg.getText(), msgreply.getText()));
		}
		return list;
	}

	/**
	 * 英雄解析
	 * 
	 * @param herojob
	 *            输入职业
	 * @return 对应职业解析结果
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList xmlHeroParsing(String herojob) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		Element root = doc.getRootElement();// 解析根节点
		List rootcount = root.elements(herojob);
		switch (herojob) {
		case "st":
			ArrayList<Hero_st> list_st = new ArrayList<Hero_st>();
			for (int j = 0; j < rootcount.size(); j++) {
				Element hero = (Element) root.elements(herojob).get(j);
				// 解析节点
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element shoot = hero.element("shoot");
				Element damage = hero.element("damage");
				/**
				 * 处理子节点skill
				 */
				Element skill = hero.element("skill");
				Element n = skill.element("n");
				Element i = skill.element("i");
				Element ii = skill.element("ii");
				Element iii = skill.element("iii");
				list_st.add(new Hero_st(id.getText(), name.getText(), job.getText(), n.getText(), i.getText(),
						ii.getText(), iii.getText(), level.getText(), red.getText(), blue.getText(), shoot.getText(),
						damage.getText()));
			}
			return list_st;
		// break;
		case "ck":
			ArrayList<Hero_ck> list_ck = new ArrayList<Hero_ck>();

			for (int j = 0; j < rootcount.size(); j++) {
				Element hero = (Element) root.elements(herojob).get(j);
				// 解析节点
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element displacement = hero.element("displacement");
				Element damage = hero.element("damage");
				/**
				 * 处理子节点skill
				 */
				Element skill = hero.element("skill");
				Element n = skill.element("n");
				Element i = skill.element("i");
				Element ii = skill.element("ii");
				Element iii = skill.element("iii");
				list_ck.add(new Hero_ck(id.getText(), name.getText(), job.getText(), n.getText(), i.getText(),
						ii.getText(), iii.getText(), level.getText(), red.getText(), blue.getText(),
						displacement.getText(), damage.getText()));
			}
			return list_ck;
		// break;
		case "fs":
			ArrayList<Hero_fs> list_fs = new ArrayList<Hero_fs>();
			for (int j = 0; j < rootcount.size(); j++) {
				Element hero = (Element) root.elements(herojob).get(j);
				// 解析节点
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element useblue = hero.element("useblue");
				Element damage = hero.element("damage");
				/**
				 * 处理子节点skill
				 */
				Element skill = hero.element("skill");
				Element n = skill.element("n");
				Element i = skill.element("i");
				Element ii = skill.element("ii");
				Element iii = skill.element("iii");
				list_fs.add(new Hero_fs(id.getText(), name.getText(), job.getText(), n.getText(), i.getText(),
						ii.getText(), iii.getText(), level.getText(), red.getText(), blue.getText(), useblue.getText(),
						damage.getText()));
			}

			return list_fs;
		// break;
		case "tf":
			ArrayList<Hero_tf> list_tf = new ArrayList<Hero_tf>();

			for (int j = 0; j < rootcount.size(); j++) {
				Element hero = (Element) root.elements(herojob).get(j);
				// 解析节点
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element makedamage = hero.element("makedamage");
				Element damage = hero.element("damage");
				/**
				 * 处理子节点skill
				 */
				Element skill = hero.element("skill");
				Element n = skill.element("n");
				Element i = skill.element("i");
				Element ii = skill.element("ii");
				Element iii = skill.element("iii");
				list_tf.add(new Hero_tf(id.getText(), name.getText(), job.getText(), n.getText(), i.getText(),
						ii.getText(), iii.getText(), level.getText(), red.getText(), blue.getText(),
						makedamage.getText(), damage.getText()));
			}
			return list_tf;
		// break;

		default:
			return null;
		// break;
		}

	}

	/**
	 * 显示全部英雄信息
	 * 
	 * @param doc
	 *            拿到文档
	 * @throws DocumentException
	 */
	public void xmlShowAllHeroInfo() throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (Hero_ck x : cklist) {
			System.out.println(x);
		}
		for (Hero_fs x : fslist) {
			System.out.println(x);
		}
		for (Hero_st x : stlist) {
			System.out.println(x);
		}
		for (Hero_tf x : tflist) {
			System.out.println(x);
		}

	}

	/**
	 * 按职业查询
	 * 
	 * @param hero
	 *            输入职业
	 * @throws DocumentException
	 */
	public boolean xmlHeroSerch_byJob(String hero) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		switch (hero) {
		case "刺客":
			for (Hero_ck x : cklist) {
				System.out.println(x);
			}

			return true;
		case "法师":
			for (Hero_fs x : fslist) {
				System.out.println(x);
			}

			return true;
		case "射手":
			for (Hero_st x : stlist) {
				System.out.println(x);
			}

			return true;
		case "坦克/战士":
			for (Hero_tf x : tflist) {
				System.out.println(x);
			}
			return true;

		default:
			System.out.println("输入有误！");
			return flag;
		}

	}

	/**
	 * 按姓名更改英雄属性
	 * 
	 * @param hero
	 *            姓名
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroChangeByName(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getName().equals(hero)) {
					root.remove((Element) root.elements("ck").get(ick));
					System.out.println("请输入新职业：|射手|刺客|法师|坦克/战士|");
					Scanner in = new Scanner(System.in);
					String newjob = in.next();
					if (!new Judge().judge(newjob)) {

						System.out.println("职业输入错误!!!\n");
					}
					xmlHeroAdd(newjob);
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getName().equals(hero)) {
					root.remove((Element) root.elements("fs").get(ifs));
					out(doc, xfile);

					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getName().equals(hero)) {
					root.remove((Element) root.elements("st").get(ist));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getName().equals(hero)) {
					root.remove((Element) root.elements("tf").get(itf));
					out(doc, xfile);
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;
	}

	/**
	 * 通过姓名或者Id删除英雄
	 * 
	 * @param hero
	 *            姓名或者ID
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelect(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(hero) || cklist.get(ick).getName().equals(hero)) {
					root.remove((Element) root.elements("ck").get(ick));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(hero) || fslist.get(ifs).getName().equals(hero)) {
					root.remove((Element) root.elements("fs").get(ifs));
					out(doc, xfile);

					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(hero) || stlist.get(ist).getName().equals(hero)) {
					root.remove((Element) root.elements("st").get(ist));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(hero) || tflist.get(itf).getName().equals(hero)) {
					root.remove((Element) root.elements("tf").get(itf));
					out(doc, xfile);
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;

	}

	/**
	 * 通过Id删除英雄
	 * 
	 * @param hero
	 *            ID
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelectByID(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(hero)) {
					root.remove((Element) root.elements("ck").get(ick));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(hero)) {
					root.remove((Element) root.elements("fs").get(ifs));
					out(doc, xfile);

					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(hero)) {
					root.remove((Element) root.elements("st").get(ist));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(hero)) {
					root.remove((Element) root.elements("tf").get(itf));
					out(doc, xfile);
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;

	}

	/**
	 * 按姓名 删除方法
	 * 
	 * @param hero
	 *            姓名
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelectByName(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getName().equals(hero)) {
					root.remove((Element) root.elements("ck").get(ick));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getName().equals(hero)) {
					root.remove((Element) root.elements("fs").get(ifs));
					out(doc, xfile);

					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getName().equals(hero)) {
					root.remove((Element) root.elements("st").get(ist));
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getName().equals(hero)) {
					root.remove((Element) root.elements("tf").get(itf));
					out(doc, xfile);
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;

	}

	/**
	 * 检查姓名或者ID是否存在
	 * 
	 * @param hero
	 *            姓名或者ID
	 * @return 是否存在
	 * @throws DocumentException
	 */
	public boolean xmlHeroIdOrNameExists(String hero) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(hero) || cklist.get(ick).getName().equals(hero)) {
					// System.out.println(cklist.get(ick));
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(hero) || fslist.get(ifs).getName().equals(hero)) {
					// System.out.println(fslist.get(ifs));
					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(hero) || stlist.get(ist).getName().equals(hero)) {
					// System.out.println(stlist.get(ist));
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(hero) || tflist.get(itf).getName().equals(hero)) {
					// System.out.println(tflist.get(itf));
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("--------输入错误！！！--------");
		}

		return false;
	}

	/**
	 * 按姓名或者id查询英雄信息
	 * 
	 * @param hero
	 *            调用者输入英雄姓名或者ID
	 * @param flag
	 *            找到标记
	 * @return
	 * @throws DocumentException
	 */
	public boolean xmlHeroSerch_byIDorName(String hero) throws DocumentException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getId().equals(hero) || cklist.get(ick).getName().equals(hero)) {
					System.out.println(cklist.get(ick));
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getId().equals(hero) || fslist.get(ifs).getName().equals(hero)) {
					System.out.println(fslist.get(ifs));
					return flag = true;
				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getId().equals(hero) || stlist.get(ist).getName().equals(hero)) {
					System.out.println(stlist.get(ist));
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getId().equals(hero) || tflist.get(itf).getName().equals(hero)) {
					System.out.println(tflist.get(itf));
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;
	}

	/**
	 * 未实现方法
	 * 
	 * @param addjob
	 * @param obj
	 * @throws DocumentException
	 * @throws IOException
	 */
	void add(String addjob, Object obj) throws DocumentException, IOException {
		Document doc = doc(xfile);
		Element root = doc.getRootElement();
		/*
		 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
		 * blue, shoot, damage)
		 */
		Hero_st x = null;
		Element hero_st = DocumentHelper.createElement("st");
		Element id = DocumentHelper.createElement("id");
		Element name = DocumentHelper.createElement("name");
		Element job = DocumentHelper.createElement("job");
		Element level = DocumentHelper.createElement("level");
		Element red = DocumentHelper.createElement("red");
		Element blue = DocumentHelper.createElement("blue");
		Element shoot = DocumentHelper.createElement("shoot");
		Element damage = DocumentHelper.createElement("damage");

		Element skill = DocumentHelper.createElement("skill");
		Element n = DocumentHelper.createElement("n");
		Element i = DocumentHelper.createElement("i");
		Element ii = DocumentHelper.createElement("ii");
		Element iii = DocumentHelper.createElement("iii");
		// 赋值
		int l = Integer.parseInt(x.getLevel());
		id.setText("1"); // 1
		name.setText(x.getName()); // 2
		job.setText(x.getJob()); // 3
		level.setText(x.getLevel()); // 4
		red.setText(x.getRed()); // 5
		blue.setText(x.getBlue()); // 6
		shoot.setText(x.getShoot()); // 7
		damage.setText(l * 3 + ""); // 8
		// skill //9
		n.setText("n");
		i.setText(x.getI());
		ii.setText(x.getIi());
		iii.setText(x.getIii());
		// 添加
		skill.add(n);
		skill.add(i);
		skill.add(ii);
		skill.add(iii);

		hero_st.add(id);
		hero_st.add(name);
		hero_st.add(job);
		hero_st.add(skill);
		hero_st.add(level);
		hero_st.add(red);
		hero_st.add(blue);
		hero_st.add(shoot);
		hero_st.add(damage);

		root.add(hero_st);
		out(doc, xfile);
	}

	/**
	 * 未实现方法
	 */
	public void getInfo() {
		System.out.println("请输入姓名,等级，");

	}

	/**
	 * ID格式化 调用系统时间生成唯一ID
	 * 
	 * @param id
	 *            无用参数 ，不想更改其他已写完代码 直接进入后重新赋值 //旧方法的参数：当前hero.xml中英雄数量
	 * @return
	 */
	public String idFormat(int id) {
		id = -(int) System.currentTimeMillis();// 暂时用系统时间作为ID
		char[] cr = new char[] { 0, 0, 0 };
		String str = null;
		String s = id + "";// ++id先加后使用
		return s;
		// if (s.length() == 3) {
		// s.getChars(0, s.length(), cr, 0);
		// str = "" + cr[0] + cr[1] + cr[2];
		// } else if (s.length() == 2) {
		// s.getChars(0, s.length(), cr, 0);
		// str = "0" + cr[0] + cr[1];
		// } else if (s.length() == 1) {
		// s.getChars(0, s.length(), cr, 0);
		// str = "00" + cr[0];
		// } else {
		// System.out.println("找不到ID默认设为null");
		// return null;
		// }
		// return str;
	}

	/**
	 * 按职业添加英雄
	 * 
	 * @param addjob
	 *            选择职业
	 * @return 是否成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroRestor(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// 拿到文档
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		for (int i = 0; i < rootcount.size(); i++) {
			for (int ick = 0; ick < cklist.size(); ick++) {
				if (cklist.get(ick).getName().equals(hero)) {
					Element ck = (Element) root.elements("ck").get(ick);
					System.out.println("请输入:等级，生命值，蓝量，位移，一技能，二技能，三技能");
					String[] inck = new String[] { "等级", "生命值", "蓝量", "位移", "一技能", "二技能", "三技能"
							/* 0 1 2 3 4 5 6 */
					};
					String[] sck = new String[7];
					String d = null;
					Scanner in = new Scanner(System.in);
					for (int j = 0; j < 7; j++) {
						System.out.println("请输入：" + inck[j]);
						if (j == 3) {
							for (;;) {
								System.out.println("位移范围500-1000");

								String dpl = in.next();
								int dplint = Integer.parseInt(dpl);

								if (dplint >= 500 && dplint <= 1000) {
									d = dpl;
									continue;
								} else {
									d = "0";
									System.out.println("输入错误！位移范围500-1000系统默认为0");
								}
							}
						}
						InputStream input = System.in;
						BufferedReader buf = new BufferedReader(new InputStreamReader(input));
						String str = buf.readLine();
						sck[j] = str;
					}
					sck[3] = d;
					int l = Integer.parseInt(sck[0]);
					ck.element("level").clearContent();
					ck.element("red").clearContent();
					ck.element("blue").clearContent();
					ck.element("displacement").clearContent();
					ck.element("i").clearContent();
					ck.element("ii").clearContent();
					ck.element("iii").clearContent();
					ck.element("damage").clearContent();

					ck.element("level").setText(sck[0]);
					ck.element("red").setText(sck[1]);
					ck.element("blue").setText(sck[2]);
					ck.element("displacement").setText(sck[3]);
					ck.element("i").setText(sck[4]);
					ck.element("ii").setText(sck[5]);
					ck.element("iii").setText(sck[6]);
					ck.element("damage").setText(l * 5 + "");
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int ifs = 0; ifs < fslist.size(); ifs++) {
				if (fslist.get(ifs).getName().equals(hero)) {
					Element element = (Element) root.elements("fs").get(ifs);
					System.out.println("请输入:等级，生命值，蓝量，位移，一技能，二技能，三技能");
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
					int l = Integer.parseInt(s[0]);
					element.element("level").setText(s[0]);
					element.element("red").setText(s[1]);
					element.element("blue").setText(s[2]);
					element.element("useblue").setText(l * 2 + "");
					element.element("i").setText(s[3]);
					element.element("ii").setText(s[4]);
					element.element("iii").setText(s[5]);
					element.element("damage").setText(l * 2 + "");
					out(doc, xfile);
					return flag = true;

				}
			}
			for (int ist = 0; ist < stlist.size(); ist++) {
				if (stlist.get(ist).getName().equals(hero)) {

					Element element = (Element) root.elements("st").get(ist);
					System.out.println("请输入:等级，生命值，蓝量，位移，一技能，二技能，三技能");
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
					int l = Integer.parseInt(s[0]);
					element.element("level").setText(s[0]);
					element.element("red").setText(s[1]);
					element.element("blue").setText(s[2]);
					element.element("shoot").setText(s[3]);
					element.element("i").setText(s[4]);
					element.element("ii").setText(s[5]);
					element.element("iii").setText(s[6]);
					element.element("damage").setText(l * 3 + "");
					out(doc, xfile);
					return flag = true;
				}
			}
			for (int itf = 0; itf < tflist.size(); itf++) {
				if (tflist.get(itf).getName().equals(hero)) {
					Element element = (Element) root.elements("tf").get(itf);
					System.out.println("请输入:等级，生命值，蓝量，输出，一技能，二技能，三技能");
					String[] inck = new String[] { "等级", "生命值", "蓝量", "输出", "一技能", "二技能", "三技能"
							/* 0 1 2 3 4 5 6 */
					};
					String[] s = new String[7];
					for (int j = 0; j < 7; j++) {
						System.out.println("请输入：" + inck[j]);
						InputStream input = System.in;
						BufferedReader buf = new BufferedReader(new InputStreamReader(input));
						String str = buf.readLine();
						s[j] = str;
					}
					int l = Integer.parseInt(s[0]);
					element.element("level").setText(s[0]);
					element.element("red").setText(s[1]);
					element.element("blue").setText(s[2]);

					element.element("damage").setText(l * 4 + "");
					element.element("makedamage").setText(s[3]);
					element.element("i").setText(s[4]);
					element.element("ii").setText(s[5]);
					element.element("iii").setText(s[6]);
					out(doc, xfile);
					return flag = true;

				}
			}

		}
		if (!flag) {
			System.out.println("输入错误！");
		}

		return false;
	}

	/**
	 * 更改某英雄职业、通过获取英雄原ID Name 信息并将它保存到临时空间 然后remove此英雄调用Add方法新建新职业英雄 但是ID name
	 * setText()的时候用原来的信息赋值
	 * 
	 * @param addjob
	 *            使用者输入更改职业
	 * @param oldid
	 *            原id
	 * @param oldname
	 *            原名字
	 * @return 是否更改职业成功
	 * @throws IOException
	 * @throws DocumentException
	 */
	public boolean xmlHeroAddNewJob(String addjob, String oldid, String oldname) throws IOException, DocumentException {
		// int id = getHeroSizeforAddID();// int id;
		String idformat = idFormat(getHeroSizeforAddID());// 获取id并格式化
		Document doc = doc(xfile);
		Element root = doc.getRootElement();
		String d = null;
		switch (addjob) {
		case "射手":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] ins = new String[] { "", "等级", "生命值", "蓝量", "射程", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6 7
			};
			String[] s = new String[8];
			for (int i = 1; i < 8; i++) {
				System.out.println("请输入：" + ins[i]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[i] = str;
			}
			Hero_st x = null;
			Element sthero = DocumentHelper.createElement("st");
			Element stid = DocumentHelper.createElement("id");
			Element stname = DocumentHelper.createElement("name");
			Element stjob = DocumentHelper.createElement("job");
			Element stlevel = DocumentHelper.createElement("level");
			Element stred = DocumentHelper.createElement("red");
			Element stblue = DocumentHelper.createElement("blue");
			Element stshoot = DocumentHelper.createElement("shoot");//
			Element stdamage = DocumentHelper.createElement("damage");
			Element stskill = DocumentHelper.createElement("skill");
			Element stn = DocumentHelper.createElement("n");
			Element sti = DocumentHelper.createElement("i");
			Element stii = DocumentHelper.createElement("ii");
			Element stiii = DocumentHelper.createElement("iii");
			// 赋值
			int l = Integer.parseInt(s[1]);

			stid.setText(oldid); // 更改职业 姓名id不变
			stname.setText(oldname); // 更改职业 姓名id不变
			stjob.setText("射手"); // 3
			stlevel.setText(s[1]); // 4
			stred.setText(s[2]); // 5
			stblue.setText(s[3]); // 6
			stshoot.setText(s[4]); // 7
			stdamage.setText(l * 3 + ""); // 8
			// skill //9
			stn.setText("n");
			sti.setText(s[5]);
			stii.setText(s[6]);
			stiii.setText(s[7]);

			// 添加
			stskill.add(stn);
			stskill.add(sti);
			stskill.add(stii);
			stskill.add(stiii);

			sthero.add(stid);
			sthero.add(stname);
			sthero.add(stjob);
			sthero.add(stskill);
			sthero.add(stlevel);
			sthero.add(stred);
			sthero.add(stblue);
			sthero.add(stshoot);//
			sthero.add(stdamage);

			root.add(sthero);
			out(doc, xfile);

			return true;
		case "刺客":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，位移，一技能，二技能，三技能");
			String[] inck = new String[] { "姓名", "等级", "生命值", "蓝量", "位移", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[8];
			Scanner in = new Scanner(System.in);
			for (int i = 1; i < 8; i++) {
				System.out.println("请输入：" + inck[i]);// 除去姓名
				if (i == 4) {
					System.out.println("位移范围500-1000");

					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
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
			sck[4] = d;

			Hero_ck ckx = null;
			Element ckhero = DocumentHelper.createElement("ck");
			Element ckid = DocumentHelper.createElement("id");
			Element ckname = DocumentHelper.createElement("name");
			Element ckjob = DocumentHelper.createElement("job");
			Element cklevel = DocumentHelper.createElement("level");
			Element ckred = DocumentHelper.createElement("red");
			Element ckblue = DocumentHelper.createElement("blue");
			Element ckdamage = DocumentHelper.createElement("damage");
			Element ckdisplacement = DocumentHelper.createElement("displacement");
			Element ckskill = DocumentHelper.createElement("skill");
			Element ckn = DocumentHelper.createElement("n");
			Element cki = DocumentHelper.createElement("i");
			Element ckii = DocumentHelper.createElement("ii");
			Element ckiii = DocumentHelper.createElement("iii");
			int ckl = Integer.parseInt(sck[1]);
			// double d1 = Math.random() * 500 + 500;//随机位移范围方法-未被使用
			ckid.setText(oldid); // 1
			ckname.setText(oldname); // 2
			ckjob.setText("刺客"); // 3
			cklevel.setText(sck[1]); // 4
			ckred.setText(sck[2]); // 5
			ckblue.setText(sck[3]); // 6
			ckdamage.setText(ckl * 5 + ""); // 7
			ckdisplacement.setText(sck[4]); // 8
			ckn.setText("n");
			cki.setText(sck[5]);
			ckii.setText(sck[6]);
			ckiii.setText(sck[7]);
			ckskill.add(ckn);
			ckskill.add(cki);
			ckskill.add(ckii);
			ckskill.add(ckiii);
			ckhero.add(ckid);
			ckhero.add(ckname);
			ckhero.add(ckjob);
			ckhero.add(ckskill);
			ckhero.add(cklevel);
			ckhero.add(ckred);
			ckhero.add(ckblue);
			ckhero.add(ckdamage);
			ckhero.add(ckdisplacement);//
			root.add(ckhero);
			out(doc, xfile);
			return true;
		case "法师":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] infs = new String[] { "姓名", "等级", "生命值", "蓝量", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6
			};
			String[] sfs = new String[7];
			for (int i = 1; i < 7; i++) {
				System.out.println("请输入：" + infs[i]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				sfs[i] = str;
			}
			Hero_fs fsx = null;
			Element fshero = DocumentHelper.createElement("fs");
			Element fsid = DocumentHelper.createElement("id");
			Element fsname = DocumentHelper.createElement("name");
			Element fsjob = DocumentHelper.createElement("job");
			Element fslevel = DocumentHelper.createElement("level");
			Element fsred = DocumentHelper.createElement("red");
			Element fsblue = DocumentHelper.createElement("blue");
			Element fsdamage = DocumentHelper.createElement("damage");
			Element fsuseblue = DocumentHelper.createElement("useblue");//
			Element fsskill = DocumentHelper.createElement("skill");
			Element fsn = DocumentHelper.createElement("n");
			Element fsi = DocumentHelper.createElement("i");
			Element fsii = DocumentHelper.createElement("ii");
			Element fsiii = DocumentHelper.createElement("iii");
			// 赋值
			int fsl = Integer.parseInt(sfs[1]);
			// double d=Math.random()*500+500;
			fsid.setText(oldid); // 1
			fsname.setText(oldname); // 2
			fsjob.setText("法师"); // 3
			fslevel.setText(sfs[1]); // 4
			fsred.setText(sfs[2]); // 5
			fsblue.setText(sfs[3]); // 6
			fsdamage.setText(fsl * 2 + ""); // 7
			fsuseblue.setText(fsl * 2 + ""); // 8
			fsn.setText("n");
			fsi.setText(sfs[4]);
			fsii.setText(sfs[5]);
			fsiii.setText(sfs[6]);
			fsskill.add(fsn);
			fsskill.add(fsi);
			fsskill.add(fsii);
			fsskill.add(fsiii);
			fshero.add(fsid);
			fshero.add(fsname);
			fshero.add(fsjob);
			fshero.add(fsskill);
			fshero.add(fslevel);
			fshero.add(fsred);
			fshero.add(fsblue);
			fshero.add(fsdamage);
			fshero.add(fsuseblue);//
			root.add(fshero);
			out(doc, xfile);
			return true;
		case "坦克/战士":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] intf = new String[] { "姓名", "等级", "生命值", "蓝量", "输出", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6 7
			};
			String[] stf = new String[8];
			for (int i = 1; i < 8; i++) {
				System.out.println("请输入：" + intf[i]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				stf[i] = str;
			}
			// Hero_tf tfx = null;
			Element tfhero = DocumentHelper.createElement("tf");
			Element tfid = DocumentHelper.createElement("id");
			Element tfname = DocumentHelper.createElement("name");
			Element tfjob = DocumentHelper.createElement("job");
			Element tflevel = DocumentHelper.createElement("level");
			Element tfred = DocumentHelper.createElement("red");
			Element tfblue = DocumentHelper.createElement("blue");
			Element tfdamage = DocumentHelper.createElement("damage");
			Element tfmakedamage = DocumentHelper.createElement("makedamage");
			Element tfskill = DocumentHelper.createElement("skill");
			Element tfn = DocumentHelper.createElement("n");
			Element tfi = DocumentHelper.createElement("i");
			Element tfii = DocumentHelper.createElement("ii");
			Element tfiii = DocumentHelper.createElement("iii");
			// 赋值
			int tfl = Integer.parseInt(stf[1]);
			// double d=Math.random()*500+500;
			tfid.setText(oldid); // 1
			tfname.setText(oldname); // 2
			tfjob.setText("坦克/战士"); // 3
			tflevel.setText(stf[1]); // 4
			tfred.setText(stf[2]); // 5
			tfblue.setText(stf[3]); // 6
			tfdamage.setText(tfl * 4 + ""); // 7
			tfmakedamage.setText(stf[4]); // 8
			tfn.setText("n");
			tfi.setText(stf[5]);
			tfii.setText(stf[6]);
			tfiii.setText(stf[7]);
			tfskill.add(tfn);
			tfskill.add(tfi);
			tfskill.add(tfii);
			tfskill.add(tfiii);
			tfhero.add(tfid);
			tfhero.add(tfname);
			tfhero.add(tfjob);
			tfhero.add(tfskill);
			tfhero.add(tflevel);
			tfhero.add(tfred);
			tfhero.add(tfblue);
			tfhero.add(tfdamage);
			tfhero.add(tfmakedamage);
			root.add(tfhero);
			out(doc, xfile);
			return true;
		default:
			System.out.println("请按如下格式输入" + "\n|射手|刺客|法师|坦克/战士|");
			break;
		}

		return false;
	}

	/**
	 * 按职业添加
	 * 
	 * @param addjob
	 *            使用者输入的职业
	 * @return 是否添加成功
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroAdd(String addjob) throws DocumentException, IOException {
		// int id = getHeroSizeforAddID();// int id;
		String idformat = idFormat(getHeroSizeforAddID());// 获取id并格式化//未用到已被系统时间法替代
		Document doc = doc(xfile);
		Element root = doc.getRootElement();
		String d = null;
		switch (addjob) {
		case "射手":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] ins = new String[] { "姓名", "等级", "生命值", "蓝量", "射程", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6 7
			};
			String[] s = new String[8];
			for (int i = 0; i < 8; i++) {//输入属性
				System.out.println("请输入：" + ins[i]);
				InputStream input = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				s[i] = str;
			}
			Hero_st x = null;
			Element sthero = DocumentHelper.createElement("st");
			Element stid = DocumentHelper.createElement("id");
			Element stname = DocumentHelper.createElement("name");
			Element stjob = DocumentHelper.createElement("job");
			Element stlevel = DocumentHelper.createElement("level");
			Element stred = DocumentHelper.createElement("red");
			Element stblue = DocumentHelper.createElement("blue");
			Element stshoot = DocumentHelper.createElement("shoot");//
			Element stdamage = DocumentHelper.createElement("damage");
			Element stskill = DocumentHelper.createElement("skill");
			Element stn = DocumentHelper.createElement("n");
			Element sti = DocumentHelper.createElement("i");
			Element stii = DocumentHelper.createElement("ii");
			Element stiii = DocumentHelper.createElement("iii");
			// 赋值
			int l = Integer.parseInt(s[1]);

			stid.setText(idformat); // 1
			stname.setText(s[0]); // 2
			stjob.setText("射手"); // 3
			stlevel.setText(s[1]); // 4
			stred.setText(s[2]); // 5
			stblue.setText(s[3]); // 6
			stshoot.setText(s[4]); // 7
			stdamage.setText(l * 3 + ""); // 8
			// skill //9
			stn.setText("n");
			sti.setText(s[5]);
			stii.setText(s[6]);
			stiii.setText(s[7]);

			// 添加
			stskill.add(stn);
			stskill.add(sti);
			stskill.add(stii);
			stskill.add(stiii);

			sthero.add(stid);
			sthero.add(stname);
			sthero.add(stjob);
			sthero.add(stskill);
			sthero.add(stlevel);
			sthero.add(stred);
			sthero.add(stblue);
			sthero.add(stshoot);//
			sthero.add(stdamage);

			root.add(sthero);
			out(doc, xfile);

			return true;
		case "刺客":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，位移，一技能，二技能，三技能");
			String[] inck = new String[] { "姓名", "等级", "生命值", "蓝量", "位移", "一技能", "二技能", "三技能"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[8];
			InputStream input = System.in;
			for (int i = 0; i < 8; i++) {
				System.out.println("请输入：" + inck[i]);
				if (i == 4) {
					System.out.println("位移范围500-1000");
					Scanner in = new Scanner(System.in);
					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
					} else {
						d = "0";
						System.out.println("输入错误！位移范围500-1000系统默认为0");
						continue;
					}
				}
				BufferedReader buf = new BufferedReader(new InputStreamReader(input));
				String str = buf.readLine();
				sck[i] = str;
			}
			sck[4] = d;
			Hero_ck ckx = null;
			Element ckhero = DocumentHelper.createElement("ck");
			Element ckid = DocumentHelper.createElement("id");
			Element ckname = DocumentHelper.createElement("name");
			Element ckjob = DocumentHelper.createElement("job");
			Element cklevel = DocumentHelper.createElement("level");
			Element ckred = DocumentHelper.createElement("red");
			Element ckblue = DocumentHelper.createElement("blue");
			Element ckdamage = DocumentHelper.createElement("damage");
			Element ckdisplacement = DocumentHelper.createElement("displacement");
			Element ckskill = DocumentHelper.createElement("skill");
			Element ckn = DocumentHelper.createElement("n");
			Element cki = DocumentHelper.createElement("i");
			Element ckii = DocumentHelper.createElement("ii");
			Element ckiii = DocumentHelper.createElement("iii");
			int ckl = Integer.parseInt(sck[1]);
			// double d1 = Math.random() * 500 + 500;
			ckid.setText(idformat); // 1
			ckname.setText(sck[0]); // 2
			ckjob.setText("刺客"); // 3
			cklevel.setText(sck[1]); // 4
			ckred.setText(sck[2]); // 5
			ckblue.setText(sck[3]); // 6
			ckdamage.setText(ckl * 5 + ""); // 7
			ckdisplacement.setText(sck[4]); // 8
			ckn.setText("n");
			cki.setText(sck[5]);
			ckii.setText(sck[6]);
			ckiii.setText(sck[7]);
			ckskill.add(ckn);
			ckskill.add(cki);
			ckskill.add(ckii);
			ckskill.add(ckiii);
			ckhero.add(ckid);
			ckhero.add(ckname);
			ckhero.add(ckjob);
			ckhero.add(ckskill);
			ckhero.add(cklevel);
			ckhero.add(ckred);
			ckhero.add(ckblue);
			ckhero.add(ckdamage);
			ckhero.add(ckdisplacement);//
			root.add(ckhero);
			out(doc, xfile);
			return true;
		case "法师":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] infs = new String[] { "姓名", "等级", "生命值", "蓝量", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6
			};
			String[] sfs = new String[7];
			for (int i = 0; i < 7; i++) {
				System.out.println("请输入：" + infs[i]);
				InputStream input1 = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input1));
				String str = buf.readLine();
				sfs[i] = str;
			}
			Hero_fs fsx = null;
			Element fshero = DocumentHelper.createElement("fs");
			Element fsid = DocumentHelper.createElement("id");
			Element fsname = DocumentHelper.createElement("name");
			Element fsjob = DocumentHelper.createElement("job");
			Element fslevel = DocumentHelper.createElement("level");
			Element fsred = DocumentHelper.createElement("red");
			Element fsblue = DocumentHelper.createElement("blue");
			Element fsdamage = DocumentHelper.createElement("damage");
			Element fsuseblue = DocumentHelper.createElement("useblue");//
			Element fsskill = DocumentHelper.createElement("skill");
			Element fsn = DocumentHelper.createElement("n");
			Element fsi = DocumentHelper.createElement("i");
			Element fsii = DocumentHelper.createElement("ii");
			Element fsiii = DocumentHelper.createElement("iii");
			// 赋值
			int fsl = Integer.parseInt(sfs[1]);
			// double d=Math.random()*500+500;
			fsid.setText(idformat); // 1
			fsname.setText(sfs[0]); // 2
			fsjob.setText("法师"); // 3
			fslevel.setText(sfs[1]); // 4
			fsred.setText(sfs[2]); // 5
			fsblue.setText(sfs[3]); // 6
			fsdamage.setText(fsl * 2 + ""); // 7
			fsuseblue.setText(fsl * 2 + ""); // 8
			fsn.setText("n");
			fsi.setText(sfs[4]);
			fsii.setText(sfs[5]);
			fsiii.setText(sfs[6]);
			fsskill.add(fsn);
			fsskill.add(fsi);
			fsskill.add(fsii);
			fsskill.add(fsiii);
			fshero.add(fsid);
			fshero.add(fsname);
			fshero.add(fsjob);
			fshero.add(fsskill);
			fshero.add(fslevel);
			fshero.add(fsred);
			fshero.add(fsblue);
			fshero.add(fsdamage);
			fshero.add(fsuseblue);//
			root.add(fshero);
			out(doc, xfile);
			return true;
		case "坦克/战士":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("请输入姓名，等级，生命值，蓝量，射程，一技能，二技能，三技能");
			String[] intf = new String[] { "姓名", "等级", "生命值", "蓝量", "输出", "一技能", "二技能", "三技能"
					// 0 1 2 3 4 5 6 7
			};
			String[] stf = new String[8];
			for (int i = 0; i < 8; i++) {
				System.out.println("请输入：" + intf[i]);
				InputStream input1 = System.in;
				BufferedReader buf = new BufferedReader(new InputStreamReader(input1));
				String str = buf.readLine();
				stf[i] = str;
			}
			// Hero_tf tfx = null;
			Element tfhero = DocumentHelper.createElement("tf");
			Element tfid = DocumentHelper.createElement("id");
			Element tfname = DocumentHelper.createElement("name");
			Element tfjob = DocumentHelper.createElement("job");
			Element tflevel = DocumentHelper.createElement("level");
			Element tfred = DocumentHelper.createElement("red");
			Element tfblue = DocumentHelper.createElement("blue");
			Element tfdamage = DocumentHelper.createElement("damage");
			Element tfmakedamage = DocumentHelper.createElement("makedamage");
			Element tfskill = DocumentHelper.createElement("skill");
			Element tfn = DocumentHelper.createElement("n");
			Element tfi = DocumentHelper.createElement("i");
			Element tfii = DocumentHelper.createElement("ii");
			Element tfiii = DocumentHelper.createElement("iii");
			// 赋值
			int tfl = Integer.parseInt(stf[1]);
			// double d=Math.random()*500+500;
			tfid.setText(idformat); // 1
			tfname.setText(stf[0]); // 2
			tfjob.setText("坦克/战士"); // 3
			tflevel.setText(stf[1]); // 4
			tfred.setText(stf[2]); // 5
			tfblue.setText(stf[3]); // 6
			tfdamage.setText(tfl * 4 + ""); // 7
			tfmakedamage.setText(stf[4]); // 8
			tfn.setText("n");
			tfi.setText(stf[5]);
			tfii.setText(stf[6]);
			tfiii.setText(stf[7]);
			tfskill.add(tfn);
			tfskill.add(tfi);
			tfskill.add(tfii);
			tfskill.add(tfiii);
			tfhero.add(tfid);
			tfhero.add(tfname);
			tfhero.add(tfjob);
			tfhero.add(tfskill);
			tfhero.add(tflevel);
			tfhero.add(tfred);
			tfhero.add(tfblue);
			tfhero.add(tfdamage);
			tfhero.add(tfmakedamage);
			root.add(tfhero);
			out(doc, xfile);
			return true;
		default:
			System.out.println("请按如下格式输入" + "\n|射手|刺客|法师|坦克/战士|");
			break;
		}

		return false;

	}

	/**
	 * 管理员用户名密码解析
	 * 
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public List<Admin> xmlAdminParsing() throws DocumentException {
		Document doc = doc(afile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		ArrayList<Admin> list = new ArrayList<Admin>();
		for (int i = 0; i < rootcount.size(); i++) {
			Element admin = (Element) root.elements("admin").get(i);
			Element id = admin.element("id");
			Element password = admin.element("password");
			list.add(new Admin(id.getText(), password.getText()));
		}
		return list;
	}

	/**
	 * 管理员用户名验证
	 * 
	 * @param doadm
	 *            输入的用户名
	 * @return 是否存在
	 * @throws DocumentException
	 */
	// 管理員查詢
	public boolean xmlAdminExists(String doadm) throws DocumentException {
		Document doc = doc(afile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Admin> adminlist = xmlAdminParsing();// 解析到herolist List传递XmlHero类
		// ArrayList<Admin> list = new ArrayList<Admin>();
		for (int i = 0; i < rootcount.size(); i++) {
			Element admin = (Element) root.elements("admin").get(i);
			Element id = admin.element("id");
			Element password = admin.element("password");
			if (adminlist.get(i).getId().equals(doadm)) {
//				 System.out.println("OK");
				return true;
			}
		}
		return false;
	}

	/**
	 * 密码检查
	 * 
	 * @param ID
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return 是否匹配
	 * @throws DocumentException
	 */
	// 管理員密碼檢查
	public boolean xmlAdminCheckPwd(String ID, String pwd) throws DocumentException {
		Document doc = doc(afile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Admin> adminlist = xmlAdminParsing();// 解析到herolist List传递XmlHero类
		// ArrayList<Admin> list = new ArrayList<Admin>();
		for (int i = 0; i < rootcount.size(); i++) {
			Element admin = (Element) root.elements("admin").get(i);
			Element id = admin.element("id");
			Element password = admin.element("password");
			if (adminlist.get(i).getId().equals(ID) && adminlist.get(i).getPassword().equals(pwd)) {
				 System.out.println("登录成功！");
				return true;
			}
		}
		return false;
	}

}
