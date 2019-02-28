package fanxing;

public class fanxing {
	public static void main(String[] args) {

		pet p = new pet("HH", 10, "ĸ");
	}

	class Entry<T> {
		private T t;

		public T getT() {
			return t;
		}

		public void setT(T t) {
			this.t = t;
		}
	}

	class pet {
		private String name;
		private int age;
		private String sex;

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

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public pet(String name, int age, String sex) {
			super();
			this.name = name;
			this.age = age;
			this.sex = sex;
		}
	}

}
