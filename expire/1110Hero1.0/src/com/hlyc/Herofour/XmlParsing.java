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
		Document doc = doc(xfile);// �õ��ĵ�
		Element root = doc.getRootElement();// �������ڵ�
		List rootcount = root.elements();
		return rootcount.size();
	}

	/**
	 * ��ȡ�ĵ�
	 * 
	 * @param f
	 *            XML�ļ�
	 * @return ����doc�ĵ�
	 * @throws DocumentException
	 */
	public Document doc(File f) throws DocumentException {
		// ����dom4j��������ȡ����
		SAXReader sax = new SAXReader();
		// ����java�ĵ�
		Document doc = sax.read(f);
		// �����ĵ�
		return doc;
	}

	/**
	 * д����
	 * 
	 * @param doc
	 *            ��Ҫд����ĵ�
	 * @param f
	 *            �����ĵ�XML�ļ�
	 * @throws IOException
	 */
	public void out(Document doc, File f) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xw = new XMLWriter(new FileOutputStream(f), format);
		xw.write(doc);
		xw.close();
	}

	/**
	 * ������԰�
	 * 
	 * @return �Ƿ���ճɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgDelectAll() throws DocumentException, IOException {
		Document doc = doc(mfile);// �õ��ĵ�
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> me = xmlMsgParsing();
		for (int i = rootcount.size() - 1; i >= 0; i--) {
			Element message = (Element) root.elements().get(i);
			root.remove(message);
		}
		out(doc, mfile);
		System.out.println("������");
		return flag;
	}

	/**
	 * ��IDɾ��ĳ��Ӣ�۵ĵ��������ݣ���������δʹ�á�������
	 * 
	 * @param id
	 *            ɾ��˭������
	 * @return �Ƿ�ɾ���ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgDelect(String id) throws DocumentException, IOException {
		Document doc = doc(mfile);// �õ��ĵ�
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
		System.out.println("�Ҳ���ָ��id,ɾ��ʧ�ܣ�");
		return flag;
	}

	//
	/**
	 * ͨ��������������
	 * 
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroChangeByCharacteristic(String[] s,
			String heroname/* String different */) throws DocumentException, IOException {
		String mark = null;//��ǩ
		Document doc = doc(xfile);// �õ��ĵ�
		Element root = doc.getRootElement();// �������ڵ�
		String herojob = xmlViaNameGetJob(heroname);// ͨ���������ɺ���ְҵ
		// ת��Ϊ��ǩ��
		if (herojob.equals("����")) {
			mark = "st";
		} else if (herojob.equals("�̿�")) {
			mark = "ck";
		} else if (herojob.equals("��ʦ")) {
			mark = "fs";
		} else if (herojob.equals("̹��/սʿ")) {
			mark = "tf";
		} else {
			System.out.println("�������");
			return false;
		}//�ɳ�ȡΪ����11.10�ĵ�
		// ��ǩ��ת�����
		List rootcount = root.elements(mark);
		switch (herojob) {
		case "����":
			ArrayList<Hero_st> list = xmlHeroParsing(mark);
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// �����ڵ�
					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("shoot");
					Element damage = hero.element("damage");
					/**
					 * �����ӽڵ�skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
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
		case "�̿�":
			ArrayList<Hero_ck/* 1110.10.13.�޸�֮ǰ��дΪhero_st */> list_ck = xmlHeroParsing(mark);//
			for (int j = 0; j < list_ck.size(); j++) {
				if (list_ck.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// �����ڵ�
					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("displacement");
					Element damage = hero.element("damage");
					/**
					 * �����ӽڵ�skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "�ȼ�", "����ֵ", "����", s3","һ����", "������", "������"
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
		case "��ʦ":
			ArrayList<Hero_fs> list_fs = xmlHeroParsing(mark);
			for (int j = 0; j < list_fs.size(); j++) {
				if (list_fs.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// �����ڵ�

					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("useblue");
					Element damage = hero.element("damage");
					/**
					 * �����ӽڵ�skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);

					// "�ȼ�", "����ֵ", "����", "һ����", "������", "������"
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
		case "̹��/սʿ":
			ArrayList<Hero_tf> list_tf = xmlHeroParsing(mark);
			for (int j = 0; j < list_tf.size(); j++) {
				if (list_tf.get(j).getName().equals(heroname)) {
					Element hero = (Element) root.elements(mark).get(j);
					// �����ڵ�

					Element id = hero.element("id");
					Element name = hero.element("name");
					Element job = hero.element("job");
					Element level = hero.element("level");
					Element red = hero.element("red");
					Element blue = hero.element("blue");
					Element shoot = hero.element("makedamage");
					Element damage = hero.element("damage");
					/**
					 * �����ӽڵ�skill
					 */
					Element skill = hero.element("skill");
					Element n = skill.element("n");
					Element i = skill.element("i");
					Element ii = skill.element("ii");
					Element iii = skill.element("iii");
					// set
					int l = Integer.parseInt(s[0]);
					// "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
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
	 * ���Իظ�
	 * 
	 * @param id
	 *            �ظ�id
	 * @param s
	 *            �ظ�����
	 * @return �ظ����
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlMsgReply(String id, String s) throws DocumentException, IOException {
		Document doc = doc(mfile);// �õ��ĵ�
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
				msgreply.setText("�ظ�ʱ�䣺" + time + " �ظ����ݣ�" + "\n" + s);
				out(doc, mfile);
				System.out.println("�ظ��ɹ���");
				return flag = true;
			}
		}
		System.out.println("û�и�Ӣ�۵����Լ�¼,�ظ�ʧ�ܣ�");
		return flag;
	}

	/**
	 * ��ʾ�ظ�
	 * 
	 * @throws DocumentException
	 */
	public boolean xmlMsgShowReMessage(String id) throws DocumentException {
		Document doc = doc(mfile);// �õ��ĵ�
		boolean flag = false;// �����ж��Ƿ��й���Ա�ظ�
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		if (rootcount.size() == 0)// �������԰���յ����
		{
			System.out.println("���԰�ܿտ���û���»ظ�Ŷ��");
			return false;
		}
		List<Message> message = xmlMsgParsing();
		String s = message.get(0).getMsgreply();
		// 1108��������ʾ�������Է���
		for (int i = 0; i < rootcount.size(); i++) {
			if (message.get(i).getId().equals(id) && !(message.get(i).getMsgreply().equals(""))) {// �½�xml�ļ�������������Ϊ
																									// ""
				System.out.println("����Ա�ظ���\n" + message.get(i).getMsgreply());
				flag = true;
			}
		}
		// �˷���������ʾȫ��ID�����ݣ�������Ա�ظ������ڵ�һ�������԰幦�ܲ�û��ʵ�ֶ����ظ�
		// for(Message x :message){if(x.getId().equals(id)){
		// if(x.getMsgreply()!=null){ System.out.println("����Ա�ظ���\n" +
		// x.getMsgreply());
		// flag=true;}
		// }
		// }
		if (!flag) {
			System.out.println("û���»ظ�Ŷ��");
		}
		return flag;
	}

	/**
	 * ��ʾ����
	 * 
	 * @throws DocumentException
	 */
	public void xmlMsgShowMessage() throws DocumentException {
		Document doc = doc(mfile);// �õ��ĵ�
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		if (rootcount.size() == 0) {
			System.out.println("û��������Ŷ��");
		} else {
			for (Message x : message) {
				System.out.println("Ӣ��ID:" + x.getId() + "����������" + "\n" + x.getMessage());
			}
		}
	}

	/**
	 * ��ʾ������ϸ��Ϣ
	 * 
	 * @throws DocumentException
	 */
	public void xmlMsgShowAll() throws DocumentException {
		Document doc = doc(mfile);// �õ��ĵ�
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Message> message = xmlMsgParsing();
		for (Message x : message) {
			System.out.println(x);
		}
	}

	/**
	 * ͨ��Id���������̶�����ID
	 * 
	 * @param id
	 *            ��������ID
	 * @return �̶�����Name�Ա����
	 * @throws DocumentException
	 */
	public String xmlViaIdorNameReturnName(String id) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");// 1110���ģ������ɫ����
		}

		return null;
	}

	/**
	 * ͨ��Id��������
	 * 
	 * @param id
	 *            ID
	 * @return �̶�����Name�Ա����
	 * @throws DocumentException
	 */
	public String xmlViaIdGetName(String id) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return null;
	}

	/**
	 * ͨ����������ID
	 * 
	 * @param name
	 *            ����
	 * @return �̶�����ID�Ա����
	 * @throws DocumentException
	 */
	public String xmlViaNameGetId(String name) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return null;
	}

	/**
	 * ͨ����������ְҵ
	 * 
	 * @param name
	 *            ����
	 * @return ְҵ
	 * @throws DocumentException
	 */
	public String xmlViaNameGetJob(String name) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("������󣡷���null");
		}

		return null;
	}

	/**
	 * Ӣ���������
	 * 
	 * @param id
	 *            Ӣ��id
	 * @param m
	 *            ��������
	 * @return �Ƿ�ɹ�
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
		// ��ֵ
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		String name = xmlViaIdGetName(id);
		idm.setText(id);
		msg.setText("����:" + name + " ʱ�䣺" + time + " ���ݣ�" + "||>" + m + "<||");
		root.add(message);
		message.add(idm);
		message.add(msg);
		message.add(msgreply);
		out(doc, mfile);
		return true;
	}

	/**
	 * ���԰���� ��hero.xml��id�������
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
	 * Ӣ�۽���
	 * 
	 * @param herojob
	 *            ����ְҵ
	 * @return ��Ӧְҵ�������
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList xmlHeroParsing(String herojob) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
		Element root = doc.getRootElement();// �������ڵ�
		List rootcount = root.elements(herojob);
		switch (herojob) {
		case "st":
			ArrayList<Hero_st> list_st = new ArrayList<Hero_st>();
			for (int j = 0; j < rootcount.size(); j++) {
				Element hero = (Element) root.elements(herojob).get(j);
				// �����ڵ�
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element shoot = hero.element("shoot");
				Element damage = hero.element("damage");
				/**
				 * �����ӽڵ�skill
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
				// �����ڵ�
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element displacement = hero.element("displacement");
				Element damage = hero.element("damage");
				/**
				 * �����ӽڵ�skill
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
				// �����ڵ�
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element useblue = hero.element("useblue");
				Element damage = hero.element("damage");
				/**
				 * �����ӽڵ�skill
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
				// �����ڵ�
				Element id = hero.element("id");
				Element name = hero.element("name");
				Element job = hero.element("job");
				Element level = hero.element("level");
				Element red = hero.element("red");
				Element blue = hero.element("blue");
				Element makedamage = hero.element("makedamage");
				Element damage = hero.element("damage");
				/**
				 * �����ӽڵ�skill
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
	 * ��ʾȫ��Ӣ����Ϣ
	 * 
	 * @param doc
	 *            �õ��ĵ�
	 * @throws DocumentException
	 */
	public void xmlShowAllHeroInfo() throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
	 * ��ְҵ��ѯ
	 * 
	 * @param hero
	 *            ����ְҵ
	 * @throws DocumentException
	 */
	public boolean xmlHeroSerch_byJob(String hero) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
		boolean flag = false;
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Hero_ck> cklist = xmlHeroParsing("ck");
		List<Hero_fs> fslist = xmlHeroParsing("fs");
		List<Hero_st> stlist = xmlHeroParsing("st");
		List<Hero_tf> tflist = xmlHeroParsing("tf");
		switch (hero) {
		case "�̿�":
			for (Hero_ck x : cklist) {
				System.out.println(x);
			}

			return true;
		case "��ʦ":
			for (Hero_fs x : fslist) {
				System.out.println(x);
			}

			return true;
		case "����":
			for (Hero_st x : stlist) {
				System.out.println(x);
			}

			return true;
		case "̹��/սʿ":
			for (Hero_tf x : tflist) {
				System.out.println(x);
			}
			return true;

		default:
			System.out.println("��������");
			return flag;
		}

	}

	/**
	 * ����������Ӣ������
	 * 
	 * @param hero
	 *            ����
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroChangeByName(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// �õ��ĵ�
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
					System.out.println("��������ְҵ��|����|�̿�|��ʦ|̹��/սʿ|");
					Scanner in = new Scanner(System.in);
					String newjob = in.next();
					if (!new Judge().judge(newjob)) {

						System.out.println("ְҵ�������!!!\n");
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
			System.out.println("�������");
		}

		return false;
	}

	/**
	 * ͨ����������Idɾ��Ӣ��
	 * 
	 * @param hero
	 *            ��������ID
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelect(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return false;

	}

	/**
	 * ͨ��Idɾ��Ӣ��
	 * 
	 * @param hero
	 *            ID
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelectByID(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return false;

	}

	/**
	 * ������ ɾ������
	 * 
	 * @param hero
	 *            ����
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroDelectByName(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return false;

	}

	/**
	 * �����������ID�Ƿ����
	 * 
	 * @param hero
	 *            ��������ID
	 * @return �Ƿ����
	 * @throws DocumentException
	 */
	public boolean xmlHeroIdOrNameExists(String hero) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("--------������󣡣���--------");
		}

		return false;
	}

	/**
	 * ����������id��ѯӢ����Ϣ
	 * 
	 * @param hero
	 *            ����������Ӣ����������ID
	 * @param flag
	 *            �ҵ����
	 * @return
	 * @throws DocumentException
	 */
	public boolean xmlHeroSerch_byIDorName(String hero) throws DocumentException {
		Document doc = doc(xfile);// �õ��ĵ�
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
			System.out.println("�������");
		}

		return false;
	}

	/**
	 * δʵ�ַ���
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
		// ��ֵ
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
		// ���
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
	 * δʵ�ַ���
	 */
	public void getInfo() {
		System.out.println("����������,�ȼ���");

	}

	/**
	 * ID��ʽ�� ����ϵͳʱ������ΨһID
	 * 
	 * @param id
	 *            ���ò��� ���������������д����� ֱ�ӽ�������¸�ֵ //�ɷ����Ĳ�������ǰhero.xml��Ӣ������
	 * @return
	 */
	public String idFormat(int id) {
		id = -(int) System.currentTimeMillis();// ��ʱ��ϵͳʱ����ΪID
		char[] cr = new char[] { 0, 0, 0 };
		String str = null;
		String s = id + "";// ++id�ȼӺ�ʹ��
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
		// System.out.println("�Ҳ���IDĬ����Ϊnull");
		// return null;
		// }
		// return str;
	}

	/**
	 * ��ְҵ���Ӣ��
	 * 
	 * @param addjob
	 *            ѡ��ְҵ
	 * @return �Ƿ�ɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroRestor(String hero) throws DocumentException, IOException {
		Document doc = doc(xfile);// �õ��ĵ�
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
					System.out.println("������:�ȼ�������ֵ��������λ�ƣ�һ���ܣ������ܣ�������");
					String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "λ��", "һ����", "������", "������"
							/* 0 1 2 3 4 5 6 */
					};
					String[] sck = new String[7];
					String d = null;
					Scanner in = new Scanner(System.in);
					for (int j = 0; j < 7; j++) {
						System.out.println("�����룺" + inck[j]);
						if (j == 3) {
							for (;;) {
								System.out.println("λ�Ʒ�Χ500-1000");

								String dpl = in.next();
								int dplint = Integer.parseInt(dpl);

								if (dplint >= 500 && dplint <= 1000) {
									d = dpl;
									continue;
								} else {
									d = "0";
									System.out.println("�������λ�Ʒ�Χ500-1000ϵͳĬ��Ϊ0");
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
					System.out.println("������:�ȼ�������ֵ��������λ�ƣ�һ���ܣ������ܣ�������");
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
					System.out.println("������:�ȼ�������ֵ��������λ�ƣ�һ���ܣ������ܣ�������");
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
					System.out.println("������:�ȼ�������ֵ�������������һ���ܣ������ܣ�������");
					String[] inck = new String[] { "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
							/* 0 1 2 3 4 5 6 */
					};
					String[] s = new String[7];
					for (int j = 0; j < 7; j++) {
						System.out.println("�����룺" + inck[j]);
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
			System.out.println("�������");
		}

		return false;
	}

	/**
	 * ����ĳӢ��ְҵ��ͨ����ȡӢ��ԭID Name ��Ϣ���������浽��ʱ�ռ� Ȼ��remove��Ӣ�۵���Add�����½���ְҵӢ�� ����ID name
	 * setText()��ʱ����ԭ������Ϣ��ֵ
	 * 
	 * @param addjob
	 *            ʹ�����������ְҵ
	 * @param oldid
	 *            ԭid
	 * @param oldname
	 *            ԭ����
	 * @return �Ƿ����ְҵ�ɹ�
	 * @throws IOException
	 * @throws DocumentException
	 */
	public boolean xmlHeroAddNewJob(String addjob, String oldid, String oldname) throws IOException, DocumentException {
		// int id = getHeroSizeforAddID();// int id;
		String idformat = idFormat(getHeroSizeforAddID());// ��ȡid����ʽ��
		Document doc = doc(xfile);
		Element root = doc.getRootElement();
		String d = null;
		switch (addjob) {
		case "����":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] ins = new String[] { "", "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					// 0 1 2 3 4 5 6 7
			};
			String[] s = new String[8];
			for (int i = 1; i < 8; i++) {
				System.out.println("�����룺" + ins[i]);
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
			// ��ֵ
			int l = Integer.parseInt(s[1]);

			stid.setText(oldid); // ����ְҵ ����id����
			stname.setText(oldname); // ����ְҵ ����id����
			stjob.setText("����"); // 3
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

			// ���
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
		case "�̿�":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ��������λ�ƣ�һ���ܣ������ܣ�������");
			String[] inck = new String[] { "����", "�ȼ�", "����ֵ", "����", "λ��", "һ����", "������", "������"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[8];
			Scanner in = new Scanner(System.in);
			for (int i = 1; i < 8; i++) {
				System.out.println("�����룺" + inck[i]);// ��ȥ����
				if (i == 4) {
					System.out.println("λ�Ʒ�Χ500-1000");

					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
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
			// double d1 = Math.random() * 500 + 500;//���λ�Ʒ�Χ����-δ��ʹ��
			ckid.setText(oldid); // 1
			ckname.setText(oldname); // 2
			ckjob.setText("�̿�"); // 3
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
		case "��ʦ":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] infs = new String[] { "����", "�ȼ�", "����ֵ", "����", "һ����", "������", "������"
					// 0 1 2 3 4 5 6
			};
			String[] sfs = new String[7];
			for (int i = 1; i < 7; i++) {
				System.out.println("�����룺" + infs[i]);
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
			// ��ֵ
			int fsl = Integer.parseInt(sfs[1]);
			// double d=Math.random()*500+500;
			fsid.setText(oldid); // 1
			fsname.setText(oldname); // 2
			fsjob.setText("��ʦ"); // 3
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
		case "̹��/սʿ":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] intf = new String[] { "����", "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					// 0 1 2 3 4 5 6 7
			};
			String[] stf = new String[8];
			for (int i = 1; i < 8; i++) {
				System.out.println("�����룺" + intf[i]);
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
			// ��ֵ
			int tfl = Integer.parseInt(stf[1]);
			// double d=Math.random()*500+500;
			tfid.setText(oldid); // 1
			tfname.setText(oldname); // 2
			tfjob.setText("̹��/սʿ"); // 3
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
			System.out.println("�밴���¸�ʽ����" + "\n|����|�̿�|��ʦ|̹��/սʿ|");
			break;
		}

		return false;
	}

	/**
	 * ��ְҵ���
	 * 
	 * @param addjob
	 *            ʹ���������ְҵ
	 * @return �Ƿ���ӳɹ�
	 * @throws DocumentException
	 * @throws IOException
	 */
	public boolean xmlHeroAdd(String addjob) throws DocumentException, IOException {
		// int id = getHeroSizeforAddID();// int id;
		String idformat = idFormat(getHeroSizeforAddID());// ��ȡid����ʽ��//δ�õ��ѱ�ϵͳʱ�䷨���
		Document doc = doc(xfile);
		Element root = doc.getRootElement();
		String d = null;
		switch (addjob) {
		case "����":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] ins = new String[] { "����", "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					// 0 1 2 3 4 5 6 7
			};
			String[] s = new String[8];
			for (int i = 0; i < 8; i++) {//��������
				System.out.println("�����룺" + ins[i]);
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
			// ��ֵ
			int l = Integer.parseInt(s[1]);

			stid.setText(idformat); // 1
			stname.setText(s[0]); // 2
			stjob.setText("����"); // 3
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

			// ���
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
		case "�̿�":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ��������λ�ƣ�һ���ܣ������ܣ�������");
			String[] inck = new String[] { "����", "�ȼ�", "����ֵ", "����", "λ��", "һ����", "������", "������"
					/* 0 1 2 3 4 5 6 7 */
			};
			String[] sck = new String[8];
			InputStream input = System.in;
			for (int i = 0; i < 8; i++) {
				System.out.println("�����룺" + inck[i]);
				if (i == 4) {
					System.out.println("λ�Ʒ�Χ500-1000");
					Scanner in = new Scanner(System.in);
					String dpl = in.next();
					int dplint = Integer.parseInt(dpl);
					if (dplint >= 500 && dplint <= 1000) {
						d = dpl;
						continue;
					} else {
						d = "0";
						System.out.println("�������λ�Ʒ�Χ500-1000ϵͳĬ��Ϊ0");
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
			ckjob.setText("�̿�"); // 3
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
		case "��ʦ":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] infs = new String[] { "����", "�ȼ�", "����ֵ", "����", "һ����", "������", "������"
					// 0 1 2 3 4 5 6
			};
			String[] sfs = new String[7];
			for (int i = 0; i < 7; i++) {
				System.out.println("�����룺" + infs[i]);
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
			// ��ֵ
			int fsl = Integer.parseInt(sfs[1]);
			// double d=Math.random()*500+500;
			fsid.setText(idformat); // 1
			fsname.setText(sfs[0]); // 2
			fsjob.setText("��ʦ"); // 3
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
		case "̹��/սʿ":
			/*
			 * Hero_st x=new Hero_st( id, name, job, n, i, ii, iii, level, red,
			 * blue, shoot, damage)
			 */
			System.out.println("�������������ȼ�������ֵ����������̣�һ���ܣ������ܣ�������");
			String[] intf = new String[] { "����", "�ȼ�", "����ֵ", "����", "���", "һ����", "������", "������"
					// 0 1 2 3 4 5 6 7
			};
			String[] stf = new String[8];
			for (int i = 0; i < 8; i++) {
				System.out.println("�����룺" + intf[i]);
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
			// ��ֵ
			int tfl = Integer.parseInt(stf[1]);
			// double d=Math.random()*500+500;
			tfid.setText(idformat); // 1
			tfname.setText(stf[0]); // 2
			tfjob.setText("̹��/սʿ"); // 3
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
			System.out.println("�밴���¸�ʽ����" + "\n|����|�̿�|��ʦ|̹��/սʿ|");
			break;
		}

		return false;

	}

	/**
	 * ����Ա�û����������
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
	 * ����Ա�û�����֤
	 * 
	 * @param doadm
	 *            ������û���
	 * @return �Ƿ����
	 * @throws DocumentException
	 */
	// ����T��ԃ
	public boolean xmlAdminExists(String doadm) throws DocumentException {
		Document doc = doc(afile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Admin> adminlist = xmlAdminParsing();// ������herolist List����XmlHero��
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
	 * ������
	 * 
	 * @param ID
	 *            �û���
	 * @param pwd
	 *            ����
	 * @return �Ƿ�ƥ��
	 * @throws DocumentException
	 */
	// ����T�ܴa�z��
	public boolean xmlAdminCheckPwd(String ID, String pwd) throws DocumentException {
		Document doc = doc(afile);
		Element root = doc.getRootElement();
		List rootcount = root.elements();
		List<Admin> adminlist = xmlAdminParsing();// ������herolist List����XmlHero��
		// ArrayList<Admin> list = new ArrayList<Admin>();
		for (int i = 0; i < rootcount.size(); i++) {
			Element admin = (Element) root.elements("admin").get(i);
			Element id = admin.element("id");
			Element password = admin.element("password");
			if (adminlist.get(i).getId().equals(ID) && adminlist.get(i).getPassword().equals(pwd)) {
				 System.out.println("��¼�ɹ���");
				return true;
			}
		}
		return false;
	}

}
