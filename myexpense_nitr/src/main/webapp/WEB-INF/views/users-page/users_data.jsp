<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table id="usrtable" class="display">
	<thead>
		<tr class="even">
			<th>SELECT</th>
			<th>NAME</th>
			<th>USERNAME</th>
			<th>ACCESS LEVEL</th>
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
			<th>IS ENABLED</th>
			<th>IS DELETED</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${list}" var="v" varStatus="status">
			<tr class="even">
				<td width="5%" align="center"><input type="radio" id="rdo"
					class="rdo" name="rdo" value="${v.id}"></td>
				<td>${v.name}</td>
				<td>${v.username}</td>
				<td>${v.access}</td>
				<td align="center"><fmt:formatDate value="${v.createdDate}"
						type="date" /></td>
				<td align="center"><fmt:formatDate value="${v.modifiedDate}"
						type="date" /></td>
				<td>${v.enabled}</td>
				<td>${v.deleted}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr class="even">
			<th>SELECT</th>
			<th>NAME</th>
			<th>USERNAME</th>
			<th>ACCESS LEVEL</th>
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
			<th>IS ENABLED</th>
			<th>IS DELETED</th>
		</tr>
	</tfoot>
</table>