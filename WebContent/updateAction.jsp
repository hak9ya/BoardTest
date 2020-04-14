<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!-- 데이터베이스를 불러옴-->
<%@ page import="board.BoardDAO" %>
<%@ page import="board.Board" %>


	<!-- 자바스크립트 문장을 작성하기 위한 것-->
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 웹 게시판 로그인</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		}
		int boardID = 0;
		if(request.getParameter("boardID") != null){
			boardID = Integer.parseInt(request.getParameter("boardID"));
		}
		if (boardID == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'board.jsp'");
			script.println("</script>");
		}
		Board board = new BoardDAO().getBoard(boardID);
		if(!userID.equals(board.getUserID())){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'board.jsp'");
			script.println("</script>");
		}else{
			if(request.getParameter("boardTitle") == null || request.getParameter("boardContent") == null
				|| request.getParameter("boardContent") == "" || request.getParameter("boardContent") == ""){ // 매개변수로 넘어온 값 체크
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 되지 않은 부분이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
		}else{
			//BoardDAO라는 인스턴스 생성
			BoardDAO boardDAO = new BoardDAO();
				int result = boardDAO.update(boardID, request.getParameter("boardTitle"), request.getParameter("boardContent"));
				if(result == -1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글수정에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}else{
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'board.jsp'");
					script.println("</script>");
				}
			}
		}
	%>
</body>
</html>