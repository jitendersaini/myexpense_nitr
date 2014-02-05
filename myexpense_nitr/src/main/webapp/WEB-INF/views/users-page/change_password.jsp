<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="dialog-form" title="EDIT ACCOUNT DETAILS">
	<p class="validateTips">All form fields are required.</p>
	<div id="loader"></div>
	<form:form modelAttribute="changepassword" id="frm" name="frm">
		<fieldset>
			<form:hidden path="userid" value="${sessionScope.login_id}" />
			<form:label path="password">CURRENT PASSWORD</form:label>
			<form:password path="password"
				class="text ui-widget-content ui-corner-all" />
			<form:label path="newpassword">NEW PASSWORD</form:label>
			<form:password path="newpassword"
				class="text ui-widget-content ui-corner-all" />
			<form:label path="retypenewpassword">RETYPE PASSWORD</form:label>
			<form:password path="retypenewpassword"
				class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form:form>
</div>