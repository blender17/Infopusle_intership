public class StaticBlock extends StaticSuper{

	public static int y2 = getStaticValue("Init 1 static variable in StaticBlock", 10); //3

	{
		System.out.println("StaticBlock block 1"); //2
	}


	int b = getValue("Init variable in StaticSuper class", 10);

	{
		System.out.println("StaticBlock block 2"); //2
	}
	static {
		System.out.println("Static block 1"); //4
	}


	public static int y1 = getStaticValue("Init 2 static variable in StaticBlock", 10); //

	static {
		System.out.println("Static block 2"); //4
	}
// 3

	StaticBlock() {
		System.out.println("subclass"); //7
	}

	public static  int getStaticValue(String v, int i ){
		System.out.println(v + ": " + i);
		return i;
	}

	public static  int getValue(String v, int i ){
		System.out.println(v + ": " + i);
		return i;
	}

}
