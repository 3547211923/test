package com.Josephus.chat.sever.sendemail;

import org.apache.commons.mail.HtmlEmail;

public class semail extends Thread {
	
	
	
	public static boolean sendEmail(String emailaddress,String code){
		try {
			HtmlEmail email = new HtmlEmail();//���ø���
			email.setHostName("smtp.163.com");//��Ҫ�޸ģ�126����Ϊsmtp.126.com,163����Ϊ163.smtp.com��QQΪsmtp.qq.com
			email.setCharset("UTF-8");
			email.addTo(emailaddress);// �ռ���ַ
 
			email.setFrom("z1635552844@163.com", "");//�˴��������ַ���û���,�û�������������д
 
			email.setAuthentication("z1635552844@163.com", "QAWXJLIZCRBOYCJR");//�˴���д�����ַ�Ϳͻ�����Ȩ��
 
			email.setSubject("ע����֤");//�˴���д�ʼ������ʼ�����������д
			email.setMsg("�𾴵��û�����,������ע�����֤����:" + '\n'+code);//�˴���д�ʼ�����
 
			email.send();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
