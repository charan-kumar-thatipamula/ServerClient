//This is my first editing of the file
package com.threadcodes;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ServerProg extends ChatUtil implements ActionListener{
	
	JFrame frame;
	JTextArea allChats;
	JTextField toClient;
	int port;
	Socket socket;
	String message;

	ServerProg()throws InterruptedException{
		this.port = 444;
		createUI();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException{
		ServerProg server = new ServerProg();
		
		ServerSocket ss = new ServerSocket(server.port);
		//while (true){
			 server.socket = server.createConnection(ss);
		//}	
	}
	
	
	public void createUI() {
		frame = new JFrame();
		
		allChats = new JTextArea(300,300);
		allChats.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		allChats.setEditable(true);
		
		toClient = new JTextField();
		toClient.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				message = toClient.getText();
				System.out.println("message on actionperformed: "+message);
			}
		});
		frame.setTitle("Server");
		frame.setLayout(new GridLayout(2,1));
		frame.add(allChats);
		frame.add(toClient);
		frame.setSize(400,400);
		frame.setVisible(true);

		//Timer t = new Timer(30000, this);
		//t.start();		
	}

	@SuppressWarnings("resource")
	private Socket createConnection(ServerSocket ss){
		//ServerSocket ss = null;
		Socket socket =null;
		try{
			//ss = new ServerSocket(port);
			socket = ss.accept();
			ReadMessage readMsg = new ReadMessage(this);//socket, frame, allChats, null);
			Thread t1 = new Thread(readMsg);
			t1.setName("Server ReadMessage");
			t1.start();
			
			SendMessage sendMsg = new SendMessage(this);//socket, frame,1);
			Thread t2 = new Thread(sendMsg);
			t2.setName("Server SendMessage");
			t2.start();
		}catch (IOException e){
			System.out.println("Error creating connection: "+e.getMessage());
		}
		finally{
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return socket;
	}
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	public String send() {
		return null;
	}

	public void recieve() {
		
	}

	public String getText() {
		return this.message;//this.toClient.getText();
	}
	
	public void setText() {
		this.message="";//this.toClient.setText("");
		this.toClient.setText("");
	}
	
	public InputStream getInputStream(){
		try{
			return this.socket.getInputStream();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	synchronized public void processMessage(String msg){
		this.allChats.append(msg+"\n");
	}

	@Override
	public OutputStream getOutputStream() {

		try{
			return this.socket.getOutputStream();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Socket getSocket() {
		return this.socket;
	}
}
