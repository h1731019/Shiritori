package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.WordLogic;

/**
 * Servlet implementation class ShiritoriKeijibanServlet
 */
@WebServlet("/ShiritoriKeijibanServlet")
public class ShiritoriKeijibanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiritoriKeijibanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//つぶやきリストを取得してリクエストスコープに保存
				GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
				List<Mutter> mutterList = getMutterListLogic.execute();
				request.setAttribute("mutterList", mutterList);
				
				//ログインしているか確認するため
				//セッションスコープからユーザー情報を取得
				HttpSession session = request.getSession();
				String user =(String)session.getAttribute("userId");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritorikeijiban.jsp");
				dispatcher.forward(request , response);
				
				//ログインしていない場合
//				if(loginUser == null) {//ログインしていない場合
//					//リダイレクト
//					response.sendRedirect("index.jsp");
//				}else {//ログイン済みの場合
//					//フォワード
//					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
//					dispatcher.forward(request, response);
//				}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> list = getMutterListLogic.execute();
		
		String text = request.getParameter("text");
		//テキストにスペースが含まれていた場合削除
		String word = text.replaceAll("[\\s　]", "");
		 //wordが空あるいは「ー」だった場合の処理
	  	if(word == null || word.length()==0 || word.equals("ー")) {
	  		System.out.println("空の文字");
	  	    String errormsg ="<br><font color=\"red\">*不正な文字が入力、または空の文字が入力されたました。</font>";
	      	request.setAttribute("errorMsg", errormsg);
    		//マターリストを保存
    		List<Mutter> mutterList = getMutterListLogic.execute();
			session.setAttribute("mutterList" ,  mutterList);
			//フォワード
	      	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
	      	dispatcher.forward(request, response);
	      	return;
	  	 }
	    
	  //プレイヤーの入力した単語の語尾に「ん」がついていれば専用のjspにフォワード
	  	if(word.substring(word.length()-1).equals("ん")) {
	  		System.out.println("んがつきました。");
	  		String errormsg = "<font color=\"red\">語尾に「ん」がついています。</font>";
	  		request.setAttribute("errorMsg", errormsg);
    		//マターリストを保存
    		List<Mutter> mutterList = getMutterListLogic.execute();
			session.setAttribute("mutterList" ,  mutterList);
			//フォワード
	  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
	      	dispatcher.forward(request, response);
	      	return;
	  	}
	    
		//ーーが含まれていた場合警告してフォワード
		String searchWord = "ーー";
		int index = word.indexOf(searchWord);
	    if (index != -1) {
	        String errormsg ="<br><font color=\"red\">不正な文字列「 " + searchWord + "」 が見つかりました。</font>";
	        request.setAttribute("errorMsg", errormsg);
    		//マターリストを保存
    		List<Mutter> mutterList = getMutterListLogic.execute();
			session.setAttribute("mutterList" ,  mutterList);
			//フォワード
    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
    		dispatcher.forward(request, response);
    		return;
	    }
	    
	  //フォームから入力された単語の頭文字と直前にlistに入った単語の尻の文字を比較。正しくなければフォワード
	    if(list.size() !=0 ) {	
		    WordLogic kasira = new WordLogic(word);
		    String listword = list.get(0).getText().replaceAll("[\\s　]", "");
		    WordLogic siri = new WordLogic(listword);
		    System.out.println(listword);
		    
		    //頭と尻があってるか
    		if(kasira.getInitial().equals(siri.getShiri())) {
//    			System.out.println("あっています");
    		}else {
//    			System.out.println("違います");
	    		String errormsg ="<br><font color=\"red\">*頭文字が正しくありません</font>";
	    		request.setAttribute("errorMsg", errormsg);
	    		
	    		//マターリストを保存
	    		List<Mutter> mutterList = getMutterListLogic.execute();
				session.setAttribute("mutterList" ,  mutterList);

	    		//フォワード
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
	    		dispatcher.forward(request, response);
	    		return;
    		}
	    }
		
		//入力値チェック
		if(text != null && text.length() !=0) {
			
			//セッションスコープに保存されたユーザー情報を取得
			
			String userId =(String)session.getAttribute("userId");

	        // 日時を指定されたフォーマットで表示
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String timestamp = currentDateTime.format(formatter);
			
			//つぶやきを作成してつぶやきリストに追加
			Mutter mutter = new Mutter(userId , text , timestamp);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);
			
		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきを入力されていません");
		}
		
		//つぶやきリストを取得して、リクエストスコープに保存
				List<Mutter> mutterList = getMutterListLogic.execute();
				session.setAttribute("mutterList" ,  mutterList);
		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		dispatcher.forward(request , response);
	}

}
