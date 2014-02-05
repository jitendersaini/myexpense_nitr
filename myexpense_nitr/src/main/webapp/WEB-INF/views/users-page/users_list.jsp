<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="${appContext}/common-js/users.js" type="text/JavaScript"></script>
<title>User Management</title>
<div
	style="position: relative; width: 96%; height: 500px; padding: 1% 4%; overflow: hidden;"
	class="fakewindowcontain">

	<div id="button_actions_usr" align="right">
		<button id="create-usr">ADD USER</button>
		<button id="edit-usr">EDIT USER</button>
		<button id="delete-usr">REMOVE USER</button>
	</div>
	<div style="height: 100px"></div>
	<div id="jtable"></div>
</div>