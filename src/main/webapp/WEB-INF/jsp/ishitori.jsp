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
	namber = namber + "(" + namber.length() + "文字)";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=result %>
	<%=errormsg %>
	<%=count %>
	<%=namber %>
	<form action="IshitoriServlet" method="post">
    1<input type="radio" name="namber" value="1">
    2<input type="radio" name="namber" value="2">
    3<input type="radio" name="namber" value="3">
    <input type="submit" value="送信">
    </form>
	<br><a href ="IshitoriServlet?action=reset">最初から</a>
	<br>
	<form action="IshitoriServlet" method="post">
	<%for(String lists : list){ %>
     <input type="radio" name="namber" value="<%=lists %>"><%=lists %>(<%=lists.length() %>文字)、
    <%} %><br><input type="submit" value="送信">
    </form><br>
    <%for(String scopeword : scopewords){ %>
    <%=scopeword %>→
    <%} %>
</body>
</html>