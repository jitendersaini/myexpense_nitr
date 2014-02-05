<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Expense Dashboard</title>
<!-- pie chart -->
<script type="text/javascript" src="${appContext}/pie-chart/js/highcharts.js"></script>
<script type="text/javascript" src="${appContext}/pie-chart/js/highcharts-more.js"></script>
<script type="text/javascript" src="${appContext}/pie-chart/js/exporting.js"></script>
  	
  	<div  id="gadgets_container">
	  	<div id="box-b-main">	
			<label class="label_box">Current Budget</label>
			<div id="box-container-b">
				<div id="box-holder-b" class="box-holder">
					<div id="box-b" class="box"><span id="box-span-b">${sessionScope.currency}&nbsp;${currentMonthBudget}</span></div>
				</div>
			</div>
		</div>
		<div id="box-e-main">
			<label class="label_box">Current Expenses</label>
			<div id="box-container-e" class="box-container">
				<div id="box-holder-e" class="box-holder">
					<div id="box-e" class="box"><span id="box-span-e">${sessionScope.currency}&nbsp;${currentMonthExp}</span></div>
				</div>
			</div>
		</div>				
		<div id="container-clock"></div>		
		<div id="datepicker-static"></div>
				
		<div class="widget widget-table">
		<div class="widget-header">
			<h3><i class="icon-table"></i>Notifications Due</h3>
		</div>
		<div class="widget-content">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Notification</th>
						<th>Category</th>
						<th>Expired On</th>
						<th>Notify Before</th>
					</tr>
				</thead>			
				<tbody>
					<c:forEach items="${list}" var="v" varStatus="status">	
					<tr>
						<td>${v.notifyTitle}</td>
						<td class="actions">${v.category.categoryName}</td>
						<td><fmt:formatDate value="${v.notifyEndDate}" type="date"/></td>
						<td>${v.notifyDays}&nbsp;(days)</td>
					</tr>
					</c:forEach>				
				</tbody>
				</table>
			</div>
	</div>
	</div>
	<div id="chart_container">
		<div id="container"></div>
	</div>
<div style="clear: both;"></div>
<script>
var monthBudget = new Array();
var monthExp = new Array();
</script>
<c:forEach items="${MONTHS_ARRAY}" var="v" varStatus="status">
 <c:choose>
	<c:when test="${map[status.index] ne null}">
		<script> monthExp.push(eval('${map[status.index]}')); </script>
	</c:when>
	<c:otherwise>
		<script> monthExp.push(0); </script>
	</c:otherwise>
</c:choose>
 <c:choose>
 	<c:when test="${budget[status.count] ne null}">
		<script> monthBudget.push(eval('${budget[status.count]}')); </script>
   </c:when>
	<c:otherwise>
		<script> monthBudget.push(0); </script>
	</c:otherwise>
 </c:choose>
</c:forEach>
<script>

//light blue
$('#box-b').css("background","#d9edf7");
$('#box-b').css("border-color","#bce8f1");
$('#box-span-b').css("color","#3a87ad"); 

if('${_color_code}' == 'A') {
	//amber
	$('#box-e').css("background","#fcf8e3");
	$('#box-e').css("border-color","#fbeed5");
	$('#box-span-e').css("color","#c09853");		
} else if('${_color_code}' == 'R') {
	// Red
	$('#box-e').css("background","#f2dede");
	$('#box-e').css("border-color","#eed3d7");
	$('#box-span-e').css("color","#b94a48");	
} else {
	// green
	$('#box-e').css("background","#dff0d8");
	$('#box-e').css("border-color","#d6e9c6");
	$('#box-span-e').css("color","#468847");	
}

/* if(isMobileUserAgent()) {
	$("#chart_container").css('width','auto');
}else {
	$("#chart_container").css('width','auto');
} */
generateGraph('${sessionScope.currency}',monthExp,monthBudget);
generateClock();
generateDatePicker();
</script>