<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errormsg = (String)session.getAttribute("errormsg"); 
if(errormsg == null || errormsg.length()==0){
errormsg = "";
}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
</head>
<body>
	<p>errormsg</p>
	<a href = "testservlet">もどる</a>
</body>
</html>