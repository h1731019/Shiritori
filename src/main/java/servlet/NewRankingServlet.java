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

import dao.RankingDAO;
import model.Score;

/**
 * Servlet implementation class NewRankingServlet
 */
@WebServlet("/NewRankingServlet")
public class NewRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRankingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//ユーザーIDの取得(userId)
		String userId =(String)session.getAttribute("userId");
		// 日時を指定されたフォーマットで表示(timestamp)
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = currentDateTime.format(formatter);
        //リストを取得し手数を取り出す(scores)
        List<String> list = (List<String>)session.getAttribute("list");
        int scores = list.size();
        Score score = new Score(timestamp,userId,scores);
		RankingDAO rankingdao = new RankingDAO();
		boolean result = rankingdao.newRanking(score);
		if(result) {
			String errormsg ="<font color=\"red\">ランキング登録に成功しました</font>";
			request.setAttribute("errormsg", errormsg);
			session.setAttribute("userId", userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/rankingCheck.jsp");
			dispatcher.forward(request, response);
			return;
		}else {
			String errormsg ="<font color=\"red\">ランキング登録に失敗しました</font>";
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/rankingCheck.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
