package com.cyl.java8.funInterface;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 关于函数引用的实例.
 * @author Yanlong Chen
 *
 */
public class MethodRefExam {

	//ClassName::instanceMethodName
	static void instanceMethod(){
		Function<String, Integer> func1 = String :: length;
		// 等价于：
		Function<String, Integer> func2 = str -> str.length();
		
		System.out.println(func1.apply("Java 8"));
		System.out.println(func2.apply("Java 8"));
	}
	//object::instanceMethodName
	static void instanceMethod2(){
		Something something = new Something();
		Function<String, String> converter = something :: startsWith;
		
		Function<String, String> converter2 = str -> something.startsWith(str);//匿名内部类访问到了外部函数的对象.
		
		System.out.println(converter.apply("Java"));
		System.out.println(converter2.apply("Java"));
	}
	
	static void staticMethod(){
		Consumer<String> consumer = str -> System.out.println(str);
		consumer.accept("Java");
	}
	
	public static void main(String[] args){
		System.out.println("--instanceMethod--");
		instanceMethod();
		System.out.println("--instanceMethod2--");
		instanceMethod2();
		System.out.println("--staticMethid--");
		staticMethod();
	}
}

class Something{
	String startsWith(String s){
		return String.valueOf(s.charAt(0));
	}
}
