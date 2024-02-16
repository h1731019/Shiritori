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
import model.NewAccountLogic;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId=request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		//同じアカウントが存在しないか
		Account account = new Account(userId,pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(account);
		//同じアカウントが存在した場合リダイレクトする
		if(result) {//同じアカウントが存在した場合
			String errormsg ="<font color=\"red\">同じアカウントが存在します</font>";
			request.setAttribute("errormsg", errormsg);
			//リダイレクト
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
			dispatcher.forward(request, response);
			return;
		}else {//存在しなかった場合
			//daoにアカウントの情報を追加、確認
			NewAccountLogic bo2 = new NewAccountLogic();
			boolean result2 = bo2.execute(account);
			if(result2) {//追加、確認できた場合アカウント情報をセッションスコープに保存してリダイレクト
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				String errormsg ="<font color=\"red\">アカウント登録に成功しました</font>";
				request.setAttribute("errormsg", errormsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
				dispatcher.forward(request, response);
				return;
			}else { //追加、確認ができなかった場合リダイレクト
				String errormsg ="<font color=\"red\">アカウント登録に失敗しました</font>";
				request.setAttribute("errormsg", errormsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
		}
	}

}
