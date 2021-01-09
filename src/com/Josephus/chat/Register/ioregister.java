package com.Josephus.chat.Register;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ioregister {
	
	
	/*
	 * ��socket�����ȡ����
	 * ������������������obje����
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
	 * �Ѵ�����������Ϣ��װ��һ��Object����
	 * Ȼ���ڹر�IO��
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
