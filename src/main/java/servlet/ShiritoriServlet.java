package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AnswerWord;
import model.WordLogic;
import model.WordSerchLogic;

/**
 * Servlet implementation class ShiritoriServlet
 */
@WebServlet("/ShiritoriServlet")
public class ShiritoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiritoriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//空のwordcheck
		String wordcheck = "";
		//セッションスコープを取得
		HttpSession session = request.getSession();
		if(request.getAttribute("list") == null) {
			// リストの生成
		    List<String> list = new ArrayList<>();
		    
		   //セッションスコープに保存
		    
		    session.setAttribute("list",list);
		}

		//checkwordをインスタンスに保存
        AnswerWord answerword = new AnswerWord(wordcheck); 
        //セッションスコープに保存
        session.setAttribute("answerword",answerword);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String word=request.getParameter("word");
		
		//セッションスコープから既に使われたワードの一覧（配列）を取得
		HttpSession session = request.getSession();
	    List<String> list = (List<String>) session.getAttribute("list");
	    
	    //プレイヤーの入力した単語の語尾に「ん」がついていれば専用のjspにフォワード
	    //フォームから入力された単語の頭文字と直前にlistに入った単語の尻の文字を比較。正しくなければフォワード
	   
	    if(list.size() !=0 ) {
	    	int i = list.size()-1;
		    WordLogic kasira = new WordLogic(word);
		    WordLogic siri = new WordLogic(list.get(i));
//		    System.out.println(siri.getWord());
//		    System.out.println(kasira.getWord());
//			System.out.println(kasira.getInitial()+siri.getShiri());
//	    		for(String list1:list) {
//	    			System.out.println(list1);
//	    		}
		    
		    //プレイヤーの入力した単語が既に使われていないか
		    for(String lists:list) {
		    	if(lists.equals(word)) {
		    		String errormsg = "<br><font color=\"red\">*すでに使われている単語です</font>";
		    		session.setAttribute("errormsg", errormsg);
		    		
		    		//フォワード
		    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		    		dispatcher.forward(request, response);
		    		
		    	    return;
		    	}
		    }
		    
			if(kasira.getShiri().equals("ん")) {
				String result = "コンピュータの勝ち";
				request.setAttribute("result", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritoriResult.jsp");
	    		dispatcher.forward(request, response);
	    		return;
			}
			
			
	    		if(kasira.getInitial().equals(siri.getShiri())) {
//	    			System.out.println("あっています");
	    		}else {
//	    			System.out.println("違います");
		    		String errormsg ="<br><font color=\"red\">*頭文字が正しくありません</font>";
		    		session.setAttribute("errormsg", errormsg);
	
		    		//フォワード
		    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		    		dispatcher.forward(request, response);
		    		
		    	    return;
	    		}
		    }
	    
	    
	    
//	    System.out.println(list.size());
	    String errormsg = "";
	    session.setAttribute("errormsg", errormsg);
	    list.add(word);
	    session.setAttribute("list", list);	
	    
		//答えの処理
    	WordSerchLogic wordSerchLogic = new WordSerchLogic();
    	String[] words = wordSerchLogic.FindWord(word);
//    	for (String word2 : words) {
//    		System.out.println(word2);
//    	}
        List<String> scopewords = list;
        int count = 0;
        String wordcheck = words[0] ;
        
        for (String dbwords : words) {
            for (String scopeword : scopewords) {
                if (scopeword.equals(dbwords)) {
                    count++;
                    
                    wordcheck = words[count];
                }
            }
        }
        
//        System.out.println(wordcheck.substring(wordcheck.length()-1));
        
        //コンピュータ側の答えた単語の語尾に「ん」がついていれば専用のjspにフォワード
        if(wordcheck.substring(wordcheck.length()-1).equals("ん")) {
			String result = "あなたの勝ち";
			request.setAttribute("result", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritoriResult.jsp");
    		dispatcher.forward(request, response);
    		return;
		}
        
        //checkwordをインスタンスに保存
        AnswerWord answerword = new AnswerWord(wordcheck); 
        //セッションスコープに保存
        session.setAttribute("answerword",answerword);
		
		
			
	    // リストに要素を追加する
	    
	    list.add(wordcheck);
			
	    // リクエストスコープにArrayListを設定
	    session.setAttribute("list", list);
	    
//	    List<String> list2 = (List<String>) session.getAttribute("list");
//	    for(String lists : list2) {
//	    	System.out.println(lists);
//	    }
			
	    //フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		dispatcher.forward(request, response);
		
	}

}
