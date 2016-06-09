package com.threadcodes;

class ThreadImplement implements Runnable{

	int c=0;
	int flag=0;
	void increment(){
		synchronized(this){
			c++;
		
		System.out.println(Thread.currentThread().getName()+": "+c);
		}
	}
	
	void decrement(){
		synchronized(this){
			c--;
		
		System.out.println(Thread.currentThread().getName()+": "+c);
		}
	}
	public void run(){
		while (flag==0){
			if (Thread.currentThread().getName().equals("T1")){
				increment();
			}
			if (Thread.currentThread().getName().equals("T2"))
				decrement();
		}
	}

}


public class ThreadProg{
	public static void main(String[] args){
		ThreadImplement ti1=new ThreadImplement();
		ThreadImplement ti2=new ThreadImplement();
		Thread t1 = new Thread(ti1);
		Thread t2 = new Thread(ti1);
		t1.setName("T1");t2.setName("T2");
		long starttime = System.currentTimeMillis();
		t1.start();
		t2.start();
		while ((System.currentTimeMillis() - starttime)<250);
		ti1.flag=1;
	}
}