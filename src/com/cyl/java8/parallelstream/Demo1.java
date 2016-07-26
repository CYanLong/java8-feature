package com.cyl.java8.parallelstream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Demo1 {
	
	
	static void demo1(){
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism());
	}
	
	/**
	 * Java8提供的并行流.
	 * 利用所有从ForkJoinPool可获得的线程去执行stream操作.
	 * 
	 */
	static void demo2(){
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
			.parallelStream()
			.filter(s -> {
				System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
				return true;
			})
			.map(s -> {
				System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
				return s.toUpperCase();
			})
			.forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
	}
	
	static void demo3(){
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		})
		.map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		})
		.sorted((s1, s2) -> {
			System.out.format("sort: %s <> %s [%s]\n", s2, s1, Thread.currentThread().getName());
			return s1.compareTo(s2);
		})
		.forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
	}
	
	static void demo4(){
		List<Person> persons = Arrays.asList(
				new Person("Max", 18),
				new Person("Peter", 23),
				new Person("Pamela", 23),
				new Person("David", 12));
		
		persons
			.parallelStream()
			.reduce(0, 
				(sum, p) -> {
					System.out.format("accumulator: sum=%s;person=%s [%s]\n", 
							sum, p, Thread.currentThread().getName());
					return sum += p.age;
				}, 
				(sum1, sum2) -> {
					System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
					return sum1 + sum2;
				});
	}
	
	static void demo5(double[] population){
		double average = Arrays.stream(population).parallel().average().orElse(0.0);
		double variance = Arrays.stream(population).parallel()
				.map(p -> (p - average) * (p - average))
				.sum() / population.length;
		
	}
	public static void main(String[] args){
		//demo1();
		//demo2();
		//demo3();
		//demo4();
		
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

