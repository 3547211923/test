package com.Josephus.chat.Tools;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Chooseimage {
	String filepath;
	File file;
	public Chooseimage(){
		
		JFileChooser fileChooser = new JFileChooser("D:\\");
		//fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//选着类型文件和目录
		fileChooser.addChoosableFileFilter(new FileCanChoose());
		int returnVal = fileChooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			 file =fileChooser.getSelectedFile();//文件
			 filepath= file.getAbsolutePath();//选着.文件的.路径
		}
	}
	public String getfilepath() {
		return filepath;
	}
	public File getFile() {
		return file; 
	}
}
//筛选代码，筛选后缀。
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
		return "图片文件 ：.gif、.jpg、.bmp、.png、.jpeg";
	}
}
