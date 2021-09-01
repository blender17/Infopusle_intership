package pkg;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {

		ClassLoader classLoader = ClassLoader.getSystemClassLoader();

		UnsafeSingleton singleton1 = (UnsafeSingleton) classLoader.loadClass("pkg.UnsafeSingleton").getMethod("getInstance").invoke(null);
		System.out.println(singleton1);

		File file = new File("D:\\Blender\\Archive\\.Source\\Java\\Intership\\2\\Singleton\\out\\production\\Singleton\\");
		//convert the file to URL format
		URL url = file.toURI().toURL();
		URL[] urls = new URL[]{url};
		//load this folder into Class loader
		ClassLoader customClassLoader = new URLClassLoader(urls, ClassLoader.getPlatformClassLoader());

		Object singleton2 = customClassLoader.loadClass("pkg.UnsafeSingleton").getMethod("getInstance").invoke(null);
		System.out.println(singleton2);

		System.out.print("UnsafeSingleton same instance - ");
		System.out.println(singleton1 == singleton2);
	}

}
