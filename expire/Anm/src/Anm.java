import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Anm {
	public static void main(String[] args) {
		dog a = new dog("ss", 2, 22.2, "�й�");
		cat b = new cat("aa", 3, 33.3, "����");
		Entry<animal> cat = new Entry<animal>();
		System.out.println(a.name + '-' + a.age + '-' + a.height + '-' + a.country);
		System.out.println(b.getName());
		cat.add("daxiang");
		cat.delect();
	}
}

// ����������
class Entry<T> extends List {
	private T t;

	public Entry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entry(T t) {
		super();
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	// ��ӷ���
	public void add() {
	}

	// ɾ������
	public void delect() {
	}

	// ��λɾ������
	public void addSpecified() {
	}
}

// ��������ʵ���� ������
class animal {
	// ��Ա����
	static String name;
	static int age;
	static double height;
	static String country;

	// ��Ա����
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public animal(String name, int age, double height, String country) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Entry [name=" + name + ", age=" + age + ", height=" + height + ", country=" + country + "]";
	}
}

class cat extends animal {

	public cat(String name, int age, double height, String country) {
		super(name, age, height, country);
		// TODO Auto-generated constructor stub
	}
	// super.animal();
}

class dog extends animal {
	public dog(String name, int age, double height, String country) {
		super(name, age, height, country);
		// TODO Auto-generated constructor stub
	}
}

// ��������
class collection {

}
