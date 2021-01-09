package com.Josephus.chat.Tools;

import java.awt.Color;
import java.io.Serializable;

public class FontStyle implements Serializable{

	private static final long serialVersionUID = 7688427344458949859L;

	private String content;//
	private String fontFamily;//
	
	private int size;//
	
	private Color color;//
	private int fontStyle;//
	private String path;//ÎÄ¼þÂ·¾¶
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}
}
