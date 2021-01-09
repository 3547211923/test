package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queryfriend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * ��ѯ�Ƿ�����������
	 */
	public static boolean  queryfriend(String name) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `user`  WHERE loginid = ?";//SELECT * FROM `group`  WHERE groupname = "������"
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1,name);//�����ǣ���λ��+��������
			
			res = presta.executeQuery();
			return res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return false ;
	}
	/*
	 * ���ݿ���Ӻ���������Ϣ
	 */
	public static void  addmessage(String sender,String reciver,String content) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		String type="3";
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
	 * ��Ӻ����б�
	 */
	public static void  addlist(int f1,int f2) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		String type="3";
		try {
			conn=JDBCtool.getconnection();
			String sql="insert into friendlist(f1,f2)values(?,?)";
			presta= conn.prepareStatement(sql);
			presta.setInt(1, f1);
			presta.setInt(2, f2);
			presta.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	/*
	 * �ں����б�����ɾ����ϵ
	 */
	public static void  deletefriend(int f1,int f2) {
		
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		String type="3";
		try {
			conn=JDBCtool.getconnection();
			String sql="delete from friendlist where f1= ? and f2= ?";
			presta= conn.prepareStatement(sql);
			presta.setInt(1, f1);
			presta.setInt(2, f2);
			presta.executeUpdate();
			presta.setInt(1, f2);
			presta.setInt(2, f1);
			presta.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
}
