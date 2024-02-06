package model;

import java.io.Serializable;

public class AnswerWord implements Serializable {
	private String answerword;
	
	public AnswerWord(String answerword) {
		this.answerword=answerword;
	}
	
	public String getAnswerword() {return answerword;}

}
