package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Querygroup {

	public static void main(String[] args) {
		
	}
	
	public static boolean  queryname(String name) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group`  WHERE groupname = ?";//SELECT * FROM `group`  WHERE groupname = "������"
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
	
	public static boolean  queryid(String id) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group`  WHERE groupid = ?" ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1,id);//�����ǣ���λ��+��������
			
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
	
	public static boolean  creategroup(String gid,String gname,String owner,String intro) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		String menberid = owner;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="insert into `group`(groupid,groupname,groupowner,menberid,groupintroduction)values(?,?,?,?,?)";
			presta= conn.prepareStatement(sql);
			presta.setString(1, gid);
			presta.setString(2, gname);
			presta.setString(3, owner);
			presta.setString(4, owner);
			presta.setString(5, intro);
			presta.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return false ;
	}
}
