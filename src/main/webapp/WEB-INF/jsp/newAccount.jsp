<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String errormsg =(String)request.getAttribute("errormsg");
    if(errormsg == null || errormsg.length()==0){
    errormsg = "";
    }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
</head>
<body>
<%=errormsg %>
	<form action="NewAccountServlet" method = "post">
			ユーザーID：<input type = "text" name="userId"><br>
			パスワード：<input type = "password" name="pass"><br>
			<input type = "submit" value="登録">
	</form>	
	<a href="ShiritoriServlet">もどる</a>
</body>
</html>