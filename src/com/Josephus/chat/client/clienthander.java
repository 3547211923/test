package com.Josephus.chat.client;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
/*
 * 客户端
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
 * 客户端取消息的线程
 * 
 */
public class clienthander extends Thread {

	Socket socket;
	//登录窗体
	Loginn loginframe;
	
	Privatechat chatframe11;//聊天主窗体
	public clienthander(Socket socket) {
		this.socket= socket;
	}
	
	public void run() {
		
		//死循环一直去取消息
		//默认重复那
		while(true) {
			System.out.println("客戶端的循环");
			try {
					//一直拿消息,产生阻塞
			System.out.println("客户端的socket"+socket);
			Object obj = iostream.readmessage(socket);
			if(obj instanceof transferinfo) {
				transferinfo tfi= (transferinfo)obj;
				
				if(tfi.getStatusenum()==Chatstastu.LoGIN) {
					//登录
					System.out.println("----登录----客户端");
					loginResult(tfi);
				}
				else if(tfi.getStatusenum()==Chatstastu.CHAT)
				{
					//聊天
					System.out.println("----发消息----客户端");
					chatresult(tfi);
					
				}
				else if(tfi.getStatusenum()==Chatstastu.SEND_FILE)
				{
					//客户端接收文件
					
					fileAcceptResult(tfi);
				}
				else if(tfi.getStatusenum()==Chatstastu.NOTICE) {
					noticeResult(tfi);
				}
//				else if(tfi.getStatusenum()==Chatstastu.ULIST) {
//					//刷新当前用户在线列表
//					onlineUsersresult(tfi);
//				}
				else if(tfi.getStatusenum()==Chatstastu.DD) {
					//
					ddresult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.CLOSE) {
					iostream.writemessage(socket, tfi);
					
					break;
				}else if(tfi.getStatusenum()==Chatstastu.GROUP) {
					//群聊
					groupchatresult(tfi);
				}else if(tfi.getStatusenum()==Chatstastu.GROUPFILE) {
					//群文件
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
			//System.out.println("客户端："+obj);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		//邀请入群消息处理
		private void addtogroupResult(transferinfo tfi) {
			String reciver = tfi.getReciver();
			String sender = tfi.getSender();
			friendf Friendframe= ManageQqFriendList.getQqFriendList(reciver);
			
			Friendframe.addmessage(sender,tfi.getNotice(),tfi.getType());
		}
	//语言消息处理
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
		
		//设置只能选择文件夹
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//打开文件选择窗体
		int state = jfc.showDialog(new JLabel(), "保存文件");//选择状态
		
		
		if(state == JFileChooser.CANCEL_OPTION) {
			return ;//没有选择文件，直接返回，表示取消
		}
		//获取文件
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
		
		//设置只能选择文件夹
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//打开文件选择窗体
		int state = jfc.showDialog(new JLabel(), "保存文件");//选择状态
		
		
		if(state == JFileChooser.CANCEL_OPTION) {
			return ;//没有选择文件，直接返回，表示取消
		}
		//获取文件
		File file = jfc.getSelectedFile();
		String filePath= file.getAbsolutePath();
		File savefile= new File(filePath,tfi.getFileName());
		FileOutputStream fos= new FileOutputStream(savefile);
		fos.write(tfi.getFileByte());
		fos.flush();
		fos.close();
	}
	/*
	 * 接受宋服务器发送来的抖动信息
	 */
	private void ddresult(transferinfo tfi) {
		//Douyidou dd = new Douyidou(chatframe);
		//dd.start();
	}
	/*
	 * 登录结果的处理
	 */
	public void loginResult(transferinfo tfi) {
		if(tfi.getLoginSucceessFlag()) {
			
			//根据实体类取出用户名
			String loginid = tfi.getUserName();
			
			new friendf(loginid,socket).setVisible(true);//给好友列表传递了我自己的账号和socket
			
			loginframe.dispose();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "验证失败，请重新输入", "提示", JOptionPane.CANCEL_OPTION);
		}
	}
	/*
	 * 聊天消息处理
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
//		chatframe.acceptPane.setText(text1+"\n"+sender+"："+);
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
	 * 系统消息提示
	 * 为了防止消息覆盖
	 * 要先getText消息，再setText消息
	 */
	public void noticeResult(transferinfo tfi) {
		String sender= tfi.getSender();
		String reciver  = tfi.getReciver();
		//取出
		//String text = chatframe.acceptPane.getText();
		//往聊天室公屏上面发消息
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
			System.out.println("文件已经发送2222");
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
	 * 刷新当前用户界面的列表
	 */
	public void onlineUsersresult(transferinfo tfi) {
		String[] userOnlineArray = tfi.getUserOnlineArray();
		//chatframe.peolist.setListData(userOnlineArray);
	}
}
