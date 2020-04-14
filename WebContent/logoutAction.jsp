<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!-- 데이터베이스를 불러옴-->
<%@ page import="user.UserDAO" %>

	<!-- 자바스크립트 문장을 작성하기 위한 것-->
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 웹 게시판 로그인</title>
</head>
<body>
	<%
		session.invalidate(); // 세션을 무효화 해서 로그아웃 처리
	%>
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>