package com.Josephus.chat.Tools;

import java.awt.Color;


import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class FontSupport {

	//��װ����ķ���
//	JTextPane txtSent, ��Ҫ���õ��ı���
//	Color color, ������ɫ
//	String fontFamily, ����
//	int fontStyle, ���
//	int fontsize ��С
	public static void setFont(JTextPane txtSent,Color color,String fontFamily,int fontStyle,int fontsize) {
		
		//�ĵ�
		Document document = txtSent.getDocument();
		
		try {
			//���һ������������ʽ����
			StyleContext sc = StyleContext.getDefaultStyleContext();
			
			//������ɫ
			AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, color);
			
			Font font = new Font(fontFamily,fontStyle,fontsize);
			//Ϊ��ʽ�������
			aset =  sc.addAttribute(aset, StyleConstants.Family, font.getFamily());
			//���������С
			aset =  sc.addAttribute(aset, StyleConstants.FontSize, fontsize);
			if(fontStyle==Font.BOLD) {//����
				aset=sc.addAttribute(aset,  StyleConstants.Bold, true);
			}
			if(fontStyle==Font.ITALIC) {//б��
				aset=sc.addAttribute(aset,  StyleConstants.Italic, true);
				
			}
			if(fontStyle==Font.PLAIN) {//����
				aset=sc.addAttribute(aset,  StyleConstants.Bold, false);
				aset=sc.addAttribute(aset,  StyleConstants.Italic, false);
			}
			
			int start = txtSent.getSelectionStart();
			int end = txtSent.getSelectionEnd();
			String str= document.getText(start, end-start);
			
			//�Ƴ�ԭ�����ַ���
			document.remove(start, end-start);
			
			//�����µ��ַ���
			document.insertString(start, str, aset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//��������ķ���
	public static List<FontStyle> fontEncode(JTextPane textPane){
		
		Document document = textPane.getDocument();
		
		List<FontStyle> list = new ArrayList<FontStyle>();
		
		for(int i=0;i<document.getLength();i++) {
			try {
				
				StyledDocument sd= textPane.getStyledDocument();
				FontStyle font = new FontStyle();
				Element e = sd.getCharacterElement(i);
				
				if(e instanceof LeafElement) {
					if(e.getName().equals("content")) {
						System.out.println("��ʼ����ת��");
						AttributeSet as = e.getAttributes();
						font.setContent(sd.getText(i,1));
						
						font.setFontFamily(as.getAttribute(StyleConstants.Family).toString());
						
						font.setSize((Integer)as.getAttribute(StyleConstants.FontSize));
						
						font.setFontStyle((Integer)as.getAttribute(StyleConstants.FontSize));
						
						font.setColor((Color)as.getAttribute(StyleConstants.Foreground));
						
						if(StyleConstants.isBold(as)) {
							font.setFontStyle(Font.BOLD);
							
						}else if(StyleConstants.isItalic(as)) {
							font.setFontStyle(Font.ITALIC);
						}else {
							font.setFontStyle(Font.PLAIN);
						}
					}
					else if(e.getName().equals("icon")) {
						//����ͼƬ·��
						System.out.println("��ʼͼƬת��");
						font.setPath(e.getAttributes().getAttribute(StyleConstants.IconAttribute).toString());
					}
				}
				list.add(font);
			}catch(Exception e) {
				
			}
		}
		return list;
	}
	
	static StyleContext sc=null;
	/*
	 * ���ݽ���
	 */
	public static void fontDecode(JTextPane textPane,List<FontStyle> list,String sender,String receiver) {
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String dateName = df.format(calendar.getTime());
		Document doc = contentAppend(textPane,sender+"   "+dateName+"\n");
		
		
		sc= StyleContext.getDefaultStyleContext();
		
		for(FontStyle zi:list) {
			if(zi!=null) {
				if(zi.getContent()!=null) {
					try {
						System.out.println("��ʼ���ֽ���");
					//���������ɫ	
						AttributeSet aset =sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, zi.getColor());
						Font font = new Font(zi.getFontFamily(),zi.getFontStyle(),zi.getSize());
						
					//�������
						aset = sc.addAttribute(aset, StyleConstants.Family, font.getFamily());
						aset =sc.addAttribute(aset, StyleConstants.FontSize, zi.getSize());
					
					if(zi.getFontStyle()==Font.BOLD) {
						aset = sc.addAttribute(aset, StyleConstants.Bold, true);
						
					}
					if(zi.getFontStyle()==Font.ITALIC) {
						aset =sc.addAttribute(aset, StyleConstants.Italic, false );
						
					}
					if(zi.getFontStyle()==Font.PLAIN) {
						aset =sc.addAttribute(aset, StyleConstants.Bold, false );
						aset = sc.addAttribute(aset, StyleConstants.Italic, false);
					
					}
					doc.insertString(doc.getLength(), zi.getContent(), aset);
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}else {
					System.out.println("��ʼͼƬ����");
					//����ͼƬ����Ϣ��
					textPane.setCaretPosition(doc.getLength());
					System.out.println(zi.getPath());
					textPane.insertIcon(new ImageIcon(zi.getPath()));
				}
			}
		}
		contentAppend(textPane,"\n");
		
	}
	
	/*
	 * 
	 * ��ĳ��Text����������
	 */
	public static Document contentAppend(JTextPane textPane,String content) {
		
		Document doc = textPane.getDocument();
		
		StyleContext sc = StyleContext.getDefaultStyleContext();
		
		AttributeSet asetLine = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
		
		try {
			doc.insertString(doc.getLength(), content, asetLine);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		textPane.setCaretPosition(doc.getLength()); 
		return doc;
	}
}
