import java.lang.reflect.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

		Class genericClass = GenericClass.class;

		System.out.println("Class: " + genericClass.getName());

		System.out.println("Methods: ");
		Method[] methods = genericClass.getDeclaredMethods();
		Arrays.stream(methods).forEach(method -> {
			System.out.print(method.getName() + " params: ");
			Arrays.stream(method.getParameterTypes()).forEach(System.out::print);
			System.out.println(" return: " + method.getReturnType());
		});

		System.out.println("Constructors: ");
		Constructor[] constructors = genericClass.getDeclaredConstructors();
		Arrays.stream(constructors).forEach(constructor -> {
			System.out.print(constructor.getModifiers() == Modifier.PRIVATE ? "private " : "");
			System.out.print(constructor.getName() + " ");
			Arrays.stream(constructor.getParameterTypes()).forEach(System.out::print);
			System.out.println();
		});

		System.out.println("Fields: ");
		Arrays.stream(genericClass.getDeclaredFields()).forEach(field -> {
			System.out.print(field.getName() + " " + field.getType() + ", ");
		});

		System.out.println("\n");

		Class genericNumberClass = GenericNumberClass.class;

		System.out.println("Class: " + genericNumberClass.getName());
		System.out.println("Superclass: " + genericNumberClass.getSuperclass().getName());

		System.out.println("Methods: ");
		Method[] methods1 = genericNumberClass.getDeclaredMethods();
		Arrays.stream(methods1).forEach(method -> {
			System.out.print(method.getName() + " params: ");
			Arrays.stream(method.getParameterTypes()).forEach(p -> System.out.print(p + ", "));
			System.out.println(" return: " + method.getReturnType());
		});

		System.out.println("Constructors: ");
		Constructor[] constructors1 = genericNumberClass.getDeclaredConstructors();
		Arrays.stream(constructors1).forEach(constructor -> {
			System.out.print(constructor.getModifiers() == Modifier.PRIVATE ? "private " : "");
			System.out.print(constructor.getName() + " ");
			Arrays.stream(constructor.getParameterTypes()).forEach(p -> System.out.print(p + ", "));
			System.out.println();
		});

		System.out.println("Fields: ");
		Arrays.stream(genericNumberClass.getDeclaredFields()).forEach(field -> {
			System.out.print(field.getName() + " " + field.getType() + ", ");
		});

		System.out.println("\n");

		Constructor<GenericNumberClass> constructor = genericNumberClass.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		GenericNumberClass numberClass = constructor.newInstance();

		Field field = numberClass.getClass().getDeclaredField("y");
		field.setAccessible(true);
		System.out.println(field.get(numberClass));
		field.set(numberClass, 10);
		System.out.println(field.get(numberClass));

	}

}
