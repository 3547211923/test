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
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group`  WHERE groupname = ?";//SELECT * FROM `group`  WHERE groupname = "哈哈哈"
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
	
	public static boolean  queryid(String id) {
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="SELECT * FROM `group`  WHERE groupid = ?" ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1,id);//参数是？的位置+？的内容
			
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
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		String menberid = owner;
		try {
			//获取连接
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
