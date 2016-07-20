package com.cyl.java8.funInterface;

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
 * 通过java8 提供的一些函数式接口实现高阶函数.
 * @author Yanlong Chen
 */
public class HighLevelFunc {
	
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
	 * 借助BiFunction实现reduce.
	 * BiFunction是接收两个参数的Function<T, R>
	 * @param input
	 * @param fun
	 * @return
	 */
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
	
	
	
	public static void main(String[] args){
		
	}
	
}
