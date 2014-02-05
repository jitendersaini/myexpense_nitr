<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Pie Chart</title>
<html>
<div id="container_pie" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
<script>
function RenderPieChart(elementId, dataList) {
	/* alert(dataList.length);
	alert(dataList);
	alert(dataList[0]);
	alert(dataList[1]); */
	//Highcharts = null;
	/* Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
	    return {
	        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
	        stops: [
	            [0, color],
	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	        ]
	    };
	}); */
	//alert($('#interval3').val());
	var title1 = $('#interval3').val()=='1' ? ('Monthly expense details Category wise for month '+$("#interval2 option:selected").text()):('Monthly expense details Payment Mode wise for month '+$("#interval2 option:selected").text());
	 $('#container_pie').highcharts({
        chart: {            
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title1
        },        
        tooltip: {
    	    //pointFormat: '{series.name}: <b>{point.percentage}%</b>',
    	    formatter: function() {                     
              return '<b>'+ this.point.name +'</b>: '+ (this.percentage).toFixed(2) +' %';
            }        	
        },        
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',                    
                    formatter: function() {                     
                        return '<b>'+ this.point.name +'</b>: '+ (this.percentage).toFixed(2) +' %';
                    }
                    
                },                
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: 'Monthly breakup',
            data: dataList
        }]
    });
}
function generatePieChart(id, arr) {
	 RenderPieChart(id, arr);
}
      
 var mainArr = new Array();
 var arr = new Array();
</script>
<c:forEach items="${catIdValue}" var="v" varStatus="status">
	<script>	
		arr = new Array();
		arr.push("${v.value}");
		arr.push(Number("${map[v.key]}"));
		mainArr.push(arr);
	</script>
</c:forEach>
<script>
generatePieChart('container',mainArr);
document.title = "Monthly Pie Chart";
</script>