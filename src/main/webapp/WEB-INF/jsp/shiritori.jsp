<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.AnswerWord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% AnswerWord a = (AnswerWord)session.getAttribute("answerword");
List<String> list = (List<String>)session.getAttribute("list");
String errormsg =(String)session.getAttribute("errormsg") ;
if(errormsg == null){
	errormsg = "";
}else{
	errormsg=(String)session.getAttribute("errormsg");
}
String msg;
int i = list.size()-1;
if(list.size()!=0){
	msg= list.get(i);
}else{msg = "何か入力してね";}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>しりとり</title>
</head>
<body>
<h1>しりとりげーむ</h1>
<% for(String lists:list){ %>
<%=lists+"→" %>
<%} %>
<p><%= msg + errormsg %></p>
<form action="ShiritoriServlet" method = "post">
<input type = "text" name="word"><br>
<input type = "submit" value="送信">
</form>
</body>
</html>