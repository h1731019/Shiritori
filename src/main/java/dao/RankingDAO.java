package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Score;


public class RankingDAO {
	private final String JBDC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	List<Score> scoreList = new ArrayList<>();
	
	public List<Score> findRanking() {
//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT * FROM SHIRITORIRANKING ORDER BY スコア LIMIT 5";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECt文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				//データベースからの値の取り出し
				String timestamp = rs.getString("日付");
				String userId = rs.getString("ユーザー名");
				int score = rs.getInt("スコア");
				Score scores = new Score(timestamp,userId,score);
				scoreList.add(scores);
				
			}
			for(Score scoreLists:scoreList) {
				System.out.println(scoreLists.getTimestamp() + scoreLists.getUserId() + scoreLists.getScore());
				
			}
			System.out.println(scoreList.get(1).getUserId());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return scoreList;
	}
	
	public boolean newRanking(Score score) {
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO SHIRITORIRANKING VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, score.getTimestamp());
			pStmt.setString(2, score.getUserId());
			pStmt.setInt(3, score.getScore());
			
			//SELECt文を実行し、結果票を取得
			int rowsAffected = pStmt.executeUpdate();
			// 変更行数が1以上であれば成功と見なす
	        return rowsAffected > 0;
			
		}catch(SQLException e) {
			 e.printStackTrace();
		        return false;
		}
	}
}
