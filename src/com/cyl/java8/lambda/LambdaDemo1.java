package com.cyl.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author Yanlong Chen
 */
public class LambdaDemo1 {
	
	//��Java8֮ǰ,Ϊ��ʵ��һ�������Ľӿ�,
	//������Ҫ����һ�������ಢ��д�ӿڷ���.
	//�����Եú�ӷ��.
	private static void pre1() {
		String[] oldWay = "Improving code with Lambda expression in Java8".split(" ");
		Arrays.sort(oldWay, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				//���Դ�Сд����
				return s1.toLowerCase().compareTo(s2.toLowerCase());
			}
			
		});
		System.out.println(String.join(",", oldWay));
	}
	
	public static void pre2(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
	}
	
	//����ֻ��һ�������Ľӿ�,��Java8�У����ڿ��԰�����Ϊһ������
	//��lambda���ʽ��.
	//Java8 û�������µĹؼ���lambda,������ () -> {}
	//() �зŲ������������Ҳ���Ҫ��������.���������ɽӿ�ǩ���Զ��Ƶ�����.
	//{} ��ʵ�ֺ�����
	//ʵ���ϣ�lambda���ʽ����Ҳ������Ϊһ��ʵ���࣬�����﷨�����˼�.
	public static void now1(){
		
		String[] newWay = "Imporving code with Lambda expression in Java 8".split(" ");
		Arrays.sort(newWay, (s1, s2) -> {
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		});
		System.out.println(String.join(",", newWay));
		
	}
	
	public static void now2(){
		Runnable newRunnable = () -> {
			System.out.println("");
		};
		new Thread(newRunnable).start();
	}
	
	
	public static void main(String[] args){
		
		pre1();
		now1();
	}
}
