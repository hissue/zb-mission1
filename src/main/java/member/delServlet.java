package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.JDBCTemplate;

/**
 * Servlet implementation class delServlet
 */
@WebServlet("/del.do")
public class delServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
    	Connection conn = null;
		JDBCTemplate data = new JDBCTemplate();
		
		// History 값 넣기
		int command = Integer.parseInt(request.getParameter("command"));
//		request.setAttribute("ID", ID);
		System.out.println(command);
		try{
				data.userDelete(command);
				
			
		} finally {
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
		
		//메인 페이지
		
		ServletContext app = this.getServletContext();
		RequestDispatcher dispatcher = app.getRequestDispatcher("/history.jsp");
		try {
		    dispatcher.forward(request, response);
		} catch (ServletException e) {
		       out.println(e);
		 	}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
