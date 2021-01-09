package com.Josephus.chat.Tools;

import java.io.Serializable;

import java.util.List;

/*
 * 客户端的
 * 用户name和password
 * 封装
 */
public class transferinfo implements Serializable {
	
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -364243004365215517L;
	private String groupname;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	//消息通知类型
		private String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	//用户账号
	private String userName;
	//用户密码
	private String id;
	//聊天消息的内容
	private List<FontStyle> content;
	//消息发送者
	private String sender;
	//消息接收者
	private String reciver;
	//系统消息
	private String notice;
	
	//在线用户列表
	private String[] userOnlineArray;
	//消息类型
	private Chatstastu statusenum;
	
	//聊天对象 和对应的聊天窗口
	private String chatuser;
	private String fileName;//文件名字
	
	private byte[] fileByte;//文件数据
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileByte() {
		return fileByte;
	}
	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}
	public String getChatuser() {
		return chatuser;
	}
	public void setChatuser(String chatuser) {
		this.chatuser = chatuser;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String password;
	
	
	

	public List<FontStyle> getContent() {
		return content;
	}
	public void setContent(List<FontStyle> content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	private Boolean loginSucceessFlag =false;
	
	
	public String[] getUserOnlineArray() {
		return userOnlineArray;
	}
	public void setUserOnlineArray(String[] userOnlineArray) {
		this.userOnlineArray = userOnlineArray;
	}
	
	
	
	public Chatstastu getStatusenum() {
		return statusenum;
	}
	public void setStatusenum(Chatstastu statusenum) {
		this.statusenum = statusenum;
	}
	public Boolean getLoginSucceessFlag() {
		return loginSucceessFlag;
	}
	public void setLoginSucceessFlag(Boolean loginSucceessFlag) {
		this.loginSucceessFlag = loginSucceessFlag;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
