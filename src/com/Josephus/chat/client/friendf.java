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

	public JPanel panel ;//Ⱥ��
	public JPanel panel_1;//��ϵ��
	public JPanel xiaoxi;//��Ϣ
	public int ID;
	Socket socket;//�Լ���socket
	String Loginid;//�˺�
	JFrame frame;
	public friendf(String loginid,Socket socket) {

		
		this.socket=socket;
		frame=this;
		this.Loginid=loginid;
		//ImageIcon icon=new ImageIcon("image/�����б�ͼ��.png");  //xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
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
		//ͷ��
		JLabel lblNewLabel = new JLabel("");
		String path1 = "image/ͷ��";
		int pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		lblNewLabel.setIcon(new ImageIcon(path1));
		lblNewLabel.setBounds(0, 0, 89, 68);
		lblNewLabel.setOpaque(false);  

		contentPane.add(lblNewLabel);
		
		//�ǳ� ��Ҫ��һ��STring���������Ϊ�ǳ�
		JLabel lblNewLabel_1 = new JLabel(name);
		
		lblNewLabel_1.setFont(new Font("����", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(119, 0, 116, 54);
		contentPane.add(lblNewLabel_1);
		
		//����
		JLabel lblNewLabel_2 = new JLabel("2020-01-02");
		lblNewLabel_2.setBounds(268, 50, 70, 18);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String time = formatter.format(date);
		lblNewLabel_2.setText(time);
		contentPane.add(lblNewLabel_2);
		
		//��������ӱ���ͼ
		ImageIcon image =new ImageIcon("image/severback.png");
		JLabel frame = new JLabel(image);//���ܵ�JLabel��2333
		frame.setBounds(0,0,349,70);//ͼƬλ�úʹ�С
		getContentPane().add(frame);


		//��Ӻ���JLAbel
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(0, 69, 34, 32);
		lblNewLabel_3.setIcon(new ImageIcon("image/��Ӻ���.png"));
		lblNewLabel_3.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// ��������� �������Լ����˺� ��socket �� Ⱥ��panel
				new Addfriend(loginid,socket,panel).setVisible(true);
				
			}
			public void mouseEntered(MouseEvent e) {
				// �����������
			}
			public void mouseExited(MouseEvent e) {
				// ��������뿪
			}
			public void mousePressed(MouseEvent e) {
				// ������갴��
			}
			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
			}
		});
		contentPane.add(lblNewLabel_3);
		
		//�˺�������
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("����", Font.PLAIN, 24));
		textField.setText("");
		textField.setBounds(36, 69, 232, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//�˺�������ť
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = textField.getText();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(268, 69, 70, 35);
		btnNewButton.setContentAreaFilled(false);  
		contentPane.add(btnNewButton);
		
		//Ⱥ�����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		scrollPane.setBounds(0, 146, 353, 542);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(50, 1,4,4));
		
		//��ϵ�����
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 146, 353, 542);
		scrollPane_1.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		contentPane.add(scrollPane_1);
		
		panel_1 = new JPanel();
		//panel_1.setBackground(new Color(224, 255, 255));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(50, 1,4,4));
		
		
		//��Ϣ���
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
		//��ϵ�˰�ť
		JButton btnNewButton_1 = new JButton("\u8054\u7CFB\u4EBA");
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
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
		
		//Ⱥ�İ�ť
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
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(243, 102, 100, 42);
		contentPane.add(btnNewButton_2);
		
		//��Ϣ��ť
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
		btnNewButton_3.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_3.setBounds(0, 102, 100, 42);
		contentPane.add(btnNewButton_3);
		 path1 = "image/ͷ��";
		 pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		JLabel jlabel = new JLabel("Josephus",new ImageIcon(path1),JLabel.LEFT);
		jlabel.setFont(new Font("����", Font.PLAIN, 24));
		
		findfriendid(ID);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
 
            }
 
            @Override
            public void windowClosing(WindowEvent e) {
                // �˴������������
            		JOptionPane.showMessageDialog(null,"�����˳���¼");
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
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1,logid);//�����ǣ���λ��+��������
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
	 * �������ݿ�����ѯ��Ӧloginid���ǳ�
	 */
	public String connected(String logid) {
		String username="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1,logid);//�����ǣ���λ��+��������
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
	 * �����Լ���ID����ѯ���ѵ��ǳƲ���ӵ��б�����
	 * ����ID��ѯ�����Ⱥ�Ĳ�����ӵ��������
	 */
	public void findfriendid(int id) {
		String username="";
		String userid="";
		String myid="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			//��ѯ����
			String sql1="select * from friendlist where f1 =  ? " ;
			String sql2="select * from friendlist where f2 =  ? " ;
			//��ѯȺ��
			String sql3="SELECT * FROM `group` where menberid =   ? " ;
			
			String sql4 ="SELECT * FROM `message` where reciver =   ? " ;
			presta= conn.prepareStatement(sql1);
			//������ֵ
			presta.setInt(1,id);//�����ǣ���λ��+��������
			res = presta.executeQuery();
			while(res.next()) {
				int FID=res.getInt("f2");
				System.out.println("����id1��"+FID);
				username=findfriendname(FID);
				userid=findfriendloginid(FID);
				myid=findfriendloginid(id);
				addfriendtopanel(username,userid,myid);
			}
			presta= conn.prepareStatement(sql2);

			presta.setInt(1,id);//�����ǣ���λ��+��������
			res = presta.executeQuery();
			while(res.next()) {
				int FID=res.getInt("f1");
				System.out.println("����id2��"+FID);
				username=findfriendname(FID);
				userid=findfriendloginid(FID);
				myid=findfriendloginid(id);
				addfriendtopanel(username,userid,myid);
			}	
			presta= conn.prepareStatement(sql3);
			
			presta.setString(1,Loginid);//�����ǣ���λ��+��������
			res = presta.executeQuery();
			while(res.next()) {
				String groupname=res.getString("groupname");
				System.out.println("Ⱥ�ǳ��ǣ�"+groupname);
				addgroup(groupname);
			}
			presta= conn.prepareStatement(sql4);
			presta.setString(1,Loginid);//�����ǣ���λ��+��������
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
			presta.setString(1,Loginid);//�����ǣ���λ��+��������
			presta.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
	}
	//����Ϣ��ӵ��������
	public void addmessage(String sender,String content,String type) {
		//��������Ϣ���Ǻ�������
		if(type.equals("3")) {
			JLabel jlabel11 = new JLabel(sender+content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("����", Font.PLAIN, 24));
			//�˵�
			JPopupMenu popuMenu = new JPopupMenu();
			
			//���˵���Ӱ�ť��� ˽��  /Ⱥ��
			JMenuItem ac = new JMenuItem("����");
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
					tfi.setNotice(Loginid+" �Ѿ�ͬ���������");
					iostream.writemessage(socket, tfi);
					
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(ac);
			
			JMenuItem delete = new JMenuItem("�ܾ�");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("1");
					tfi.setReciver(sender);
					tfi.setNotice(" �ܾ��˺�������");
					iostream.writemessage(socket, tfi);
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(delete);
			
			//��Ӽ�����
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

				@Override//�����������
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.blue);
				}

				@Override//����뿪���
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
			jlabel11.setFont(new Font("����", Font.PLAIN, 24));
			//�˵�
			JPopupMenu popuMenu = new JPopupMenu();
			
			//���˵���Ӱ�ť��� ˽��  /Ⱥ��
			JMenuItem ac = new JMenuItem("����");
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
					tfi.setNotice(Loginid+" �Ѿ�����"+gname);
					iostream.writemessage(socket, tfi);
					
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(ac);
			
			JMenuItem delete = new JMenuItem("�ܾ�");
			delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					
					transferinfo tfi =new transferinfo();
					tfi.setStatusenum(Chatstastu.FriendApply);
					tfi.setSender(Loginid);
					tfi.setType("1");
					tfi.setReciver(sender);
					tfi.setNotice(" �ܾ�����Ⱥ��");
					iostream.writemessage(socket, tfi);
					xiaoxi.remove(jlabel11);
					xiaoxi.repaint();
				}
			});
			popuMenu.add(delete);
			
			//��Ӽ�����
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

				@Override//�����������
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.blue);
				}

				@Override//����뿪���
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.black);
				}
				
				
			});
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
			
		}else if(type.equals("1")) {
			//��ͨ��Ϣ��ֻ��ɾ���Ĳ���
			JLabel jlabel11 = new JLabel(sender+content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("����", Font.PLAIN, 24));
			//�˵�
			JPopupMenu popuMenu = new JPopupMenu();
			JMenuItem delete = new JMenuItem("ɾ��");
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

				@Override//�����������
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.blue);
				}

				@Override//����뿪���
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.black);
				}
				
				
			});
			xiaoxi.add(jlabel11);
			xiaoxi.repaint();
		}else if(type.equals("4")) {
			//����ɾ��֮�⻹Ҫ�Ѻ�����ӵ��б���
			JLabel jlabel11 = new JLabel(content,new ImageIcon("image/messagetip.png"),JLabel.LEFT);
			jlabel11.setFont(new Font("����", Font.PLAIN, 24));
			JPopupMenu popuMenu = new JPopupMenu();
			JMenuItem delete = new JMenuItem("ɾ��");
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

				@Override//�����������
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl=(JLabel)e.getSource();
					jl.setForeground(Color.blue);
				}

				@Override//����뿪���
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
		
		JLabel jlabel = new JLabel(gname,new ImageIcon("image/ͷ��6.png"),JLabel.LEFT);
		List<String> groupmenber= new ArrayList<>();
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			//��ѯ����
			String sql1="SELECT * FROM `group` where groupname =  ? " ;
			
			presta= conn.prepareStatement(sql1);
			//������ֵ
			presta.setString(1,gname);//�����ǣ���λ��+��������
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
				System.out.println("��ʼȺ��");
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
			
			@Override//�����������
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.red);
			}

			@Override//����뿪���
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.black);
			}
			
			
		});
		jlabel.setFont(new Font("����", Font.PLAIN, 24));
		panel.add(jlabel);
	}
	/*
	 * �������ݿ�����ѯ��ӦID��loginid
	 */
	public String findfriendloginid(int ID) {
		String userid="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where ID = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setInt(1, ID);//�����ǣ���λ��+��������
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
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where loginid = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setString(1, ID);//�����ǣ���λ��+��������
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
	 * �������ݿ�����ѯ��ӦID���ǳ�
	 */
	public String findfriendname(int ID) {
		String username="";
		Connection conn = null;
		PreparedStatement  presta  = null;//Ҫִ��SQL�������ҪStatement����
		ResultSet  res  = null;
		try {
			//��ȡ����
			conn=JDBCtool.getconnection();
			String sql="select * from user where ID = ? " ;
			presta= conn.prepareStatement(sql);
			//������ֵ
			presta.setInt(1, ID);//�����ǣ���λ��+��������
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
	//��Ӻ���
	int x=1;
	public void  addfriendtopanel(String username,String userid,String myid) {
	
		String path1 = "image/����";
		if(x>7)x=1;
		path1 = path1+Integer.toString(x++);
		path1+=".png";
		JLabel jlabel = new JLabel(username,new ImageIcon(path1),JLabel.LEFT);
		jlabel.setBackground(Color.BLUE);
		
		//�˵�
		JPopupMenu popuMenu = new JPopupMenu();
		
		//���˵���Ӱ�ť��� ˽��  /Ⱥ��
		JMenuItem privateChat = new JMenuItem("������Ϣ");
		privateChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//���� �Լ����˺�+socket   ���������˺� +name
				Privatechat privatechat  = new Privatechat(Loginid,socket,userid,username);
				String filePath="D:\\Chat\\Util\\"+myid+"to"+userid+".txt";
				createFile(filePath);
				readFile(filePath,privatechat.acceptPane);
				MangerChatframe.addchat(Loginid+" "+userid, privatechat);
			}
		});
		popuMenu.add(privateChat);
		
		JMenuItem groupChat = new JMenuItem("ɾ������");
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
		
		//��Ӽ�����
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

			@Override//�����������
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.blue);
			}

			@Override//����뿪���
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.black);
			}
			
			
		});
		jlabel.setFont(new Font("����", Font.PLAIN, 24));
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
//				FirendandmessageMap.friendmessagewindowsmap.remove("����"+friendnumber+"�ĺ�������");
//			}
//			//�����֪������ɾ������map.��ʾ����رա���ʾ���������ɴ����ˡ�
		}
	}
	
	private static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            // �ж��ļ��Ƿ����
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
            // ���ж�ȡ
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


