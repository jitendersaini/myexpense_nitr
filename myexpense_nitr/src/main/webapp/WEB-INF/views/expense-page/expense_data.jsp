<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="flag" value="false"/>
<table id="exptable" class="display">
	<thead>
		<tr>
			<th><div style="margin-left: 9px;" title="Select All"><input type="checkbox" id="selectAll" class="selectAll"></div></th>
			<th>TITLE</th>
			<th>VALUE (${sessionScope.currency})</th>
			<th>CATEGORY</th>
			<th>PAID VIA</th>
			<th>EXPENSE DATE</th>
			<th>CREATED BY</th>
			<th>MODIFIED BY</th>
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="v" varStatus="status">
			<c:choose>
				<c:when test="${listMB[status.index]}">
					<c:set var="flag" value="true"/>
					<tr class="gradeX">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
			<td width="1%" align="center"><input type="checkbox" id="rdo"
				class="rdo" name="rdo" value="${v.id}"></td>
			<td>${v.expenseTitle}</td>
			<td align="right">${v.expenseValue}</td>
			<td><input type="hidden" class="clazz" name="hidCat" value="${v.category.id}"/>${v.category.categoryName}</td>			
			<td><input type="hidden" class="clazzPM" name="hidPaymentMode" value="${v.paidVia.id}"/>${v.paidVia.paymentMode}</td>
			<td align="center"><fmt:formatDate value="${v.expenseDate}"
					type="date" pattern="MMM dd, yyyy" /><input type="hidden" name="hidUserid" value="${v.userid}"/></td>
			<td>${v.users.name}</td>
			<td>${v.usersModified.name}</td>			
			<td align="center"><fmt:formatDate value="${v.createdDate}"
					type="both" /></td>
			<td align="center"><fmt:formatDate value="${v.modifiedDate}"
					type="both" /></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th></th>
			<th>TITLE</th>
			<th><b>TOTAL (${sessionScope.currency} ${amount})</b></th>
			<th>CATEGORY</th>
			<th>PAID VIA</th>
			<th>EXPENSE DATE</th>
			<th>CREATED BY</th>
			<th>MODIFIED BY</th>
			<th>CREATED DATE</th>
			<th>MODIFIED DATE</th>
		</tr>
	</tfoot>
</table>
<c:choose>
<c:when test="${flag}">
<script>
$(function() {
	var txt = $(".budgt_msg");
	txt.text("You are running out of current monthly budget by "+'${sessionScope.currency}'+' ${balance}').addClass("ui-state-highlight");
});
</script>
</c:when>
<c:otherwise>
<script>
$(function() {
	var txt = $(".budgt_msg");
	txt.text("").removeClass("ui-state-highlight");
});
</script>
</c:otherwise>
</c:choose>
<script>
checkUncheckAll();
</script>