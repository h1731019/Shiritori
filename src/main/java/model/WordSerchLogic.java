package model;

import dao.ShiritoriDAO;

public class WordSerchLogic {
	private String returnword;
	private String[] words;
	
	public WordSerchLogic() {}
	
	public String[] FindWord(String word) {
		WordLogic wordLogic = new WordLogic(word);
		ShiritoriDAO dao = new ShiritoriDAO();
		ShiritoriWord shiritoriWord = dao.findByWord(wordLogic.getShiri());
		String words[] = shiritoriWord.getWordVariations();
		
		
		return words;
		
		
		
	}
	
	public String getReturnword() {return returnword;}
	public String[] getWords() {return words;}
	

}
