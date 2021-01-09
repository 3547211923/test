package com.Josephus.chat.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Demodao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		try {
			//加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//建立和数据库的连接
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaclass?serverTimezone=GMT", "root", "1635552844");
			//conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}

