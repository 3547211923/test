package com.Josephus.chat.Register;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ioregister {
	
	
	/*
	 * 从socket里面读取对象
	 * 并返回这个对象了吗的obje对象
	 */
	public static Object readmessage(Socket socket) {
		Object obj= null;
		try {
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			 obj=  ois.readObject();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	/*
	 * 把传过来的类信息封装成一个Object对象
	 * 然后在关闭IO流
	 */
	public static void writemessage(Socket socket,Object message) {
		try {
			OutputStream os= socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
