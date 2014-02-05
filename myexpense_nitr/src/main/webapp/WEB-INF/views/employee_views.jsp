<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My sample mongodb</title>
</head>
<body>
	<%-- <table><tr><td>ID</td><td>username</td><td>Name</td><td>Created Date</td></tr>
		<c:forEach items="${list}" var="v" varStatus="status">
			<tr>
				<td>${v.id}</td>
				<td>${v.username}</td>
				<td>${v.name}</td>
				<td>${v.createdDate}</td>				
			</tr>
		</c:forEach>
	</table> --%>
	<table><tr><td>ID</td><td>CatName</td></tr>
		<c:forEach items="${list}" var="v" varStatus="status">
			<tr>
				<td>${v.id}</td>
				<td>${v.categoryName}</td>							
			</tr>
		</c:forEach>
	</table>
</body>
</html>