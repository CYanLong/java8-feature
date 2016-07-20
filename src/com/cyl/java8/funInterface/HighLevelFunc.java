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
 * ͨ��java8 �ṩ��һЩ����ʽ�ӿ�ʵ�ָ߽׺���.
 * @author Yanlong Chen
 */
public class HighLevelFunc {
	
	/**
	 * �����java.util.function.Function<T, R>�ӿ���һ��FunctionalInterface.
	 * ��������ӿڴ�����һ��������
	 * 		ͨ������  R apply(T t) ����һ���������ҷ���һ�����.
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
	 * �����java.util.function.Predicate<T> �ӿ�ͬ����һ������ʽ�ӿ�.
	 *  �˺���ʵ�ֶԲ���������߼��϶���ͨ������ʵ�ֹ���Ԫ�ء�(filter�߽׺���)
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
	 * ����BiFunctionʵ��reduce.
	 * BiFunction�ǽ�������������Function<T, R>
	 * @param input
	 * @param fun
	 * @return
	 */
	static <T> T reduce(List<T> input, BiFunction<T, T, T> fun){
		
		//����ȡ��һ��.
		T first = input.isEmpty() ? null : input.get(0);
		T second = null; 
		
		for(int i = 1; first != null && i < input.size() ; i++){
			second = input.get(i);
			first = fun.apply(first, second);
		}	
		return first;
	}

	/** 
	 * Consumer����ʽ�ӿ��������յ���������ִ��һЩ����(accpet ����)������û�з���ֵ.
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
