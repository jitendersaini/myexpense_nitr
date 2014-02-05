<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>Payment Mode</title>
<script src="${appContext}/common-js/paymode.js" type="text/JavaScript"></script>
<div
	style="position: relative; width: 96%; height: 28px; padding: 1% 4%; "
	class="fakewindowcontain">	
	<div id="button_actions_paymode" align="right" style="float: right;margin-top: 8px;">
		<button id="create-pm">ADD PAYMENT MODE</button>
		<button id="edit-pm">EDIT PAYMENT MODE</button>
		<button id="delete-pm">REMOVE PAYMENT MODE</button>
	</div>
	<div style="clear: both;"></div>
	<div id="dialog-confirm" title="Delete the record?"
		style="display: none;">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>These entries will be
			permanently deleted and cannot be recovered. Are you sure?
		</p>
	</div>	
</div>
<script>
document.title = "Payment Mode";
</script>