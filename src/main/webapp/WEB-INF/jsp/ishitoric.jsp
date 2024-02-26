<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int count =(int)session.getAttribute("count") ; %>
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
	<p><%=namber %></p>
	<p>石の数:<%=count %>個</p>
	<img src="img/ishi<%=count %>.png" alt="test" style="object-fit: cover; width: 600px; height: 300px;">
	<br><a href ="IshitoriServlet?action=reset">最初から</a>
	   <!-- 入力設定 -->
    <div id="fnc_wait"></div>


    <!-- ここにjavascriptを実装する-->
    <script type="text/javascript">

        // ホームページに移動する処理
        function gotoHomepage(){
            location.href = 'IshitoriServlet?action=cpu';
        }

        // div要素取得
        let div_element_wait = document.getElementById('fnc_wait');
        // 文言を画面に出力
        div_element_wait.innerHTML = "コンピューターが考え中";

        // 5秒後に「ホームページに移動する処理」を呼び出す
        let wait_5sec = setTimeout(gotoHomepage, 5000);

    </script>	
</body>
</html>