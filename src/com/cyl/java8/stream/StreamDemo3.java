package com.cyl.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
/**
 * @author Yanlong Chen
 *
 */
public class StreamDemo3 {
	
	static List<Person> persons ;
	static{
		persons = Arrays.asList(new Person("Max", 18),
								new Person("Peter", 23),
								new Person("Pamela", 23));
	}
	
	static void demo1(){
		
		
		List<Person> filted = persons.stream()
									.filter(p -> p.name.startsWith("P"))
									.collect(Collectors.toList());
		System.out.println(filted);
	}
	
	static void demo2(){
		Map<Integer, List<Person>> personsByAge = persons
								.stream()
								.collect(Collectors.groupingBy(p -> p.age));
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));		
	}
	
	static void demo3(){
		Double averageAge = persons
				.stream()
				.collect(Collectors.averagingInt(p -> p.age));
		System.out.println(averageAge);
		
	}
	
	static void demo4(){
		String phrase = persons
						.stream()
						.filter(p -> p.age >= 18)
						.map(p -> p.name) //joining(delimiter, prefix, suffix)
						.collect(Collectors.joining(" and ", "In Germany ", " are of legal age "));
		System.out.println(phrase);
	}
	
	/**
	 * 将toMap(keyMapper, valueMapper,BinaryOperator mergeFunction)
	 */
	static void demo5(){
		Map<Integer, String> map = persons
				.stream()
				.collect(Collectors.toMap(
						p -> {
							System.out.println("keyMap");
							return p.age;
						},
						p -> {
							System.out.println("ValueMap");
							return p.name;
						},
						(name1, name2) -> {
							System.out.println("mergeFunction");
							return name1 + ";" + name2;
						}
						));
		System.out.println(map);
	}
	
	//
	static void demo6(){
		Collector<Person, StringJoiner, String> personNameCollector = 
				Collector.of(() -> new StringJoiner("|"), //supplier  构造一个StringJoiner
					         (j, p) -> {
					        	 System.out.println("accumulator..");
					        	 j.add(p.name.toUpperCase());
					         	},
					         (j1, j2) -> {
					        	 System.out.println("combine");
					        	 return j1.merge(j2);
					         },
					         StringJoiner::toString); //finisher
		String names = persons
				.stream()
				.collect(personNameCollector);
		
		System.out.println(names);
		
	}
	
	
	
	public static void main(String[] args){
		//demo1();
		//demo2();
		//demo3();
		//demo4();
		demo5();
		//demo6();
	}
}

class Person{
	String name;
	int age;
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
