package com.threadcodes;

public class ThreadDemo implements Runnable{
	public void run(){
		String name = Thread.currentThread().getName();
		for (int i=0;i<10;i++){
			try {
				Thread.sleep(1000);				
			} catch (InterruptedException e) {
				
			}
			System.out.println("Thread name: "+name+"  "+(i+1));
		}
	}
	public static void main(String[] args) throws InterruptedException{
		Thread t1 = new Thread(new ThreadDemo());
		Thread t2 = new Thread(new ThreadDemo());
		t1.setName("first Thread");
		t2.setName("Second Thread");
		t1.start();
		t2.start();
	}

}
