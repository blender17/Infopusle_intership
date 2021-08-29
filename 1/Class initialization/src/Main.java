public class Main {

	public static void main(String[] args) {

		StaticBlock staticBlock = new StaticBlock();

		B b = new B();
		B a = new B("str");


		A ax = new B();
		System.out.println("X = " + ax.x);

		a.b();

	}

}
