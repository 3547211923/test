package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ADDtogroup {

	/*
	 * ���ݿ���Ӻ���������Ϣ
	 */
	public static void  addmessage(String sender,String reciver,String content,String type) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			conn=JDBCtool.getconnection();
			String sql="insert into message(content,sender,reciver,type)values(?,?,?,?)";
			presta= conn.prepareStatement(sql);
			presta.setString(1, content);
			presta.setString(2, sender);
			presta.setString(3, reciver);
			presta.setString(4, type);
			presta.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	
	/*
	 * �ѳ�Ա����Ⱥ�����ݿ�
	 */
	public static void  addtogroup(String groupname,String menberid) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group` WHERE groupname =?";
			presta= conn.prepareStatement(sql);
			presta.setString(1,groupname);
			res = presta.executeQuery();
			res.next();
			String groupid=res.getString("groupid");
			String groupowner=res.getString("groupowner");
			String groupintroduction=res.getString("groupintroduction");
			
			String sql2="insert into `group`(groupid,groupname,groupowner,menberid,groupintroduction)values(?,?,?,?,?)";
			presta= conn.prepareStatement(sql2);
			presta.setString(1, groupid);
			presta.setString(2, groupname);
			presta.setString(3, groupowner);
			presta.setString(4, menberid);
			presta.setString(5, groupintroduction);
			presta.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	
	/*
	 * ��ѯȺ��
	 */
	public static String  findowner(String groupname) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		String groupowner="";
		try {
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group` WHERE groupname =?";
			presta= conn.prepareStatement(sql);
			presta.setString(1,groupname);
			res = presta.executeQuery();
			res.next();
			groupowner=res.getString("groupowner");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return groupowner;
	}
}
