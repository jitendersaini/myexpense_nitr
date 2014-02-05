<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="CREATE NEW EXPENSE">
<p style="float: left;" class="validateTips">All form fields are required</p>
	<div id="addremoverows" style="float: right; margin-top: 10px;">
		<button id="addrows">Add Rows</button>		
		<button id="removerows">Remove Rows</button>		
	</div>
	<div style="clear: both;"></div>
<form:form id="frm" name="frm" modelAttribute="expense" method="post">
	<fmt:formatDate value="${expense.expenseDate}" type="date" var="dtt"
		pattern="MM/dd/yyyy" />
	<div style="margin-left: 20px;">
		<fieldset id="fieldset">
			<div style="float: left; width: 150px;">
				<form:label for="expenseTitle" path="expenseTitle">TITLE</form:label>
			</div>
			<div style="float: left; width: 88px;">
				<form:label for="expenseValue" path="expenseValue">VALUE</form:label>
			</div>
			
			<div style="float: left; width: 103px;">
				<form:label for="expenseDate" path="expenseDate">DATE</form:label>
			</div>
			
			<div style="float: left; width: 132px;">
				<form:label for="categoryid" path="categoryid">CATEGORY</form:label>
			</div>
			
			<div style="float: left;">
				<form:label for="paidViaId" path="paidViaId">MODE OF PAYMENT</form:label>
			</div>
			<div style="clear:both;height: 5px;"></div>
				<div id="1" class="div_content">
					<div style="float: left; width: 150px;">
						<form:input path="expenseTitle"
							class="text_exp ui-widget-content ui-corner-all" />
					</div>
					<div style="float: left; width: 88px;">
						<form:input path="expenseValue"
							class="text_exp_value ui-widget-content ui-corner-all"
							maxlength="10" onkeypress="return isNumberKey(event)" />
					</div>
					<div style="float: left; width: 103px;">
						<form:input path="expenseDate" readonly="true" id="dt1"
							class="datepicker text_datepicker ui-widget-content ui-corner-all" value="${dtt}" />
					</div>
					<div style="float: left; width: 132px;">
						<form:select path="categoryid" class="selectbox ui-widget-content" multiple="single"
							items="${map}" itemValue="id" itemLabel="categoryName" />
					</div>
					<div style="float: left;">
						<form:select path="paidViaId" class="selectbox ui-widget-content" multiple="single"
							items="${mapPaidVia}" itemValue="id" itemLabel="paymentMode"/>
					</div>
					<div style="clear: both;"></div>
				</div>
		</fieldset>
	</div>	
</form:form>
</div>
<script>
/* initilizeDatePicker3Months();

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
	initilizeDatePicker3Months();
});
$("#removerows").button().click(function() {		
	removerows();		
}); */

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
	            	deInitilizeDatePicker('dt','datepicker',1,true);      
	                initilizeDatePicker3Months('dt','datepicker',1,true);
	                return false;	                
	            });
	        $('#removerows').click(
	            function() {
	            	removerows();
	                return false;
	            });	        
	        $('#dt1').datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */
	 			altField: "#dt1",
	 			maxDate:'0',
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
	    });

	
	
</script>