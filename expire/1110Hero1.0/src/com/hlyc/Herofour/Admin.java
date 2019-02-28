package com.hlyc.Herofour;

public class Admin {
private String id;
private String password;
public String getId() {
	return id;
}
public void setId(String username) {
	this.id = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Admin [id=" + id + ", password=" + password + "]";
}
public Admin(String id, String password) {
	super();
	this.id = id;
	this.password = password;
}











}
