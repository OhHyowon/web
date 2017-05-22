<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>집중을 하란말이다</title>
</head>
<body>
	현재시간 : <%=String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", new Date())%>
</body>
</html>