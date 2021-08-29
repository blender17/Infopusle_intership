public class A {

	int x = 1;

	public A() {
		System.out.println("Class A constructor");
	}

	public A (String str) {
		System.out.println("str = " + str);
	}

	void a() {
		System.out.println("A-a");
	}

	void b() {
		System.out.println("A-b");
		a();
	}

}
