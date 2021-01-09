package com.Josephus.chat.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.Josephus.chat.Tools.transferinfo;


public class ConServer {

	
	public static  Socket s;
	
	//发送第一次请求
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
			//这里就是验证用户登录的地方
			if(tfi.getLoginSucceessFlag())
			{
				
//				//就创建一个该qq号和服务器端保持通讯连接得线程
//				ClientConServerThread ccst=new ClientConServerThread(s);
//				//启动该通讯线程
//				ccst.start();
				
				clienthander cli = new clienthander(s);//传递socket 和this frame窗体用来完成登录后关闭所用
				cli.start();
				
				
//				ManageClientConServerThread.addClientConServerThread
//				(((User)o).getUserId(), ccst);
				b=true;
			}else{
				//关闭Scoket
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
