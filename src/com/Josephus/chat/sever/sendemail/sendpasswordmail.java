package com.Josephus.chat.sever.sendemail;

import org.apache.commons.mail.HtmlEmail;

public class sendpasswordmail {

	public static boolean sendEmail(String emailaddress,String code){
		try {
			HtmlEmail email = new HtmlEmail();//不用更改
			email.setHostName("smtp.163.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
			email.setCharset("UTF-8");
			email.addTo(emailaddress);// 收件地址
 
			email.setFrom("z1635552844@163.com", "");//此处填邮箱地址和用户名,用户名可以任意填写
 
			email.setAuthentication("z1635552844@163.com", "RKUJUBOAZUINOVBQ");//此处填写邮箱地址和客户端授权码
 
			email.setSubject("找回密码");//此处填写邮件名，邮件名可任意填写
			email.setMsg("尊敬的用户您好,您的验证码是:" + '\n'+code);//此处填写邮件内容
 
			email.send();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
