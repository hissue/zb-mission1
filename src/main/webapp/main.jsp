<%@page import="member.GetterSetter"%>
<%@ page import="com.jdbc.JDBCTemplate" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSet" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="member.Distance" %>
<%@ page import="java.text.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<h1>와이파이 정보 구하기</h1>
</head>
<body>
<div>
<ul>
<li><a href="main.jsp">홈</a></li>
<li><a href="history.jsp">위치 히스토리 목록</a></li>
<li>
<!-- <form method="post" action="load-wifi.do"> -->
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<input type="hidden" name="command" value="openAPI">
<!-- </form> -->
</li>
</ul> 
</div>
<div>
<form method="get" action="lib.do">
LAT: <input type=text value=<%if(request.getAttribute("LAT")==null){%>
	<%="0.0"%>
<% }else{%>
	<%=request.getAttribute("LAT")%>
	<%}%> name="LAT" />, LNT: <input type=text value=<%if(request.getAttribute("LNT")==null){%>
	<%="0.0"%>
<% }else{%>
	<%=request.getAttribute("LNT")%>
	<%}%>  name="LNT" />
<input type="submit" value="내 위치 가져오기" name="ground">
<!-- <input type="button" onclick="command=click" value="근처 WIFI 정보 보기" > -->
<input type="submit" value="근처 WIFI 정보 보기" name="command">
</form>
</div>
<div>
<table id="customers">
  <tr>
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>

		
<%
	if(request.getAttribute("LAT") != null || request.getAttribute("LNT") != null){
	JDBCTemplate data = new JDBCTemplate();
	Connection conn = null;

	try {
		conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\suhyun\\test.db");
		Statement state = conn.createStatement();
		Distance ds = new Distance();
		Map<String, Double> map = new HashMap<String, Double>(20);
		List<Entry<String,Double>> twenty = new LinkedList<>(map.entrySet());
		twenty = ds.Cal(String.valueOf(request.getAttribute("LAT")),String.valueOf(request.getAttribute("LNT")));
		
		DecimalFormat format = new DecimalFormat("0.0000");
		
		
		
		for(Map.Entry<String, Double> entry : twenty){
			
			ResultSet rs = state.executeQuery("select * from test where X_SWIFI_MGR_NO = " + '"' + entry.getKey() + '"');
			%>
			<%
			  while(rs.next()){
			%>
			<tr>
				<td><%=format.format(entry.getValue()) %></td>
    			<td><%=rs.getString("X_SWIFI_MGR_NO")%></td>
  				<td><%=rs.getString("X_SWIFI_WRDOFC")%></td>
  				<td><%=rs.getString("X_SWIFI_MAIN_NM")%></td>
  				<td><%=rs.getString("X_SWIFI_ADRES1")%></td>
  				<td><%=rs.getString("X_SWIFI_ADRES2")%></td>
  				<td><%=rs.getString("X_SWIFI_INSTL_FLOOR")%></td>
  				<td><%=rs.getString("X_SWIFI_INSTL_TY")%></td>
  				<td><%=rs.getString("X_SWIFI_INSTL_MBY")%></td>
  				<td><%=rs.getString("X_SWIFI_SVC_SE")%></td>
  				<td><%=rs.getString("X_SWIFI_CMCWR")%></td>
  				<td><%=rs.getString("X_SWIFI_CNSTC_YEAR")%></td>
  				<td><%=rs.getString("X_SWIFI_INOUT_DOOR")%></td>
  				<td><%=rs.getString("X_SWIFI_REMARS3")%></td>
  				<td><%=rs.getString("LAT")%></td>
  				<td><%=rs.getString("LNT")%></td> 
  				<td><%=rs.getString("WORK_DTTM")%></td>
  				</tr>   
		
		<% }
			
			rs.close();
			%>
		
	<% }
		
		conn.close(); %>

<%}finally {
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
}%>		
<%if(request.getAttribute("LAT") == null || request.getAttribute("LAT") == null){ %>	
		<tr>
		<td colspan="17" height=50px align=center><b>위치 정보를 입력한 후에 조회해 주세요</b></td>
	  </tr>
  
  <% } %>
  
</table>
</div>
</body>
</html>