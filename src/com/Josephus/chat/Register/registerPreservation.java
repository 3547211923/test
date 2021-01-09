package com.Josephus.chat.Register;

import java.net.Socket;



/*
 * ×¢²á¶ËµÄÏß³Ì
 */
public class registerPreservation extends Thread {
	
	Socket socket;
	registerframe regframe;
	public  registerPreservation(Socket socket,registerframe regframe) {
		this.regframe=regframe;
		this.socket=socket;
	}

	public void run() {
		while(true) {
			Object obj = ioregister.readmessage(socket);
			if(obj instanceof transregister) {
				transregister tran = (transregister)obj;
				System.out.println(tran.getEmail());
			}
		}
	}
}
