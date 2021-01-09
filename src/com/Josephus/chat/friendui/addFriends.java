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
		ImageIcon icon=new ImageIcon("image/��Ӻ��ѿ���.png");  //xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
		this.setIconImage(icon.getImage());
		//��ȡ��Ļ�Ĵ�С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w-100)/2,(h-100)/2);

		jframe=this;
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u662F\u5426\u6DFB\u52A0\u5BF9\u65B9\u4E3A\u597D\u53CB\uFF1F");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 24));
		lblNewLabel.setBounds(14, 13, 286, 57);
		contentPane.add(lblNewLabel);
		String ans= lblNewLabel.getText();
		

		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"���������ѷ���");
				friendf Friendframe= ManageQqFriendList.getQqFriendList(myid);

				//Queryfriend.addmessage(myid, reciver, "������Ӻ���");//���ݿ����Ϣ���

				//����֪ͨ�������������ߺ���������Ϣ
				transferinfo tfi =new transferinfo();
				tfi.setStatusenum(Chatstastu.FriendApply);
				tfi.setSender(myid);
				tfi.setType("3");
				tfi.setReciver(reciver);
				tfi.setNotice("������Ӻ���");
				iostream.writemessage(socket, tfi);
				
				
				JLabel jlabel11 = new JLabel("���������ѷ���",new ImageIcon("image/messagetip.png"),JLabel.LEFT);
				jlabel11.setFont(new Font("����", Font.PLAIN, 24));
				//�˵�
				JPopupMenu popuMenu = new JPopupMenu();
				
				//���˵���Ӱ�ť��� ˽��  /Ⱥ��
				JMenuItem delete = new JMenuItem("ɾ��");
				delete.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						Friendframe.xiaoxi.remove(jlabel11);
						Friendframe.xiaoxi.repaint();
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
