package model;

public class Score {
	private String timestamp;
	private String userId;
	private int score;
	
	public Score(String timestamp, String userId,int score) {
		this.timestamp = timestamp;
		this.userId = userId;
		this.score = score;
	}
	
	public String getTimestamp() {return timestamp;}
	public String getUserId() {return userId;}
	public int getScore() {return score;}

}
