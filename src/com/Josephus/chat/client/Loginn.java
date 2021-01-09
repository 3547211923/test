/**
 * Function��chatroom�ͻ��˵�½����
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
	JLabel jbl1,jptom,jpchai;//���山����Ҫ�����
	
	
	//�����в���Ҫ�����
	//�в���һ��������JPanel������ʹ��ѡ����
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;//���ֿ�
	JButton jp2_jb1,jp2_jb2;//һ����ť
	JTextField jp2_jtf;//�˺�
	JPasswordField jp2_jpf;//����
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�����ϲ���Ҫ�����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		Loginn login = new Loginn();
	}
	JFrame fram;
	public Loginn(){
		
		//������
		jbl1 = new JLabel(new ImageIcon("image/��¼���涥��.png"));//���붥��ͼƬ
		this.fram=this;
		//�����ϲ�
		jp1=new JPanel();
		
		jp1_jb1= new JButton(new ImageIcon("image/p3.png"));
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/p2.png"));//ȡ��
		jp1_jb2.addActionListener(this);
		jp1_jb3=new JButton(new ImageIcon("image/p1.png"));//ע��
		jp1_jb3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new registerframe();
			}
		});
		
		//��������ť����jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		
		//�����в�
		jp2= new JPanel (new GridLayout(3,3));//
		jp2_jbl1=new JLabel("QQ����",JLabel.CENTER);
		
		
		jp2_jbl2=new JLabel("QQ����",JLabel.CENTER);
		
		
		jp2_jb1=new JButton (new ImageIcon("image/�������.png"));
		jp2_jb1.addActionListener(this);
		jp2_jb2=new JButton (new ImageIcon("image/��������.png"));
		jp2_jb2.addActionListener(this);
		jp2_jtf= new JTextField();//�����
		jp2_jpf=new JPasswordField();//�����
		jp2_jbl4=new JLabel("���뱣��",JLabel.CENTER);
		jp2_jcb1 =new JCheckBox("�����¼");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�����Ͽؼ�����jp2,˳���ر���Ҫ
		jp2.add(jp2_jbl1);jp2.add(jp2_jtf);jp2.add(jp2_jb1);//qq�˺š��˺ſ�clear����
		jp2.add(jp2_jbl2);jp2.add(jp2_jpf);jp2.add(jp2_jb2);//qq���롢�������������
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);jp2.add(jp2_jbl4);//�����¼����ס���롢���뱣��
		
		jtp = new JTabbedPane();//����һ��ѡ�����
		jtp.add("QQ����",jp2);
		jp3= new JPanel();jp4=new JPanel();
		jptom = new JLabel(new ImageIcon("image/Tom.png"));jp3.add(jptom);
		jpchai = new JLabel(new ImageIcon("image/chai.png"));jp4.add(jpchai);
		
		jtp.add("Tom",jp3);jtp.add("Chai",jp4);
		
		this.add(jtp,"Center");
		this.add(jbl1,"North");//�Ѷ�����ͼƬ������ߣ�������λ��
		this.add(jp1,"South");//װ������ѡ�ť��jp1�����ϲ�
		this.setSize(535,410);//ͼƬ���С,ͼƬ��СΪ407*216
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		this.setLocation((w-535)/2,(h-410)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�JFrameʱ���˳�����
		this.setTitle("QQ");
		//ImageIcon icon=new ImageIcon("image/pict.png"); 
		//this.setIconImage(icon.getImage());
		this.setVisible(true);//�ɼ�
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����û������¼
		if(e.getSource()==jp1_jb1)
		{
			System.out.println("��֤��¼��Ϣ");
			toConServer tocon=new toConServer();
			String userName =jp2_jtf.getText();//�����˺ŵ��ı���
			String password = jp2_jpf.getText();//������ı���
			
			transferinfo tif=new transferinfo();//transferinfo����Ǳ���һ���˺ź�����
			tif.setUserName(userName);
			tif.setPassword(password);
			//���δ������Ϣ����Ϊ��½
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
					
					friendf friendlist =new friendf(loginid,tocon.getsocket());//�������б��������Լ����˺ź�socket
					friendlist.setVisible(true);
					ManageQqFriendList.addQqFriendList(tif.getUserName(), friendlist);

				} catch (Exception x) {
					x.printStackTrace();
					// TODO: handle exception
				}
				//�رյ���¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"�û����������");
			}
		}else if(e.getSource()==jp1_jb2) {
			System.out.println("�ر�");
			this.dispose();
		}else if(e.getSource()==jp2_jb1) {
			jp2_jtf.setText("");
		}else if(e.getSource()==jp2_jb2) {
			new findpassword().setVisible(true);
		}
	}
}
