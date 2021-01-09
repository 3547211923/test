package com.Josephus.chat.friendui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;

public class Filesendframe extends JFrame{

	
	private static final long serialVersionUID = -155745099631786503L;
	public static final Integer FRAME_WIDTH=450;
	public static final Integer FRAME_HIGHT=200;
	
	private static final int MIN_PROGTRSS = 0;
	private static final int MAX_PROGTRSS =100;
	
	private static int currentProgress= MIN_PROGTRSS;
	
	Filesendframe fileFrame;
	private Socket socket;
	public Filesendframe(Socket socket,String sender,String reciver) {
		fileFrame=this;
		this.socket=socket;
		setTitle("SendFile");
		setSize(450,220);
		setVisible(true);
		
		
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		int wid= screenSize.width;
		int he =screenSize.height;
		setLocation((wid-450)/2,(he-200)/2);
		setLayout(null);
	
		JLabel fileLabel = new JLabel("文件路径：");
		
		fileLabel.setBounds(60,20,150,30);
		fileLabel.setFont(new Font("宋体",0,16));
		
		fileLabel.setForeground(Color.BLACK);//表建文本为白色
		add(fileLabel);
		
		//账号文本框
		
		JTextField filePathFile = new JTextField();
		
		filePathFile.setBounds(150,20,240,30);
		add(filePathFile);
		
		JButton enter = new JButton("选择文件");
		
		enter.setBounds(60,130,130,25);
		enter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc =  new JFileChooser();
				
				//设置不可以选择文件夹
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				JButton jb = new JButton("发送文件");
				jb.setSize(130,25);
				jb.setLocation(480,100);
				//jfc.add(jb);
				//打开文件选择窗体
				int state = jfc.showDialog(new JLabel(), "选择文件");//选择状态
				
				
				if(state == JFileChooser.CANCEL_OPTION) {
					return ;//没有选择文件，直接返回，表示取消
				}
				//获取文件
				File file = jfc.getSelectedFile();
				filePathFile.setText(file.getAbsolutePath());//获取文件路径
			}
		});
		add(enter);
		JButton sendFileBtn = new JButton("发送文件");
		
		sendFileBtn.setBounds(250,130,140,25);
		sendFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
					String filePath = filePathFile.getText();
					if(filePath!=null&&!"".equals(filePath)) {
						
						File file=  new File(filePath);
						FileInputStream fis = new FileInputStream(file);
						
						int filelen=  fis.available();//文件内容的字节数
						
						byte [] fileData= new byte[filelen];
						
						fis.read(fileData);
						
						fis.close();
						//把fis弄成byte数组，然后使用byte数组进行文件上传，消耗内存
						transferinfo tfi = new transferinfo();
						
						tfi.setSender(sender);
						tfi.setReciver(reciver);
						tfi.setFileByte(fileData);
						tfi.setFileName(file.getName());
						tfi.setStatusenum(Chatstastu.SEND_FILE);
						iostream.writemessage(socket, tfi);
						fileFrame.dispose();
						JOptionPane.showMessageDialog(null, "文件发送成功");
					}
			}catch(Exception e2) {
				e2.printStackTrace();
			}}
		});
		add(sendFileBtn);
		
		//进度条
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.setMinimum(MIN_PROGTRSS);//设置进度的最大值~最小值 1~100
		progressBar.setMaximum(MAX_PROGTRSS);
		
		progressBar.setBounds(60,80,330,25);
		
		progressBar.setValue(currentProgress);//进度条当前值
		
		progressBar.setStringPainted(true);
		
		progressBar.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				System.out.println("now:"+progressBar.getValue()+" 百分比："+progressBar.getPercentComplete());
			}
		});
		
		add(progressBar);
	}
//		new Timer(50,new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				currentProgress++;
//				if(currentProgress>MAX_PROGTRSS) {
//					currentProgress=MIN_PROGTRSS;
//				}
//				progressBar.setValue(currentProgress);
//				//progressBar.setString("传输完成");
//			}
//		}).start();
//		setVisible(true);
//	}
}
