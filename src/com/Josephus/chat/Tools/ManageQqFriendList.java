package com.Josephus.chat.Tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.Josephus.chat.client.friendf;

public class ManageQqFriendList {

	private static HashMap hm=new HashMap<String, friendf>();
	
	public static void addQqFriendList(String qqid,friendf qqFriendList){
		
		hm.put(qqid, qqFriendList);
		
		Iterator iter = ManageQqFriendList.hm.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + ":" +"统计好友列表");
		}
	}
	
	public static friendf getQqFriendList(String qqId)
	{
		return (friendf)hm.get(qqId);
	}
}