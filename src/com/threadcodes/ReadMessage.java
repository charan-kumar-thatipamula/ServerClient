package com.threadcodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadMessage implements Runnable{
	ChatUtil type;
	ReadMessage(ChatUtil type){//Socket socket, JFrame frame, JTextArea allChats, JTextField chat){
		this.type = type;
	}

	public void run() {
		String tName = Thread.currentThread().getName();
		System.out.println(tName+": waiting for message");
		BufferedReader br = new BufferedReader(new InputStreamReader(type.getInputStream()));//this.socket.getInputStream()));
		String msgReceived = null;
		while (true){
			try {
				Thread.sleep(3000);
				msgReceived = br.readLine();
				System.out.println(tName+", message recieved: "+msgReceived);
				if (((msgReceived)!=null && msgReceived.length()>0)){
					System.out.println(tName+" Message received"+ msgReceived);
					type.processMessage(msgReceived);
				}//while ((msgReceived = br.readLine())!=null && msgReceived.length()>0);
				
			}catch(IOException e){
				e.printStackTrace();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
