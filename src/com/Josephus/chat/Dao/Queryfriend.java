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
	 * 查询是否存在这个好友
	 */
	public static boolean  queryfriend(String name) {
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `user`  WHERE loginid = ?";//SELECT * FROM `group`  WHERE groupname = "哈哈哈"
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1,name);//参数是？的位置+？的内容
			
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
	 * 数据库添加好友申请消息
	 */
	public static void  addmessage(String sender,String reciver,String content) {
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
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
	 * 添加好友列表
	 */
	public static void  addlist(int f1,int f2) {
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
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
	 * 在好友列表里面删除关系
	 */
	public static void  deletefriend(int f1,int f2) {
		
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
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
