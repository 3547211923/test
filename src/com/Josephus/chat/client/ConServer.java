package com.Josephus.chat.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.Josephus.chat.Tools.transferinfo;


public class ConServer {

	
	public static  Socket s;
	
	//���͵�һ������
	public static boolean sendLoginInfoToServer(Object o)
	{
		boolean b=false;
		try {
			s = new Socket("127.0.0.1",8888);

			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			//Message ms=(Message)ois.readObject();
			transferinfo tfi =(transferinfo)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(tfi.getLoginSucceessFlag())
			{
				
//				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
//				ClientConServerThread ccst=new ClientConServerThread(s);
//				//������ͨѶ�߳�
//				ccst.start();
				
				clienthander cli = new clienthander(s);//����socket ��this frame����������ɵ�¼��ر�����
				cli.start();
				
				
//				ManageClientConServerThread.addClientConServerThread
//				(((User)o).getUserId(), ccst);
				b=true;
			}else{
				//�ر�Scoket
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
	public static Socket getsocket() {
		return s;
	}

}
