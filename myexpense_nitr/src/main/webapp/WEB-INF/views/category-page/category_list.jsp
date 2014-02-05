<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>Category Management</title>
<script src="${appContext}/common-js/category.js" type="text/JavaScript"></script>
<div
	style="position: relative; width: 96%; height: 28px; padding: 1% 4%; "
	class="fakewindowcontain">	
	<div style="float: left;">	
			<div>
				<label>Category Type</label>
			</div>
			<div style="margin-top: 2px;">
				<select style="width: 130px" id="type"
					onchange="changeResult(this.value);">				
						<option value="1" class="bullet">Expense</option>
						<option value="2" class="bullet">Notification</option>				
				</select>
			</div>
		</div>
	<div id="button_actions" align="right" style="float: right;margin-top: 8px;">
		<button id="create">ADD CATEGORY</button>
		<button id="edit">EDIT CATEGORY</button>
		<button id="delete">REMOVE CATEGORY</button>
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
	document.title = "Category Management";
	$('#type').selectmenu({
		style : 'dropdown'
	});	
</script>