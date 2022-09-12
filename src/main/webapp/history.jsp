<%@page import="member.GetterSetter"%>
<%@ page import="com.jdbc.JDBCTemplate" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>



</head>
<script type="text/javascript" src="./js/getHistoryData.js"></script>
<h1>위치 히스토리 목록</h1>
</head>
<body>
<div class="job-title">
<ul>
<li><a href="main.jsp">홈</a></li>
<li><a href="history.jsp">위치 히스토리 목록</a></li>
<li><a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a></li>
</ul> 
</div>
<div>
<%
		//int count =0;		
		//JDBCTemplate data = new JDBCTemplate();
		
//		String result = data.dbSelect();
// 		String[] arr = result.split("/");
 		 		
%>
<table id="customers">
  <tr>
    <th>ID</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>조회일자</th>
    <th>비고</th>
  </tr>
  <% 
	JDBCTemplate data = new JDBCTemplate();
  try {
	  	Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test1.db");
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery("select * from test1 ORDER BY DATE DESC");		

		
	%>
<%
  while(rs.next()){
%>

<tr>
  <td><%=rs.getInt("ID")%> </td>
  <td><%=rs.getString("LAT")%></td>
  <td><%=rs.getString("LNT")%></td>
  <td><%=rs.getString("DATE")%></td>
  <td>
  <input type="button" onclick="document.location.href='del.do?command=<%=rs.getInt("ID")%>'" value="삭제" >
  </td>
  </tr>
  <% } %></table>
  <%
  rs.close();
  conn.close();
  %>
  <%
}catch (SQLException e) {
	e.printStackTrace();

}%>

</div>
</body>
</html>