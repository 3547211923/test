package com.Josephus.chat.friendui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Josephus.chat.Dao.Queryfriend;
import com.Josephus.chat.Dao.Querygroup;
import com.Josephus.chat.Tools.TextBorderUtlis;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

public class Addfriend extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1800628768759186517L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Addfriend frame = new Addfriend("123");
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
	JLabel lblNewLabel;//添加好友
	JLabel lblNewLabel_1;//创建群聊
	JLabel lblNewLabel_2;//找群
	private JTextField qunnichengText;
	private JTextField qunhaomaText;
	private JTextField qunjianjieText;
	Socket socket;
	public Addfriend(String owner,Socket ownersocket,JPanel grouppanel) {
		this.socket=ownersocket;
		setBounds(100, 100, 773, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//ImageIcon icon=new ImageIcon("image/添加好友咖啡.png");  //xxx代表图片存放路径，2.png图片名称及格式
		//this.setIconImage(icon.getImage());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w-550)/2,(h-500)/2);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//添加好友Panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setDoubleBuffered(false);
		panel.setEnabled(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setBounds(0, 82, 755, 417);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//创建群聊Panel
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 82, 755, 417);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		TextBorderUtlis textset123 =new TextBorderUtlis(new Color(0,191,255),1,true);
		qunnichengText = new JTextField();
		qunnichengText.setFont(new Font("宋体", Font.PLAIN, 24));
		qunnichengText.setBounds(232, 33, 424, 46);
		qunnichengText.setBorder(textset123);
		panel_1.add(qunnichengText);
		qunnichengText.setColumns(10);
		
		qunhaomaText = new JTextField();
		qunhaomaText.setFont(new Font("宋体", Font.PLAIN, 24));
		qunhaomaText.setBounds(232, 128, 424, 46);
		qunhaomaText.setBorder(textset123);
		panel_1.add(qunhaomaText);
		qunhaomaText.setColumns(10);
		
		qunjianjieText = new JTextField();
		qunjianjieText.setFont(new Font("宋体", Font.PLAIN, 24));
		qunjianjieText.setBounds(232, 229, 424, 46);
		qunjianjieText.setBorder(textset123);
		panel_1.add(qunjianjieText);
		qunjianjieText.setColumns(10);
		
		JButton lijichuangjianqunliao = new JButton("\u7ACB\u5373\u521B\u5EFA");
		lijichuangjianqunliao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gname = qunnichengText.getText();
				String gid = qunhaomaText.getText();
				String gintro = qunjianjieText.getText();
				if(Querygroup.queryname(gname)) {
					System.out.println("群聊 “"+gname +"” 已经存在");
					JOptionPane.showMessageDialog(null,"群聊“"+gname +"” 已经存在");
				}
				else if(Querygroup.queryid(gid)) {
					
					System.out.println("群聊 “"+gid +"” 已经存在");
					
					JOptionPane.showMessageDialog(null,"群号码 “"+gid +"” 已经存在");
					
				}
				else if(Querygroup.creategroup(gid,gname,owner,gintro))
				{
					JOptionPane.showMessageDialog(null,"群聊创建成功");
					JLabel jlabel = new JLabel(gname,new ImageIcon("image/头像6.png"),JLabel.LEFT);
					jlabel.addMouseListener(new MouseListener() {

						
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							System.out.println("开始群聊");
							List<String> groupmenber= new ArrayList<>();
							groupmenber.add("zs");
							groupmenber.add("ls");
							groupmenber.add("ww");
							Groupchat group = new Groupchat(owner,ownersocket,groupmenber,gname);
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
							jl.setForeground(Color.red);
						}

						@Override//鼠标离开变黑
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							JLabel jl=(JLabel)e.getSource();
							jl.setForeground(Color.black);
						}
						
						
					});
					jlabel.setFont(new Font("宋体", Font.PLAIN, 24));
					grouppanel.add(jlabel);
					qunnichengText.setText("");qunhaomaText.setText("");qunjianjieText.setText("");
				}	
			}
		});
		lijichuangjianqunliao.setFont(new Font("宋体", Font.PLAIN, 26));
		lijichuangjianqunliao.setBounds(352, 325, 188, 46);
		lijichuangjianqunliao.setBorder(textset123);
		panel_1.add(lijichuangjianqunliao);
		
		JLabel qunnicheng = new JLabel("\u7FA4\u6635\u79F0");
		qunnicheng.setFont(new Font("宋体", Font.PLAIN, 22));
		qunnicheng.setBounds(94, 33, 72, 46);
		panel_1.add(qunnicheng);
		
		JLabel qunhaoma = new JLabel("\u7FA4\u53F7\u7801");
		qunhaoma.setFont(new Font("宋体", Font.PLAIN, 22));
		qunhaoma.setBounds(94, 128, 72, 46);
		panel_1.add(qunhaoma);
		
		JLabel qunjianjie = new JLabel("\u7FA4\u7B80\u4ECB");
		qunjianjie.setFont(new Font("宋体", Font.PLAIN, 22));
		qunjianjie.setBounds(94, 229, 72, 46);
		panel_1.add(qunjianjie);
		TextBorderUtlis textset =new TextBorderUtlis(new Color(0,191,255),1,true);
		//找群Panel
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 82, 755, 417);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		JTextField textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 32));
		textField_2.setBorder(textset);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(97, 62, 436, 58);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		//找群的查找按钮
		JButton btnNewButton_3 = new JButton("查找");
		TextBorderUtlis textset2 =new TextBorderUtlis(new Color(0,191,255),0,false);
		btnNewButton_3.setBorder(textset2);
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnNewButton_3.setForeground(new Color(230, 230, 250));
		btnNewButton_3.setBackground(new Color(0, 191, 255));
		btnNewButton_3.setBounds(603, 62, 113, 58);
		panel_2.add(btnNewButton_3);
		
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		//添加好友的搜索框
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 32));
		textField.setBorder(textset);
		textField.setBackground(Color.WHITE);
		textField.setBounds(97, 62, 436, 58);
		panel.add(textField);
		textField.setColumns(10);
		
		//添加好友的搜索按钮
		JButton btnNewButton_2 = new JButton("\u641C\u7D22");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加好友监听器
				String fid = textField.getText();
				
				if(Queryfriend.queryfriend(fid)) {
					new addFriends(owner,fid,socket).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"用户"+"“"+fid+"”不存在");
				}
			}
		});
		TextBorderUtlis textset22 =new TextBorderUtlis(new Color(0,191,255),0,false);
		btnNewButton_2.setBorder(textset22);
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnNewButton_2.setForeground(new Color(230, 230, 250));
		btnNewButton_2.setBackground(new Color(0, 191, 255));
		btnNewButton_2.setBounds(603, 62, 113, 58);
		panel.add(btnNewButton_2);
		
		
		
		//添加好友
		lblNewLabel = new JLabel("\u6DFB\u52A0\u597D\u53CB");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		lblNewLabel.setBounds(76, 29, 157, 40);
		lblNewLabel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				System.out.println("添加好友");
				panel.setVisible(true);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				lblNewLabel.setForeground(Color.BLACK);
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_2.setForeground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		contentPane.add(lblNewLabel);
		
		//创建群聊
		lblNewLabel_1 = new JLabel("\u521B\u5EFA\u7FA4\u804A");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(310, 29, 170, 40);
		lblNewLabel_1.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				System.out.println("创建群聊");
				panel.setVisible(false);
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_2.setForeground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		contentPane.add(lblNewLabel_1);
		
		//找群
		lblNewLabel_2 = new JLabel("\u627E\u7FA4");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		lblNewLabel_2.setBounds(548, 29, 157, 40);
		lblNewLabel_2.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				System.out.println("找群");
				panel.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_2.setForeground(Color.BLACK);
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		contentPane.add(lblNewLabel_2);
		
		//给窗体添加背景图
		ImageIcon image =new ImageIcon("image/chatroomback.png");
		JLabel back1 = new JLabel(image);//万能的JLabel，2333
		back1.setBounds(0,0,800,500);//图片位置和大小
		//panel.add(back1);
		
		ImageIcon image2 =new ImageIcon("image/chatroomback.png");
		JLabel back12 = new JLabel(image);//万能的JLabel，2333
		back12.setBounds(0,0,800,500);//图片位置和大小
		//panel_1.add(back12);
		
		ImageIcon image3 =new ImageIcon("image/chatroomback.png");
		JLabel back13 = new JLabel(image);//万能的JLabel，2333
		back13.setBounds(0,0,800,500);//图片位置和大小
		//panel_2.add(back13);

	}
}
