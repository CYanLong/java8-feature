# java8-feature

###Summary about Java8 new features. &emsp;关于Java8 新特性的总结.
---
###一:Lambda表达式

在讲解Lambda表达式之前，我们先来复习一下内部类。

> 内部类(**Nested Classes**)

在Java中，内部类分为以下几种：

* 静态内部类(**Static Nested class**) ：
	
		class OuterClass{
			static class InnerClass{
			}
		}
	
		OuterClass.InnerClass oi = new OuterClass.InnerClass();

* 非静态内部类(**No-Static Nested class**)
	
		class OuterClass{	
			class InnerClass{
			}
		}	
		
		OuterClass outObject = new OuterClass();
		OuterClass.InnerClass oi = outObject.new InnerClass();
		//or
		OuterClass.InnerClass oi = new OuterClass().InnerClass();

* 局部内部类(**local inner class**)
	
		public void test1{
			class RunnableImpl implements Runnable{
				public void run(){
				}
			}
			new Thread(new RunnableImpl).start()
		}
	
* 匿名内部类(**anonymous inner class**)
	
		public void test2(){
			new Thread(new Runnable{
			
			}).start()
		}

> lambda表达式

&emsp;我们从上面看到，要实现带一个方法的接口，往往需要定义一个匿名内部类并重写接口方法，代码显得很臃肿。

	#使用匿名内部类.
	String[] oldWay = "Improving code with Lambda expression in Java 8".split(" ")
	Arrays.sort(oldWay, new Comparator<String>{
		@Override
		public int compare(String s1, String s2){
			//忽略大小写排序:
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		}
	})

	#使用Lambda表达式
	String[] oldWay = "Improving code with Lambda expression in Java 8".split(" ")
	Arrays.sort(newWay, (s1, s2) -> {
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	});

&emsp;**Lambda表达式是由参数列表 `()` ，箭头符号 `->` 和函数体组成。函数体既可以是一个表达式，也可以是一个语句块。**

### 二：函数式接口(functional interface)：

&emsp;面向对象编程和函数式编程中的基本元素(Basic Values)都是用来封装程序行为的。面向对象编程使用带有方法的对象封装行为，函数式编程使用函数封装行为。

&emsp;在Java中，有很多接口（对象）只是对单个函数的封装。我们把这种只拥有一个抽象方法的接口 `(Single Abstract Method)` 称为函数式接口。比如Java标准类库中的	`java.lang.Runnable` 和 `java.util.Comparator` 都是典型的函数式接口。对于函数式接口，除了可以使用Java中标准的方法来创建实例对象之外，还可以使用lambda来创建实现对象。lambda本质上还是匿名内部类，只是看起来像函数的实现。

---

###三：java.util.function下常用的函数式接口。

&emsp;Java8 在 `java.util.function` 包下新增了一些函数式接口。用来支持高阶函数。

* `java.util.function.Function<T, R>` 接口:
	
&emsp;这个函数式接口代表的函数 `R apply(T t)` 接收一个参数并返回一个结果。可以用它来实现高阶函数 `map`。 

&emsp;下面借助 `Function<T, R>` 接口实现了满足List的 `map` 高阶函数。

	static <T> List<T> map(List<T> input, Function<T, T> processor){
		List<T> result = new ArrayList<T>();
		for(T obj : input){
			result.add(processor.apply(obj));
		}
		return result;
	}

	#使用：
	List<T> list = Collections.asList(1, 2, 3, 4);
	map(list, (x) -> return x * x);


* `java.util.function.Predicate<T>` 接口：

&emsp;这个函数式接口代表的函数 `boolean test(T t)` 对传入的一个参数对象进行逻辑演算，最终返回boolean值。可以用它来实现高阶函数 `filter`。

	static <T> List<T> filter(List<T> input, Predicate<T> filter){
		List<T> result = new ArrayList<T>();
		for(T obj : input){
			if(filter.test(obj)){
				result.add(obj);
			}
		}
		return result;
	}
	
	#使用：
	List<T> list = Collections.asList(1, 2, 3, 4);
	filter(list, x —> x % 2 == 0) #表达式时无需写return.

* `java.util.function.BiFunction<T, U, R>`
	
&emsp;这个函数式接口代表的函数 `R apply(T t, U u)` 接收两个参数并返回一个值。
可以将它看做是两个参数版本的 `Function`。我们可以通过它来实现 `reduce` 函数。
		
	static <T> T reduce(List<T> input, BiFunction<T, T, T> fun){
		T first = input.isEmpty() ? null : input.get(0);
		T second = null;
		
		for(int i = 1 ; first != null && i < input.size() ; i++){
			second = input.get(i);
			first = fun.apply(first, second);
		}
		return first;
	}




