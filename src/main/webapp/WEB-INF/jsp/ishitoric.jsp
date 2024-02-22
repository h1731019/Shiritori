<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<% int count =(int)session.getAttribute("count") ; %>
<% List<String> list = (List<String>) session.getAttribute("list"); %>
<% List<String> scopewords = (List<String>) session.getAttribute("scopewords"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=count %>
	<br><a href ="IshitoriServlet?action=reset">最初から</a>
	   <!-- 入力設定 -->
    <div id="fnc_wait"></div>
    
    <form action="IshitoriServlet" method="post">
	<%for(String lists : list){ %>
     <input type="radio" name="namber" value="<%=lists %>"><%=lists %>(<%=lists.length() %>文字)、
    <%} %>
    </form>
    
    <br>
    <%for(String scopeword : scopewords){ %>
    <%=scopeword %>→
    <%} %>


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
        let wait_5sec = setTimeout(gotoHomepage, 3000);

    </script>	
</body>
</html>