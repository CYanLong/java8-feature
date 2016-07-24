package com.cyl.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class StreamDemo4 {

	static void demo1(){
		List<Foo> foos = new ArrayList<>();
		
		IntStream
			.range(1, 4)
			.forEach(i -> foos.add(new Foo("Foo" + i)));
		
		foos.forEach(f -> IntStream
							.range(1, 4)
							.forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));
		
		//flatMap函数返回一个对象的stream.
		foos.stream()
			.flatMap(f ->{
				System.out.println("flatMap...");
				return f.bars.stream();
			})
			.forEach(b -> System.out.println(b.name));
		
	}
	
	static void demo2(){
		IntStream.range(1, 4)
				.mapToObj(i -> new Foo("foo" + i))
				.peek(f -> IntStream.range(1, 4)
						.mapToObj(i -> new Bar("Bar" + i + "<-" + f.name))
						.forEach(f.bars::add))
				.flatMap(f -> f.bars.stream())
				.forEach(b -> System.out.println(b.name));;
	}
	public static void main(String[] args){
		demo1();
	}
	
}
class Foo{
	String name;
	List<Bar> bars = new ArrayList<>();
	
	Foo(String name){
		this.name = name;
	}
}

class Bar{
	String name;
	
	Bar(String name){
		this.name = name;
	}
}
