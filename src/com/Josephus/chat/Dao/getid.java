package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getid {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? and password = ?";
			presta= conn.prepareStatement(sql);
			//������ֵ
//			presta.setString(1,tfi.getUserName());//�����ǣ���λ��+��������
//			presta.setString(2,tfi.getPassword());
			res = presta.executeQuery();
			//return res.next();//���������һ���򷵻�true
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	
}
