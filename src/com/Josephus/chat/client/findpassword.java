package com.Josephus.chat.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import com.Josephus.chat.Dao.Password;
import com.Josephus.chat.sever.sendemail.passwordmail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class findpassword extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    
		                    break;
		                }
		            }
		        }catch(Exception e) {
		        	System.out.println(e);
		        }

				try {
					findpassword frame = new findpassword();
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
	private String emailnum="123";
	private JTextField new1;
	private JTextField new2;
	JTextPane textPane1;
	JTextPane textPane2;
	JTextPane textPane3;
	JPanel panel ;
	findpassword fram;
	public findpassword() {
		
		ImageIcon icon=new ImageIcon("image/findpassword.png");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());
		this.fram=this;

		

		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 432, 253);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(24, 44, 72, 35);
		panel1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u9A8C\u8BC1\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(24, 140, 93, 43);
		panel1.add(lblNewLabel_1);
		
		 textPane1 = new JTextPane();
		textPane1.setFont(new Font("宋体", Font.PLAIN, 22));
		textPane1.setBounds(122, 44, 220, 35);
		panel1.add(textPane1);
		
		 textPane3 = new JTextPane();
		textPane3.setFont(new Font("宋体", Font.PLAIN, 22));
		textPane3.setBounds(122, 140, 220, 35);
		panel1.add(textPane3);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.setVisible(false);
		//验证是否正确
		JButton yanzheng = new JButton("\u70B9\u51FB\u9A8C\u8BC1");
		yanzheng.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		yanzheng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textPane3.getText();
				
				if(text.equals(emailnum)) {
					panel.setVisible(true);
					panel1.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "验证码错误", "Tips",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		yanzheng.setForeground(Color.BLUE);
		yanzheng.setContentAreaFilled(false);
		yanzheng.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		yanzheng.setBounds(181, 199, 113, 27);
		panel1.add(yanzheng);
		
		JLabel lblNewLabel_2 = new JLabel("\u90AE\u7BB1");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(24, 92, 72, 35);
		panel1.add(lblNewLabel_2);
		
		 textPane2 = new JTextPane();
		textPane2.setFont(new Font("宋体", Font.PLAIN, 22));
		textPane2.setBounds(122, 92, 220, 35);
		panel1.add(textPane2);
		//获取验证码
		JButton huoqu = new JButton("\u83B7\u53D6");
		huoqu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String regex="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
				if(!Password.Findemail(textPane1.getText()).equals(textPane2.getText())) 
				{
					JOptionPane.showMessageDialog(null, "账号与邮箱不匹配", "Tips",JOptionPane.ERROR_MESSAGE);
				}
				else if(textPane2.getText().matches(regex))
				{
					JOptionPane.showMessageDialog(null, "验证码已发送请注意查收", "Tips",JOptionPane.ERROR_MESSAGE);
					emailnum=passwordmail.sendmail(textPane2.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "邮箱不合法", "Tips",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		huoqu.setForeground(Color.BLUE);
		huoqu.setContentAreaFilled(false);
		huoqu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		huoqu.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 12));
		huoqu.setBounds(362, 140, 56, 35);
		panel1.add(huoqu);
		
		 
		
		JLabel password1 = new JLabel("\u65B0\u5BC6\u7801");
		password1.setFont(new Font("宋体", Font.PLAIN, 30));
		password1.setBounds(37, 45, 90, 37);
		panel.add(password1);
		
		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(37, 118, 108, 37);
		panel.add(lblNewLabel_3);
		
		new1 = new JTextField();
		new1.setFont(new Font("宋体", Font.PLAIN, 22));
		new1.setBounds(168, 45, 204, 37);
		panel.add(new1);
		new1.setColumns(10);
		
		new2 = new JTextField();
		new2.setFont(new Font("宋体", Font.PLAIN, 22));
		new2.setBounds(168, 118, 204, 35);
		panel.add(new2);
		new2.setColumns(10);
		
		JButton wancheng = new JButton("\u5B8C\u6210");
		wancheng.setForeground(Color.BLUE);
		wancheng.setContentAreaFilled(false);
		wancheng.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		wancheng.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		wancheng.setBounds(198, 192, 113, 37);
		panel.add(wancheng);
		wancheng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String p1 = new1.getText();
				String p2 = new2.getText();
				
				if(p1==null||p2==null||!p1.equals(p2)) {
					JOptionPane.showMessageDialog(null, "两次密码不匹配", "Tips",JOptionPane.ERROR_MESSAGE);
				}else  {
					Password.updatepassword(textPane1.getText(), p1);
					JOptionPane.showMessageDialog(null, "密码修改成功", "Tips",JOptionPane.ERROR_MESSAGE);
					fram.dispose();
				}
			}
		});
		
		//获取屏幕的大小
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int w = screenSize.width;
				int h = screenSize.height;
				setLocation((w-432)/2,(h-253)/2);
	}
}
