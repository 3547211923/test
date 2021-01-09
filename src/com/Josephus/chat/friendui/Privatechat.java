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
	public JTextPane acceptPane;// 接受框设置为public方便访问
	public JList peolist;// 当前用户在线列表
	JComboBox font;// 字体
	Privatechat Chatroom;

	public Privatechat(String userid, Socket mysocket, String fuserid, String friendname) {
		Chatroom = this;
		// this.socket=socket;
		// this.username=username;
		this.setTitle(friendname);
		ImageIcon icon = new ImageIcon("image/chatroompict.png");
		this.setIconImage(icon.getImage());
		setSize(750, 600);
		// 禁用最大化按钮
		setResizable(false);
		// 获取屏幕大小、居中处理
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		setLocation((w - 750) / 2, (h - 600) / 2);

		// 给窗体添加背景图
		ImageIcon image = new ImageIcon("image/chatroomback.png");
		JLabel frame = new JLabel(image);
		frame.setBounds(0, 0, 750, 600);// 图片位置和大小
		this.add(frame);

		// 聊天界面的接受框
		acceptPane = new JTextPane();
		acceptPane.setOpaque(false);// 设置透明
		acceptPane.setFont(new Font("宋体", 0, 16));

		// 接受框的滚动条
		JScrollPane scone = new JScrollPane(acceptPane);
		scone.setBounds(15, 20, 500, 332);
		scone.setOpaque(false);
		scone.getViewport().setOpaque(false);
		frame.add(scone);

		// 右侧框框显示当前在线用户人数
		peolist = new JList();
		peolist.setFont(new Font("宋体", 0, 14));
		peolist.setVisibleRowCount(17);
		peolist.setFixedCellHeight(18);
		peolist.setFixedCellWidth(180);

		JScrollPane peopane = new JScrollPane(peolist);
		peopane.setFont(new Font("宋体", 0, 14));
		peopane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		peopane.setBounds(530, 17, 200, 507);
		frame.add(peopane);

		// 聊天界面的输入框
		JTextPane send = new JTextPane();
		// ImageIcon image2 =new ImageIcon("image/chai.png");
		// send.insertIcon(image2);
		send.setOpaque(false);// 设置透明
		send.setFont(new Font("宋体", 0, 16));
		// 接受框的滚动条
		JScrollPane sctwo = new JScrollPane(send);
		sctwo.setBounds(15, 400, 500, 122);
		sctwo.setOpaque(false);
		sctwo.getViewport().setOpaque(false);
		frame.add(sctwo);

		// 表情选项
		JLabel orzqwq = new JLabel(new ImageIcon("image/biaoqin.png"));
		orzqwq.setBounds(14, 363, 25, 25);
		orzqwq.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Faceframe face = new Faceframe(send);
			}
		});
		frame.add(orzqwq);

		// 抖动效果
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

		// 图片
		JLabel nothing = new JLabel(new ImageIcon("image/sendpic.png"));
		nothing.addMouseListener(new MouseAdapter() {
			public File browsefile;

			@Override
			public void mouseClicked(MouseEvent e) {
				final JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg", "gif", "png","gif"); // 设置文件过滤器 gif、 .jpg、 .bmp、 .png、 .jpeg
				filechooser.setFileFilter(filter);
				try {
					filechooser.showOpenDialog(null); //
				} catch (HeadlessException ex) {
					System.out.println("HeadlessException");
					ex.printStackTrace();
				}
				browsefile = filechooser.getSelectedFile();
				Icon icon = new ImageIcon(browsefile.getAbsolutePath());// 获得图像文件
				send.insertIcon(icon);
			}
		});
		nothing.setBounds(90, 363, 25, 25);
		frame.add(nothing);

		// 文件选择
		JLabel doc = new JLabel(new ImageIcon("image/senddoc.png"));
		doc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("发送文件");
				new Filesendframe(mysocket, userid, fuserid);
			}
		});
		doc.setBounds(130, 363, 27, 25);

		frame.add(doc);

		// 语音
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

			@Override // 鼠标碰到变蓝
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl = (JLabel) e.getSource();
				jl.setForeground(Color.blue);
			}

			@Override // 鼠标离开变黑
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl = (JLabel) e.getSource();
				jl.setForeground(Color.black);
			}

		});
		frame.add(voice);

		// 字体选择
		JLabel chin = new JLabel(new ImageIcon("image/ziti.png"));
		chin.setBounds(210, 363, 25, 25);
		chin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JColorChooser colorchooser = new JColorChooser();
				Color color = colorchooser.showDialog(Privatechat.this, "字体颜色", Color.BLACK);
				// 字体改变
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
		font.setSelectedItem("楷体");
		font.setBounds(240, 363, 150, 25);
		frame.add(font);

		// 常用语选项
		JComboBox often = new JComboBox();
		often.addItem("常用语");
		often.addItem("您吃了吗？");
		often.addItem("吃了！");
		often.addItem("ORZ");
		often.addItem("QWQ");
		often.addItem("tql");
		often.addItem("今天天气怎么样？");
		often.addItem("今天你刷题了吗？");
		often.setSelectedItem("常用语");
		often.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 选择的下拉框选项
					System.out.println(e.getItem());

					FontSupport.contentAppend(send, e.getItem().toString());
				}
			}
		});
		often.setBounds(15, 533, 150, 25);
		frame.add(often);

		// 发送按钮
		JButton send1 = new JButton("发送");
		send1.setBounds(389, 533, 125, 25);
		send1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				transferinfo tif = new transferinfo();// transferinfo类就是保存一下账号和密码

				// tif.setContent(content);
				// 包装了所有文字对象对应的属性
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

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭JFrame时，退出程序
		// 设置可见
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
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
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
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件 
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
