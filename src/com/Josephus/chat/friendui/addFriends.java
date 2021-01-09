package com.Josephus.chat.friendui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.Josephus.chat.Dao.Queryfriend;
import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.ManageQqFriendList;
import com.Josephus.chat.Tools.MangerChatframe;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;
import com.Josephus.chat.client.friendf;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class addFriends extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//addFriends frame = new addFriends("111","222");
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JFrame jframe;
	Socket socket;
	public   addFriends(String myid,String reciver,Socket socket) {
		this.socket=socket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon=new ImageIcon("image/添加好友咖啡.png");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());
		//获取屏幕的大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w-100)/2,(h-100)/2);

		jframe=this;
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u662F\u5426\u6DFB\u52A0\u5BF9\u65B9\u4E3A\u597D\u53CB\uFF1F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setBounds(14, 13, 286, 57);
		contentPane.add(lblNewLabel);
		String ans= lblNewLabel.getText();
		

		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"好友申请已发送");
				friendf Friendframe= ManageQqFriendList.getQqFriendList(myid);

				//Queryfriend.addmessage(myid, reciver, "申请添加好友");//数据库的消息添加

				//发送通知给服务器，告诉好友申请消息
				transferinfo tfi =new transferinfo();
				tfi.setStatusenum(Chatstastu.FriendApply);
				tfi.setSender(myid);
				tfi.setType("3");
				tfi.setReciver(reciver);
				tfi.setNotice("申请添加好友");
				iostream.writemessage(socket, tfi);
				
				
				JLabel jlabel11 = new JLabel("好友申请已发送",new ImageIcon("image/messagetip.png"),JLabel.LEFT);
				jlabel11.setFont(new Font("宋体", Font.PLAIN, 24));
				//菜单
				JPopupMenu popuMenu = new JPopupMenu();
				
				//给菜单添加按钮组件 私聊  /群聊
				JMenuItem delete = new JMenuItem("删除");
				delete.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						Friendframe.xiaoxi.remove(jlabel11);
						Friendframe.xiaoxi.repaint();
					}
				});
				popuMenu.add(delete);
				
				//添加监听器
				jlabel11.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						popuMenu.show(jlabel11,e.getX(),e.getY());
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
						
					}

					@Override//鼠标碰到变蓝
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						JLabel jl=(JLabel)e.getSource();
						jl.setForeground(Color.blue);
					}

					@Override//鼠标离开变黑
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						JLabel jl=(JLabel)e.getSource();
						jl.setForeground(Color.black);
					}
					
					
				});
				Friendframe.xiaoxi.add(jlabel11);
				Friendframe.xiaoxi.repaint();
				jframe.dispose();
			}
		});
		btnNewButton.setBounds(14, 103, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5426");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});
		btnNewButton_1.setBounds(187, 103, 113, 27);
		contentPane.add(btnNewButton_1);
	}
}
