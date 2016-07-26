# java8-feature

###Summary about Java8 new features. &emsp;关于Java8 新特性的总结.
---
Java8 于2014年3月发布，新版本的特性几乎都是围绕着 `函数式编程`。

* 1.lambda表达式和Stream流:使代码更易于编写和有更好的可读性。
* 2.提供了并行流，我们可以更方便的构建出并发程序。
* 3.新的时间处理类库。

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

> **lambda表达式**

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

---
###二：方法引用(Method Reference)：

&emsp;我们使用lambda表达式创建了匿名方法。通常情况下，一个lambda表达式仅仅是调用一个已存在的方法。这时，我们可以通过 `引用 (::)` 这个方法代替显示的调用它。

	String::valueOf ==> x -> String.valueOf(x)
	Object::toString ==>  x.toString()
	x::toString() ==> x.toString()
	ArrayList::new() ==> new ArrayList<>()

### 三：函数式接口(functional interface)：

&emsp;面向对象编程和函数式编程中的基本元素(Basic Values)都是用来封装程序行为的。面向对象编程使用带有方法的对象封装行为，函数式编程使用函数封装行为。

&emsp;在Java中，有很多接口（对象）只是对单个函数的封装。我们把这种只拥有一个抽象方法的接口 `(Single Abstract Method)` 称为函数式接口。比如Java标准类库中的	`java.lang.Runnable` 和 `java.util.Comparator` 都是典型的函数式接口。对于函数式接口，除了可以使用Java中标准的方法来创建实例对象之外，还可以使用lambda来创建实现对象。lambda本质上还是匿名内部类，只是看起来像函数的实现。

---

###四：java.util.function下常用的函数式接口。

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


* `java.util.Consumer<T>`

&emsp;这个函数式接口代表的函数 `void accept(T t)` 对参数t执行一些操作(Operation)，并且没有返回值。我们可以用来实现对集合元素的批量处理。
	
	static <T> forEach(List<T> input, Consumer<T> c){
		for(T i : input){
			c.accept(i);
		}
	}
	#使用forEach打印集合所有的元素.
	List<String> strs = Arrays.asList("Java1", "Java2", "Java3");
	forEach(strs, str -> System.out.println(str));


* `java.util.function.Supplier<T>`

&emsp;这个函数式接口代表的函数 `T get()` 用于产生值。通常，每次调用时产生的值不同。类似于生成器。

---
###五：Stream API

集合API是Java API中最重要，最常用的部分。然而，集合仅仅提供了单一的迭代功能，我们通常要自己去迭代元素。Stream API 提供了更加抽象的处理数据集的接口。使得对数据集的操作更加简洁。

Stream代表着一个元素序列 (sequence of elements) 并且可以在这个序列上执行不同的函数式计算。

	List<String> myList = Arrays.asList("a1", "a2", "b1", "c1", "c2");
	
	myList.stream()
		  .filter(s -> s.startsWith("c"))
		  .map(String::toUpperCase)
		  .sorted()
		  .forEach(System.out::println);

	//输出：
	//C1
	//C2


* 像上面这样对 `Stream` 进行链式操作也叫作 `operation pipeline`。

* 大多数的 `Stream` 操作接受 `lambda表达式` 作为参数(也可以说接受一个函数)，参数不同的函数式接口用来表示不同的函数行为。

* 大多数传入的 `lambda表达式` 都是无状态的 `(stateless)` 和`non-interfering`（ `non-interfering` 表示操作不会修改底层的元素结构，例如删除,添加等）。

* 通常Streams的创建来自于集合`(Collection)`，`List` 和 `Set`支持 `stream()` 和 `parallelStream()`用来创建 `Stream`。
我们也可以通过 `Stream.of()` 来直接创建一个Stream。

		Stream.of("a1", "a2", "a3")
			.findFirst()
			.ifPresent(System.out::println);

