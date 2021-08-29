public class B extends A{

	int x = 2;

	public B() {}

	public B (String str) {
//		super(str);
	}

	void a() {
		System.out.println("B-a");
	}

	void b() {
		System.out.println("B-b");
		super.b();
	}

}
