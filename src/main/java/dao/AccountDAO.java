package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;



public class AccountDAO {
	private final String JBDC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//アカウント検索
	public Account findByAccount(Account account) {
		//JBDCドライバを読み込む
		Account result = null;
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT USER_ID , PASS FROM ACCOUNTS WHERE USER_ID= ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getPass());
			
			//SELECt文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//データベースからの値の取り出し
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				result = new Account(userId,pass);
				return result;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	//アカウント登録
	public boolean newAccount(Account account) {
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO ACCOUNTS VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getPass());
			
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