* 除了 `Stream` 类之外，还有针对原始类型（`primitive data type`）工作的 Stream：`IntStream`，`LongStream`，`DoubleStream`。 这些Stream额外支持 `sum()` 和 `average()` 等聚合(aggregate)操作。并且，我们可以通过 `mapToXxx`进行原始类型的Stream和普通的Stream间的转换。
	
		Stream.of("a1", "a2", "a3")
			.map(s -> s.substring(1))
			.mapToInt(Integer::parseInt) //return IntStream
			.average()
			.ifPresent(System.out::println);



* `IntStreams` 提供的 `range(1, 4)` 能代替普通的 `for-loop` 循环。


> **intermediate operation / terminal operation**

&emsp;像 filter, sorted 和 map 这样可以被连接起来形成一个管道的操作叫做 中间操作。

&emsp;像 collect, forEach 等终止管道并返回数据的操作叫做 结束操作。

* `intermediate operation`的一个重要特点是推迟计算(`laziness`)。

		Stream.of("d2", "a2", "b1", "b3", "c")
			.filter(s -> {
				System.out.println("filter: " + s);
				return true;
			});

&emsp;&emsp;执行上面的代码片段不会有任何输出。因为 `intermediate operation` 将仅仅被执行当遇到一个 `terminal operation`。
	
		Stream.of("d2", "a2")
			.map(s -> {
				System.out.println("map: " + s);
			})
			.filter(s -> {
				System.out.println("filter: " + s);
				return s.startsWith("a");
			})
			.forEach(s -> System.out.println("forEach: " + s));
		
		//输出:
		map: d2

&emsp;&emsp;我们还发现整个执行顺序并不是将所有元素 `map` 之后再 `filter` 再`forEach`，而是以元素来执行整个调用链。 **所以，我们可以将 `filter`写在调用链的最前面来减少其他操作( `map` 等)的执行以提高性能。**

* Stream 不能被重用。一旦你调用了任何 `terminal operation` 这个Stream就被关闭了。

> Collect

&emsp;&emsp;`Collect` 用来将 `Stream` 转换为不同`类型(kind)`的结果，像 `List` , `Set`, `Map`。 `Collect` 接受一个`Collector` ，包含四个不同的操作： `supplier`, `accumulator`, `combiner`, `finisher`。当然，Java8 提供了很多内置(build-in)collectors。
		
		class Person {
    		String name;
    		int age;

    		Person(String name, int age) {
       			this.name = name;
        		this.age = age;
    		}

    		@Override
    		public String toString() {
       			return name;
    		}
		}

		List<Person> persons =
		    Arrays.asList(
		        new Person("Max", 18),
		        new Person("Peter", 23),
		        new Person("Pamela", 23),
		        new Person("David", 12));
		

&emsp;&emsp;上面创建了一个装有 Person 的 List。我们可以通过 stream 来操作这个 list，然后在通过 `collect` 转换为 List。


	List<Person> filter = 
			persons
				.stream()
				.filter(p -> p.name.startsWith("P"))
				.collect(Collectors.toList());


> 我们还可以将一个 List 通过 Stream 操作转换为 Map。

&emsp;&emsp;下面的Collectors.toMap通过接受函数式参数来确定 keys 和 values。并且，我们可以指定一个merge 参数 (BiFunction interface) 来确定当key重复时如何做。
	
	Map<Integer, String> map = 
			persons
				.stream()
				.collect(Collectors.toMap(
					p -> p.age,
					p -> p.name,
					(name1, name2) -> name1 + ";" + name2));



> **parallel Streams**

并行Stream使得我们可以 `声明式` 地编写并发程序。

下面的程序使用 Parallel Stream 构建了利用多线程计算一组数据的方差。

	public static double varianceStreams(double[] population){
		double average = Arrays.stream(population).parallel().average().orElse(0.0);
		double variance = Arrays.stream(population).parallel()
				.map(p -> (p -> average) * (p -> average))
				.sum() / population.length;
		return variance;
	}

我们看到，通过使用Stream API内建的操作，我们以声明式的，而且非常简洁的方式构建了并发程序。