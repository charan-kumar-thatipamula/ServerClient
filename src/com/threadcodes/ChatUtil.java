package com.threadcodes;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class ChatUtil {

	public abstract void createUI();
	public abstract String send();
	public abstract void recieve();
	public abstract String getText();
	public abstract void setText();
	public abstract InputStream getInputStream();
	public abstract void processMessage(String msg);
	public abstract OutputStream getOutputStream();
	public abstract Socket getSocket();
}
