package com.threadcodes;

import java.awt.Graphics;
import java.util.Date;

public class ClockThread extends java.applet.Applet implements Runnable {

    Thread clockThread = null;

    public void start() {
        if (clockThread == null) {
            clockThread = new Thread(this, "ClockThread");
            clockThread.start();
        }
    }
    public void run() {
        // loop terminates when clockThread is set to null in stop()
        while (Thread.currentThread() == clockThread) {
            repaint();
            try {
                clockThread.sleep(1000);
            } catch (InterruptedException e){
            }
        }
    }
    public void paint(Graphics g) {
        Date now = new Date();
        g.drawString(now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds(), 5, 10);
    }
    public void stop() {
        clockThread = null;
    }
    
/*    public static void main(String[] args){
    	ClockThread c = new ClockThread();
    	c.start();
    }
*/}