package model;

public class WordLogic {
	private String word;
	private String initial;
	private String shiri;
	
	public WordLogic(String word) {
		this.word=word;
		this.shiri=word.substring(word.length()-1);
		this.initial=word.substring(0,1);
		switch(this.shiri) {
		case "ー" : shiri = word.substring(word.length() - 2, word.length() - 1); break;
		case "が" : shiri = "か"; break;
		case "ぎ" : shiri = "き"; break;
		case "ぐ" : shiri = "く"; break;
		case "げ" : shiri = "け"; break;
		case "ご" : shiri = "こ"; break;
		case "ざ" : shiri = "さ"; break;
		case "じ" : shiri = "し"; break;
		case "ず" : shiri = "す"; break;
		case "ぜ" : shiri = "せ"; break;
		case "ぞ" : shiri = "そ"; break;
		case "だ" : shiri = "た"; break;
		case "ぢ" : shiri = "ち"; break;
		case "づ" : shiri = "つ"; break;
		case "で" : shiri = "て"; break;
		case "ど" : shiri = "と"; break;
		case "ば" : shiri = "は"; break;
		case "び" : shiri = "ひ"; break;
		case "ぶ" : shiri = "ふ"; break;
		case "べ" : shiri = "へ"; break;
		case "ぼ" : shiri = "ほ"; break;
		case "ぱ" : shiri = "は"; break;
		case "ぴ" : shiri = "ひ"; break;
		case "ぷ" : shiri = "ふ"; break;
		case "ぺ" : shiri = "へ"; break;
		case "ぽ" : shiri = "ほ"; break;
		case "ぁ" : shiri = "あ"; break;
		case "ぃ" : shiri = "い"; break;
		case "ぅ" : shiri = "う"; break;
		case "ぇ" : shiri = "え"; break;
		case "ぉ" : shiri = "お"; break;
		case "ゃ" : shiri = "や"; break;
		case "ゅ" : shiri = "ゆ"; break;
		case "ょ" : shiri = "よ"; break;
		case "を" : shiri = "お"; break;
		case "っ" : shiri = "つ"; break;
		case "ゑ" : shiri = "え"; break;
		}
		switch(this.initial) {
		case "が" : initial = "か"; break;
		case "ぎ" : initial = "き"; break;
		case "ぐ" : initial = "く"; break;
		case "げ" : initial = "け"; break;
		case "ご" : initial = "こ"; break;
		case "ざ" : initial = "さ"; break;
		case "じ" : initial = "し"; break;
		case "ず" : initial = "す"; break;
		case "ぜ" : initial = "せ"; break;
		case "ぞ" : initial = "そ"; break;
		case "だ" : initial = "た"; break;
		case "ぢ" : initial = "ち"; break;
		case "づ" : initial = "つ"; break;
		case "で" : initial = "て"; break;
		case "ど" : initial = "と"; break;
		case "ば" : initial = "は"; break;
		case "び" : initial = "ひ"; break;
		case "ぶ" : initial = "ふ"; break;
		case "べ" : initial = "へ"; break;
		case "ぼ" : initial = "ほ"; break;
		case "ぱ" : initial = "は"; break;
		case "ぴ" : initial = "ひ"; break;
		case "ぷ" : initial = "ふ"; break;
		case "ぺ" : initial = "へ"; break;
		case "ぽ" : initial = "ほ"; break;
		case "ぁ" : initial = "あ"; break;
		case "ぃ" : initial = "い"; break;
		case "ぅ" : initial = "う"; break;
		case "ぇ" : initial = "え"; break;
		case "ぉ" : initial = "お"; break;
		case "ゃ" : initial = "や"; break;
		case "ゅ" : initial = "ゆ"; break;
		case "ょ" : initial = "よ"; break;
		case "を" : initial = "お"; break;
		case "っ" : initial = "つ"; break;
		case "ゑ" : initial = "う"; break;
		
		}
	}
	public String getWord() {return word;}
	public String getInitial() {return initial;}
	public String getShiri() {return shiri;}
}
