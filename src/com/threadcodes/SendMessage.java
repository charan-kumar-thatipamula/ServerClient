package com.threadcodes;

import java.io.PrintWriter;

public class SendMessage implements Runnable{
	PrintWriter print;
	ChatUtil type;
	SendMessage(ChatUtil type){//Socket socket, JFrame frame, int clientORServer){
		this.type = type;	
	}

	public void run() {
		if (type.getSocket().isConnected()){
			this.print = new PrintWriter(type.getOutputStream(), true);
			String message=null;
			while ("EXIT"!=message){
				try{
					Thread.currentThread().sleep(2000);
				}catch(InterruptedException e){
					
				}
				message = type.getText();
				if (message!=null && message.length()>0){
					System.out.println("messge to send: ["+message+"]");
					this.print.println(message);
					type.setText();
					this.print.flush();
				}
			}
			this.print.close();
		}
	}
	
	
}
