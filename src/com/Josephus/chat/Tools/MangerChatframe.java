package com.Josephus.chat.Tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.Josephus.chat.friendui.Privatechat;

public class MangerChatframe {

	private static  Map<String,Privatechat> hm= new HashMap<>();
	
	public static void addchat(String str,Privatechat privatechat) {
		hm.put(str,privatechat);
		System.out.println("目前的对象有：");
		Iterator iter = MangerChatframe.hm.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + ":" +"统计聊天窗口");
		}
	}
	
	public static Privatechat getchat(String str) {
		return hm.get(str);
	}
	

}
