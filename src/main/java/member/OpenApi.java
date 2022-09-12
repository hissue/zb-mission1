package member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.JDBCTemplate;

import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class OpenApi {
    public String openApi() {
    	
    	// 파싱한 데이터를 저장할 변수
    	String result = "";
    	int st = 0; //17080
    	int end =499; //18000
    	String list_total_count="";
    	long total_count;
    	
    	Connection conn = null;
    	
    	try{
        	JDBCTemplate data = new JDBCTemplate();
//    		conn = DriverManager.getConnection("jdbc:sqlite:C:\\dev\\test.db");
//			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");
			
			conn = data.getConnection();


    	while(true) {
    		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
    		urlBuilder.append("/" + "4944736269776c6433347a46457442");
    		urlBuilder.append("/" + "json/TbPublicWifiInfo");
    		urlBuilder.append("/" + st);
    		urlBuilder.append("/" + end);
    		// http://openapi.seoul.go.kr:8088/4944736269776c6433347a46457442/json/TbPublicWifiInfo/0/10
    	
    		URL url = new URL(urlBuilder.toString());

    		BufferedReader bf;
    		bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
    		result = bf.readLine();
    		
        	JSONParser jsonParser = new JSONParser();
        	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
        	JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
        	total_count = (long)TbPublicWifiInfo.get("list_total_count");
        	list_total_count = Long.toString((long)TbPublicWifiInfo.get("list_total_count"));
        	JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
        	
        	
//        	public Connection getConnection() { // 커넥션 해주는 역할, DB객체
//    			
//    			Connection conn = null;
//    			
//    			try {
//    				conn = DriverManager.getConnection("jdbc:sqlite:C:\\dev\\test.db");
//    				System.out.println("SQL Connection Success!!");
//    			} catch (SQLException e) {
//    				 System.out.println("DB를 연결하지 못했습니다");
//    			}
//    			return conn;
//    		}
        	
        	
        	
        	for(int i=0; i<row.size(); i++) {
        		JSONObject list = (JSONObject) row.get(i);
        		String X_SWIFI_MGR_NO = (String) list.get("X_SWIFI_MGR_NO");
        		String WORK_DTTM = (String) list.get("WORK_DTTM");
        		String X_SWIFI_WRDOFC = (String) list.get("X_SWIFI_WRDOFC");
        		String X_SWIFI_MAIN_NM = (String) list.get("X_SWIFI_MAIN_NM");
        		String X_SWIFI_ADRES1 = (String) list.get("X_SWIFI_ADRES1");
        		String X_SWIFI_ADRES2 = (String) list.get("X_SWIFI_ADRES2");
        		String X_SWIFI_INSTL_FLOOR = (String) list.get("X_SWIFI_INSTL_FLOOR");
        		String X_SWIFI_INSTL_TY = (String) list.get("X_SWIFI_INSTL_TY");
        		String X_SWIFI_INSTL_MBY = (String) list.get("X_SWIFI_INSTL_MBY");
        		String X_SWIFI_SVC_SE = (String) list.get("X_SWIFI_SVC_SE");
        		String X_SWIFI_CMCWR = (String) list.get("X_SWIFI_CMCWR");
        		String X_SWIFI_CNSTC_YEAR = (String) list.get("X_SWIFI_CNSTC_YEAR");
        		String X_SWIFI_INOUT_DOOR = (String) list.get("X_SWIFI_INOUT_DOOR");
        		String X_SWIFI_REMARS3 = (String) list.get("X_SWIFI_REMARS3");
        		String LAT = (String) list.get("LAT");
        		String LNT = (String) list.get("LNT");
        		data.userInsert(conn, X_SWIFI_MGR_NO,WORK_DTTM,X_SWIFI_WRDOFC,X_SWIFI_MAIN_NM,X_SWIFI_ADRES1,X_SWIFI_ADRES2,
        				X_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_TY,X_SWIFI_INSTL_MBY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,
        				X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3,LAT,LNT); 
        	
        	}


        	
        	if(end>=total_count) {
        		break;
        		
        	}
        	st +=500;
        	end=st+500;
        	Thread.sleep(10);
    	}
    	
    	}catch(Exception e){
    		e.printStackTrace();
    }finally {
    	try {
			if(conn != null) {
				conn.close();
			}
//			if(psmt != null) {
//				psmt.close();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
    }
    	return list_total_count;
    }
   
   
}