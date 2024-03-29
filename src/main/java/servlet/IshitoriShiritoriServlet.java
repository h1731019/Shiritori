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

import dao.ShiritoriDAO;
import model.WordLogic;

/**
 * Servlet implementation class IshitoriServlet
 */
@WebServlet("/IshitoriShiritoriServlet")
public class IshitoriShiritoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IshitoriShiritoriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//リセットボタンを押された時の処理
		if(action !=null && action.equals("reset")) {
			session.removeAttribute("count");
			session.removeAttribute("scopewords");
			session.removeAttribute("list");
			session.removeAttribute("namber");
		}
		List<String> scopewords = (List<String>) session.getAttribute("scopewords");
		//セッションスコープに使われたワードを保存するためのリストを作成
		if(scopewords==null) {
			scopewords = new ArrayList<>();
			session.setAttribute("scopewords", scopewords);
		}
		
		//cpuに処理が渡されたら
		if(action !=null && action.equals("cpu")) {
			//フォームされた値を取得
			String namber = (String) session.getAttribute("namber");
			//尻からワードを検索
			ShiritoriDAO dao = new ShiritoriDAO();
			WordLogic wordlogic = new WordLogic(namber);
			String shiri = wordlogic.getShiri();
			//ワードの選定
			List<String> list = dao.ishitoriFindByWord(shiri);
			session.setAttribute("list", list);
			
	        String wordcheck = list.get(0) ;
	        
	        
	        //スコープに保存
	        scopewords.add(wordcheck);
	        session.setAttribute("scopewords", scopewords);
	        session.setAttribute("namber", wordcheck);
	        wordlogic = new WordLogic(wordcheck);
	        shiri = wordlogic.getShiri();
	        list = dao.ishitoriFindByWord(shiri);
	        session.setAttribute("list",list);
	        List<String> newList = new ArrayList<>();

	        for (String listItem : list) {
	            boolean isMatched = false;
	            for (String scopeWord : scopewords) {
	                if (scopeWord.equals(listItem)) {
	                    isMatched = true;
	                    break;
	                }
	            }
	            if (!isMatched) {
	                newList.add(listItem);
	            }
	        }

	        // 新しいリストをセッションに設定
	        session.setAttribute("list", newList);	
	        
	        //選んだワードに「ん」がついていたらフォワード
	        if(wordcheck.substring(wordcheck.length()-1).equals("ん")) {
				String result = "<font color=\"red\">あなたの勝ち!!!</font>";
				request.setAttribute("result", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritori.jsp");
	    		dispatcher.forward(request, response);
	    		return;
			}
			int count = (int) session.getAttribute("count");
			count -= wordcheck.length(); 
			session.setAttribute("count",count);
			if(count<=0) {
				count=0;
				String errormsg="<font color=\"red\">あなたの勝ち!!!</font><br>";
				session.setAttribute("count", count);
				request.setAttribute("errormsg", errormsg);
			}
		}
		if(session.getAttribute("count") == null) {
			int count=15;
			session.setAttribute("count", count);
		}
		if(session.getAttribute("list") == null) {
			ShiritoriDAO dao = new ShiritoriDAO();
			List<String> list = dao.randomFindByWord();
			session.setAttribute("list", list);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritori.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String namber = request.getParameter("namber");
		List<String> scopewords=(List<String>) session.getAttribute("scopewords");
		
		
		if(namber==null) {
			String errormsg = "値が選択されていません<br>";
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritori.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if(namber.substring(namber.length()-1).equals("ん")) {
			String result = "<font color=\"red\">「ん」がつきました。<br>コンピュータの勝ち!!!</font>";
			scopewords.add(namber);
			request.setAttribute("result", result);
			session.setAttribute("namber", namber);
			session.setAttribute("scopewords",scopewords);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritori.jsp");
    		dispatcher.forward(request, response);
    		return;
		}
		
		
		//セッションスコープに送られてきたワードを保存
		scopewords.add(namber);
		session.setAttribute("namber", namber);
		session.setAttribute("scopewords", scopewords);
		
		//尻からワードを検索
		ShiritoriDAO dao = new ShiritoriDAO();
		WordLogic wordlogic = new WordLogic(namber);
		String shiri = wordlogic.getShiri();
		//ワードの選定
		List<String> list = dao.ishitoriFindByWord(shiri);
		session.setAttribute("list", list);
		
		List<String> newList = new ArrayList<>();

		for (String listItem : list) {
		    boolean isMatched = false;
		    for (String scopeWord : scopewords) {
		        if (scopeWord.equals(listItem)) {
		            isMatched = true;
		            break;
		        }
		    }
		    if (!isMatched) {
		        newList.add(listItem);
		    }
		}

		// 新しいリストをセッションに設定
		session.setAttribute("list", newList);
		
		
		int number = namber.length();
		int count = (int) session.getAttribute("count");
		count -= number;
		session.setAttribute("count",count);
		if(count<=0) {
			count=0;
			String errormsg="<font color=\"red\">あなたの勝ち!!!</font><br>";
			session.setAttribute("count", count);
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritori.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitorishiritoric.jsp");
		dispatcher.forward(request, response);
	}

}
