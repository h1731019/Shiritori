package model;

public class Account {
	private String userId;
	private String pass;
	
	public Account(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	
	public String getUserId() {return userId;}
	public String getPass(){return pass;}
}
