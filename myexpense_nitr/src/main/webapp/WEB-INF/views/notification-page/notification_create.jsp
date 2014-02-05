<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="CREATE NEW NOTIFICATION">
<p style="float: left;" class="validateTips">All form fields are required</p>
	<div id="addremoverows" style="float: right; margin-top: 10px;">
		<button id="addrows">Add Rows</button>		
		<button id="removerows">Remove Rows</button>		
	</div>
	<div style="clear: both;"></div>
<form:form id="frm" name="frm" modelAttribute="notification" method="post">
	<fmt:formatDate value="${notification.notifyStartDate}" type="date" var="nStartDate"
		pattern="MM/dd/yyyy" />
	<fmt:formatDate value="${notification.notifyEndDate}" type="date" var="nEndDate"
		pattern="MM/dd/yyyy" />
	<div style="margin-left: 20px;">
		<fieldset id="fieldset">
			<div style="float: left; width: 150px;">
				<form:label for="notifyTitle" path="notifyTitle">NOTIFICATION TITLE</form:label>
			</div>
			<div style="float: left; width: 130px;">
				<form:label for="notifyStartDate" path="notifyStartDate">START DATE</form:label>
			</div>
			
			<div style="float: left; width: 129px;">
				<form:label for="notifyEndDate" path="notifyEndDate">END DATE</form:label>
			</div>
			
			<div style="float: left; width: 125px;">
				<form:label for="notifyDays" path="notifyDays">NOTIFY BEFORE(days)</form:label>
			</div>
			
			<div style="float: left; width: 119px;">
				<form:label for="categoryid" path="categoryid">CATEGORY</form:label>
			</div>
			<div style="float: left;">
				<form:label for="notifyVia" path="notifyVia">NOTIFY VIA</form:label>
			</div>
			
			<div style="clear:both;height: 5px;"></div>
				<div id="1" class="div_content">
					<div style="float: left; width: 150px;">
						<form:input path="notifyTitle"
							class="text_exp ui-widget-content ui-corner-all" />
					</div>
					<div style="float: left; width: 130px;">
						<form:input path="notifyStartDate" readonly="true"  id="dtStart1"
							class="datepicker datepickerStart text_datepicker ui-widget-content ui-corner-all" value="${nStartDate}" />
					</div>
					<div style="float: left; width: 129px;">
						<form:input path="notifyEndDate" readonly="true"  id="dtEnd1"
							class="datepicker datepickerEnd text_datepicker ui-widget-content ui-corner-all" value="${nEndDate}" />
					</div>
					<div style="float: left; width: 125px;">
						<form:input path="notifyDays" onkeypress="return isNumberKey(event)" maxlength="3" class="text_exp_value ui-widget-content ui-corner-all"/>
					</div>
					<div style="float: left; width: 119px;">
						<form:select path="categoryid" multiple="single"
								class="selectbox ui-widget-content" items="${map}" itemValue="id" itemLabel="categoryName"/>
					</div>
					<div style="float: left;">
						<form:select path="notifyVia" multiple="single"
								class="selectbox ui-widget-content" items="${mapNotifyVia}"/>
					</div>
					<div style="clear: both;"></div>
				</div>
		</fieldset>
	</div>	
</form:form>
</div>
<script>
$(document).ready(
	    function(){
	    	$("#addremoverows button:first").button({
	    		icons : {
	    			primary : "ui-icon-plus"
	    		}
	    	}).next().button({
	    		icons : {
	    			primary : "ui-icon-close"
	    		}
	    	});
	    	
	        $('#addrows').click(
	            function() {
	            	addrows();
	            	deInitilizeDatePicker('dtStart','datepickerStart',1,false);      
	                initilizeDatePicker3Months('dtStart','datepickerStart',1,false);
	                deInitilizeDatePicker('dtEnd','datepickerEnd',1,false);      
	                initilizeDatePicker3Months('dtEnd','datepickerEnd',1,false);
	                return false;	                
	            });
	        $('#removerows').click(
	            function() {
	            	removerows();
	                return false;
	            });
	        $('#dtStart1').datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */	 			
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
	        
	        $('#dtEnd1').datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */	 			
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
	    });

	
	
</script>