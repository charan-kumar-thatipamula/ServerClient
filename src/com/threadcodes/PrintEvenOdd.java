package com.threadcodes;

public class PrintEvenOdd {

	public static void main(String[] args) {
		PrintValues p = new PrintValues();
		Thread t1 = new Thread(p, "Thread-1");
		Thread t2 = new Thread(p, "Thread-2");
		t1.start();
		t2.start();
	}

}


class PrintValues implements Runnable{
	int i;
	PrintValues(){
		i=0;
	}
	public void run(){
		try{
		printValues(i);
		}catch(Exception e){
			
		}
	}
	
	void printValues(int k) throws Exception{
		Thread t = Thread.currentThread();
		while (i<20){
			Thread.sleep(5);
			synchronized(this){
				System.out.println(t.getName()+" "+(i++));
			}
		}
	}
}