package com.Josephus.chat.friendui;
/*
 * ¶¶Ò»¶¶
 * ¸ü½¡¿µ
 */
public class Douyidou extends Thread{

	Privatechat Chatroom;
	public Douyidou(Privatechat Chatroom) {
		this.Chatroom=Chatroom;
	}
	public void run() {
		try {
    		Chatroom.setLocation(Chatroom.getX()-28, Chatroom.getY());
			Thread.sleep(50);
			Chatroom.setLocation(Chatroom.getX(), Chatroom.getY()-28);
			Thread.sleep(50);
			Chatroom.setLocation(Chatroom.getX()+28, Chatroom.getY());
			Thread.sleep(50);
			Chatroom.setLocation(Chatroom.getX(), Chatroom.getY()+28);
			
    	} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

