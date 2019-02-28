package com.hlyc.Herofour;

public class Message {
	private String id;
	private String message;
	private String msgreply;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsgreply() {
		return msgreply;
	}
	public void setMsgreply(String msgreply) {
		this.msgreply = msgreply;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", msgreply=" + msgreply + "]";
	}
	public Message(String id, String message, String msgreply) {
		super();
		this.id = id;
		this.message = message;
		this.msgreply = msgreply;
	}


}
