package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Distance {
	
//	public static void main(String[] args) {
//		ArrayList<String> arr = new ArrayList<>();
//		System.out.println(Cal("127.96675","37.561924"));
//		Cal("127.96675","37.561924");
//
//		
//	}

	public List<Entry<String,Double>> Cal(String usrLAT, String usrLNT) {	
		Connection conn = null;
		double dist = 0.0;
//		double[] space= new double[20];
//		String[] list = new String[20];
		
//		Arrays.fill(space,10000.0);
		Map<String, Double> map = new HashMap<String, Double>(20);
		map.put("1", 1000000.0);
		map.put("2", 1000000.0);
		map.put("3", 1000000.0);
		map.put("4", 1000000.0);
		map.put("5", 1000000.0);
		map.put("6", 1000000.0);
		map.put("7", 1000000.0);
		map.put("8", 1000000.0);
		map.put("9", 1000000.0);
		map.put("10", 1000000.0);
		map.put("11", 1000000.0);
		map.put("12", 1000000.0);
		map.put("13", 1000000.0);
		map.put("14", 1000000.0);
		map.put("15", 1000000.0);
		map.put("16", 1000000.0);
		map.put("17", 1000000.0);
		map.put("18", 1000000.0);
		map.put("19", 1000000.0);
		map.put("20", 1000000.0);
		
//		List<String> keySetList = new ArrayList<>(map.keySet());
//		System.out.println("Sorted arr[] : " + Arrays.toString(arr));
		List<Map.Entry<String, Double>> entryList = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select X_SWIFI_MGR_NO,LAT,LNT from test");
			
		while (rs.next()) {
			String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
			double LAT = Double.parseDouble(rs.getString("LAT"));
			double LNT = Double.parseDouble(rs.getString("LNT"));
			dist = distance(LAT,LNT,Double.parseDouble(usrLAT), Double.parseDouble(usrLNT)); //경,위도 거리 km 계산
			
			// 최대 value의 key 찾기
			String maxKey = null;
		        for (String key : map.keySet()) {
		            if (maxKey == null || map.get(key) > map.get(maxKey)) {
		                maxKey = key;
		            }
		        }
		          
//		    System.out.println(maxKey);
		    //
			if(dist < map.get(maxKey) ) {
				map.remove(maxKey);
				map.put(X_SWIFI_MGR_NO, dist);
			}
			//정렬
			
			entryList = new LinkedList<>(map.entrySet());
			entryList.sort(Map.Entry.comparingByValue());
			
//			for(Map.Entry<String, Double> entry : entryList){
//			    System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
//			}
			
		}
		
		rs.close();
		conn.close();
		

		
	}catch (SQLException e) {
		e.printStackTrace();
		}
		
	return entryList;

	}
	
	
	
	
	
	 // 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
	public double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1.609344;// km
//        dist = dist * 60*1.1515*1609.344; //m

        return dist; //단위 kmeter
    }
    
    
  //10진수를 radian(라디안)으로 변환
	public double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
	public double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
	
	
	
	
}
