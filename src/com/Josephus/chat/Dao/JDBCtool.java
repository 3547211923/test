package com.Josephus.chat.Dao;
/*
 * JDBC�Ĺ�����
 * ��ȡconnection
 * �ͷ�C��S��R
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCtool {
	
	/*
	 * 
	 */
	public static Connection getconnection() {
		Connection conn = null;
		try {
			//��������
			Class.forName("com.mysql.cj.jdbc.Driver");
			//���������ݿ������
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zhang?serverTimezone=GMT", "root", "1635552844");
			//conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close2(Statement sta,Connection conn) {
		if(sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement sta,Connection conn,ResultSet res) {
		if(res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close3(Statement sta,Connection conn,ResultSet res) {
		if(res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
