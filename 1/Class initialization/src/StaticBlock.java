public class StaticBlock extends StaticSuper{

	static int y = 10; //3

	int z = 11; //5

	static {
		System.out.println("Static block"); //4
	}

	StaticBlock() {
		System.out.println("subclass"); //7
	}

}
