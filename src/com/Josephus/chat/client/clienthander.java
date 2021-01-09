package com.Josephus.chat.client;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
/*
 * �ͻ���
 */
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.FontStyle;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.ManageQqFriendList;
import com.Josephus.chat.Tools.Mangegroupframe;
import com.Josephus.chat.Tools.MangerChatframe;
import com.Josephus.chat.Tools.Sound;
import com.Josephus.chat.Tools.iostream;
import com.Josephus.chat.Tools.transferinfo;
import com.Josephus.chat.friendui.Douyidou;
import com.Josephus.chat.friendui.Groupchat;
import com.Josephus.chat.friendui.Privatechat;
/*
 * �ͻ���ȡ��Ϣ���߳�
 * 
 */
public class clienthander extends Thread {

	Socket socket;
	//��¼����
	Loginn loginframe;
	
	Privatechat chatframe11;//����������
	public clienthander(Socket socket) {
		this.socket= socket;
	}
	
	public void run() {
		
		//��ѭ��һֱȥȡ��Ϣ
		//Ĭ���ظ���
		while(true) {
			System.out.println("�͑��˵�ѭ��");
			try {
					//һֱ����Ϣ,��������
			System.out.println("�ͻ��˵�socket"+socket);
			Object obj = iostream.readmessage(socket);
			if(obj instanceof transferinfo) {
				transferinfo tfi= (transferinfo)obj;
				
				if(tfi.getStatusenum()==Chatstastu.LoGIN) {
					//��¼
					System.out.println("----��¼----�ͻ���");
					loginResult(tfi);
				}
				else if(tfi.getStatusenum()==Chatstastu.CHAT)
				{
					//����
					System.out.println("----����Ϣ----�ͻ���");
					chatresult(tfi);
					
				}
				else if(tfi.getStatusenum()==Chatstastu.SEND_FILE)
				{
					//�ͻ��˽����ļ�
					
					fileAcceptResult(tfi);
				}
				else if(tfi.getStatusenum()==Chatstastu.NOTICE) {
					noticeResult(tfi);
				}
//				else if(tfi.getStatusenum()==Chatstastu.ULIST) {
//					//ˢ�µ�ǰ�û������б�
//					onlineUsersresult(tfi);
//				}
				else if(tfi.getStatusenum()==Chatstastu.DD) {
					//
					ddresult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.CLOSE) {
					iostream.writemessage(socket, tfi);
					
					break;
				}else if(tfi.getStatusenum()==Chatstastu.GROUP) {
					//Ⱥ��
					groupchatresult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.GROUPFILE) {
					//Ⱥ�ļ�
					groupsendFile(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.FriendApply) {
					FriendApplyResult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.Voicefile) {
					VoicefileResult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.Addtogroup) {
					addtogroupResult(tfi);
				}

			}
			else {
				
			}
			//System.out.println("�ͻ��ˣ�"+obj);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		//������Ⱥ��Ϣ����
		private void addtogroupResult(transferinfo tfi) {
			String reciver = tfi.getReciver();
			String sender = tfi.getSender();
			friendf Friendframe= ManageQqFriendList.getQqFriendList(reciver);
			
			Friendframe.addmessage(sender,tfi.getNotice(),tfi.getType());
		}
	//������Ϣ����
		private void VoicefileResult(transferinfo tfi) {
			String reciver = tfi.getReciver();
			String sender = tfi.getSender();
			Privatechat chatframe=  MangerChatframe.getchat(sender+" "+reciver);
			
			String filepath = tfi.getNotice();
			
			List<FontStyle> contents =tfi.getContent();
			if(tfi.getFlag().equals("2")) {
				 sender=reciver; 
			}
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String dateName = df.format(calendar.getTime());
			Document doc = FontSupport.contentAppend(chatframe.acceptPane,sender+"   "+dateName+"\n");
			//FontSupport.fontDecode( chatframe.acceptPane, contents, sender, reciver);
			JLabel lb = new JLabel(new ImageIcon("image/voicepict.png"), JLabel.LEFT);
			lb.addMouseListener(new MouseAdapter() {
				public File browsefile;
				@Override
			    public void mouseClicked(MouseEvent e) {
					Sound sound =  new Sound(filepath);
					try {
						sound.play_sound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			chatframe.acceptPane.insertComponent(lb);
			FontSupport.contentAppend(chatframe.acceptPane, "\n");
		}
	private void FriendApplyResult(transferinfo tfi) {
		String reciver = tfi.getReciver();
		String sender = tfi.getSender();
		friendf Friendframe= ManageQqFriendList.getQqFriendList(reciver);
		
		Friendframe.addmessage(sender,tfi.getNotice(),tfi.getType());
	}
	private void fileAcceptResult(transferinfo tfi) throws IOException {
		JFileChooser jfc =  new JFileChooser();
		
		//����ֻ��ѡ���ļ���
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//���ļ�ѡ����
		int state = jfc.showDialog(new JLabel(), "�����ļ�");//ѡ��״̬
		
		
		if(state == JFileChooser.CANCEL_OPTION) {
			return ;//û��ѡ���ļ���ֱ�ӷ��أ���ʾȡ��
		}
		//��ȡ�ļ�
		File file = jfc.getSelectedFile();
		String filePath= file.getAbsolutePath();
		File savefile= new File(filePath,tfi.getFileName());
		FileOutputStream fos= new FileOutputStream(savefile);
		fos.write(tfi.getFileByte());
		fos.flush();
		fos.close();
	}
	private void  groupsendFile (transferinfo tfi) throws IOException{
		JFileChooser jfc =  new JFileChooser();
		
		//����ֻ��ѡ���ļ���
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//���ļ�ѡ����
		int state = jfc.showDialog(new JLabel(), "�����ļ�");//ѡ��״̬
		
		
		if(state == JFileChooser.CANCEL_OPTION) {
			return ;//û��ѡ���ļ���ֱ�ӷ��أ���ʾȡ��
		}
		//��ȡ�ļ�
		File file = jfc.getSelectedFile();
		String filePath= file.getAbsolutePath();
		File savefile= new File(filePath,tfi.getFileName());
		FileOutputStream fos= new FileOutputStream(savefile);
		fos.write(tfi.getFileByte());
		fos.flush();
		fos.close();
	}
	/*
	 * �����η������������Ķ�����Ϣ
	 */
	private void ddresult(transferinfo tfi) {
		//Douyidou dd = new Douyidou(chatframe);
		//dd.start();
	}
	/*
	 * ��¼����Ĵ���
	 */
	public void loginResult(transferinfo tfi) {
		if(tfi.getLoginSucceessFlag()) {
			
			//����ʵ����ȡ���û���
			String loginid = tfi.getUserName();
			
			new friendf(loginid,socket).setVisible(true);//�������б��������Լ����˺ź�socket
			
			loginframe.dispose();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "��֤ʧ�ܣ�����������", "��ʾ", JOptionPane.CANCEL_OPTION);
		}
	}
	/*
	 * ������Ϣ����
	 */
	public void chatresult(transferinfo tfi) {
		
		String sender= tfi.getSender();
		String reciver  = tfi.getReciver();
		
		List<FontStyle> contents =tfi.getContent();
		Privatechat chatframe=  MangerChatframe.getchat(sender+" "+reciver);
		if(tfi.getFlag().equals("2")) {
			 sender=reciver; 
		}
		
		FontSupport.fontDecode( chatframe.acceptPane, contents, sender, reciver);
		
//		String text1 = chatframe.acceptPane.getText();
//		if(tfi.getFlag().equals("2")) {
//			 sender=reciver; 
//		}
//		chatframe.acceptPane.setText(text1+"\n"+sender+"��"+);
	}
	
	public void groupchatresult(transferinfo tfi) {
		
		String sender= tfi.getSender();
		String reciver  = tfi.getReciver();
		String gname = tfi.getGroupname();
		List<FontStyle> contents =tfi.getContent();
		//Groupchat chatframe=  MangerChatframe.getchat(sender+" "+reciver);
		//Mangegroupframe
		Groupchat chatframe=  Mangegroupframe.getchat(reciver+" "+gname);
		
		FontSupport.fontDecode( chatframe.acceptPane, contents, sender, reciver);
		
	}
	/*
	 * ϵͳ��Ϣ��ʾ
	 * Ϊ�˷�ֹ��Ϣ����
	 * Ҫ��getText��Ϣ����setText��Ϣ
	 */
	public void noticeResult(transferinfo tfi) {
		String sender= tfi.getSender();
		String reciver  = tfi.getReciver();
		//ȡ��
		//String text = chatframe.acceptPane.getText();
		//�������ҹ������淢��Ϣ
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String dateName = df.format(calendar.getTime());
		if(tfi.getFlag().equals("1")) {
			Privatechat chatframe=  MangerChatframe.getchat(sender+" "+reciver);
			FontSupport.contentAppend(chatframe.acceptPane,tfi.getNotice());
			FontSupport.contentAppend(chatframe.acceptPane,"   "+dateName+"\n");
			
		}
		else if(tfi.getFlag().equals("2")) {
			Privatechat chatframe=  MangerChatframe.getchat(reciver+" "+sender);
			FontSupport.contentAppend(chatframe.acceptPane,tfi.getNotice());
			FontSupport.contentAppend(chatframe.acceptPane,"   "+dateName+"\n");
		}if(tfi.getFlag().equals("0")) {
			String gname = tfi.getGroupname();
			System.out.println("�ļ��Ѿ�����2222");
			Groupchat chatframe=  Mangegroupframe.getchat(sender+" "+gname);
			FontSupport.contentAppend(chatframe.acceptPane,tfi.getNotice());
			FontSupport.contentAppend(chatframe.acceptPane,"   "+dateName+"\n");
			
		}if(tfi.getFlag().equals("3")) {
			String gname = tfi.getGroupname();
			Groupchat chatframe=  Mangegroupframe.getchat(reciver+" "+gname);
			System.out.println(tfi.getNotice()+"2333");
			FontSupport.contentAppend(chatframe.acceptPane,tfi.getNotice());
			FontSupport.contentAppend(chatframe.acceptPane,"   "+dateName+"\n");
			
		}
		
		
	}
	/*
	 * ˢ�µ�ǰ�û�������б�
	 */
	public void onlineUsersresult(transferinfo tfi) {
		String[] userOnlineArray = tfi.getUserOnlineArray();
		//chatframe.peolist.setListData(userOnlineArray);
	}
}
