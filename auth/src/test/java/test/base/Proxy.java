package test.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 模拟实现java动态代理
 * @author lz
 *
 */
public class Proxy {
	
	public static Object newProxyInstance(Class infce, Handler h) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//1.拼接代理类java文件
		String methodStr = "";
		String rt = "\r\n";
		//接口方法
		Method[] methods = infce.getMethods();
		for(Method m : methods) {
			methodStr += "@Override" + rt +
					"public void " + m.getName() + "() {" + rt + 
					"	try {" + rt +
					"		Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
					"		h.invoke(this, md);" + rt +
					"	} catch (Exception e) {e.printStackTrace();}" + rt + 
					"}";
		}
		String src = "package test.base;" + rt +
					"import java.lang.reflect.Method;" + rt +
					"public class $Proxy1 implements " + infce.getName() + "{" + rt + 
					"	public $Proxy1(Handler h) {" + rt + 
					"		this.h = h;" + rt + 
					"	}" + rt +
					"	test.base.Handler h;" + rt + 
						methodStr +
					"}";
		
		//将拼接完成的java文件打印到指定目录
		String fileName = System.getProperty("user.dir") + "/src/test/java/test/base/$Proxy1.java";
		File f = new File(fileName);
		FileWriter fw =	new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//2.动态编译java文件，生成class文件
		JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
		Iterable units = standardFileManager.getJavaFileObjects(fileName);
		CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, units);
		task.call();
		standardFileManager.close();
		
		//3.加载class文件到内存
		URL[] urls = new URL[] {new URL("file:/" + System.getProperty("user.dir") + "/src/test/java")};
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("test.base.$Proxy1");
		return c.getConstructor(Handler.class).newInstance(h);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
