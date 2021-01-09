package com.Josephus.chat.Tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.Josephus.chat.client.Loginn;
/*
 * ��������
 */
public class Faceframe extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 168497430264112079L;

	public Faceframe(JTextPane textPane) {
		
		JPanel panel=(JPanel)getContentPane();
		
		panel.setLayout(null);
		
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				ImageIcon icon = new ImageIcon("image/face/"+(i*10+j+1)+".gif");
				
				JLabel lbIcon = new JLabel(icon);
				
				lbIcon.setSize(40,40);
				
				lbIcon.setLocation(0+j*40, 0+i*40);
				
				lbIcon.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e) {
						
						JLabel jlabel = (JLabel)e.getSource();
						Icon icon2 = jlabel.getIcon();
						
						//����
						textPane.insertIcon(icon2);
						//�ر�
						Faceframe.this.dispose();
					}
				});
				
				panel.add(lbIcon);
				
			}
		}
		setSize(420,450);
		setLocation(800,400);
		setTitle("����");
		setVisible(true);
		//��������ӱ���ͼ
		ImageIcon image =new ImageIcon("image/face/faceback.png");
		JLabel frame = new JLabel(image);
		frame.setBounds(0,0,420,420);//ͼƬλ�úʹ�С
		this.add(frame);
		
		ImageIcon icon=new ImageIcon("image/facetop.png");  //xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
		this.setIconImage(icon.getImage());

	}
	public static void main(String args[]) {
		JTextPane x=new JTextPane();
		new Faceframe(x);
	}
	
	
}
