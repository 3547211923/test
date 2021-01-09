package com.Josephus.chat.friendui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;

public class friendframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	private String userid="";
	public  void setname(String userid) {
		this.userid= userid;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					friendframe frame = new friendframe();
					ImageIcon icon=new ImageIcon("image/�����б�ͼ��.png");  //xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
					frame.setIconImage(icon.getImage());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public JPanel panel ;
	public JPanel panel_1;
	public friendframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ͷ��
		JLabel lblNewLabel = new JLabel("Josephus");
		String path1 = "image/ͷ��";
		int pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		lblNewLabel.setIcon(new ImageIcon(path1));
		lblNewLabel.setBounds(0, 0, 89, 68);
		lblNewLabel.setOpaque(false);  

		contentPane.add(lblNewLabel);
		
		//�ǳ� ��Ҫ��һ��STring���������Ϊ�ǳ�
		JLabel lblNewLabel_1 = new JLabel(userid);
		
		lblNewLabel_1.setFont(new Font("����", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(119, 0, 116, 54);
		contentPane.add(lblNewLabel_1);
		
		//����
		JLabel lblNewLabel_2 = new JLabel("2020-01-02");
		lblNewLabel_2.setBounds(268, 50, 70, 18);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String time = formatter.format(date);
		lblNewLabel_2.setText(time);
		contentPane.add(lblNewLabel_2);
		
		//��������ӱ���ͼ
		ImageIcon image =new ImageIcon("image/severback.png");
		JLabel frame = new JLabel(image);//���ܵ�JLabel��2333
		frame.setBounds(0,0,349,70);//ͼƬλ�úʹ�С
		getContentPane().add(frame);


		//����ͼƬ
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(0, 69, 35, 35);
		lblNewLabel_3.setIcon(new ImageIcon("image/����.png"));
		contentPane.add(lblNewLabel_3);
		
		//�˺�������
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("����", Font.PLAIN, 24));
		textField.setText("");
		textField.setBounds(36, 69, 232, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//�˺�������ť
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = textField.getText();
				textField.setText("���Ǹ����ˣ�����");
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(268, 69, 70, 35);
		btnNewButton.setContentAreaFilled(false);  
		contentPane.add(btnNewButton);
		
		//Ⱥ�����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		scrollPane.setBounds(0, 146, 353, 542);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		//panel.setBackground(new Color(224, 255, 255));
		panel.setLayout(new GridLayout(50, 1,4,4));
		scrollPane.setViewportView(panel);
		
		String path11 = "image/����";
		if(x>7)x=1;
		path11 = path11+Integer.toString(x++);
		path11+=".png";
		JLabel jlabel = new JLabel("Josephus",new ImageIcon(path11),JLabel.LEFT);
		jlabel.setBackground(Color.BLUE);
		jlabel.setFont(new Font("����", Font.PLAIN, 24));
		panel.add(jlabel);
		
		//��ϵ�����
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 146, 353, 542);
		scrollPane_1.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		
		contentPane.add(scrollPane_1);
		
		
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(50, 1,4,4));
		scrollPane_1.setViewportView(panel_1);
		addfriend();

		
//		addfriend();
//		addfriend();
//		addfriend();
		scrollPane_1.setVisible(false);
		scrollPane.setVisible(true);
		//��ϵ�˰�ť
		JButton btnNewButton_1 = new JButton("\u8054\u7CFB\u4EBA");
		
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setVisible(true);
				scrollPane.setVisible(false);
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(0, 102, 157, 42);
		contentPane.add(btnNewButton_1);
		
		//Ⱥ�İ�ť
		JButton btnNewButton_2 = new JButton("\u7FA4\u804A");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setVisible(false);
				scrollPane.setVisible(true);
				panel_1.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(171, 102, 167, 42);
		contentPane.add(btnNewButton_2);
		 path1 = "image/ͷ��";
		 pic = (int)(Math.random()*10)%6+1;
		path1 = path1+Integer.toString(pic);
		path1+=".png";
		addfriend();
	}
	
	//��Ӻ���
	int x=1;
	public void  addfriend() {
		
		String path1 = "image/����";
		if(x>7)x=1;
		path1 = path1+Integer.toString(x++);
		path1+=".png";
		JLabel jlabel = new JLabel("Josephus",new ImageIcon(path1),JLabel.LEFT);
		jlabel.setBackground(Color.BLUE);
		jlabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//popuMenu.show(jlabel,e.getX(),e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override//�����������
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.blue);
			}

			@Override//����뿪���
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel jl=(JLabel)e.getSource();
				jl.setForeground(Color.black);
			}
			
			
		});
		jlabel.setFont(new Font("����", Font.PLAIN, 24));
		panel_1.add(jlabel);
		
	}
	//����Ⱥ��
	public void startchat() {
		
	}
}
