package com.Josephus.chat.Register;

/*
 * ��ȡ�����
 * �˺�
 * ����ʵ�������ݿ��ѯ��ֹ�ظ�
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