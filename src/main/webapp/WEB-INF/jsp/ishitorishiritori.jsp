<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<% int count =(int)session.getAttribute("count") ; %>
<% String errormsg = (String)request.getAttribute("errormsg");
if(errormsg==null||errormsg.equals("")||errormsg.length()==0){
	errormsg = "";
}
%>
<% List<String> list = (List<String>) session.getAttribute("list"); %>
<% List<String> scopewords = (List<String>) session.getAttribute("scopewords"); %>
<% String result = (String)request.getAttribute("result");
if(result==null||result.equals("")||result.length()==0){
	result = "";
}
%>
<% String namber = (String)session.getAttribute("namber");
if(namber==null){
	namber = "";
}else{
	namber = "コンピュータの選んだ単語:"+namber + "(" + namber.length() + "文字)";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いしとりしりとり</title>
</head>
<body style="text-align: center;">
	<h1>いしとりしりとり</h1>
	<p>ルール<br>
	・いしとりとしりとりを組み合わせたゲームです。<br>
	・下のラジオボタンから単語を選びます。<br>
	・単語の文字の数だけ石を取ります。<br>
	・最後の一個の石を取った人の負けです。<br>
	</p>
	<%=result %>
	<p><%=errormsg %></p>
	<p>石の数:<%=count %>個</p>
	<p><%=namber %></p>
	<img src="img/ishi<%=count %>.png" alt="残り<%=count %>個" style="object-fit: cover; width: 600px; height: 300px;">
	<br>
	<form action="IshitoriShiritoriServlet" method="post">
	<%for(String lists : list){ %>
     <input type="radio" name="namber" value="<%=lists %>"><%=lists %>(<%=lists.length() %>文字)、
    <%} %><br><input type="submit" value="送信">
    </form><br>
    <%for(String scopeword : scopewords){ %>
    <%=scopeword %>→
    <%} %>
    <br><a href ="IshitoriShiritoriServlet?action=reset">最初から</a>
</body>
</html>