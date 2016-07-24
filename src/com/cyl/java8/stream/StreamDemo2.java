package com.cyl.java8.stream;

import java.util.stream.Stream;

/**
 * 
 * @author Yanlong Chen
 *
 */
public class StreamDemo2 {
	
	
	static void demo1(){
		//��ִ������Ĵ���ʱ,�������κ����.
		//��ΪfilterΪintermediate operation.
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c")
			.filter(s -> {
				System.out.println("filter:" + s);
				return true;
			});
		System.out.println("===");
		//forEach��terminal operation.	
		stream
		.forEach(s -> System.out.println("forEach:" + s));
	}
	
	/**
	 * anyMatch ֹͣ��Stream�ĵ���������trueֻҪ��һ��Ԫ������predicate.
	 */
	static void demo2(){
		boolean b = Stream.of("d2", "b2", "a1", "b3", "c")
			.map(s -> {
				System.out.println("map: " + s);
				return s.toUpperCase();
			})
			.anyMatch(s -> {
				System.out.println("anyMatch: " + s);
				return s.startsWith("A");
			});
		
	}
	
	static void demo3(){
		Stream.of("d2", "a2", "b1", "b3", "c")
			.map(s -> {
				System.out.println("map: " + s);
				return s.toUpperCase();
			})
			.filter(s -> {
				System.out.println("filter: " + s);
				return s.startsWith("A");
			})
			.forEach(s -> System.out.println("forEach: " + s));
	}
	
	static void demo4(){
		Stream.of("d2", "a2", "b1", "b3", "c")
			.filter(s -> {
				System.out.println("filter: " +s);
				return s.startsWith("a");
			})
			.map(s -> {
				System.out.println("map: " + s);
				return s.toUpperCase();
			})
			.forEach(s -> System.out.println("forEach:" + s));
	}
	//Sorting ��һ�������intermediate ����,
	//Ҳ����stateful�Ĳ���.
	//
	static void demo5(){
		Stream.of("d2", "a2", "b1", "b3", "c")
			.sorted((s1, s2) -> {
				System.out.printf("sort: %s; %s\n", s1, s2);
				return s1.compareTo(s2);
			})
			.filter(s -> {
				System.out.println("filter: " + s);
				return s.startsWith("a");
			})
			.map(s -> {
				System.out.println("map: " +s);
				return s.toUpperCase();
			})
			.forEach(s -> System.out.println("forEach: " + s));
	}
	
	
	public static void main(String[] args){
		//demo1();
		//demo2();
		//demo3();
		//demo4();
		demo5();
	}
	
}
