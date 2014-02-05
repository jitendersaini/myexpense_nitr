<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id='currentDate' class='java.util.Date' />
<fmt:formatDate var='currentYear' value='${currentDate}' pattern='yyyy' />
<fmt:formatDate var='currentMonth' value='${currentDate}' pattern='MM' />
<script src="${appContext}/common-js/expense.js" type="text/JavaScript"></script>
<title>Expense Management</title>
<div
	style="position: relative; width: 96%; height: 100px; padding: 1% 4%;"
	class="fakewindowcontain">
	<div style="margin-top:0px; float: left;">
		<form:form id="frmb" name="frmb" modelAttribute="budget">			
			<p>
				<form:label path="value">Monthly Budget (500 increments):</form:label>
				<form:label style="border: 0; color: #f6931f; font-weight: bold;"
					path="value">${sessionScope.currency}</form:label>
				<form:input path="value" readonly="true" size="8"
					style="border: 0; color: #f6931f; font-weight: bold;" />
				<label style="border: 0; color: #f6931f; font-weight: bold;">(Created By: ${budget.users.name} | Modified By: ${budget.usersModified.name})</label>
				<c:if test="${empty budget.id }">				
					<form:hidden path="userid" value="${sessionScope.login_id}" />
					<form:hidden path="users.id" value="${sessionScope.login_id}" />
					<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
				</c:if>
				<c:if test="${not empty budget.id }">
					<form:hidden path="userid" />
					<form:hidden path="users.id"/>						
					<form:hidden path="usersModified.id" value="${sessionScope.login_id}" />								
					<form:hidden path="id" />
				</c:if>
				<form:hidden path="month" value="${currentMonth}" />
				<form:hidden path="year" value="${currentYear}" />			
			</p>
		</form:form>
		<div id="slider" style="width: 335px; float: left;"></div>		
		<div style="float: left;margin-top: -9px;margin-left: 3px;">
			<button id="update-budg">UPDATE</button>
		</div>
		<div id="loader" class="loader" style="margin-left:3px;float: left;width: 110px;"></div>
		<div style="clear: both; margin-top: 50px;"></div>
		<div style="float: left;">	
			<select style="width: 130px" id="interval"
				onchange="fetch4period(this.value);">
				<c:forEach items="${interval}" var="budget" varStatus="status">
					<option value="${budget.month}-${budget.year}" class="bullet">${MONTHS_ARRAY[budget.month-1]}, ${budget.year}</option>
				</c:forEach>
			</select>
		</div>		
	</div>	
	<div id="button_actions_exp" style="float: right; margin-top: 78px;" align="right">
			<button id="create-exp">ADD EXPENSE</button>
			<button id="edit-exp">EDIT EXPENSE</button>
			<button id="delete-exp">REMOVE EXPENSE</button>
		</div>
		<div style="clear: both;"></div>
</div>
<div class="budgt_msg" style="width: 500px;"></div>
<script>
	$('#interval').selectmenu({
		style : 'dropdown'
	});
	getSlider('${budget.value}');
	document.title = "Expense Management";
	if(isMobileUserAgent()) {
		$('#button_actions_exp').css('margin-top','-27px');
	}	
</script>