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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.Faceframe;
import com.Josephus.chat.Tools.FontStyle;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;

public class Groupchat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6297387213415789280L;
	public JTextPane acceptPane;// ���ܿ�����Ϊpublic�������
	public JList peolist;// ��ǰ�û������б�
	JComboBox font;// ����
	Groupchat Chatroom;
	String groupname;
	String userid;
	Socket mysocket;
	public Groupchat(String userid, Socket mysocket, List<String> groupmenber, String groupname) {
		Chatroom = this;
		 this.mysocket=mysocket;
		 this.userid=userid;
		this.setTitle(groupname);
		this.groupname = groupname;
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
		getContentPane().setLayout(null);
		JLabel frame = new JLabel(image);
		frame.setBounds(0, 0, 744, 565);// ͼƬλ�úʹ�С
		getContentPane().add(frame);

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
		// List<String> onlineUsers= new ArrayList<>();
		// JList lsUser=severFrame.onlinel.lsuer;1

		// String[] userArray = onlineUsers.toArray(new String [onlineUsers.size()]);
		String[] userArray = groupmenber.toArray(new String[groupmenber.size()]);
		peolist.setListData(userArray);

		JScrollPane peopane = new JScrollPane(peolist);
		peopane.setFont(new Font("����", 0, 14));
		peopane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		peopane.setBounds(530, 17, 200, 507);
		frame.add(peopane);

		// �������������
		JTextPane send = new JTextPane();
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
				// new Douyidou(Chatroom).start();

				transferinfo tfi = new transferinfo();
				tfi.setStatusenum(Chatstastu.DD);
				tfi.setSender(userid);

				// tfi.setReciver(fuserid);
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
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg", "gif", "png",
						"gif"); // �����ļ������� gif�� .jpg�� .bmp�� .png�� .jpeg
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
				new Groupfilesend(mysocket, userid, groupmenber, groupname);
			}
		});
		doc.setBounds(130, 363, 27, 25);

		frame.add(doc);

		// ����
		JLabel voice = new JLabel(new ImageIcon("image/voice.png"));
		voice.setBounds(170, 363, 27, 25);
		frame.add(voice);

		// ����ѡ��
		JLabel chin = new JLabel(new ImageIcon("image/ziti.png"));
		chin.setBounds(210, 363, 25, 25);
		chin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JColorChooser colorchooser = new JColorChooser();
				Color color = colorchooser.showDialog(Groupchat.this, "������ɫ", Color.BLACK);
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

				addtofile(send.getText(), userid);
				tif.setContent(fontSupport);

				tif.setSender(userid);
				tif.setGroupname(groupname);
				tif.setStatusenum(Chatstastu.GROUP);
				String[] userArray = groupmenber.toArray(new String[groupmenber.size()]);
				for (int i = 0; i < userArray.length; i++) {

					tif.setReciver(userArray[i]);
					iostream.writemessage(mysocket, tif);
				}
				send.setText("");
			}
		});
		frame.add(send1);

		JButton addfriends = new JButton("\u9080\u8BF7\u597D\u53CB");
		addfriends.setBounds(605, 533, 125, 25);
		addfriends.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Addfriframe(mysocket,userid,groupname);
				
			}
		});
		frame.add(addfriends);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر�JFrameʱ���˳�����
		// ���ÿɼ�
		setVisible(true);
		
	}

	public void addtofile(String content, String sender) {
		if (content == null)
			return;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String dateName = df.format(calendar.getTime());
		FileWriter fw = null;
		FileWriter fw2 = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File f = new File("C:\\Users\\Zhang\\eclipse-workspace\\�����¼\\" + groupname + ".txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(sender + "   " + dateName);

		pw.println(content);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
