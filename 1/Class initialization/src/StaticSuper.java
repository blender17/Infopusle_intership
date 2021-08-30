public class StaticSuper {

	static int x = getStaticValue("taticSuper var block", 20); //1

	{
		System.out.println("Superclass block 1"); //2
	}


	int b = getValue("Init variable in StaticSuper class", 10);

	{
		System.out.println("Superclass block 2"); //2
	}

	static {
		System.out.println("Superclass static block 1"); //2
	}

	static {
		System.out.println("Superclass static block 2"); //2
	}

	StaticSuper() {
		System.out.println("superclass"); //6
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
