package com.Josephus.chat.Tools;

import java.io.Serializable;

import java.util.List;

/*
 * �ͻ��˵�
 * �û�name��password
 * ��װ
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
	//��Ϣ֪ͨ����
		private String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	//�û��˺�
	private String userName;
	//�û�����
	private String id;
	//������Ϣ������
	private List<FontStyle> content;
	//��Ϣ������
	private String sender;
	//��Ϣ������
	private String reciver;
	//ϵͳ��Ϣ
	private String notice;
	
	//�����û��б�
	private String[] userOnlineArray;
	//��Ϣ����
	private Chatstastu statusenum;
	
	//������� �Ͷ�Ӧ�����촰��
	private String chatuser;
	private String fileName;//�ļ�����
	
	private byte[] fileByte;//�ļ�����
	
	
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
