<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="EDIT EXPENSE">
<p class="validateTips">All form fields are required.</p>
<form:form id="frm" name="frm" modelAttribute="expense" method="post">
	<fmt:formatDate value="${expense.expenseDate}" type="date" var="dtt"
		pattern="MM/dd/yyyy" />

	<fieldset>
		<form:label for="expenseTitle" path="expenseTitle">Title</form:label>
		<form:input path="expenseTitle"
			class="text ui-widget-content ui-corner-all" />

		<form:label for="expenseValue" path="expenseValue">Value</form:label>
		<form:input path="expenseValue"
			class="text ui-widget-content ui-corner-all" onkeypress="return isNumberKey(event)"/>
		<form:label for="expenseDate" path="expenseDate">Date</form:label>
		<br>
		<form:input path="expenseDate" readonly="true"
			class="datepicker ui-widget-content ui-corner-all" value="${dtt}" />
		<br> <br>
		<form:label for="categoryid" path="categoryid">Category</form:label>
		<br>
		<form:select path="categoryid"
			class="selectbox ui-widget-content" items="${map}" itemValue="id" itemLabel="categoryName"/>		

		<br> <br>
		<form:label for="paidViaId" path="paidViaId">Paid Via</form:label>
		<br>
		<form:select path="paidViaId"
			class="selectbox ui-widget-content" items="${mapPaidVia}" itemValue="id" itemLabel="paymentMode"/>		
		
		<c:if test="${empty expense.id }">				
			<form:hidden path="userid" value="${sessionScope.login_id}" />
			<form:hidden path="users.id" value="${sessionScope.login_id}" />
			<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
		</c:if>
		<c:if test="${not empty expense.id }">
			<form:hidden path="userid" />
			<form:hidden path="users.id"/>						
			<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />								
			<form:hidden path="id" />
		</c:if>
		
		<c:if test="${expense.createdDate != null}">
			<fmt:formatDate value='${expense.createdDate}' type='both' var="dt"
				pattern="MM/dd/yyyy HH:mm:ss" />
			<form:hidden path="createdDate" value="${dt}" />
		</c:if>

	</fieldset>
</form:form>
</div>
<script>
	$(function() {
		$( "#expenseDate" ).datepicker({
			/* showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true, */
			maxDate:'0',
			changeMonth: true,
			numberOfMonths: 2,
			showCurrentAtPos: 1,
			changeYear: true
		});
	});
	
	
</script>