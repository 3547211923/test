package com.Josephus.chat.client;

import java.net.Socket;

import com.Josephus.chat.Tools.transferinfo;

public class toConServer {

	 ConServer conserver = new ConServer();
	public boolean checkUser(transferinfo u)
	{
		return conserver.sendLoginInfoToServer(u);
	}
	
	public Socket getsocket()
	{
		return conserver.getsocket();
	}
}
