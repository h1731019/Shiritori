package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IshitoriServlet
 */
@WebServlet("/IshitoriServlet")
public class IshitoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IshitoriServlet() {
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
			session.removeAttribute("namber");
		}
		//cpuに処理が渡されたとき(フォームから送信があったとき)
		if(action !=null && action.equals("cpu")) {
			Random rand = new Random();
			int randomNumber = rand.nextInt(3) + 1;
			int count = (int) session.getAttribute("count");
			count -= randomNumber; 
			String str= "コンピュータは"+randomNumber+"個石を取った。";
			session.setAttribute("namber",str);
			session.setAttribute("count",count);
			if(count<=0) {
				count=0;
				String errormsg="あなたのかち<br>";
				session.setAttribute("count", count);
				request.setAttribute("errormsg", errormsg);
			}
		}
		if(session.getAttribute("count") == null) {
			int count=15;
			session.setAttribute("count", count);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitori.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String namber = request.getParameter("namber");
		
		
		if(namber==null) {
			String errormsg = "値が選択されていません<br>";
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitori.jsp");
			dispatcher.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String str= "あなたは"+namber+"個石を取った。";
		session.setAttribute("namber",str);
		int number = Integer.parseInt(namber);
		int count = (int) session.getAttribute("count");
		count -= number;
		session.setAttribute("count",count);
		if(count<=0) {
			count=0;
			String errormsg="コンピュータのかち<br>";
			session.setAttribute("count", count);
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitori.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ishitoric.jsp");
		dispatcher.forward(request, response);
	}

}
