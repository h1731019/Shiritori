<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int count =(int)session.getAttribute("count") ; %>
<% String errormsg = (String)request.getAttribute("errormsg");
if(errormsg==null||errormsg.equals("")||errormsg.length()==0){
	errormsg = "";
}
%>
<% String namber = (String)session.getAttribute("namber");
if(namber==null||namber.equals("")||namber.length()==0){
	namber = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いしとり</title>
</head>
<body style="text-align: center;">
	<h1>いしとり</h1>
	<p>ルール<br>
	・いしとりとしりとりを組み合わせたゲームです。<br>
	・下のラジオボタンから数を選びます。<br>
	・選んだ数だけ石を取ります。<br>
	・最後の一個の石を取った人の負けです。<br>
	</p>
	<p><%=errormsg %></p>
	<p><%=namber %></p>
	<p>石の数:<%=count %>個</p>
	<img src="img/ishi<%=count %>.png" alt="test" style="object-fit: cover; width: 600px; height: 300px;">
	<form action="IshitoriServlet" method="post">
    1<input type="radio" name="namber" value="1">
    2<input type="radio" name="namber" value="2">
    3<input type="radio" name="namber" value="3">
    <input type="submit" value="送信">
    </form>
	<br><a href ="IshitoriServlet?action=reset">最初から</a>
</body>
</html>