package com.Josephus.chat.Tools;
/*
 * 客户端
 */
public enum Chatstastu {

	LoGIN(1,"登录消息"),NOTICE(2,"系统消息"),CHAT(2,"聊天消息"),DD(4,"抖动消息"),ULIST(5,"在线用户列表"),hMap(6,"传输聊天界面"),SEND_FILE(7,"发送文件"),CLOSE(8,"退出登录"),GROUP(9,"群聊"),GROUPFILE(10,"群文件"),FriendApply(11,"好友申请"),Voicefile(12,"语音文件"),Addtogroup(13,"邀请加群");
	
	private Integer status;
	private String decs;
	
	private Chatstastu(int status,String desc) {
		this.decs=desc;
		this.status=status;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDecs() {
		return decs;
	}

	public void setDecs(String decs) {
		this.decs = decs;
	}


}

