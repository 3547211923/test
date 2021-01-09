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
import java.util.List;

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

public class Groupfilesend extends JFrame{

	private static final long serialVersionUID = -7264699251751588263L;

	public static final Integer FRAME_WIDTH=450;
	public static final Integer FRAME_HIGHT=200;
	
	private static final int MIN_PROGTRSS = 0;
	private static final int MAX_PROGTRSS =100;
	
	private static int currentProgress= MIN_PROGTRSS;
	
	Groupfilesend fileFrame;
	private Socket socket;
	public Groupfilesend(Socket socket,String sender,List<String> groupmenber,String groupname) {
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
	
		JLabel fileLabel = new JLabel("�ļ�·����");
		
		fileLabel.setBounds(60,20,150,30);
		fileLabel.setFont(new Font("����",0,16));
		
		fileLabel.setForeground(Color.BLACK);//���ı�Ϊ��ɫ
		add(fileLabel);
		
		//�˺��ı���
		
		JTextField filePathFile = new JTextField();
		
		filePathFile.setBounds(150,20,240,30);
		add(filePathFile);
		
		JButton enter = new JButton("ѡ���ļ�");
		
		enter.setBounds(60,130,130,25);
		enter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc =  new JFileChooser();
				
				//���ò�����ѡ���ļ���
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				JButton jb = new JButton("�����ļ�");
				jb.setSize(130,25);
				jb.setLocation(480,100);
				//jfc.add(jb);
				//���ļ�ѡ����
				int state = jfc.showDialog(new JLabel(), "ѡ���ļ�");//ѡ��״̬
				
				
				if(state == JFileChooser.CANCEL_OPTION) {
					return ;//û��ѡ���ļ���ֱ�ӷ��أ���ʾȡ��
				}
				//��ȡ�ļ�
				File file = jfc.getSelectedFile();
				filePathFile.setText(file.getAbsolutePath());//��ȡ�ļ�·��
			}
		});
		add(enter);
		JButton sendFileBtn = new JButton("�����ļ�");
		
		sendFileBtn.setBounds(250,130,140,25);
		sendFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
					String filePath = filePathFile.getText();
					if(filePath!=null&&!"".equals(filePath)) {
						
						File file=  new File(filePath);
						FileInputStream fis = new FileInputStream(file);
						
						int filelen=  fis.available();//�ļ����ݵ��ֽ���
						
						byte [] fileData= new byte[filelen];
						
						fis.read(fileData);
						
						fis.close();
						//��fisŪ��byte���飬Ȼ��ʹ��byte��������ļ��ϴ��������ڴ�
						transferinfo tfi = new transferinfo();
						
						tfi.setSender(sender);
						tfi.setFileByte(fileData);
						tfi.setGroupname(groupname);
						tfi.setFileName(file.getName());
						
						tfi.setStatusenum(Chatstastu.GROUPFILE);
						JOptionPane.showMessageDialog(null, "�ļ����ͳɹ�");

						tfi.setStatusenum(Chatstastu.NOTICE);
						iostream.writemessage(socket, tfi);
						tfi.setStatusenum(Chatstastu.GROUPFILE);
						String[] userArray = groupmenber.toArray(new String [groupmenber.size()]);
						for(int i=0;i<userArray.length;i++) {
							if(userArray[i].equals(sender))continue;
							tfi.setReciver(userArray[i]);
							iostream.writemessage(socket, tfi);
						}
						fileFrame.dispose();
					}
			}catch(Exception e2) {
				e2.printStackTrace();
			}}
		});
		add(sendFileBtn);
		
		//������
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.setMinimum(MIN_PROGTRSS);//���ý��ȵ����ֵ~��Сֵ 1~100
		progressBar.setMaximum(MAX_PROGTRSS);
		
		progressBar.setBounds(60,80,330,25);
		
		progressBar.setValue(currentProgress);//��������ǰֵ
		
		progressBar.setStringPainted(true);
		
		progressBar.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				System.out.println("now:"+progressBar.getValue()+" �ٷֱȣ�"+progressBar.getPercentComplete());
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
//				//progressBar.setString("�������");
//			}
//		}).start();
//		setVisible(true);
//	}
}
