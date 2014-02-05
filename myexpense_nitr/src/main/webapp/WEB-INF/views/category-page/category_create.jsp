<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="CREATE NEW CATEGORY">
<!-- <div class="dialog-form" title="Create new category"> -->
	<p style="float: left;" class="validateTips">Category field is required.</p>
	<div id="addremoverows" style="float: right; margin-top: 10px;">
		<button id="addrows">Add Rows</button>		
		<button id="removerows">Remove Rows</button>		
	</div>
	<div style="clear: both;"></div>
	<form:form id="frm" name="frm" modelAttribute="category" method="post">
		<div style="margin-left: 60px;">
			<fieldset id="fieldset">
				<div style="float: left; width: 245px;">
					<form:label for="categoryName" path="categoryName">Category name</form:label>
				</div>
				<div style="float: left;">
					<form:label for="categoryType" path="categoryType">Category Type</form:label>
				</div>
				<div style="clear:both;height: 5px;"></div>
				<div id="1" class="div_content">
					<div style="width:245px;float: left;">				
						<form:input path="categoryName"
							class="text_cat ui-widget-content ui-corner-all"/>
					</div>
					<div style="float: left;">	
						<form:select multiple="single" path="categoryType" items="${map}" class="selectbox ui-widget-content"></form:select>
					</div>
					<div style="clear: both;"></div>
				</div>
			</fieldset>
		</div>
	</form:form>
</div>
<script>
$(function() {	
	$("#addremoverows button:first").button({
		icons : {
			primary : "ui-icon-plus"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-close"
		}
	});
	
	$("#addrows").button().click(function() {		
		addrows();		
	});
	$("#removerows").button().click(function() {		
		removerows();		
	});
});
</script>