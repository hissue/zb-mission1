<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.net.URL" %>
<%@ page import="com.jdbc.JDBCTemplate" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="member.OpenApi" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><style type="text/css">
div{text-align: center;}
</style>
<script type="text/javascript" src="./js/getApiData.js"></script>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body> 
<%
 member.OpenApi openapi = new member.OpenApi();
 		String total = openapi.openApi();
 %>


<h1> 
<p style="text-align:center;"> <%=total%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<p style="text-align:center;"> <a href="main.jsp">홈으로 가기</a>
</body>
</html>