package com.threadcodes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.*;
public class ClientProg extends ChatUtil{
	
	JFrame frame;
	JTextArea chatHistory;
	JTextField chat;
	int port;
	Socket socket;
	String message;
	
	ClientProg(){
		port=444;
		createUI();
	}

	
	public static void main(String[] args) {
		ClientProg client = new ClientProg();
		
		client.createConnection();
	}
	

	@Override
	public void createUI() {
		chatHistory = new JTextArea(300,300);
		chatHistory.setEditable(false);
		
		chat = new JTextField();
		chat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				message = chat.getText();
				System.out.println("message on actionperformed: "+message);
			}
		});
		frame = new JFrame();
		frame.setTitle("Client");
		frame.setLayout(new GridLayout(2,1));
		frame.add(chatHistory);
		frame.add(chat);
		frame.setSize(350, 350);
		frame.setVisible(true);
				
	}

	Socket createConnection(){
		//Socket socket=null;
		try{
			socket = new Socket("localhost", this.port);

			SendMessage msgSend = new SendMessage(this);//socket, frame, 0);//, null, chat);
			Thread t2 = new Thread(msgSend);
			t2.setName("Client SendMessage");
			t2.start();
			
			ReadMessage msgRead = new ReadMessage(this);//socket, frame, null, chat);
			Thread t1 = new Thread(msgRead);
			t1.setName("Client ReadMessage");
			t1.start();			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return socket;
	}



	@Override
	public String send() {
		
		return null;
	}


	@Override
	public void recieve() {
		
		
	}


	@Override
	public String getText() {
		return this.message;//this.chat.getText();////
	}


	@Override
	public void setText() {
		this.message="";//this.chat.setText("");
		this.chat.setText("");
	}

	
	@Override
	public InputStream getInputStream(){
		try{
			return this.getSocket().getInputStream();
		}catch(IOException e){
			
		}
		return null;
	}


	@Override
	public void processMessage(String msg) {
		this.chatHistory.append(msg+"\n");
	}


	@Override
	public OutputStream getOutputStream() {
		try {
			return this.getSocket().getOutputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Socket getSocket() {
		return this.socket;
	}

}
