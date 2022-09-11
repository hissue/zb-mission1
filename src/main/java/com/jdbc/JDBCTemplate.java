package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;


 
public class JDBCTemplate {
		Connection conn = null;
		PreparedStatement psmt = null;

		public JDBCTemplate() {	
			
			try { 
				Class.forName("org.sqlite.JDBC");
				System.out.println("Driver Loading Success");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
    	public Connection getConnection() { // 커넥션 해주는 역할, DB객체
			
			
			
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");
//				conn = DriverManager.getConnection("jdbc:sqlite:C:\\dev\\test.db");
				
				System.out.println("SQL Connection Success!!");
			} catch (SQLException e) {
				 System.out.println("DB를 연결하지 못했습니다");
			}
			return conn;
		}
//		

		public void userInsert(Connection conn, String x_SWIFI_MGR_NO, String wORK_DTTM, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1,
				String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY,
				String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR,
				String x_SWIFI_REMARS3, String lAT, String lNT) {
			
			String sql = "INSERT INTO test(X_SWIFI_MGR_NO, WORK_DTTM, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM,X_SWIFI_ADRES1,X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_TY,X_SWIFI_INSTL_MBY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3,LAT,LNT) " 
					   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
//			String sql = "INSERT INTO test1(LAT,LNT)" 
//					   + " VALUES(?, ?)";
			
			
//			Connection conn = getConnection();
//			conn = getConnection();
//			PreparedStatement psmt = null;
			
//			try {
//				psmt = conn.prepareStatement(sql);
//				
//				// 첫번째, 두번째, 세번째 데이터를 넣기
//				psmt.setString(1, lAT);
//				psmt.setString(2, lNT);
//				
//				psmt.executeUpdate();
//				
//				System.out.println(psmt);
		
			
			try {
				psmt = conn.prepareStatement(sql);
				
				// 첫번째, 두번째, 세번째 데이터를 넣기
				psmt.setString(1, x_SWIFI_MGR_NO); 
				psmt.setString(2, wORK_DTTM);
				psmt.setString(3, x_SWIFI_WRDOFC);
				psmt.setString(4, x_SWIFI_MAIN_NM);
				psmt.setString(5, x_SWIFI_ADRES1);
				psmt.setString(6, x_SWIFI_ADRES2);
				psmt.setString(7, x_SWIFI_INSTL_FLOOR);
				psmt.setString(8, x_SWIFI_INSTL_TY);
				psmt.setString(9, x_SWIFI_INSTL_MBY);
				psmt.setString(10, x_SWIFI_SVC_SE);
				psmt.setString(11, x_SWIFI_CMCWR);
				psmt.setString(12, x_SWIFI_CNSTC_YEAR);
				psmt.setString(13, x_SWIFI_INOUT_DOOR);
				psmt.setString(14, x_SWIFI_REMARS3);
				psmt.setString(15, lAT);
				psmt.setString(16, lNT);
					
				
				psmt.executeUpdate();

				System.out.println(psmt);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// DB close 필수!
				 // 접속이 된 것
				try {
//					if(conn != null) {
//						conn.close();
//					}
					if(psmt != null) {
						psmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				
				}
			}
		}
		
		public void userDelete(int number) {
			String sql = "Delete From test1 where ID ="+ number;
//			PreparedStatement psmt = null;
			
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test1.db");
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// DB close 필수!
				 // 접속이 된 것
				try {
//					if(conn != null) {
//						conn.close();
//					}
					if(psmt != null) {
						psmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				
				}
			}
			
		}

		
		
		
		
//		public ArrayList<String> userHistory() {
//	    	conn = getConnection();
//			ArrayList<String> arr = new ArrayList<>();
//
//			
//			return arr;
//		}
		
		public String[] userSelect(Connection conn, String list) {
			String[] result = new String[16];
			
			try {
//	    	conn = getConnection();
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");

			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from test where X_SWIFI_MGR_NO = " + '"' + list + '"');
//			int cnt =0;
			
//			while (rs.next()) {
//					String X_SWIFI_MGR_NO = gs.getX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
//					String X_SWIFI_WRDOFC = gs.getX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
//					String X_SWIFI_MAIN_NM = gs.getX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
//					String X_SWIFI_ADRES1 = gs.getX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
//				 	String X_SWIFI_ADRES2 = gs.getX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
//					String X_SWIFI_INSTL_FLOOR = gs.getX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
//					String X_SWIFI_INSTL_TY = gs.getX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
//					String X_SWIFI_INSTL_MBY = gs.getX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
//					String X_SWIFI_SVC_SE = gs.getX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
//					String X_SWIFI_CMCWR = gs.getX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
//					String X_SWIFI_CNSTC_YEAR = gs.getX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
//					String X_SWIFI_INOUT_DOOR = gs.getX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
//					String X_SWIFI_REMARS3 = gs.getX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
//					String LAT = gs.getLAT(rs.getString("LAT"));
//					String LNT = gs.getLNT(rs.getString("LNT"));
//					String WORK_DTTM = gs.getWORK_DTTM(rs.getString("WORK_DTTM"));
//					
////					result = X_SWIFI_MGR_NO +"/"+ X_SWIFI_WRDOFC + X_SWIFI_MAIN_NM + X_SWIFI_ADRES1 +
////							X_SWIFI_ADRES2 + X_SWIFI_INSTL_FLOOR + X_SWIFI_INSTL_TY + X_SWIFI_INSTL_MBY +
////							X_SWIFI_SVC_SE + X_SWIFI_CMCWR + X_SWIFI_CNSTC_YEAR + X_SWIFI_INOUT_DOOR + 
////							X_SWIFI_REMARS3 + LAT + LNT + WORK_DTTM; 
////					
////					System.out.println(result);
////					cnt++;
//					
//					
//			}
			
			while (rs.next()) {
				result[0] = rs.getString("X_SWIFI_MGR_NO");
				result [1] = rs.getString("X_SWIFI_WRDOFC");
				result [2] = rs.getString("X_SWIFI_MAIN_NM");
				result [3] = rs.getString("X_SWIFI_ADRES1");
				result [4] = rs.getString("X_SWIFI_ADRES2");
				result [5] = rs.getString("X_SWIFI_INSTL_FLOOR");
				result [6] = rs.getString("X_SWIFI_INSTL_TY");
				result [7] = rs.getString("X_SWIFI_INSTL_MBY");
				result [8] = rs.getString("X_SWIFI_SVC_SE");
				result [9] = rs.getString("X_SWIFI_CMCWR");
				result [10] =rs.getString("X_SWIFI_CNSTC_YEAR");
				result [11] =rs.getString("X_SWIFI_INOUT_DOOR");
				result [12] =rs.getString("X_SWIFI_REMARS3");
				result [13] =rs.getString("LAT");
				result [14] =rs.getString("LNT");
				result [15] =rs.getString("WORK_DTTM");
				
//				result = X_SWIFI_MGR_NO +"/"+ X_SWIFI_WRDOFC + X_SWIFI_MAIN_NM + X_SWIFI_ADRES1 +
//						X_SWIFI_ADRES2 + X_SWIFI_INSTL_FLOOR + X_SWIFI_INSTL_TY + X_SWIFI_INSTL_MBY +
//						X_SWIFI_SVC_SE + X_SWIFI_CMCWR + X_SWIFI_CNSTC_YEAR + X_SWIFI_INOUT_DOOR + 
//						X_SWIFI_REMARS3 + LAT + LNT + WORK_DTTM; 
//				
//				System.out.println(result);
//				cnt++;
				
				
		}
			rs.close();
			conn.close();
//			System.out.println(cnt);

			}catch (SQLException e) {
					e.printStackTrace();
				
				}
			return result;
		}
		
		
		public void dbInsert(String LAT, String LNT) {
			
//			SimpleDateFormat DATE = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	        LocalDateTime now = LocalDateTime.now();
	        
	        String sql1 = "INSERT INTO test1(LAT,LNT,DATE)" 
					   + " VALUES(?, ?, ?)";
			
//			String sql2 = "create index ID on test1 (DATE)";
	        
	        
			
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test1.db");
				psmt = conn.prepareStatement(sql1);
				
//				psmt.setInt(1, rowid);
				psmt.setString(1, LAT);
				psmt.setString(2, LNT);
				psmt.setString(3, now.toString());
				
//				psmt = conn.prepareStatement(sql2);
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// DB close 필수!
				 // 접속이 된 것
				try {
//					if(conn != null) {
//						conn.close();
//					}
					if(psmt != null) {
						psmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				
				}
			}
//			return result;
			
		}

//		public String dbSelect() {
//			String result="";
//			Distance ds = new Distance();
//			try {
//				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");
//				Statement state = conn.createStatement();
//				ResultSet rs = state.executeQuery("select X_SWIFI_MGR_NO,LAT,LNT from test");
//			while (rs.next()) {
//				result = rs.getString("X_SWIFI_MGR_NO")+"/"+rs.getString("LAT") + "/"+ rs.getString("LNT") +"\n";
//				return result;
//			}
//				
//			rs.close();
//			conn.close();
//			
//
//			
//		}catch (SQLException e) {
//			e.printStackTrace();
//		
//		}	
//			
//	}

}