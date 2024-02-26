package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ShiritoriWord;

public class ShiritoriDAO {
	private final String JBDC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public ShiritoriWord findByWord(String shiri) {
		ShiritoriWord shiritoriWord = null;
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT INITIAL, VARIATION1, VARIATION2, VARIATION3, VARIATION4, VARIATION5 FROM SHIRITORI WHERE INITIAL= ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, shiri  );
			
			//SELECt文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//データベースからの値の取り出し
				String initial = rs.getString("INITIAL");
				String variation1 = rs.getString("VARIATION1");
				String variation2 = rs.getString("VARIATION2");
				String variation3 = rs.getString("VARIATION3");
				String variation4 = rs.getString("VARIATION4");
				String variation5 = rs.getString("VARIATION5");
				shiritoriWord = new ShiritoriWord(initial,variation1,variation2,variation3,variation4,variation5);
//				String[] words= {variation1,variation2,variation3,variation4,variation5};
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return shiritoriWord;
	}
	
	public List<String> ishitoriFindByWord(String shiri) {
		List<String> list = new ArrayList<>();
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT INITIAL, VARIATION1, VARIATION2, VARIATION3, VARIATION4, VARIATION5 FROM SHIRITORI WHERE INITIAL= ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, shiri  );
			
			//SELECt文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//データベースからの値の取り出し
				String initial = rs.getString("INITIAL");
				String variation1 = rs.getString("VARIATION1");
				String variation2 = rs.getString("VARIATION2");
				String variation3 = rs.getString("VARIATION3");
				String variation4 = rs.getString("VARIATION4");
				String variation5 = rs.getString("VARIATION5");
//				shiritoriWord = new ShiritoriWord(initial,variation1,variation2,variation3,variation4,variation5);
				list.add(variation1);
				list.add(variation2);
				list.add(variation3);
				list.add(variation4);
				list.add(variation5);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> randomFindByWord() {
		List<String> list = new ArrayList<>();
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JBDCドライバが読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JBDC_URL, DB_USER, DB_PASS)){
			
			
			for(int i=0;i<=4;i++) {
				Random rand = new Random();
				int j = rand.nextInt(4)+1;
				//SELECT文を準備
				String sql = "SELECT VARIATION" + j + " FROM SHIRITORI ORDER BY RAND() LIMIT 1; ";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECt文を実行し、結果票を取得
				ResultSet rs = pStmt.executeQuery();
				
				if(rs.next()) {
					//データベースからの値の取り出し
					String variation = rs.getString(1);
					list.add(variation);
					
				}
			}
				
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
