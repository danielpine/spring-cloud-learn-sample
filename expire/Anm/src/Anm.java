import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Anm {
	public static void main(String[] args) {
		dog a = new dog("ss", 2, 22.2, "中国");
		cat b = new cat("aa", 3, 33.3, "美国");
		Entry<animal> cat = new Entry<animal>();
		System.out.println(a.name + '-' + a.age + '-' + a.height + '-' + a.country);
		System.out.println(b.getName());
		cat.add("daxiang");
		cat.delect();
	}
}

// 创建泛型类
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

	// 添加方法
	public void add() {
	}

	// 删除方法
	public void delect() {
	}

	// 按位删除方法
	public void addSpecified() {
	}
}

// 创建动物实体类 ，父类
class animal {
	// 成员变量
	static String name;
	static int age;
	static double height;
	static String country;

	// 成员方法
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

// 创建容器
class collection {

}
