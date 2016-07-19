package com.cyl.java8.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

class NaturalSupplier implements Supplier<Long>{
	long value = 0;
	
	@Override
	public Long get() {
		this.value = this.value + 1;
		return value;
	}
	
	void test(){
		Stream<Long> natural = Stream.generate(new NaturalSupplier());
		natural.map((x) -> {
			return x * x;
		}).limit(10).forEach(System.out::println);;
	}
	
}

public class StreamDemo2 {
	
}
