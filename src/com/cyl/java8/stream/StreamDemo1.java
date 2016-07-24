package com.cyl.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * ����java8������Stream API��ʾ������.
 * @author Yanlong Chen
 */
public class StreamDemo1 {
	
	/**
	 * ������������stream�������ڰ�һ�����ϱ��Stream.
	 * Ȼ��ͨ��filter(), map()��ʵ��Stream�ı任.
	 * Stream����һ��forEach()�����ÿ��Ԫ�صĵ���.
	 */
	static void demo1(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Stream<Integer> stream = numbers.stream();
		stream.filter(x -> x % 2 == 0)
			  .map(x -> x * x)
			  .forEach(System.out:: println);
	}
	
	/**
	 * Stream�ṩ��̬����generate ����һ��������stream,
	 * ÿ��Ԫ��ʹ�ô����Supplier��������.
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
	 * ����Ҳ����ֱ�Ӵ���һ��Stream��
	 */
	static void demo4(){
		Stream.of("a1", "a2", "a3")
			.findFirst()
			.ifPresent(System.out::println);
	}
	
	/**
	 * ����ͨ����Stream�⣬�������ԭʼ����(primitive data type)
	 * ������Stream��IntStream, LongStream, DoubleStream
	 * 
	 * ԭʼ���͵�Stream֧�ֶ����sum()��average()�ۺ�(aggregate)����
	 */
	static void demo5(){
		IntStream.range(1, 4)// return IntStream
				.forEach(System.out::println);
		
		IntStream.range(1, 5)
				.map(n -> 2 *n + 1)
				.average()
				.ifPresent(System.out::println); //6.0
		
		//����ͨ��StreamתΪԭʼ���͵�Stream��
		Stream.of("a1", "a2", "a3")
			.map(str -> str.substring(1))
			.mapToInt(s -> Integer.parseInt(s))
			.max()
			.ifPresent(System.out::println);
		
	} 
	
	//����IntStream.range(startIndex, endIndex).
	static void demo6(){
		IntStream intStream = IntStream.range(1, 4);
		//intStream �ȼ��� intStream2.
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