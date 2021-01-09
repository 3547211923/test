package com.Josephus.chat.Tools;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Chooseimage {
	String filepath;
	File file;
	public Chooseimage(){
		
		JFileChooser fileChooser = new JFileChooser("D:\\");
		//fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//ѡ�������ļ���Ŀ¼
		fileChooser.addChoosableFileFilter(new FileCanChoose());
		int returnVal = fileChooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			 file =fileChooser.getSelectedFile();//�ļ�
			 filepath= file.getAbsolutePath();//ѡ��.�ļ���.·��
		}
	}
	public String getfilepath() {
		return filepath;
	}
	public File getFile() {
		return file; 
	}
}
//ɸѡ���룬ɸѡ��׺��
class FileCanChoose extends FileFilter{
	public boolean accept(File file) {
		String name = file.getName();
		return (name.toLowerCase().endsWith(".gif")||
				name.toLowerCase().endsWith(".jpg")||
				name.toLowerCase().endsWith(".bmp")||
				name.toLowerCase().endsWith(".png")||
				name.toLowerCase().endsWith(".jpeg")
			);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "ͼƬ�ļ� ��.gif��.jpg��.bmp��.png��.jpeg";
	}
}
