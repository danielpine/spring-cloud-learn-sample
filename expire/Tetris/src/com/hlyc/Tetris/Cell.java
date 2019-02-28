package com.hlyc.Tetris;

public class Cell {
	// cell类的对象
	int row;
	int col;

	// cell类的drop方法
	public void drop() {
		row++;
	}

	// cell类的moveLeft方法
	public void moveLeft(int d) {
		col -= d;
	}
	//输出格子的位置信息，getCellInfo(),该方法返回格子 的位置信息
	public String getCellInfo(){
		return row+","+col;		
	}
}
