package com.demo.elk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OssaoPhotographApplicationTests {
	private static volatile ThreadLocal<String> userID=new ThreadLocal<String>();
	@Test
	void contextLoads() {
	}
	@Test
	void threadDome(){
		Runnable r=new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String name =Thread.currentThread().getName();
				if(name.equals("A")) {
					userID.set("FOXTROT");
				}
				else
					userID.set("charlie");
				//userID.remove();
				System.err.println(name+""+userID.get());
				System.out.println(111111);
				System.err.println(name+""+userID.get());
			}
		};
		Thread A=new Thread(r);
		A.setName("A");
		Thread B=new Thread(r);
		B.setName("B");
		System.out.println("线程开始执行....");
		A.start();
		B.start();
	}
}
