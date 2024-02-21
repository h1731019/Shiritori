package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String text;
	private String timestamp;
	
	public Mutter() {}
	public Mutter(String userName , String text , String timestamp) {
		this.userName = userName;
		this.text = text;
		this.timestamp = timestamp;
	}
	public Mutter(int id , String userName, String text , String timestamp) {
		this.id = id;
		this.userName=userName;
		this.text=text;
		this.timestamp = timestamp;
	}
	
	public int getId() {return id;}
	public String getUserName() {return userName;}
	public String getText() {return text;}
	public String getTimestamp() {return timestamp;}

}
