package com.cyl.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author Yanlong Chen
 */
public class LambdaDemo1 {
	
	//在Java8之前,为了实现一个方法的接口,
	//往往需要定义一个匿名类并复写接口方法.
	//代码显得很臃肿.
	private static void pre1() {
		String[] oldWay = "Improving code with Lambda expression in Java8".split(" ");
		Arrays.sort(oldWay, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				//忽略大小写排序
				return s1.toLowerCase().compareTo(s2.toLowerCase());
			}
			
		});
		System.out.println(String.join(",", oldWay));
	}
	
	public static void pre2(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
	}
	
	//对于只有一个方法的接口,在Java8中，现在可以把它视为一个函数
	//用lambda表达式简化.
	//Java8 没有引入新的关键字lambda,而是用 () -> {}
	//() 中放参数声明，并且不需要声明类型.参数类型由接口签名自动推导出来.
	//{} 中实现函数体
	//实际上，lambda表达式最终也被编译为一个实现类，不过语法上做了简化.
	public static void now1(){
		
		String[] newWay = "Imporving code with Lambda expression in Java 8".split(" ");
		Arrays.sort(newWay, (s1, s2) -> {
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		});
		System.out.println(String.join(",", newWay));
		
	}
	
	public static void now2(){
		Runnable newRunnable = () -> {
			System.out.println("");
		};
		new Thread(newRunnable).start();
	}
	
	
	public static void main(String[] args){
		
		pre1();
		now1();
	}
}
