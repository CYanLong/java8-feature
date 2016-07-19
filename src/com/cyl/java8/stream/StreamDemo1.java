package com.cyl.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Yanlong Chen
 */
public class StreamDemo1 {
	
	/**
	 * 下面的java.util.function.Function<T, R>接口是一个FunctionalInterface.
	 * 这个函数接口代表着一个函数：
	 * 		通过调用  R apply(T t) 接收一个参数并且返回一个结果.
	 *  
	 * @param input
	 * @param processor
	 * @return
	 */
	static <T> List<T> map(List<T> input, Function<T, T> processor){
		List<T> result = new ArrayList<T>();
		for(T obj : input){
			result.add(processor.apply(obj));
		}
		return result;
	}
		
	/**
	 * 下面的java.util.function.Predicate<T> 接口同样是一个函数式接口.
	 *  此函数实现对参数对象的逻辑断定。通常用来实现过滤元素。(filter高阶函数)
	 *  
	 * @param input
	 * @param pre
	 * @return
	 */
	static <T> Collection<T> filter(Collection<T> input, Predicate<T> pre){
		ArrayList<T> result = new ArrayList<T>();
		for(T obj : input){
			if(pre.test(obj))
				result.add(obj);
		}
		return result;
	}
	

	/** 
	 * Consumer函数式接口用来接收单个参数并执行一些操作(accpet 函数)，并且没有返回值.
	 * @param input
	 * @param c
	 */
	static <T> void forEach(List<T> input, Consumer<T> c){
		for(T i : input){
			c.accept(i);
		}
	}
	
	static <T> T reduce(List<T> input, BiFunction<T, T, T> fun){
		
		//尝试取第一个.
		T first = input.isEmpty() ? null : input.get(0);
		T second = null; 
		
		for(int i = 1; first != null && i < input.size() ; i++){
			second = input.get(i);
			first = fun.apply(first, second);
		}	
		return first;
	}
	
	public static void main(String[] args){
		List<Integer> nums = Arrays.asList(1, 2);
		int value = reduce(nums, (ar1, ar2) -> ar1 + ar2);	
		System.out.println(value);
	}
	
	public static void testMap(){
		List<Integer> input = Arrays.asList(1, 2, 3);
		List<Integer> inputMutiple = map(input, v -> v * v);
		
		List<String> input2 = Arrays.asList("apple", "orange", "pear");
		List<String> upperInput2 = map(input2, v -> v.toUpperCase());
	}

	private static void test1() {
	}
	
	/*public static void main(String[] args){
		List<Integer> numbers = Arrays.asList();
		Stream<Integer> stream = numbers.stream();
		//BinaryOperator
		Optional<Integer> op = stream.reduce((x1, x2) -> {return x1 + x2;});
		//System.out.println(op.get());
		//System.out.println(op.isPresent());
		
	}*/
}
