package pkg;

public enum EnumSingleton {
	INSTANCE;

	private int num;

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void printNum() {
		System.out.println(num);
	}

}
