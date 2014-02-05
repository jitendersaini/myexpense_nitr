<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>Notification Scheduler</title>
<script src="${appContext}/common-js/notification.js" type="text/JavaScript"></script>
<div
	style="position: relative; width: 96%; height: 25px; padding: 1% 4%;"
	class="fakewindowcontain">

	<div id="button_actions_not" align="right" style="margin-top: 7px;">
		<button id="create-notify">CREATE NOTIFICATION</button>
		<button id="edit-notify">UPDATE NOTIFICATION</button>
		<button id="deactivate-notify">DEACTIVATE NOTIFICATION</button>
		<button id="delete-notify">REMOVE NOTIFICATION</button>		
	</div>
	<div id="dialog-confirm" title="Delete the record?"
		style="display: none;">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>This entry will be
			permanently deleted and cannot be recovered. Are you sure?
		</p>
	</div>
	
	<div id="dialog-confirm_deactivate" title="Deactivate the record?"
		style="display: none;">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>This selected enteries will be deactivated. Are you sure?
		</p>
	</div>
	
</div>
<script>
document.title = "Notification Scheduler";
</script>