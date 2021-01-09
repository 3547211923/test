package com.Josephus.chat.sever.sendemail;

import org.apache.commons.mail.HtmlEmail;
public class Email {
	//邮箱验证码
		public static boolean sendEmail(String emailaddress,String code){
			while(true)
			{
				try {
					HtmlEmail email = new HtmlEmail();//不用更改
					email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
					email.setCharset("UTF-8");
					email.addTo(emailaddress);// 收件地址
					email.setFrom("1223788353@qq.com","aa");//此处填邮箱地址和用户名,用户名可以任意填写
					email.setAuthentication("1223788353@qq.com","qhmpoarhgsaqfgda");
					email.setSubject("账号申请成功！");//此处填写邮件名，邮件名可任意填写
					email.setMsg("尊敬的用户您好,您本次注册的账号是:111" );//此处填写邮件内容
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
