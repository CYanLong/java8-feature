package com.cyl.java8.funInterface;

import java.util.function.Supplier;

/**
 * ����java.util.Supplier�ӿڵ�ʾ������
 * @author YanLong Chen
 *
 */
//ʹ��Supplierģ��ʵ��python�е�range ����.
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
			System.out.println("�� " + value + "��.");
	}
	public static void main(String[] args){
		demo1();
		demo2();
	}
	
}	
