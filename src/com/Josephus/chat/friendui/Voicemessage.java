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

	//����¼����ʽ
		AudioFormat af = null;
		//����Ŀ��������,���Դ��ж�ȡ��Ƶ����,�� TargetDataLine �ӿ��ṩ��Ŀ�������еĻ�������ȡ���������ݵķ�����
		TargetDataLine td = null;
		//����Դ������,Դ�������ǿ���д�����ݵ������С����䵱���Ƶ����Դ��Ӧ�ó�����Ƶ�ֽ�д��Դ�����У������ɴ����ֽڻ��岢�����Ǵ��ݸ���Ƶ����
		SourceDataLine sd = null;
		//�����ֽ��������������
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		//������Ƶ������
		AudioInputStream ais = null;
		//����ֹͣ¼���ı�־��������¼���̵߳�����
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
	
		//��ȡ��Ļ�Ĵ�С
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
		ImageIcon image =new ImageIcon("image/������Ϣ.gif");
		textPane.insertIcon(image);
		panel.setLayout(null);
		panel.add(textPane);
		
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 364, 102);
		contentPane.add(panel2);
		JTextPane textPane2 = new JTextPane();
		textPane2.setBounds(-33, 0, 600, 102);
		ImageIcon image2 =new ImageIcon("image/��Ͳ.png");
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
		
		//���ÿ���ͼ��
		ImageIcon icon=new ImageIcon("image/Voicepic.png");  //xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
		this.setIconImage(icon.getImage());


	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			//��ʼ
			panel.setVisible(true);
			capture();//����¼���ķ���
		}
		if(e.getActionCommand().equals("End")) {
			//����
			panel.setVisible(false);
			
            stop();//����ֹͣ¼���ķ���     
		}
		if(e.getActionCommand().equals("Play")) {
			//����
			panel.setVisible(true);
			//���ò���¼���ķ���
			play();
		}
		if(e.getActionCommand().equals("Cancel")) {
			//ȡ��
			voicemessage.dispose();
			panel.setVisible(false);
		}
		if(e.getActionCommand().equals("Send")) {
			//����
			panel.setVisible(false);
			//���ñ���¼���ķ���
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
	
	//��ʼ¼��
	public void capture()
	{
		try {
			//afΪAudioFormatҲ������Ƶ��ʽ
			af = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,af);
			td = (TargetDataLine)(AudioSystem.getLine(info));
			//�򿪾���ָ����ʽ���У�������ʹ�л�����������ϵͳ��Դ����ÿɲ�����
			td.open(af);
			//����ĳһ������ִ������ I/O
			td.start();
			
			//��������¼�����߳�
			Record record = new Record();
			Thread t1 = new Thread(record);
			t1.start();
			
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			return;
		}
	}
	//ֹͣ¼��
	public void stop()
	{
		stopflag = true;			
	}
	//����¼��
	public void play()
	{
		//��baos�е�����ת��Ϊ�ֽ�����
		byte audioData[] = baos.toByteArray();
		//ת��Ϊ������
		bais = new ByteArrayInputStream(audioData);
		af = getAudioFormat();
		ais = new AudioInputStream(bais, af, audioData.length/af.getFrameSize());
		
		try {
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
            sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sd.open(af);
            sd.start();
            //�������Ž���
            Play py = new Play();
            Thread t2 = new Thread(py);
            t2.start();           
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//�ر���
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
	//����¼��
	public String save()
	{
		 //ȡ��¼��������
        af = getAudioFormat();
        String filepath="";
        byte audioData[] = baos.toByteArray();
        bais = new ByteArrayInputStream(audioData);
        ais = new AudioInputStream(bais,af, audioData.length / af.getFrameSize());
        //�������ձ�����ļ���
        File file = null;
        //д���ļ�
        try {	
        	//�Ե�ǰ��ʱ������¼��������
        	//��¼�����ļ���ŵ�F���������ļ�����
        	File filePath = new File("C:/�����ļ�");
        	if(!filePath.exists())
        	{//����ļ������ڣ��򴴽���Ŀ¼
        		filePath.mkdir();
        	}
        	filepath =filePath.getPath()+"/"+System.currentTimeMillis()+".mp3";
        	file = new File(filePath.getPath()+"/"+System.currentTimeMillis()+".mp3");    
        	
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	//�ر���
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
	//����AudioFormat�Ĳ���
	public AudioFormat getAudioFormat() 
	{
		//����ע�Ͳ���������һ����Ƶ��ʽ�����߶�����
		AudioFormat.Encoding encoding = AudioFormat.Encoding.
        PCM_SIGNED ;
		float rate = 8000f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = true;
		int channels = 1;
		return new AudioFormat(encoding, rate, sampleSize, channels,
				(sampleSize / 8) * channels, rate, bigEndian);
//		//��������ÿ�벥�ź�¼�Ƶ�������
//		float sampleRate = 16000.0F;
//		// ������8000,11025,16000,22050,44100
//		//sampleSizeInBits��ʾÿ�����д˸�ʽ�����������е�λ��
//		int sampleSizeInBits = 16;
//		// 8,16
//		int channels = 1;
//		// ������Ϊ1��������Ϊ2
//		boolean signed = true;
//		// true,false
//		boolean bigEndian = true;
//		// true,false
//		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,bigEndian);
	}
	//¼���࣬��ΪҪ�õ�MyRecord���еı��������Խ��������ڲ���
	class Record implements Runnable
	{
		//������¼�����ֽ�����,��Ϊ������
		byte bts[] = new byte[10000];
		//���ֽ������װ��������մ��뵽baos��
		//��дrun����
		public void run() {	
			baos = new ByteArrayOutputStream();		
			try {
				System.out.println("ok3");
				stopflag = false;
				while(stopflag != true)
				{
					//��ֹͣ¼��û����ʱ�����߳�һֱִ��	
					//�������е����뻺������ȡ��Ƶ���ݡ�
					//Ҫ��ȡbts.length���ȵ��ֽ�,cnt ��ʵ�ʶ�ȡ���ֽ���
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
					//�رմ򿪵��ֽ�������
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
	//������,ͬ��Ҳ�����ڲ���
	class Play implements Runnable
	{
		//����baos�е����ݼ���
		public void run() {
			byte bts[] = new byte[10000];
			try {
				int cnt;
	            //��ȡ���ݵ���������
	            while ((cnt = ais.read(bts, 0, bts.length)) != -1) 
	            {
	                if (cnt > 0) 
	                {
	                    //д�뻺������
	                    //����Ƶ����д�뵽��Ƶ��
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
