package com.cyl.java8.funInterface;

import java.util.function.Supplier;

/**
 * 关于java.util.Supplier接口的示例程序
 * @author YanLong Chen
 *
 */
//使用Supplier模拟实现python中的range 函数.
class MyRange implements Supplier<Integer>{
	int max;
	int current;
	public MyRange(int max){
		this.max = max;
		this.current = 0;
	}
	
	@Override
	public Integer get() {
		if(current < max)
			return current++;
		return null;
	}
	
}
public class SupplierDemo {
	static int i = 0;
	
	static void demo1(){
		Supplier<Integer> supplier = () -> ++i;
		System.out.println(supplier.get());
		System.out.println(supplier.get());
		System.out.println(supplier.get());
	}
	
	static void demo2(){
		MyRange range = new MyRange(5);
		Integer value = null;
		while((value = range.get()) != null)
			System.out.println("第 " + value + "次.");
	}
	public static void main(String[] args){
		demo1();
		demo2();
	}
	
}	
