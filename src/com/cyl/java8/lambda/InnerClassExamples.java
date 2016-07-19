package com.cyl.java8.lambda;

/**
 * 
 * @author Yanlong Chen
 *
 */

class InstanceOuter{
	public InstanceOuter(int xx){ x = xx; }
	
	private int x;
	
	class InstanceInner{
		public void printSomething(){
			System.out.println("The value of x in my outer is " + x);
		}
	}
}

class StaticOuter{
	private static int x = 24;
	
	static class StaticInner{
		public void printSomething(){
			System.out.println("The value of x in my outer is " + x);
		}
	}
}

public class InnerClassExamples {
	
	public void test1(){
		class RunnableImpl implements Runnable{
			@Override
			public void run() {
				System.out.println("");				
			}
		}
		RunnableImpl r = new RunnableImpl();
		new Thread(r).start();
	}
	
	public void test2(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
	}
	public static void main(String[] args){
	
		InstanceOuter io = new InstanceOuter(12);
		
		InstanceOuter.InstanceInner ii = io.new InstanceInner();
		
		ii.printSomething(); //12
		
		StaticOuter.StaticInner si = new StaticOuter.StaticInner();
		si.printSomething(); // 24
		
	}
	
	
}
