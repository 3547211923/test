package com.Josephus.chat.Register;
/*
 * 注册界面
 * 
 */
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Josephus.chat.Dao.JDBCtool;
import com.Josephus.chat.Tools.transferinfo;
import com.Josephus.chat.client.clienthander;
import com.Josephus.chat.sever.sendemail.trytosendemail;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class registerframe extends JFrame {

	private JPanel contentPane;
	private JTextField nametext;
	private JTextField idtext;
	private JPasswordField passwordtext;
	private JTextField birthdaytext;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JTextField emailtext;
	private JTextField emailpassword;
	String flag_sex="男";
	String emailnum="";
	boolean flag_email=false;
	private JPasswordField quedinpassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerframe frame = new registerframe();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registerframe() {
		setBackground(Color.WHITE);
		setTitle("\u6CE8\u518C");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 595);
		contentPane = new JPanel();
		contentPane.setToolTipText("\u6CE8\u518C");
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//昵称
		JLabel lblNewLabel = new JLabel("\u6635\u79F0");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(61, 13, 70, 45);
		contentPane.add(lblNewLabel);
		
		nametext = new JTextField();
		nametext.setFont(new Font("宋体", Font.PLAIN, 20));
		nametext.setToolTipText("\u6635\u79F0");
		nametext.setBounds(145, 17, 252, 39);
		contentPane.add(nametext);
		nametext.setColumns(10);
		
		//账号
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(61, 75, 72, 34);
		contentPane.add(lblNewLabel_1);
		
		idtext = new JTextField();
		idtext.setFont(new Font("宋体", Font.PLAIN, 20));
		idtext.setBackground(new Color(255, 255, 255));
		idtext.setToolTipText("\u8D26\u53F7");
		idtext.setBounds(145, 75, 252, 39);
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		//密码
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(59, 130, 72, 45);
		contentPane.add(lblNewLabel_2);
		
		passwordtext = new JPasswordField();
		passwordtext.setFont(new Font("宋体", Font.PLAIN, 20));
		passwordtext.setToolTipText("\u5BC6\u7801");
		passwordtext.setBounds(145, 130, 252, 39);
		contentPane.add(passwordtext);
		
		//性别
		JLabel lblNewLabel_3 = new JLabel("\u6027\u522B");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(61, 250, 72, 45);
		contentPane.add(lblNewLabel_3);
		
		
		JButton btnNewButton = new JButton("\u7537");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 flag_sex="男";
				 System.out.println("性别设置为男");
			}
		});
		btnNewButton.setToolTipText("\u6027\u522B\u7537");
		btnNewButton.setForeground(Color.BLUE);
		buttonGroup.add(btnNewButton);
		btnNewButton.setFont(new Font("仿宋", Font.PLAIN, 13));
		btnNewButton.setBounds(147, 260, 47, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5973");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag_sex="女";
				System.out.println("性别设置为女");
			}
		});
		btnNewButton_1.setToolTipText("\u6027\u522B\u5973");
		btnNewButton_1.setForeground(Color.PINK);
		btnNewButton_1.setBounds(242, 260, 53, 27);
		contentPane.add(btnNewButton_1);
		
		
		//生日
		JLabel lblNewLabel_4 = new JLabel("\u751F\u65E5");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(61, 310, 72, 45);
		contentPane.add(lblNewLabel_4);
		
		
		birthdaytext = new JTextField();
		birthdaytext.setFont(new Font("宋体", Font.PLAIN, 20));
		birthdaytext.setToolTipText("\u751F\u65E5");
		birthdaytext.setBounds(147, 310, 252, 39);
		contentPane.add(birthdaytext);
		birthdaytext.setColumns(10);
		 
		// 定义日历控件面板类
		CalendarPanel p = new CalendarPanel(birthdaytext, "yyyy/MM/dd");
		p.initCalendarPanel();
 
		JLabel l = new JLabel("日历面板");
		p.add(l);
		this.getContentPane().add(p);
		
		//邮箱
		JLabel lblNewLabel_5 = new JLabel("\u90AE\u7BB1");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(59, 370, 72, 45);
		contentPane.add(lblNewLabel_5);
		
		emailtext = new JTextField();
		emailtext.setFont(new Font("宋体", Font.PLAIN, 20));
		emailtext.setToolTipText("\u90AE\u7BB1");
		emailtext.setBounds(145, 370, 252, 39);
		contentPane.add(emailtext);
		emailtext.setColumns(10);
		
		//验证码
		JLabel lblNewLabel_6 = new JLabel("\u9A8C\u8BC1\u7801");
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(52, 430, 91, 45);
		contentPane.add(lblNewLabel_6);
		
		emailpassword = new JTextField();
		emailpassword.setFont(new Font("宋体", Font.PLAIN, 20));
		emailpassword.setToolTipText("\u9A8C\u8BC1\u7801");
		emailpassword.setBounds(145, 430, 252, 39);
		contentPane.add(emailpassword);
		emailpassword.setColumns(10);
		
		//邮箱验证按钮
		JButton getemailid = new JButton("\u9A8C\u8BC1");
		getemailid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag_email=emailnum.equals(emailpassword.getText());
				if(flag_email)
				{
					JOptionPane.showMessageDialog(null, "验证码正确", "Tips",JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "验证码有误，请重新输入", "Tips",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getemailid.setToolTipText("\u9A8C\u8BC1\u90AE\u7BB1");
		getemailid.setFont(new Font("宋体", Font.PLAIN, 12));
		getemailid.setBounds(411, 430, 59, 39);
		contentPane.add(getemailid);

		//立即注册
		JButton registerbu = new JButton("\u7ACB\u5373\u6CE8\u518C");
		registerbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(nametext.getText().equals(""))//是否有昵称
				{
					JOptionPane.showMessageDialog(null, "请输入你的昵称", "Tips",JOptionPane.WARNING_MESSAGE);
				}
				else if(idtext.getText().equals(""))//是否有账号
				{
					JOptionPane.showMessageDialog(null, "请获取你的账号", "Tips",JOptionPane.WARNING_MESSAGE);
				}
				else if(!passwordtext.getText().equals(quedinpassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "两次密码输入不一致", "Tips",JOptionPane.WARNING_MESSAGE);
				}
				else if(birthdaytext.getText().equals(""))//是否选择了生日
				{
					JOptionPane.showMessageDialog(null, "请选择你的生日", "Tips",JOptionPane.WARNING_MESSAGE);
				}
				else if(flag_email)//这个里面封装刚刚的账号密码等等等
				{
					transregister tran = new transregister();
					JOptionPane.showMessageDialog(null, "注册成功", "Tips",JOptionPane.WARNING_MESSAGE);
					
					tran.setName(nametext.getText());tran.setLoginid(idtext.getText());tran.setPassword(passwordtext.getText());
					tran.setSex(flag_sex);tran.setBirthday(birthdaytext.getText());tran.setEmail(emailtext.getText());
					connectionSever(tran);
					dispose();
				}
				else
				{
					//邮箱验证失败，弹出提示框
					JOptionPane.showMessageDialog(null, "邮箱验证失败，请检查验证码！", "Tips",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		registerbu.setToolTipText("\u7ACB\u5373\u6CE8\u518C");
		registerbu.setFont(new Font("微软雅黑", Font.BOLD, 15));
		registerbu.setForeground(Color.BLACK);
		registerbu.setBounds(180, 489, 164, 34);
		contentPane.add(registerbu);
		
		//获取邮箱验证码按钮
		JButton getemail = new JButton("\u83B7\u53D6");
		getemail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String regex="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
				if(emailtext.getText().matches(regex))
				{
					JOptionPane.showMessageDialog(null, "验证码已发送请注意查收", "Tips",JOptionPane.ERROR_MESSAGE);
					emailnum=trytosendemail.sendmail(emailtext.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "邮箱不合法", "Tips",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		getemail.setToolTipText("\u83B7\u53D6\u90AE\u7BB1\u9A8C\u8BC1\u7801");
		getemail.setFont(new Font("宋体", Font.PLAIN, 12));
		getemail.setBounds(411, 370, 59, 39);
		contentPane.add(getemail);
		
		JButton getnumber = new JButton("\u83B7\u53D6");
		getnumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=getrandomnumber.getrandomnumber();
				System.out.println("获取账号成功"+s);
				idtext.setText(s);
			}
		});
		getnumber.setFont(new Font("宋体", Font.PLAIN, 12));
		getnumber.setBounds(411, 75, 59, 39);
		contentPane.add(getnumber);
		
		JLabel lblNewLabel_7 = new JLabel("\u786E\u5B9A\u5BC6\u7801");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblNewLabel_7.setBounds(42, 190, 91, 45);
		contentPane.add(lblNewLabel_7);
		
		quedinpassword = new JPasswordField();
		quedinpassword.setFont(new Font("宋体", Font.PLAIN, 20));
		quedinpassword.setBounds(145, 197, 252, 39);
		contentPane.add(quedinpassword);

		//居中处理
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w-549)/2,(h-537)/2);
		
		
		//给窗体添加背景图
		ImageIcon image =new ImageIcon("image/register.png");
		JLabel fframe = new JLabel(image);//万能的JLabel，2333
		fframe.setBounds(0,0,560,550);//图片位置和大小
		getContentPane().add(fframe);


		//更换小咖啡
		//ImageIcon icon=new ImageIcon("image/qqqie.png");  //括号内写图片路径
		//this.setIconImage(icon.getImage());
		this.setVisible(true);
	}
	public void connectionSever(transregister tran) {
		
		Connection conn = null;
		PreparedStatement  presta  = null;//要执行SQL语句首先要Statement对象
		ResultSet  res  = null;
		String name=tran.getName();
		String loginid=tran.getLoginid();
		String password=tran.getPassword();
		String sex=tran.getSex();
		String birthday=tran.getBirthday();
		String email=tran.getEmail();
		
		try {
			conn=JDBCtool.getconnection();
			String sql="insert into user(name,loginid,password,sex,birthday,email)values(?,?,?,?,?,?)";
			presta= conn.prepareStatement(sql);
			presta.setString(1, name);
			presta.setString(2, loginid);
			presta.setString(3, password);
			presta.setString(4, sex);
			presta.setString(5, birthday);
			presta.setString(6, email);
			presta.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCtool.close(presta, conn, res);
		}
		
		
		
		//System.exit(0);
//		try {
//			
//			//Socket socket = new Socket("127.0.0.1",8888);
//			//ioregister.writemessage(socket, tran);
//			//System.out.println("开始连接数据库保存数据");
//			//registerPreservation preservation = new registerPreservation(socket,this);
//			//preservation.start();
//			
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
