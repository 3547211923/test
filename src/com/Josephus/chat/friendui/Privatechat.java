package com.Josephus.chat.friendui;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.Faceframe;
import com.Josephus.chat.Tools.FontStyle;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.sendpic;
import com.Josephus.chat.Tools.transferinfo;

public class Privatechat extends JFrame {

	static final long serialVersionUID = -565356834623395299L;
	public JTextPane acceptPane;// ���ܿ�����Ϊpublic�������
	public JList peolist;// ��ǰ�û������б�
	JComboBox font;// ����
	Privatechat Chatroom;

	public Privatechat(String userid, Socket mysocket, String fuserid, String friendname) {
		Chatroom = this;
		// this.socket=socket;
		// this.username=username;
		this.setTitle(friendname);
		ImageIcon icon = new ImageIcon("image/chatroompict.png");
		this.setIconImage(icon.getImage());
		setSize(750, 600);
		// ������󻯰�ť
		setResizable(false);
		// ��ȡ��Ļ��С�����д���
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w - 750) / 2, (h - 600) / 2);

		// ��������ӱ���ͼ
		ImageIcon image = new ImageIcon("image/chatroomback.png");
		JLabel frame = new JLabel(image);
		frame.setBounds(0, 0, 750, 600);// ͼƬλ�úʹ�С
		this.add(frame);

		// �������Ľ��ܿ�
		acceptPane = new JTextPane();
		acceptPane.setOpaque(false);// ����͸��
		acceptPane.setFont(new Font("����", 0, 16));

		// ���ܿ�Ĺ�����
		JScrollPane scone = new JScrollPane(acceptPane);
		scone.setBounds(15, 20, 500, 332);
		scone.setOpaque(false);
		scone.getViewport().setOpaque(false);
		frame.add(scone);

		// �Ҳ�����ʾ��ǰ�����û�����
		peolist = new JList();
		peolist.setFont(new Font("����", 0, 14));
		peolist.setVisibleRowCount(17);
		peolist.setFixedCellHeight(18);
		peolist.setFixedCellWidth(180);

		JScrollPane peopane = new JScrollPane(peolist);
		peopane.setFont(new Font("����", 0, 14));
		peopane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		peopane.setBounds(530, 17, 200, 507);
		frame.add(peopane);

		// �������������
		JTextPane send = new JTextPane();
		// ImageIcon image2 =new ImageIcon("image/chai.png");
		// send.insertIcon(image2);
		send.setOpaque(false);// ����͸��
		send.setFont(new Font("����", 0, 16));
		// ���ܿ�Ĺ�����
		JScrollPane sctwo = new JScrollPane(send);
		sctwo.setBounds(15, 400, 500, 122);
		sctwo.setOpaque(false);
		sctwo.getViewport().setOpaque(false);
		frame.add(sctwo);

		// ����ѡ��
		JLabel orzqwq = new JLabel(new ImageIcon("image/biaoqin.png"));
		orzqwq.setBounds(14, 363, 25, 25);
		orzqwq.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Faceframe face = new Faceframe(send);
			}
		});
		frame.add(orzqwq);

		// ����Ч��
		JLabel dzz = new JLabel(new ImageIcon("image/doudong.png"));
		dzz.setBounds(50, 363, 28, 25);
		dzz.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				new Douyidou(Chatroom).start();

				transferinfo tfi = new transferinfo();
				tfi.setStatusenum(Chatstastu.DD);
				tfi.setSender(userid);

				tfi.setReciver(fuserid);
				iostream.writemessage(mysocket, tfi);
			}
		});
		frame.add(dzz);

		// ͼƬ
		JLabel nothing = new JLabel(new ImageIcon("image/sendpic.png"));
		nothing.addMouseListener(new MouseAdapter() {
			public File browsefile;

			@Override
			public void mouseClicked(MouseEvent e) {
				final JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg", "gif", "png","gif"); // �����ļ������� gif�� .jpg�� .bmp�� .png�� .jpeg
				filechooser.setFileFilter(filter);
				try {
					filechooser.showOpenDialog(null); //
				} catch (HeadlessException ex) {
					System.out.println("HeadlessException");
					ex.printStackTrace();
				}
				browsefile = filechooser.getSelectedFile();
				Icon icon = new ImageIcon(browsefile.getAbsolutePath());// ���ͼ���ļ�
				send.insertIcon(icon);
			}
		});
		nothing.setBounds(90, 363, 25, 25);
		frame.add(nothing);

		// �ļ�ѡ��
		JLabel doc = new JLabel(new ImageIcon("image/senddoc.png"));
		doc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("�����ļ�");
				new Filesendframe(mysocket, userid, fuserid);
			}
		});
		doc.setBounds(130, 363, 27, 25);

		frame.add(doc);

		// ����
		JLabel voice = new JLabel(new ImageIcon("image/voice.png"));
		voice.setBounds(170, 363, 27, 25);
		voice.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Voicemessage voicemassage = new Voicemessage(mysocket, userid, fuserid);
				voicemassage.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override // �����������
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl = (JLabel) e.getSource();
				jl.setForeground(Color.blue);
			}

			@Override // ����뿪���
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl = (JLabel) e.getSource();
				jl.setForeground(Color.black);
			}

		});
		frame.add(voice);

		// ����ѡ��
		JLabel chin = new JLabel(new ImageIcon("image/ziti.png"));
		chin.setBounds(210, 363, 25, 25);
		chin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JColorChooser colorchooser = new JColorChooser();
				Color color = colorchooser.showDialog(Privatechat.this, "������ɫ", Color.BLACK);
				// ����ı�
				FontSupport.setFont(send, color, font.getSelectedItem().toString(), Font.BOLD, 20);
			}
		});
		frame.add(chin);

		font = new JComboBox();
		GraphicsEnvironment gra = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] str = gra.getAvailableFontFamilyNames();
		for (String string : str) {
			font.addItem(string);
		}
		font.setSelectedItem("����");
		font.setBounds(240, 363, 150, 25);
		frame.add(font);

		// ������ѡ��
		JComboBox often = new JComboBox();
		often.addItem("������");
		often.addItem("��������");
		often.addItem("���ˣ�");
		often.addItem("ORZ");
		often.addItem("QWQ");
		often.addItem("tql");
		often.addItem("����������ô����");
		often.addItem("������ˢ������");
		often.setSelectedItem("������");
		often.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// ѡ���������ѡ��
					System.out.println(e.getItem());

					FontSupport.contentAppend(send, e.getItem().toString());
				}
			}
		});
		often.setBounds(15, 533, 150, 25);
		frame.add(often);

		// ���Ͱ�ť
		JButton send1 = new JButton("����");
		send1.setBounds(389, 533, 125, 25);
		send1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				transferinfo tif = new transferinfo();// transferinfo����Ǳ���һ���˺ź�����

				// tif.setContent(content);
				// ��װ���������ֶ����Ӧ������
				List<FontStyle> fontSupport = FontSupport.fontEncode(send);
				
				addtofile(send.getText(),userid,fuserid);
				
				tif.setContent(fontSupport);

				tif.setSender(userid);
				tif.setReciver(fuserid);

				tif.setStatusenum(Chatstastu.CHAT);

				iostream.writemessage(mysocket, tif);

				send.setText("");
			}
		});
		frame.add(send1);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�JFrameʱ���˳�����
		// ���ÿɼ�
		setVisible(true);

	}

	public void addtofile(String content,String sender,String reciver) {
		if(content==null)return;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String dateName = df.format(calendar.getTime());
		FileWriter fw = null;
		FileWriter fw2 = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File f = new File("D:\\Chat\\Util\\"+sender+"to"+reciver+".txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(sender+"   "+dateName);
		
		pw.println(content);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ� 
			File f1 = new File("D:\\Chat\\Util\\"+reciver+"to"+sender+".txt");
			fw2 = new FileWriter(f1, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw2 = new PrintWriter(fw2);
		pw2.println(sender+"   "+dateName);
		
		
		
		pw2.println(content);
		pw2.flush();
		try {
			fw2.flush();
			pw2.close();
			fw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
