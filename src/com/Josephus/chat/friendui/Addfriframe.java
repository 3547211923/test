package com.Josephus.chat.friendui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.MatteBorder;

import com.Josephus.chat.Dao.Queryfriend;
import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.MangerChatframe;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Addfriframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton addf;

	
	/**
	 * Create the frame.
	 */
	public Addfriframe(Socket socket,String userid,String groupname) {

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setVisible(true);
		textField = new JTextField();
		textField.setBounds(27, 29, 282, 41);
		contentPane.add(textField);
		textField.setColumns(10);

		
		// 搜索
		JButton sousuo = new JButton("\u641C\u7D22");
		sousuo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = textField.getText();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入id", "Tips", JOptionPane.CANCEL_OPTION);
				} else {
					
					JPanel panel = new JPanel();
					panel.setBounds(0, 83, 432, 170);
					contentPane.add(panel);
					panel.setLayout(null);

					//立即邀请的按钮
					addf = new JButton("\u7ACB\u5373\u9080\u8BF7");
					addf.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							transferinfo tfi = new transferinfo();
							tfi.setSender(userid);
							tfi.setReciver(username);
							tfi.setNotice("邀请加入"+groupname);
							
							tfi.setType("2");
							tfi.setStatusenum(Chatstastu.Addtogroup);
							iostream.writemessage(socket, tfi);
							JOptionPane.showMessageDialog(null, "邀请已经发送", "Tips", JOptionPane.CANCEL_OPTION);
						}
						
					});
					addf.setForeground(new Color(30, 144, 255));
					addf.setFont(new Font("宋体", Font.PLAIN, 26));
					addf.setContentAreaFilled(false);
					addf.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(30, 144, 255)));
					addf.setBounds(109, 122, 208, 35);
					panel.add(addf);
					
					panel.setVisible(true);
					
					addfriendtopanel(username,panel);
					panel.repaint();
				}
			}
		});
		sousuo.setContentAreaFilled(false);
		sousuo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		sousuo.setFont(new Font("宋体", Font.PLAIN, 24));
		sousuo.setBounds(323, 29, 95, 41);
		contentPane.add(sousuo);
	}

	public void addfriendtopanel(String username,JPanel panel) {

		String path1 = "image/朋友1.png";
		JLabel jlabel = new JLabel(username, new ImageIcon(path1), JLabel.LEFT);
		jlabel.setBackground(Color.BLUE);
		jlabel.setFont(new Font("宋体", Font.PLAIN, 24));
		jlabel.setBounds(0, 0, 300, 100);
		panel.add(jlabel);
	}
}
