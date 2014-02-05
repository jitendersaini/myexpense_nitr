<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table id="nottable" class="display">
	<thead>
		<tr>
			<th><div style="margin-left: 9px;" title="Select All"><input type="checkbox" id="selectAll" class="selectAll"></div></th>
			<th>DESCRIPTION</th>
			<th>CATEGORY</th>
			<th>DAYS</th>
			<th>NOTIFY VIA</th>
			<th>START DATE</th>
			<th>END DATE</th>
			<th>CURRENT STATUS</th>		
			<th>CREATED BY</th>
			<th>MODIFIED BY</th>
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="v" varStatus="status">						
			<tr class="${cssName[status.index]}">
				<td width="5%" align="center"><input type="checkbox" id="rdo" class="rdo" name="rdo"
					value="${v.id}"></td>
				<td style="width: 15%">${v.notifyTitle}</td>
				<td style="width: 10%"><input type="hidden" class="clazz" name="hidCat" value="${v.category.id}"/>${v.category.categoryName}</td>
				<td align="center" style="width: 8%;">${v.notifyDays}</td>
				<td style="width: 10%">
					<c:choose>
						<c:when test="${v.notifyVia == 1}">
							Email
						</c:when>
						<c:when test="${v.notifyVia == 2}">
							Sms
						</c:when>
						<c:when test="${v.notifyVia == 3}">
							Both
						</c:when>
					</c:choose>
					
				</td>
				<td align="center" style="width: 13%;"><input type="hidden" name="hidUserid" value="${v.userid}"/><fmt:formatDate value="${v.notifyStartDate}" type="date"/></td>
				<td align="center" style="width: 13%;"><fmt:formatDate value="${v.notifyEndDate}" type="date"/></td>
				<td align="center" style="width: 10%;">${statusDescription[status.index]}</td>
				<td align="center" style="width: 10%;">${v.users.name}</td>
				<td align="center" style="width: 10%;">${v.usersModified.name}</td>										
				<td align="center" style="width: 13%;"><fmt:formatDate value="${v.createdDate}" type="both"/></td>
				<td align="center" style="width: 13%;"><fmt:formatDate value="${v.modifiedDate}" type="both"/></td>
			</tr>			
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th></th>
			<th>DESCRIPTION</th>
			<th>CATEGORY</th>
			<th>DAYS</th>
			<th>NOTIFY VIA</th>
			<th>START DATE</th>
			<th>END DATE</th>	
			<th>CURRENT STATUS</th>
			<th>CREATED BY</th>
			<th>MODIFIED BY</th>		
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
		</tr>
	</tfoot>
</table>
<script>
checkUncheckAll();
</script>