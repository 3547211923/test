package com.Josephus.chat.client;


import javax.swing.ImageIcon;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

import com.Josephus.chat.Dao.ADDtogroup;
import com.Josephus.chat.Dao.JDBCtool;
import com.Josephus.chat.Dao.Queryfriend;
import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.Mangegroupframe;
import com.Josephus.chat.Tools.MangerChatframe;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;
import com.Josephus.chat.friendui.Addfriend;
import com.Josephus.chat.friendui.DemoScrollBarUI;
import com.Josephus.chat.friendui.Groupchat;
import com.Josephus.chat.friendui.Privatechat;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;

public class friendf extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4996566357655328349L;
	private JPanel contentPane;
	private JTextField textField;

	public JPanel panel ;//群聊
	public JPanel panel_1;//联系人
	public JPanel xiaoxi;//消息
	public int ID;
	Socket socket;//自己的socket
	String Loginid;//账号
	JFrame frame;
	public friendf(String loginid,Socket socket) {

		
		this.socket=socket;
		frame=this;
		this.Loginid=loginid;
		//ImageIcon icon=new ImageIcon("image/朋友列表图标.png");  //xxx代表图片存放路径，2.png图片名称及格式
		//this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocation(1450,150);

		
		String name ="";
		name=connected(loginid);
		//头像
		JLabel lblNewLabel = new JLabel("");
		String path1 = "image/头像";
		int pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		lblNewLabel.setIcon(new ImageIcon(path1));
		lblNewLabel.setBounds(0, 0, 89, 68);
		lblNewLabel.setOpaque(false);  

		contentPane.add(lblNewLabel);
		
		//昵称 需要传一个STring对象过来作为昵称
		JLabel lblNewLabel_1 = new JLabel(name);
		
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(119, 0, 116, 54);
		contentPane.add(lblNewLabel_1);
		
		//日期
		JLabel lblNewLabel_2 = new JLabel("2020-01-02");
		lblNewLabel_2.setBounds(268, 50, 70, 18);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String time = formatter.format(date);
		lblNewLabel_2.setText(time);
		contentPane.add(lblNewLabel_2);
		
		//给窗体添加背景图
		ImageIcon image =new ImageIcon("image/severback.png");
		JLabel frame = new JLabel(image);//万能的JLabel，2333
		frame.setBounds(0,0,349,70);//图片位置和大小
		getContentPane().add(frame);


		//添加好友JLAbel
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(0, 69, 34, 32);
		lblNewLabel_3.setIcon(new ImageIcon("image/添加好友.png"));
		lblNewLabel_3.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击 传递我自己的账号 和socket 和 群聊panel
				new Addfriend(loginid,socket,panel).setVisible(true);
				
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
		contentPane.add(lblNewLabel_3);
		
		//账号搜索框
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("宋体", Font.PLAIN, 24));
		textField.setText("");
		textField.setBounds(36, 69, 232, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//账号搜索按钮
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = textField.getText();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(268, 69, 70, 35);
		btnNewButton.setContentAreaFilled(false);  
		contentPane.add(btnNewButton);
		
		//群聊面板
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		scrollPane.setBounds(0, 146, 353, 542);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(50, 1,4,4));
		
		//联系人面板
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 146, 353, 542);
		scrollPane_1.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		contentPane.add(scrollPane_1);
		
		panel_1 = new JPanel();
		//panel_1.setBackground(new Color(224, 255, 255));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(50, 1,4,4));
		
		
		//消息面板
		JScrollPane scrollPanexx = new JScrollPane();
		scrollPanexx.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		scrollPanexx.setBounds(0, 146, 353, 542);
		contentPane.add(scrollPanexx);
		
		xiaoxi = new JPanel();
		xiaoxi.setBackground(Color.LIGHT_GRAY);
		scrollPanexx.setViewportView(xiaoxi);
		xiaoxi.setLayout(new GridLayout(50, 1,4,4));
		
		
		scrollPane_1.setVisible(true);
		scrollPane.setVisible(false);
		scrollPanexx.setVisible(false);
		//联系人按钮
		JButton btnNewButton_1 = new JButton("\u8054\u7CFB\u4EBA");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setVisible(true);
				scrollPane.setVisible(false);
				scrollPanexx.setVisible(false);
				xiaoxi.setVisible(false);
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setBounds(119, 102, 100, 42);
		contentPane.add(btnNewButton_1);
		
		//群聊按钮
		JButton btnNewButton_2 = new JButton("\u7FA4\u804A");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setVisible(false);
				scrollPane.setVisible(true);
				scrollPanexx.setVisible(false);
				xiaoxi.setVisible(false);
				panel_1.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(243, 102, 100, 42);
		contentPane.add(btnNewButton_2);
		
		//消息按钮
		JButton btnNewButton_3 = new JButton("\u6D88\u606F");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setVisible(false);
				scrollPane.setVisible(false);
				scrollPanexx.setVisible(true);
				xiaoxi.setVisible(true);
				panel_1.setVisible(false);
				panel.setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setBounds(0, 102, 100, 42);
		contentPane.add(btnNewButton_3);
		 path1 = "image/头像";
		 pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		JLabel jlabel = new JLabel("Josephus",new ImageIcon(path1),JLabel.LEFT);
		jlabel.setFont(new Font("宋体", Font.PLAIN, 24));
		
		findfriendid(ID);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
 
            }
 
            @Override
            public void windowClosing(WindowEvent e) {
                // 此处加入操作动作
            		JOptionPane.showMessageDialog(null,"即将退出登录");
            		transferinfo tfi = new transferinfo();
            		tfi.setSender(Loginid);
            		tfi.setStatusenum(Chatstastu.CLOSE);
            		iostream.writemessage(socket, tfi);
					
					
					System.exit(0);
				
            	
            }
 
            @Override
            public void windowClosed(WindowEvent e) {
 
            }
 
            @Override
            public void windowIconified(WindowEvent e) {
            }
 
            @Override
            public void windowDeiconified(WindowEvent e) {
 
            }
 
            @Override
            public void windowActivated(WindowEvent e) {
 
            }
 
            @Override
            public void windowDeactivated(WindowEvent e) {
 
            }
        });
		

	}
	public int findID(String logid) {
		int id=1;
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1,logid);//参数是？的位置+？的内容
			res = presta.executeQuery();
			res.next();
			id=res.getInt("ID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return id;
	}


	/*
	 * 连接数据库来查询对应loginid的昵称
	 */
	public String connected(String logid) {
		String username="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setString(1,logid);//参数是？的位置+？的内容
			res = presta.executeQuery();
			res.next();
			username = res.getString("name");
			this.ID=res.getInt("ID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return username;
	}
	/*
	 * 根据自己的ID来查询朋友的昵称并添加到列表上面
	 * 根据ID查询加入的群聊并且添加到面板上面
	 */
	public void findfriendid(int id) {
		String username="";
		String userid="";
		String myid="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			//查询好友
			String sql1="select * from friendlist where f1 =  ? " ;
			String sql2="select * from friendlist where f2 =  ? " ;
			//查询群聊
			String sql3="SELECT * FROM `group` where menberid =   ? " ;
			
			String sql4 ="SELECT * FROM `message` where reciver =   ? " ;
			presta= conn.prepareStatement(sql1);
			//给？赋值
			presta.setInt(1,id);//参数是？的位置+？的内容
			res = presta.executeQuery();
			while(res.next()) {
				int FID=res.getInt("f2");
				System.out.println("好友id1："+FID);
				username=findfriendname(FID);
				userid=findfriendloginid(FID);
				myid=findfriendloginid(id);
				addfriendtopanel(username,userid,myid);
			}
			presta= conn.prepareStatement(sql2);

			presta.setInt(1,id);//参数是？的位置+？的内容
			res = presta.executeQuery();
			while(res.next()) {
				int FID=res.getInt("f1");
				System.out.println("好友id2："+FID);
				username=findfriendname(FID);
				userid=findfriendloginid(FID);
				myid=findfriendloginid(id);
				addfriendtopanel(username,userid,myid);
			}	
			presta= conn.prepareStatement(sql3);
			
			presta.setString(1,Loginid);//参数是？的位置+？的内容
			res = presta.executeQuery();
			while(res.next()) {
				String groupname=res.getString("groupname");
				System.out.println("群昵称是："+groupname);
				addgroup(groupname);
			}
			presta= conn.prepareStatement(sql4);
			presta.setString(1,Loginid);//参数是？的位置+？的内容
			res = presta.executeQuery();
			while(res.next()) {
				String sender=res.getString("sender");
				String content=res.getString("content");
				String reciver=res.getString("reciver");
				String type=res.getString("type");
				addmessage(sender,content,type);
			}
			String sql5 = "DELETE  FROM `message` where reciver =   ? ";
			presta= conn.prepareStatement(sql5);
			presta.setString(1,Loginid);//参数是？的位置+？的内容
			presta.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	//把消息添加到面板上面
	public void addmessage(String sender,String content,String type) {
		//第三类消息就是好友申请
		if(type.equals("3")) {
			JLabel jlabel11 = new JLabel(sender+content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("宋体", Font.PLAIN, 24));
			//菜单
			JPopupMenu popuMenu = new JPopupMenu();
			
			//给菜单添加按钮组件 私聊  /群聊
			JMenuItem ac = new JMenuItem("接受");
			ac.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					String username=Findfriendname(sender);
					addfriendtopanel(username,sender,Loginid);
					Queryfriend.addlist(ID, findID(sender));
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("4");
					tfi.setReciver(sender);
					tfi.setNotice(Loginid+" 已经同意好友申请");
					iostream.writemessage(socket, tfi);
					
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(ac);
			
			JMenuItem delete = new JMenuItem("拒绝");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("1");
					tfi.setReciver(sender);
					tfi.setNotice(" 拒绝了好友申请");
					iostream.writemessage(socket, tfi);
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
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
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
		}else if(type.equals("2")) {
			
			JLabel jlabel11 = new JLabel(sender+content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("宋体", Font.PLAIN, 24));
			//菜单
			JPopupMenu popuMenu = new JPopupMenu();
			
			//给菜单添加按钮组件 私聊  /群聊
			JMenuItem ac = new JMenuItem("接受");
			ac.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					String username=Findfriendname(sender);
					String gname = content.substring(4);
					addgroup(gname);
					
					ADDtogroup.addtogroup(gname, Loginid);
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("1");
					tfi.setReciver(ADDtogroup.findowner(gname));
					tfi.setNotice(Loginid+" 已经加入"+gname);
					iostream.writemessage(socket, tfi);
					
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(ac);
			
			JMenuItem delete = new JMenuItem("拒绝");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("1");
					tfi.setReciver(sender);
					tfi.setNotice(" 拒绝加入群聊");
					iostream.writemessage(socket, tfi);
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
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
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
			
		}else if(type.equals("1")) {
			//普通消息，只有删除的操作
			JLabel jlabel11 = new JLabel(sender+content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("宋体", Font.PLAIN, 24));
			//菜单
			JPopupMenu popuMenu = new JPopupMenu();
			JMenuItem delete = new JMenuItem("删除");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			
			popuMenu.add(delete);
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
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
		}else if(type.equals("4")) {
			//除了删除之外还要把好友添加到列表上
			JLabel jlabel11 = new JLabel(content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("宋体", Font.PLAIN, 24));
			JPopupMenu popuMenu = new JPopupMenu();
			JMenuItem delete = new JMenuItem("删除");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					String username=Findfriendname(sender);
					addfriendtopanel(username,sender,Loginid);
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			
			popuMenu.add(delete);
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
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
		}
		
	}
	public void addgroup(String gname) {
		
		JLabel jlabel = new JLabel(gname,new ImageIcon("image/头像6.png"),JLabel.LEFT);
		List<String> groupmenber= new ArrayList<>();
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			//查询好友
			String sql1="SELECT * FROM `group` where groupname =  ? " ;
			
			presta= conn.prepareStatement(sql1);
			//给？赋值
			presta.setString(1,gname);//参数是？的位置+？的内容
			res = presta.executeQuery();
			while(res.next()) {
				String men = res.getString("menberid");
				groupmenber.add(men);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		jlabel.addMouseListener(new MouseListener() {

			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("开始群聊");
				Groupchat group = new Groupchat(Loginid,socket,groupmenber,gname);
				
				String filePath="D:\\Chat\\Util\\"+gname+".txt";
				createFile(filePath);
				readFile(filePath,group.acceptPane);
				
				Mangegroupframe.addchat(Loginid+" "+gname, group);
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
		panel.add(jlabel);
	}
	/*
	 * 连接数据库来查询对应ID的loginid
	 */
	public String findfriendloginid(int ID) {
		String userid="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where ID = ? " ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setInt(1, ID);//参数是？的位置+？的内容
			res = presta.executeQuery();
			res.next();
			userid = res.getString("loginid");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return userid;
	}
	public String Findfriendname(String ID) {
		String username="";
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
			username = res.getString("name");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return username;
	}
	/*
	 * 连接数据库来查询对应ID的昵称
	 */
	public String findfriendname(int ID) {
		String username="";
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		try {
			//获取连接
			conn=JDBCtool.getconnection();
			String sql="select * from user where ID = ? " ;
			presta= conn.prepareStatement(sql);
			//给？赋值
			presta.setInt(1, ID);//参数是？的位置+？的内容
			res = presta.executeQuery();
			res.next();
			username = res.getString("name");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		return username;
	}
	//添加好友
	int x=1;
	public void  addfriendtopanel(String username,String userid,String myid) {
	
		String path1 = "image/朋友";
		if(x>7)x=1;
		path1 = path1+Integer.toString(x++);
		path1+=".png";
		JLabel jlabel = new JLabel(username,new ImageIcon(path1),JLabel.LEFT);
		jlabel.setBackground(Color.BLUE);
		
		//菜单
		JPopupMenu popuMenu = new JPopupMenu();
		
		//给菜单添加按钮组件 私聊  /群聊
		JMenuItem privateChat = new JMenuItem("发送消息");
		privateChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//传递 自己的账号+socket   聊天对象的账号 +name
				Privatechat privatechat  = new Privatechat(Loginid,socket,userid,username);
				String filePath="D:\\Chat\\Util\\"+myid+"to"+userid+".txt";
				createFile(filePath);
				readFile(filePath,privatechat.acceptPane);
				MangerChatframe.addchat(Loginid+" "+userid, privatechat);
			}
		});
		popuMenu.add(privateChat);
		
		JMenuItem groupChat = new JMenuItem("删除好友");
		groupChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int f1=ID;
				int f2=findID(userid);
				Queryfriend.deletefriend(f1, f2);
				panel_1.remove(jlabel);
				panel_1.repaint();
			}
		});
		popuMenu.add(groupChat);
		
		//添加监听器
		jlabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				popuMenu.show(jlabel,e.getX(),e.getY());
				
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
		jlabel.setFont(new Font("宋体", Font.PLAIN, 24));
		panel_1.add(jlabel);
	}
	class MywindowListener extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			
			System.out.println("window is closed !");super.windowClosing(e);
//			if(when.equals("friendwait")) {
//				FirendandmessageMap.friendmessagewindowsmap.remove(friendnumber);
//			}
//			else if(when.equals("getfriendwait")){
//				FirendandmessageMap.friendmessagewindowsmap.remove("来自"+friendnumber+"的好友申请");
//			}
//			//这里告知服务器删掉窗体map.表示窗体关闭。表示可以在生成窗口了。
		}
	}
	
	private static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            // 判断文件是否存在
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void readFile(String filePath,JTextPane textPane) {
        InputStreamReader in = null;
        BufferedReader br = null;
        try {
            File file = new File(filePath);
            in = new InputStreamReader(new FileInputStream(file), "gbk");
            br = new BufferedReader(in);
            String s;
            // 逐行读取
            while ((s = br.readLine()) != null) {
            	FontSupport.contentAppend(textPane,s+"\n");
            	
            	
            }
            br.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


