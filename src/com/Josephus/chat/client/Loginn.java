/**
 * Function：chatroom客户端登陆界面
 */
package com.Josephus.chat.client;
import javax.swing.*;

import com.Josephus.chat.Register.registerframe;
import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.FontStyle;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.ManageQqFriendList;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;

import java.awt.*;
import java.awt.event.*;
//import java.awt.event.*;
import java.util.List;

public class Loginn extends JFrame implements ActionListener{
	JLabel jbl1,jptom,jpchai;//定义北部需要的组件
	
	
	//定义中部需要的组件
	//中部有一个有三个JPanel，所以使用选项卡面板
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;//文字框
	JButton jp2_jb1,jp2_jb2;//一个按钮
	JTextField jp2_jtf;//账号
	JPasswordField jp2_jpf;//密码
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		Loginn login = new Loginn();
	}
	JFrame fram;
	public Loginn(){
		
		//处理北边
		jbl1 = new JLabel(new ImageIcon("image/登录界面顶部.png"));//插入顶部图片
		this.fram=this;
		//处理南部
		jp1=new JPanel();
		
		jp1_jb1= new JButton(new ImageIcon("image/p3.png"));
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/p2.png"));//取消
		jp1_jb2.addActionListener(this);
		jp1_jb3=new JButton(new ImageIcon("image/p1.png"));//注册
		jp1_jb3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new registerframe();
			}
		});
		
		//把三个按钮放入jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		
		//处理中部
		jp2= new JPanel (new GridLayout(3,3));//
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		
		
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		
		
		jp2_jb1=new JButton (new ImageIcon("image/清除号码.png"));
		jp2_jb1.addActionListener(this);
		jp2_jb2=new JButton (new ImageIcon("image/忘记密码.png"));
		jp2_jb2.addActionListener(this);
		jp2_jtf= new JTextField();//输入框
		jp2_jpf=new JPasswordField();//密码框
		jp2_jbl4=new JLabel("密码保护",JLabel.CENTER);
		jp2_jcb1 =new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把以上控件加入jp2,顺序特别重要
		jp2.add(jp2_jbl1);jp2.add(jp2_jtf);jp2.add(jp2_jb1);//qq账号、账号框、clear密码
		jp2.add(jp2_jbl2);jp2.add(jp2_jpf);jp2.add(jp2_jb2);//qq密码、密码框、忘记密码
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);jp2.add(jp2_jbl4);//隐身登录、记住密码、密码保护
		
		jtp = new JTabbedPane();//创建一个选项卡窗口
		jtp.add("QQ号码",jp2);
		jp3= new JPanel();jp4=new JPanel();
		jptom = new JLabel(new ImageIcon("image/Tom.png"));jp3.add(jptom);
		jpchai = new JLabel(new ImageIcon("image/chai.png"));jp4.add(jpchai);
		
		jtp.add("Tom",jp3);jtp.add("Chai",jp4);
		
		this.add(jtp,"Center");
		this.add(jbl1,"North");//把顶部的图片放在最北边，即顶部位置
		this.add(jp1,"South");//装有三个选项按钮的jp1放在南部
		this.setSize(535,410);//图片框大小,图片大小为407*216
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		this.setLocation((w-535)/2,(h-410)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭JFrame时，退出程序
		this.setTitle("QQ");
		//ImageIcon icon=new ImageIcon("image/pict.png"); 
		//this.setIconImage(icon.getImage());
		this.setVisible(true);//可见
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(e.getSource()==jp1_jb1)
		{
			System.out.println("验证登录消息");
			toConServer tocon=new toConServer();
			String userName =jp2_jtf.getText();//这是账号的文本框
			String password = jp2_jpf.getText();//密码的文本框
			
			transferinfo tif=new transferinfo();//transferinfo类就是保存一下账号和密码
			tif.setUserName(userName);
			tif.setPassword(password);
			//本次处理的消息类型为登陆
			tif.setStatusenum(Chatstastu.LoGIN);
			if(tocon.checkUser(tif))
			{
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {//Motif,Windows,Nimbus
		                    try {
		                    	
								javax.swing.UIManager.setLookAndFeel(info.getClassName());
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace(); 
							}
		                    break;
		                }
		            }
		       
				try {
					String loginid = tif.getUserName();
					
					friendf friendlist =new friendf(loginid,tocon.getsocket());//给好友列表传递了我自己的账号和socket
					friendlist.setVisible(true);
					ManageQqFriendList.addQqFriendList(tif.getUserName(), friendlist);

				} catch (Exception x) {
					x.printStackTrace();
					// TODO: handle exception
				}
				//关闭掉登录界面
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"用户名密码错误");
			}
		}else if(e.getSource()==jp1_jb2) {
			System.out.println("关闭");
			this.dispose();
		}else if(e.getSource()==jp2_jb1) {
			jp2_jtf.setText("");
		}else if(e.getSource()==jp2_jb2) {
			new findpassword().setVisible(true);
		}
	}
}
