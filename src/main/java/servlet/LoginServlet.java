package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.removeAttribute("errormsg");
		session.removeAttribute("loginErrormsg");
		
		String userId=request.getParameter("userId");
		String pass = request.getParameter("pass");
		System.out.println(userId+pass);
		
		if(userId!=null&&pass!=null) {
		
		//ログイン処理の実行
		Account account = new Account(userId,pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(account);
		//ログイン処理の成否によって処理を分岐
		if(result) {//ログイン成功時
//			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
			dispatcher.forward(request, response);
			return;
		}else {//ログイン失敗時
			//リダイレクト
			String errormsg="<font color=\"red\">ログインに失敗しました</font>";
			session.setAttribute("loginErrormsg", errormsg);
			response.sendRedirect("ShiritoriServlet");
			return;
			
		}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shiritori.jsp");
		dispatcher.forward(request, response);
	}

}
