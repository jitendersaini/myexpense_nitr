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
	<form:form modelAttribute="users" id="frm" name="frm">
		<fieldset>
			<label for="name">NAME</label>
			<form:hidden path="id" value="${sessionScope.login_id}" />			
			<form:input path="name" class="text ui-widget-content ui-corner-all"
				value="${sessionScope.name}" />
			<label for="email">EMAIL</label>
			<form:input path="email" class="text ui-widget-content ui-corner-all"
				value="${sessionScope.email}" />
			<form:label path="colorTheme">COLOR THEME</form:label>
			<form:input path="colorTheme" readonly="true"
				style="border: 0; color: #f6931f; font-weight: bold;" />
			<div id="slidertheme" style="width: 335px;"></div>
		</fieldset>
	</form:form>
</div>
<script>
	var arr = [ 'redmond', 'aristo', 'black-tie', 'blitzer', 'cupertino',
			'dark-hive', 'dot-luv', 'eggplant', 'excite-bike', 'flick',
			'hot-sneaks', 'humanity', 'le-frog', 'mint-choc', 'overcast',
			'pepper-grinder', 'smoothness', 'south-street', 'start', 'sunny',
			'swanky-purse', 'trontastic', 'ui-darkness', 'ui-lightness',
			'vader' ];
var index = 0;	
for ( var i = 0; i < arr.length; i++) {
	if('${sessionScope.colortheme}'==arr[i]) {
		index = i;
	}
}
	//alert(arr.length);
	$("#slidertheme").slider({
		value : index,
		min : 0,
		max : 24,
		step : 1,
		slide : function(event, ui) {
			$("#colorTheme").val(arr[ui.value]);
		}
	});
	$("#colorTheme").val('${sessionScope.colortheme}');
</script>