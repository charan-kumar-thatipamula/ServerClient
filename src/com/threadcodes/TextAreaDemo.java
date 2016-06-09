package com.threadcodes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextAreaDemo extends JFrame {
    //============================================== instance variables
    JTextArea enterChat = new JTextArea(4, 30);
    
    public JTextArea getEnterChat() {
		return enterChat;
	}

	public void setEnterChat(JTextArea enterChat) {
		this.enterChat = enterChat;
	}

	public JTextArea getChatHistory() {
		return chatHistory;
	}

	public void setChatHistory(JTextArea chatHistory) {
		this.chatHistory = chatHistory;
	}

	JTextArea chatHistory = new JTextArea(10, 30);
        
    //====================================================== constructor
    public TextAreaDemo() {
        //... Set initial text, scrolling, and border.
        enterChat.setText("Enter more text to see scrollbars");
        enterChat.getDocument().addDocumentListener(new DocumentListener() {

            public void removeUpdate(DocumentEvent e) {

            }

            public void insertUpdate(DocumentEvent e) {

            }

            public void changedUpdate(DocumentEvent arg0) {

            }
        });
        JScrollPane scrollingArea = new JScrollPane(enterChat);
        scrollingArea.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        
        
        chatHistory.setEditable(false);
        JScrollPane scrollingArea1 = new JScrollPane(chatHistory);
        // Get the content pane, set layout, add to center
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(scrollingArea1, BorderLayout.PAGE_START);
        content.add(scrollingArea, BorderLayout.PAGE_END);
        this.pack();
    }
    
    //============================================================= main
    public static void main(String[] args) {
        JFrame win = new TextAreaDemo();
        win.setTitle("TextAreaDemo");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
    
    
    
    
}