<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String result = (String)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>しりとり結果</title>
</head>
<body>
<%= result %>
</body>
</html>