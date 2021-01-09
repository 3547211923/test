package com.Josephus.chat.friendui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Josephus.chat.Tools.Chatstastu;
import com.Josephus.chat.Tools.FontStyle;
import com.Josephus.chat.Tools.FontSupport;
import com.Josephus.chat.Tools.Sound;
import com.Josephus.chat.Tools.iostream;
//import com.Josephus.chat.Tools.TextBorderUtlis;
import com.Josephus.chat.Tools.transferinfo;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Voicemessage extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144668163468564830L;

	private JPanel contentPane;

	//定义录音格式
		AudioFormat af = null;
		//定义目标数据行,可以从中读取音频数据,该 TargetDataLine 接口提供从目标数据行的缓冲区读取所捕获数据的方法。
		TargetDataLine td = null;
		//定义源数据行,源数据行是可以写入数据的数据行。它充当其混频器的源。应用程序将音频字节写入源数据行，这样可处理字节缓冲并将它们传递给混频器。
		SourceDataLine sd = null;
		//定义字节数组输入输出流
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		//定义音频输入流
		AudioInputStream ais = null;
		//定义停止录音的标志，来控制录音线程的运行
		Boolean stopflag = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Voicemessage frame = new Voicemessage();
		//frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Voicemessage voicemessage;
	private JButton End;
	public JPanel panel;
	public JPanel panel2;
	String sender;
	Socket socket;
	String reciver;
	public  Voicemessage(Socket socket,String sender,String reciver) {
		this.voicemessage=this;
		this.sender=sender;
		this.socket=socket;
		this.reciver=reciver;
		setTitle("Voice");
	
		//获取屏幕的大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;

		setBounds((w-535)/2, (h-410)/2, 382, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.setVisible(true);
		panel = new JPanel();
		panel.setBounds(0, 0, 364, 102);
		contentPane.add(panel);
		panel.setVisible(false);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(-4, 0, 370, 102);
		ImageIcon image =new ImageIcon("image/语言消息.gif");
		textPane.insertIcon(image);
		panel.setLayout(null);
		panel.add(textPane);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 364, 102);
		contentPane.add(panel2);
		JTextPane textPane2 = new JTextPane();
		textPane2.setBounds(-33, 0, 600, 102);
		ImageIcon image2 =new ImageIcon("image/话筒.png");
		textPane2.insertIcon(image2);
		panel2.setLayout(null);
		panel2.add(textPane2);
		
		panel2.setVisible(true);
		
		//TextBorderUtlis Bor = new TextBorderUtlis(new Color(255,240,240),1,true);
		
		JButton Start = new JButton("\u5F00\u59CB");
		Start.setBackground(Color.WHITE);
		//Start.setBorder(Bor);
		Start.addActionListener(this);
		Start.setActionCommand("Start");
		Start.setBounds(0, 102, 73, 38);
		contentPane.add(Start);
		
		End = new JButton("\u7ED3\u675F");
		End.addActionListener(this);
		End.setActionCommand("End");
		End.setBackground(Color.WHITE);
		End.setBounds(73, 102, 73, 38);
		contentPane.add(End);
		
		JButton Play = new JButton("\u64AD\u653E");
		Play.addActionListener(this);
		Play.setActionCommand("Play");
		Play.setBackground(Color.WHITE);
		Play.setBounds(145, 102, 73, 38);
		contentPane.add(Play);
		
		JButton Cancel = new JButton("\u53D6\u6D88");
		Cancel.setBackground(Color.WHITE);
		Cancel.setBounds(218, 102, 73, 38);
		contentPane.add(Cancel);
		Cancel.addActionListener(this);
		Cancel.setActionCommand("Cancel");
		
		JButton Send = new JButton("\u53D1\u9001");
		Send.addActionListener(this);
		Send.setActionCommand("Send");
		Send.setBackground(Color.WHITE);
		Send.setBounds(291, 102, 73, 38);
		contentPane.add(Send);
		
		//设置咖啡图标
		ImageIcon icon=new ImageIcon("image/Voicepic.png");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());


	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			//开始
			panel.setVisible(true);
			capture();//调用录音的方法
		}
		if(e.getActionCommand().equals("End")) {
			//结束
			panel.setVisible(false);
			
            stop();//调用停止录音的方法     
		}
		if(e.getActionCommand().equals("Play")) {
			//播放
			panel.setVisible(true);
			//调用播放录音的方法
			play();
		}
		if(e.getActionCommand().equals("Cancel")) {
			//取消
			voicemessage.dispose();
			panel.setVisible(false);
		}
		if(e.getActionCommand().equals("Send")) {
			//发送
			panel.setVisible(false);
			//调用保存录音的方法
			String filepath = save();
			transferinfo tif=new transferinfo();
			tif.setSender(sender);
			tif.setReciver(reciver);
			tif.setNotice(filepath);
			tif.setStatusenum(Chatstastu.Voicefile);
			JTextPane send = new JTextPane();
			send.setText("");
			List<FontStyle> fontSupport=FontSupport.fontEncode(send);
			tif.setContent(fontSupport);
			iostream.writemessage(socket, tif);
			voicemessage.dispose();
		}
	}
	
	//开始录音
	public void capture()
	{
		try {
			//af为AudioFormat也就是音频格式
			af = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,af);
			td = (TargetDataLine)(AudioSystem.getLine(info));
			//打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
			td.open(af);
			//允许某一数据行执行数据 I/O
			td.start();
			
			//创建播放录音的线程
			Record record = new Record();
			Thread t1 = new Thread(record);
			t1.start();
			
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			return;
		}
	}
	//停止录音
	public void stop()
	{
		stopflag = true;			
	}
	//播放录音
	public void play()
	{
		//将baos中的数据转换为字节数据
		byte audioData[] = baos.toByteArray();
		//转换为输入流
		bais = new ByteArrayInputStream(audioData);
		af = getAudioFormat();
		ais = new AudioInputStream(bais, af, audioData.length/af.getFrameSize());
		
		try {
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
            sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sd.open(af);
            sd.start();
            //创建播放进程
            Play py = new Play();
            Thread t2 = new Thread(py);
            t2.start();           
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//关闭流
				if(ais != null)
				{
					ais.close();
				}
				if(bais != null)
				{
					bais.close();
				}
				if(baos != null)
				{
					baos.close();
				}
				
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}
	}
	//保存录音
	public String save()
	{
		 //取得录音输入流
        af = getAudioFormat();
        String filepath="";
        byte audioData[] = baos.toByteArray();
        bais = new ByteArrayInputStream(audioData);
        ais = new AudioInputStream(bais,af, audioData.length / af.getFrameSize());
        //定义最终保存的文件名
        File file = null;
        //写入文件
        try {	
        	//以当前的时间命名录音的名字
        	//将录音的文件存放到F盘下语音文件夹下
        	File filePath = new File("C:/语音文件");
        	if(!filePath.exists())
        	{//如果文件不存在，则创建该目录
        		filePath.mkdir();
        	}
        	filepath =filePath.getPath()+"/"+System.currentTimeMillis()+".mp3";
        	file = new File(filePath.getPath()+"/"+System.currentTimeMillis()+".mp3");    
        	
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	//关闭流
        	try {
        		
        		if(bais != null)
        		{
        			bais.close();
        		} 
        		if(ais != null)
        		{
        			ais.close();		
        		}
			} catch (Exception e) {
				e.printStackTrace();
			}   	
        }
        return filepath;
	}
	//设置AudioFormat的参数
	public AudioFormat getAudioFormat() 
	{
		//下面注释部分是另外一种音频格式，两者都可以
		AudioFormat.Encoding encoding = AudioFormat.Encoding.
        PCM_SIGNED ;
		float rate = 8000f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = true;
		int channels = 1;
		return new AudioFormat(encoding, rate, sampleSize, channels,
				(sampleSize / 8) * channels, rate, bigEndian);
//		//采样率是每秒播放和录制的样本数
//		float sampleRate = 16000.0F;
//		// 采样率8000,11025,16000,22050,44100
//		//sampleSizeInBits表示每个具有此格式的声音样本中的位数
//		int sampleSizeInBits = 16;
//		// 8,16
//		int channels = 1;
//		// 单声道为1，立体声为2
//		boolean signed = true;
//		// true,false
//		boolean bigEndian = true;
//		// true,false
//		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,bigEndian);
	}
	//录音类，因为要用到MyRecord类中的变量，所以将其做成内部类
	class Record implements Runnable
	{
		//定义存放录音的字节数组,作为缓冲区
		byte bts[] = new byte[10000];
		//将字节数组包装到流里，最终存入到baos中
		//重写run函数
		public void run() {	
			baos = new ByteArrayOutputStream();		
			try {
				System.out.println("ok3");
				stopflag = false;
				while(stopflag != true)
				{
					//当停止录音没按下时，该线程一直执行	
					//从数据行的输入缓冲区读取音频数据。
					//要读取bts.length长度的字节,cnt 是实际读取的字节数
					int cnt = td.read(bts, 0, bts.length);
					if(cnt > 0)
					{
						baos.write(bts, 0, cnt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					//关闭打开的字节数组流
					if(baos != null)
					{
						baos.close();
					}	
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					td.drain();
					td.close();
				}
			}
		}
		
	}
	//播放类,同样也做成内部类
	class Play implements Runnable
	{
		//播放baos中的数据即可
		public void run() {
			byte bts[] = new byte[10000];
			try {
				int cnt;
	            //读取数据到缓存数据
	            while ((cnt = ais.read(bts, 0, bts.length)) != -1) 
	            {
	                if (cnt > 0) 
	                {
	                    //写入缓存数据
	                    //将音频数据写入到混频器
	                    sd.write(bts, 0, cnt);
	                }
	            }
	           
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				 sd.drain();
		         sd.close();
			}
			
			
		}		
	}
}
