<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<% String errormsg = (String)request.getAttribute("errormsg"); 
if(errormsg == null || errormsg.length()==0){
errormsg = "";
}%>
<%List<String> list = (List<String>)session.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
</head>
<body>
	<p><%=errormsg %></p>
	<a href = "ShiritoriServlet">もどる</a>
</body>
</html>