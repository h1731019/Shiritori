<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.AnswerWord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Score" %>

<% String userId =(String)session.getAttribute("userId") ; %>
<% List<Score> rankinglist = (List<Score>)session.getAttribute("rankinglist"); %>

<% String loginErrormsg =(String)session.getAttribute("loginErrormsg");
if(loginErrormsg == null){
	loginErrormsg = "";
}; %>
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
}else{msg = "　　　　　";}
%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	<script type="text/javascript" src="https://github.com/niwaringo/moji/releases/download/V1.2.0/moji.js"></script>
	<script type="text/javascript" src="./input-validate.js"></script>
<meta charset="UTF-8">
<title>しりとり</title>
</head>
<body class="box bg_dot is-small">
	<h1>しりとりげーむ</h1>
	
	<div >現在のステータス：<%= userId %></div>
	<br><%=loginErrormsg %><br>
	<div class="parent">
	<div class ="child">
	<form action="LoginServlet" method = "post">
			<label style="background-color: white;">ユーザーID：<input type = "text" name="userId"></label><br>
			<label style="background-color: white;">パスワード：<input type = "password" name="pass"></label><br>
			<input type = "submit" value="送信">
	</form>	
	</div>
	</div>
	<div class="box26">
        <span class="box-title">Word</span>
		<p><%= msg + errormsg %></p>
	</div>
	<div>
		<form align="center" action="ShiritoriServlet" method = "post">
			<input class="form-control" type = "text" name="word"><br>
			<input type = "submit" value="送信">
		</form>	
	</div>
	
	<div class="box2">
        <% for(String lists:list){ %>
		<%=lists + "→" %>
		<%} %>
		
    </div>
    <div align = "center">ランキング
    <br>(手数が少ない順)</div>
    <table align="center" bgcolor="#ffffff" border ="1">
	<tr>
		<th>順位</th>
		<th>名前</th>
		<th>手数</th>
		<th>日付</th>
	</tr>
	<tr>
		<td>1位</td>
		<td><%=rankinglist.get(0).getUserId() %></td>
		<td><%=rankinglist.get(0).getScore() %></td>
		<td><%=rankinglist.get(0).getTimestamp() %></td>
	</tr>
	<tr>
		<td>2位</td>
		<td><%=rankinglist.get(1).getUserId() %></td>
		<td><%=rankinglist.get(1).getScore() %></td>
		<td><%=rankinglist.get(1).getTimestamp() %></td>
	</tr>
	<tr>
		<td>3位</td>
		<td><%=rankinglist.get(2).getUserId() %></td>
		<td><%=rankinglist.get(2).getScore() %></td>
		<td><%=rankinglist.get(2).getTimestamp() %></td>
	</tr>
	
	</table>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
	<script>
    	var isComposing = false;
    	// ひらがなのみに変換(スペース含む)
    	$('[name="word"]').on('keyup blur compositionstart compositionend', function (event) {
        convert_hiragana(this, event.type);
    	});
	</script>	
</body>
</html>