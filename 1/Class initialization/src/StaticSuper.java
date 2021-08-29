public class StaticSuper {

	static int x = 42; //1

	static {
		System.out.println("Superclass static block"); //2
	}

	StaticSuper() {
		System.out.println("superclass"); //6
	}

}
