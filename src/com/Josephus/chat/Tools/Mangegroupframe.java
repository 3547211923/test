package com.Josephus.chat.Tools;

import java.util.HashMap;
import java.util.Map;

import com.Josephus.chat.friendui.Groupchat;

public class Mangegroupframe {

private static  Map<String,Groupchat> hm= new HashMap<>();
	
	public static void addchat(String str,Groupchat privatechat) {
		hm.put(str,privatechat);
		
	}
	
	public static Groupchat getchat(String str) {
		return hm.get(str);
	}
}
