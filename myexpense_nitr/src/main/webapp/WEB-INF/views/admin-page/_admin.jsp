<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
<jsp:include page="../common/common_include.jsp" />
<script>
	$(function() {
		// Tabs
		$('#tabs').tabs();
	});
</script>
<link href="${appContext}/common-css/initialization.css"
	rel="stylesheet" type="text/css" />
<script>
	var appContext = '${appContext}';
</script>
</head>
<body>
	<jsp:include page="../common/common_header.jsp" />
	<script>
		var usrname = '${usr}';
		var login_id = '${sessionScope.login_id}';
		var usrid = '${sessionScope.userid}';		
	</script>
	<div>
		<fieldset>	
			<div id="tabs">
				<ul>					
					<li><a href="${appContext}/dashboard/action">DASHBOARD</a></li>
					<li><a href="${appContext}/category/action">CATEGORIES</a></li>
					<li><a href="${appContext}/paymode/action">PAYMENT MODE</a></li>
					<li><a href="${appContext}/expense/action">EXPENSE DETAILS</a></li>
					<li><a href="${appContext}/reports/action">REPORTS</a></li>
					<li><a href="${appContext}/bargraph/action">BAR GRAPH |
							YEARLY</a></li>
					<li><a href="${appContext}/notification/action">SCHEDULE
							NOTIFICATION</a></li>
				</ul>			
			</div>
			<div style="height: 10px;"></div>
			<div id="jtable"></div>		
		</fieldset>
	</div>
</body>
<div class="demo_form"></div>
</html>
