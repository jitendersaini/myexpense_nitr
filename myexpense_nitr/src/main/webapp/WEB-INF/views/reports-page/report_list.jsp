<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="${appContext}/common-js/report.js" type="text/JavaScript"></script>
<title>Reports</title>
<div class="validateTips"></div>
<div
	style="position: relative; width: 96%; height: 20px; padding: 1% 4%;"
	class="fakewindowcontain">
	<div style="float: left; padding-top: 4px">
		<form:form modelAttribute="report" method="post" id="repform"
			name="repform">
			<div style="width:200px; float: left;">
				<form:label path="grouping">Grouping Column</form:label>
				<form:select path="grouping" items="${grouping}"/>
			</div>
			<div style="float: left;">
				<form:label path="from">From</form:label>
				<form:input path="from" />
				<form:label path="to">To</form:label>
				<form:input path="to" />
			</div>
			<div style="clear: both;"></div>
		</form:form>
	</div>

	<div id="button_actions_report" style="float: left;">
		<button id="gen-button">Generate</button>
	</div>
	
	<div style="clear: both"></div>
</div>
</head>
<body>
<script>
document.title = "Reports";
</script>