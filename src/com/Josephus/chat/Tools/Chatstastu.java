package com.Josephus.chat.Tools;
/*
 * �ͻ���
 */
public enum Chatstastu {

	LoGIN(1,"��¼��Ϣ"),NOTICE(2,"ϵͳ��Ϣ"),CHAT(2,"������Ϣ"),DD(4,"������Ϣ"),ULIST(5,"�����û��б�"),hMap(6,"�����������"),SEND_FILE(7,"�����ļ�"),CLOSE(8,"�˳���¼"),GROUP(9,"Ⱥ��"),GROUPFILE(10,"Ⱥ�ļ�"),FriendApply(11,"��������"),Voicefile(12,"�����ļ�"),Addtogroup(13,"�����Ⱥ");
	
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

