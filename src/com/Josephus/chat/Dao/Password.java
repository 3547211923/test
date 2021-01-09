package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Password {

	
	/*
	 * 查询数据库的邮箱
	 */
	public static String Findemail(String ID) {
		String email="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1, ID);//参数是？的位置+？的内容
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
	 * 修改密码
	 */
	public static void  updatepassword(String ID,String pass) {
		String email="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="update  user SET password= ? WHERE loginid =?" ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1,pass);//参数是？的位置+？的内容
			presta.setString(2,ID);//参数是？的位置+？的内容
			presta.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
}
