package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.JDBCTemplate;

/**
 * Servlet implementation class libServlet
 */
@WebServlet("/lib.do")
public class libServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
    	Connection conn = null;
		JDBCTemplate data = new JDBCTemplate();
		
		// History 값 넣기
		String command = request.getParameter("command");

//		String LAT = request.getParameter("LAT");
//		String LNT = request.getParameter("LNT");
		
		
		String LAT = request.getParameter("LAT");
		String LNT = request.getParameter("LNT");
		
//		System.out.println(command);

//		if(LAT.equals("null") || LNT.equals("null")) {
//			LAT = "0.0";
//			LNT = "0.0";
////		}
			
		request.setAttribute("LAT", LAT);
		request.setAttribute("LNT", LNT);
////		
		
		try{
			if(command.equals("근처 WIFI 정보 보기")) {
				
				data.dbInsert(LAT, LNT);
				
				
				// 20개 map 형식으로 출력
				
//				Distance ds = new Distance();
//				Map<String, Double> map = new HashMap<String, Double>(20);
//				List<Entry<String,Double>> twenty = new LinkedList<>(map.entrySet());
//				twenty = ds.Cal(LAT,LNT);
//
//				// 출력 값 가져오기
//				for(Map.Entry<String, Double> entry : twenty){
//					String[] getData = data.userSelect(conn, entry.getKey());
//					System.out.println(entry.getValue());
//					request.setAttribute("dis", entry.getValue());
//					request.setAttribute("X_SWIFI_MGR_NO", getData[0]);
//					request.setAttribute("X_SWIFI_WRDOFC", getData[1]);
//					request.setAttribute("X_SWIFI_MAIN_NM", getData[2]);
//					request.setAttribute("X_SWIFI_ADRES1", getData[3]);
//					request.setAttribute("X_SWIFI_ADRES2", getData[4]);
//					request.setAttribute("X_SWIFI_INSTL_FLOOR", getData[5]);
//					request.setAttribute("X_SWIFI_INSTL_TY", getData[6]);
//					request.setAttribute("X_SWIFI_INSTL_MBY", getData[7]);
//					request.setAttribute("X_SWIFI_SVC_SE", getData[8]);
//					request.setAttribute("X_SWIFI_CMCWR", getData[9]);
//					request.setAttribute("X_SWIFI_CNSTC_YEAR", getData[10]);
//					request.setAttribute("X_SWIFI_INOUT_DOOR", getData[11]);
//					request.setAttribute("X_SWIFI_REMARS3", getData[12]);
//					request.setAttribute("get_LAT", getData[13]);
//					request.setAttribute("get_LAT", getData[14]);
//					request.setAttribute("WORK_DTTM", getData[15]);
			
//				}
			}
		
		
			
		}
			finally {
			// DB close 필수!
			 // 접속이 된 것
			try {
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			
			}
		}
		
				
		
		// 메인페이지
		
		
		
		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/main.jsp");
		try {
		    dispatcher.forward(request, response);
		} catch (ServletException e) {
		       out.println(e);
		 	}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
