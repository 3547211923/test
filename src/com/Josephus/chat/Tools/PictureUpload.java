package com.Josephus.chat.Tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PictureUpload extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5502420494457635281L;
	final private JPanel  panel;
	final private JToolBar toolbar;
	final private JLabel picLabel;//用来显示图像的标签
	
	public PictureUpload(){
		this.setTitle("显示图像程序");
		this.setBounds(200, 200, 300, 300);
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		panel = new JPanel();		
		picLabel = new JLabel();
		panel.add(picLabel);
		this.getContentPane().add(panel,BorderLayout.CENTER);	
		
		toolbar = new JToolBar();
		final JButton button = new JButton("打开");
		button.addActionListener(new btnActionListener());
		toolbar.add(button);
	    this.getContentPane().add(toolbar,BorderLayout.NORTH);
	    
	    this.setVisible(true);
	}
	
	class btnActionListener implements ActionListener{
		public File browsefile;
		public void actionPerformed(ActionEvent e){
			final JFileChooser filechooser = new JFileChooser();		
			FileNameExtensionFilter filter=new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg", "gif","png","gif"); //设置文件过滤器 gif、 .jpg、 .bmp、 .png、 .jpeg
			filechooser.setFileFilter(filter);
			try{ 
				filechooser.showOpenDialog(null); //  
			}catch(HeadlessException ex){
				System.out.println("HeadlessException");
				ex.printStackTrace();
			}
			browsefile = filechooser.getSelectedFile();	
			 Icon icon = new ImageIcon(browsefile.getAbsolutePath());//获得图像文件
			 picLabel.setIcon(icon);		     
		}	
	}
	public static void main(String args[]){
		new PictureUpload();
	}
}