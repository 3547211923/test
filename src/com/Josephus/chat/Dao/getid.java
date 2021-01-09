package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getid {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? and password = ?";
			presta= conn.prepareStatement(sql);
			//给？赋值
//			presta.setString(1,tfi.getUserName());//参数是？的位置+？的内容
//			presta.setString(2,tfi.getPassword());
			res = presta.executeQuery();
			//return res.next();//如果存在下一行则返回true
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	
}
