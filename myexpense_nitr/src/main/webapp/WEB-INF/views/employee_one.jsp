<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My sample mongodb one user</title>
</head>
<body>
	<table><tr><td>ID</td><td>UserID</td><td>Name</td><td>Age</td><td>Department</td></tr>
		
			<tr>
				<td>${emp.ID}</td>
				<td>${emp.userid}</td>
				<td>${emp.name}</td>
				<td>${emp.age}</td>
				<td>${emp.department.depname}</td>
			</tr>
		
	</table>
</body>
</html>