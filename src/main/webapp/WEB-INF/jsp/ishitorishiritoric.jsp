<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<% int count =(int)session.getAttribute("count") ; %>
<% List<String> list = (List<String>) session.getAttribute("list"); %>
<% List<String> scopewords = (List<String>) session.getAttribute("scopewords"); %>
<% String namber = (String)session.getAttribute("namber");
if(namber==null){
	namber = "";
}else{
	namber = "プレイヤーの選んだ単語:"+namber + "(" + namber.length() + "文字)";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いしとりしりとり</title>
</head>
<body style="text-align: center;"	>
	<h1>いしとりしりとり</h1>
	<p>ルール<br>
	・いしとりとしりとりを組み合わせたゲームです。<br>
	・下のラジオボタンから単語を選びます。<br>
	・単語の文字の数だけ石を取ります。<br>
	・最後の一個の石を取った人の負けです。<br>
	</p>
	<p>石の数:<%=count %>個</p>
	<p><%=namber %></p>
	<img src="img/ishi<%=count %>.png" alt="残り<%=count %>個" style="object-fit: cover; width: 600px; height: 300px;">
	   <!-- 入力設定 -->
    <div id="fnc_wait"></div>
    
    <form action="IshitoriShiritoriServlet" method="post">
	<%for(String lists : list){ %>
     <input type="radio" name="namber" value="<%=lists %>"><%=lists %>(<%=lists.length() %>文字)、
    <%} %>
    </form>
    
    <br>
    <%for(String scopeword : scopewords){ %>
    <%=scopeword %>→
    <%} %>
	<br><a href ="IshitoriShiritoriServlet?action=reset">最初から</a>

    <!-- ここにjavascriptを実装する-->
    <script type="text/javascript">

        // ホームページに移動する処理
        function gotoHomepage(){
            location.href = 'IshitoriShiritoriServlet?action=cpu';
        }

        // div要素取得
        let div_element_wait = document.getElementById('fnc_wait');
        // 文言を画面に出力
        div_element_wait.innerHTML = "コンピューターが考え中";

        // 5秒後に「ホームページに移動する処理」を呼び出す
        let wait_5sec = setTimeout(gotoHomepage, 3000);

    </script>	
</body>
</html>