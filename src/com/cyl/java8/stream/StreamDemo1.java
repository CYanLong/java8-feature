package com.cyl.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * 关于java8新增的Stream API的示例代码.
 * @author Yanlong Chen
 */
public class StreamDemo1 {
	
	/**
	 * 集合类新增的stream方法用于把一个集合变成Stream.
	 * 然后通过filter(), map()等实现Stream的变换.
	 * Stream还有一个forEach()来完成每个元素的迭代.
	 */
	static void demo1(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Stream<Integer> stream = numbers.stream();
		stream.filter(x -> x % 2 == 0)
			  .map(x -> x * x)
			  .forEach(System.out:: println);
	}
	
	/**
	 * Stream提供静态方法generate 产生一个无穷大的stream,
	 * 每个元素使用传入的Supplier函数产生.
	 * 
	 */
	static void demo2(){
		Stream<Long> natural = Stream.generate(new NaturalSupplier());
		natural.map(x -> x * x).limit(10).forEach(System.out::println);
	}
	
	/**
	 * 
	 */
	static void demo3(){
		Stream<Long> fib = Stream.generate(new FibonacciSupplier());
		fib.skip(20).limit(10).collect(Collectors.toList());
	}
	
	/**
	 * 我们也可以直接创建一个Stream。
	 */
	static void demo4(){
		Stream.of("a1", "a2", "a3")
			.findFirst()
			.ifPresent(System.out::println);
	}
	
	/**
	 * 除了通常的Stream外，还有针对原始类型(primitive data type)
	 * 工作的Stream，IntStream, LongStream, DoubleStream
	 * 
	 * 原始类型的Stream支持额外的sum()和average()聚合(aggregate)操作
	 */
	static void demo5(){
		IntStream.range(1, 4)// return IntStream
				.forEach(System.out::println);
		
		IntStream.range(1, 5)
				.map(n -> 2 *n + 1)
				.average()
				.ifPresent(System.out::println); //6.0
		
		//将普通的Stream转为原始类型的Stream。
		Stream.of("a1", "a2", "a3")
			.map(str -> str.substring(1))
			.mapToInt(s -> Integer.parseInt(s))
			.max()
			.ifPresent(System.out::println);
		
	} 
	
	//关于IntStream.range(startIndex, endIndex).
	static void demo6(){
		IntStream intStream = IntStream.range(1, 4);
		//intStream 等价于 intStream2.
		IntStream intStream2 = IntStream.of(1, 2, 3);
		intStream.forEach(System.out::println);
		intStream2.forEach(System.out::println);
	}
	
	public static void main(String[] args){
		demo1();
		System.out.println("demo2---");
		demo2();
		System.out.println("demo3---");
		demo3();
		System.out.println("demo5---");
		demo5();
	}
}

class NaturalSupplier implements Supplier<Long>{
	
	long value = 0;
	
	@Override
	public Long get() {
		return this.value++;
	}
}

class FibonacciSupplier implements Supplier<Long>{

	long first = 0;
	long second = 1;
	@Override
	public Long get() {
		long x = first + second;
		first = second;
		second = x;
		return first;
	}
	
}