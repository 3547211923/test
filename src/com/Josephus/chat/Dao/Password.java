package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Password {

	
	/*
	 * ��ѯ���ݿ������
	 */
	public static String Findemail(String ID) {
		String email="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1, ID);//�����ǣ���λ��+��������
			res = presta.executeQuery();
			res.next();
			email = res.getString("email");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return email;
	}
	
	/*
	 * �޸�����
	 */
	public static void  updatepassword(String ID,String pass) {
		String email="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="update  user SET password= ? WHERE loginid =?" ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1,pass);//�����ǣ���λ��+��������
			presta.setString(2,ID);//�����ǣ���λ��+��������
			presta.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
}
