package com.cyl.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MethodRefExam {
	
	/**
	 * �������ã������ڲ����÷��������������һ��������
	 * �������ֻ���Ǿ�̬������
	 */
	public static void demo1(){
		List<String> list = Arrays.asList("apple", "orange");
		
		Stream<String> stream = list.stream();
		
		Stream<String> newStream = stream.filter(MethodRefExam :: isStartWithA);

		list.forEach(System.out :: println); 
		//stream.forEach(System.out :: println); Streamֻ�ܵ���ʹ�á�
		System.out.println("newStream :");
		newStream.forEach(System.out :: println);
	}
	
	static boolean isStartWithA(String str){
		return str.startsWith("a");
	}
	
	public static void demo2(){
		
	}
	
	public static void main(String[] args){
		demo1();
	}
	
	
	
}
