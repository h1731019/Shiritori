package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Mutter> findALL(){
		List<Mutter> mutterList = new ArrayList<>();
		//JBDCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER, DB_PASS)){
			
			//SELECT文の準備
			String sql = "SELECT ID , 名前 , つぶやき , 日付 FROM MUTTERS ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			//SELECCT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("名前");
				String text = rs.getString("つぶやき");
				String timestamp = rs.getString("日付");
				Mutter mutter = new Mutter(id , userName, text , timestamp);
				mutterList.add(mutter);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
		public boolean create(Mutter mutter) {
			//JDBCドライバを読み込む
			try {
				Class.forName("org.h2.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			//データベース接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS)){
				
				//INSERT文の準備(idは自動連番なので指定しなくてもよい
				String sql = "INSERT INTO MUTTERS(名前 , つぶやき , 日付) VAlUES(?,?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//INSERT文中の「？」に使用する値を設定してSQL文を完成
				pStmt.setString(1, mutter.getUserName());
				pStmt.setString(2, mutter.getText());
				pStmt.setString(3, mutter.getTimestamp());
				
				//INSERT文を実行(resultには追加された行数が代入される)
				int result = pStmt.executeUpdate();
				if(result != 1) {
					return false;
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	

}
