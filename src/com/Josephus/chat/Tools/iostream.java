package com.Josephus.chat.Tools;

import java.io.DataInputStream;


import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * �ͻ��˵�
 * IO��������
 * 
 */
public class iostream {
	
	/*
	 * ��socket�����ȡ����
	 */
	public static Object readmessage(Socket socket) {
		Object obj= null;
		try {
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
				 int objByteLen=dis.readInt();
				 byte[] objByte= new byte[objByteLen];
				 dis.readFully(objByte);
				 obj=ByteObjectConvert.byteToObject(objByte);
		}
			catch(IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	/*
	 * ����Ϣ��ע����Ϣ
	 */
	public static void writemessage(Socket socket,Object obj) {
		
		try {
			OutputStream os= socket.getOutputStream();
			//ObjectOutputStream oos = new ObjectOutputStream(os);
			byte[] objByte= ByteObjectConvert.objectToByte(obj);
			DataOutputStream dos=  new DataOutputStream(os);
			dos.writeInt(objByte.length);
			dos.write(objByte);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}