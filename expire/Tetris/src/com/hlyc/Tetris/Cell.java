package com.hlyc.Tetris;

public class Cell {
	// cell��Ķ���
	int row;
	int col;

	// cell���drop����
	public void drop() {
		row++;
	}

	// cell���moveLeft����
	public void moveLeft(int d) {
		col -= d;
	}
	//������ӵ�λ����Ϣ��getCellInfo(),�÷������ظ��� ��λ����Ϣ
	public String getCellInfo(){
		return row+","+col;		
	}
}
