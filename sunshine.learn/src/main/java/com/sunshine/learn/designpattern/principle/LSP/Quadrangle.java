package com.sunshine.learn.designpattern.principle.LSP;

// 四边形的抽象
public class Quadrangle {
	
	private int height, width;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}

// 普通矩形继承自四边形
class Rectangle2 extends Quadrangle{
	
}

// 正方形继承自四边形
class Square2 extends Quadrangle{
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public int getHeight() {
		return this.size;
	}
	
	@Override
	public void setHeight(int height) {
		this.size = height;
	}
	
	@Override
	public int getWidth() {
		return this.size;
	}
	
	@Override
	public void setWidth(int width) {
		this.size = width;
	}
	
}
