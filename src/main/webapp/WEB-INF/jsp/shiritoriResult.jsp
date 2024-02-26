<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.AnswerWord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% String userId =(String)session.getAttribute("userId") ; %>
<% AnswerWord a = (AnswerWord)session.getAttribute("answerword");
List<String> list = (List<String>)session.getAttribute("list");
String errormsg =(String)request.getAttribute("errormsg") ;
if(errormsg == null){
	errormsg = "";
}else{
	errormsg=(String)session.getAttribute("errormsg");
}
String msg;
int i = list.size()-1;
if(list.size()!=0){
	msg= list.get(i);
}else{msg = "　　　　　";}
%>
<%String result = (String)request.getAttribute("result"); %>
<%String rankingCheck = (String)request.getAttribute("rankingCheck"); %>
<% if(userId.equals("ゲスト")||userId.length()==0||userId==null){
		rankingCheck="";
}
	%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
<meta charset="UTF-8">
<title>しりとり結果</title>
</head>
<body class="box bg_dot is-small">
<p>現在のステータス：<%=userId %></p>
<br><a href = "ShiritoriServlet"></a>
	<div class="box26">
        <span class="box-title">Word</span>
		<p><%= msg + errormsg %></p>
		
	</div>
	<p style="text-align: center;"><%=rankingCheck %></p>
<p class="center"><%=list.size() %>手で決着</p>
	
<p class="center"><%= result %></p>

	<div class="box2">
        <% for(String lists:list){ %>
		<%=lists + "→" %>
		<%} %>
		「ん」がついちゃった！
    </div>
<a href ="ShiritoriServlet">もどる</a>
</body>
</html>