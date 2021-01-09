package com.Josephus.chat.Register;

/*
 * 获取随机数
 * 账号
 * 这里实现了数据库查询防止重复
 */
public class getrandomnumber {

		
	public static String getrandomnumber() {

	      int a[] = new int[10];
	      String s="";
	      for(int i=0;i<a.length;i++ ) {

	          a[i] = (int)(10*(Math.random()));
	          s+=a[i];
	      }
		return s;
	   }
}