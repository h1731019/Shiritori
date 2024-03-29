<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.AnswerWord" %>
<%@ page import="model.Mutter , java.util.List" %>
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
String errormsg =(String)request.getAttribute("errormsg") ;
if(errormsg == null||errormsg.length()==0||errormsg.equals("")){
	errormsg = "";
}
String msg="";
int i = list.size()-1;
if(list.size()!=0){
	msg= list.get(i);
	if(list.get(i).equals("")||list.get(i)==null){
	msg= "";}
}else{msg = "　　　　　";}
%>
<%String logincheck="";
if(userId.equals("ゲスト")){
	logincheck = "<form action=\"LoginServlet\" method = \"post\"><label class=\"smallfont\" style=\"background-color: white;\">ユーザーID：<br><input type = \"text\" name=\"userId\"></label><br><label class=\"smallfont\" style=\"background-color: white;\">パスワード：<br><input type = \"password\" name=\"pass\"></label><br><input type = \"submit\" value=\"送信\"></form><a href =\"NewAccountServlet\">ユーザー登録はこちら</a>";
}else{
	logincheck = "<a href=\"ShiritoriServlet?action=logout\">ログアウト</a>　　　　　　";
}

List<Mutter> mutterList = (List<Mutter>)session.getAttribute("mutterList");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
if(errorMsg==null||errorMsg.length()==0||errorMsg.equals("")){
	errorMsg = "";
}
%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	<script type="text/javascript" src="https://github.com/niwaringo/moji/releases/download/V1.2.0/moji.js"></script>
	<script type="text/javascript" src="./input-validate.js"></script>
	<script type="text/javascript" src="./oritatami.js"></script>
<meta charset="UTF-8">
<title>しりとり</title>
</head>
<body class="box bg_dot is-small">
	<h1>しりとりげーむ</h1>
	
	<div>現在のステータス：<%= userId %></div>
	<br><%=loginErrormsg %><br>
	
	<div class="container">
		<div class="item"><%=logincheck %>
		</div>
		<div class="item-center">
        		<div class="rule-box">
            		<p>ルール</p>
            		・入力はひらがなのみです。<br>
            		・単語の終わりが濁点や半濁点を持つ文字（例：「ば」「ぱ」）の場合、次の単語の頭文字は「は」「ば」「ぱ」で始まる単語のみ許可されます。<br>
            		・単語の終わりが「ー」（伸ばし棒）の場合、その一つ前の文字が判定の基準となります。<br>
            		・単語の終わりが「ゃ」や「ぁ」の場合、それぞれ「や」「あ」として判定されます。<br>
        		</div>
    		
    	</div>
    </div>
	
	<div class="box26">
        <span class="box-title">Word</span>
		<p><%= msg%><%=errormsg %></p>
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
	<tr>
		<td>4位</td>
		<td><%=rankinglist.get(3).getUserId() %></td>
		<td><%=rankinglist.get(3).getScore() %></td>
		<td><%=rankinglist.get(3).getTimestamp() %></td>
	</tr>
	<tr>
		<td>5位</td>
		<td><%=rankinglist.get(4).getUserId() %></td>
		<td><%=rankinglist.get(4).getScore() %></td>
		<td><%=rankinglist.get(4).getTimestamp() %></td>
	</tr>
	
	</table>
	
	<button id="toggle-button" class="collapsible-btn" onclick="toggleBoard()">
		<span class="bar"></span>
    	<span class="bar"></span>
    	<span class="bar"></span>
    </button>
	<div class="board-container" id="board-container">
		<form action = "ShiritoriKeijibanServlet" method = "post" id="post-form" class="post-form" onsubmit="submitForm(event)">
        	<textarea class="form-control" name="text" placeholder="投稿内容"></textarea>
        	<input type="submit" value="投稿">
    	</form>
    	<%=errorMsg %>
    <div id="mutter-list">
        <% for (Mutter mutter : mutterList){ %>
            <div class="message">
                <%= mutter.getId()%>. <%= mutter.getUserName() %>: <%= mutter.getTimestamp() %><br><%= mutter.getText() %>
            </div>
        <% } %>
    </div>
    
	</div>

<script src="./oritatami.js"></script>
	
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
	<script>
    	var isComposing = false;
    	// ひらがなのみに変換(スペース含む)
    	$('[name="text"]').on('keyup blur compositionstart compositionend', function (event) {
        convert_hiragana(this, event.type);
    	});
	</script>	
</body>
</html>