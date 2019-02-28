package com.hlyc.Herofour;

public class Hero_fs {
  private String              id                  ;  
	private String            name                ;  
	private String            job                 ;  
	//skill N I II III                                  
	private String             n                  ; 
	private String             i                  ; 
	private String             ii                 ; 
	private String             iii                ; 
	private String             level              ;  
	private String             red                ;  
	private String             blue               ;  
	private String             useblue       ;  
	private String             damage             ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public String getIi() {
		return ii;
	}
	public void setIi(String ii) {
		this.ii = ii;
	}
	public String getIii() {
		return iii;
	}
	public void setIii(String iii) {
		this.iii = iii;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRed() {
		return red;
	}
	public void setRed(String red) {
		this.red = red;
	}
	public String getBlue() {
		return blue;
	}
	public void setBlue(String blue) {
		this.blue = blue;
	}
	public String getUseblue() {
		return useblue;
	}
	public void setUseblue(String useblue) {
		this.useblue = useblue;
	}
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	@Override
	public String toString() {
	return	"法师 --->>id=" + id + "|姓名：" + name + "|职业：" + job + "|普攻：" + n + "| 一技能：" + i + "|二技能：" + ii + "|三技能："
				+ iii + "|等级：" + level + "|生命值：" + red + "|蓝量：" + blue + "|耗法：" + useblue
				+ "|伤害：" + damage + "|<<";
//		return "Hero_fs [id=" + id + ", name=" + name + ", job=" + job + ", n=" + n + ", i=" + i + ", ii=" + ii
//				+ ", iii=" + iii + ", level=" + level + ", red=" + red + ", blue=" + blue + ", useblue=" + 
//				+ ", damage=" + damage + "]";
	}
	public Hero_fs(String id, String name, String job, String n, String i, String ii, String iii, String level,
			String red, String blue, String useblue, String damage) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.n = n;
		this.i = i;
		this.ii = ii;
		this.iii = iii;
		this.level = level;
		this.red = red;
		this.blue = blue;
		this.useblue = useblue;
		this.damage = damage;
	}  
	
	
	
	
	
	
	
}
