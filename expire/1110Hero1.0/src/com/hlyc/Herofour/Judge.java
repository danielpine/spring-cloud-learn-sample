package com.hlyc.Herofour;

public class Judge {
public boolean judge(String job){
	
	if(
	(job.equals("射手")||
	job.equals("刺客")||
	job.equals("法师")||
	job.equals("坦克/战士"))
			)return true;
	return false;
}
}
