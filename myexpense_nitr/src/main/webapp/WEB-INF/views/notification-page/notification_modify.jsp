<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>	
	<c:when test="${not empty notification.id }">
		<div id="dialog-form" title="EDIT NOTIFICATION">
	</c:when>
	<c:otherwise>
		<div id="dialog-form" title="CREATE NEW NOTIFICATION">
	</c:otherwise>
</c:choose>
<p class="validateTips">All form fields are required.</p>
<form:form id="frm" name="frm" modelAttribute="notification" method="post">
	<fmt:formatDate value="${notification.notifyStartDate}" type="date" var="nStartDate"
		pattern="MM/dd/yyyy" />
	<fmt:formatDate value="${notification.notifyEndDate}" type="date" var="nEndDate"
		pattern="MM/dd/yyyy" />	

	<fieldset>
		<form:label for="notifyTitle" path="notifyTitle">Notification Title</form:label>
		<br>
		<form:input path="notifyTitle"
			class="text ui-widget-content ui-corner-all" />

		<br> <br>
		<form:label for="notifyStartDate" path="notifyStartDate">Notification Start Date</form:label>
		<br>
		<form:input path="notifyStartDate" readonly="true"
			class="datepicker ui-widget-content ui-corner-all" value="${nStartDate}" />
		<br> <br>
		<form:label for="notifyEndDate" path="notifyEndDate">Notification End Date</form:label>
		<br>
		<form:input path="notifyEndDate" readonly="true"
			class="datepicker ui-widget-content ui-corner-all" value="${nEndDate}" />				
		<br> <br>
		<form:label for="notifyDays" path="notifyDays">Notify Before (days)</form:label>		
		<br>
		<form:input path="notifyDays" onkeypress="return isNumberKey(event)" maxlength="3" class="datepicker ui-widget-content ui-corner-all"/>
		<br> <br>
		<form:label for="categoryid" path="categoryid">Category</form:label>
		<br>
		<form:select path="categoryid"
			class="selectbox ui-widget-content" items="${map}" itemValue="id" itemLabel="categoryName"/>		
		<br> <br>
		<form:label for="notifyVia" path="notifyVia">Notify Via</form:label>		
		<br>
		<form:select path="notifyVia"
			class="selectbox ui-widget-content" items="${mapNotifyVia}"/>
		<c:if test="${empty notification.id }">				
			<form:hidden path="userid" value="${sessionScope.login_id}" />
			<form:hidden path="users.id" value="${sessionScope.login_id}" />
			<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
		</c:if>
		<c:if test="${not empty notification.id }">
			<form:hidden path="userid" />
			<form:hidden path="users.id"/>						
			<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />							
			<form:hidden path="id" />
		</c:if>
		<c:if test="${notification.createdDate != null}">
			<fmt:formatDate value='${notification.createdDate}' type='both' var="dt"/>
			<form:hidden path="createdDate" value="${dt}" />
		</c:if>	
	</fieldset>
</form:form>
<script>
	$(function() {
		$( "#notifyStartDate" ).datepicker({
			/* showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true, */
			changeMonth: true,
			numberOfMonths: 2,
			showCurrentAtPos: 1,
			changeYear: true
		});
		$( "#notifyEndDate" ).datepicker({
			/* showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true, */
			changeMonth: true,
			numberOfMonths: 2,
			showCurrentAtPos: 1,
			changeYear: true
		});
	});
	
	
</script>