package com.Josephus.chat.sever.sendemail;

import org.apache.commons.mail.HtmlEmail;
public class Email {
	//������֤��
		public static boolean sendEmail(String emailaddress,String code){
			while(true)
			{
				try {
					HtmlEmail email = new HtmlEmail();//���ø���
					email.setHostName("smtp.qq.com");//��Ҫ�޸ģ�126����Ϊsmtp.126.com,163����Ϊ163.smtp.com��QQΪsmtp.qq.com
					email.setCharset("UTF-8");
					email.addTo(emailaddress);// �ռ���ַ
					email.setFrom("1223788353@qq.com","aa");//�˴��������ַ���û���,�û�������������д
					email.setAuthentication("1223788353@qq.com","qhmpoarhgsaqfgda");
					email.setSubject("�˺�����ɹ���");//�˴���д�ʼ������ʼ�����������д
					email.setMsg("�𾴵��û�����,������ע����˺���:111" );//�˴���д�ʼ�����
					email.send();
					return true;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			}
			
}
