package com.hlyc.Tetris;

public class CellGame {
public static void main(String[] args) {
	//����cell
	System.out.println("=======����cell======");
//	����cell���󲢴�ӡ
	Cell cell=new Cell();
	cell.row=16;
	cell.col=5;
//	printCell(cell);
	cell.moveLeft(2);
	printCell(cell);
}
public static void printCell(Cell cell) {
	int totalRow=20;
	int totalcol=10;
	for(int row=0;row<totalRow;row++){
		for(int col=0;col<totalRow;col++){
			if(cell.row==row&&cell.col==col){
				//��ӡָ������
				System.out.print("*");
			}else {
				System.out.print("-");
			}
		}
		System.out.println();
		
	}
//	System.out.println();
	
}
}
