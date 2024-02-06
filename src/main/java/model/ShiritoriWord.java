package model;

public class ShiritoriWord {
	private String initial;
	private String[] wordVariations;
	
	
	public ShiritoriWord(String initial, String wordVariation1,String wordVariation2, String wordVariation3, String wordVariation4, String wordVariation5) {
		this.initial=initial;
		this.wordVariations = new String[] {wordVariation1,wordVariation2,wordVariation3,wordVariation4,wordVariation5};
		
	}
	public String getInitial() {return initial;}
	public String[] getWordVariations() {return wordVariations;}

}