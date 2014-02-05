<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<title>Bar Chart</title>
<link type="text/css" href="${appContext}/bar-chart/css/common.css" rel="stylesheet" />
<link type="text/css" href="${appContext}/common-css/custom.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${appContext}/bar-chart/css/03.css">
		
		<!-- JavaScript at the bottom for fast page loading -->
<!-- bar chart -->						
<script type="text/javascript" src="${appContext}/bar-chart/js/03.js"></script>
<script type="text/javascript" src="${appContext}/bar-chart/js/chart.js"></script>
<!-- bar chart -->

<!-- pie chart -->
<script type="text/javascript" src="${appContext}/pie-chart/js/highcharts.js"></script>
<script type="text/javascript" src="${appContext}/pie-chart/js/exporting.js"></script>
<!-- pie chart -->
		
<div id="radio">
		<input type="radio" id="rdoYear" name="radio" value="year" checked="checked" onclick="setRadio(this.value);"/><label for="rdoYear">Yearly</label>
		<input type="radio" id="rdoMonth" name="radio" value="month" onclick="setRadio(this.value);" /><label for="rdoMonth">Monthly</label>		
</div>
<div style="position: relative; margin-top:14px; overflow: hidden; display: block;" id="div1">  	
  	<select style="width: 100px; clear: both;" id="interval1" onchange="changeChart(this.value);">		
		<c:forEach items="${budgets}" var="v" varStatus="status">
			<option value="${v}">${v}</option>
		</c:forEach>		
	</select>
</div>
<div style="position: relative; margin-top:14px; overflow: hidden; display: none; float: left" id="div2">	
	<select style="width: 130px; clear: both;" id="interval2" onchange="changeChart(this.value);">		
		<c:forEach items="${interval}" var="budget" varStatus="status">
			<option value="${budget.month}-${budget.year}" class="bullet">${MONTHS_ARRAY[budget.month-1]}, ${budget.year}</option>
		</c:forEach>	
	</select>
</div>
<div style="position: relative; margin-left:5px; overflow: hidden; display: none; float: left" id="div3">
	<label for="interval3">Grouping</label>	
	<select style="width: 130px; clear: both;" id="interval3" onchange="changeChart($('#interval2').val());">		
		<c:forEach items="${grouping}" var="grouping" varStatus="status">
			<option value="${grouping.key}" class="bullet">${grouping.value}</option>
		</c:forEach>	
	</select>
</div>
<div style="clear: both;"></div>
<div id="res_chart"
	style="position: relative; padding: 1% 1%; overflow: hidden;"
	class="fakewindowcontain">	
</div>
<div class="budgt_msg"></div>
<script>
Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
    return {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, color],
            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
        ]
    };
});
document.title = "Yearly Bar Chart";
</script>