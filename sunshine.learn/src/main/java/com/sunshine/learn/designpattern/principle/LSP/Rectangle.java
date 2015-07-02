package com.sunshine.learn.designpattern.principle.LSP;

// 矩形
public class Rectangle {

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

// 正方形(因为Square的长宽相同，用一个size来统一 所以改写了父类的方法，因为它们实际上都是对size进行修改)
class Square extends Rectangle{
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
