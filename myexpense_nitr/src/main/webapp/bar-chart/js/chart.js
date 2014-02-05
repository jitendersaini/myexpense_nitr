$('#interval1').selectmenu({
	style : 'dropdown'
});
$('#interval2').selectmenu({
	style : 'dropdown'
});
$('#interval3').selectmenu({
	style : 'dropdown'
});
$(function() {
	$( "#radio" ).buttonset();
	
	searchYear($("#interval1").val());
});
function searchYear(interval) {
	simpleAjaxCall('bargraph/action?search_year=&year='+interval, 'post', 'res_chart',
			'Loading Details', 'Something Went Wrong');
	$("#jtable").html('');
}
/*function searchMonth4Grouping(interval, grouping) {
	simpleAjaxCall('bargraph/action?pie_chart=&grouping='+grouping+'&interval='+interval, 'post', 'res_chart',
			'Loading Details', 'Something Went Wrong');
	$("#jtable").html('');
}*/
function searchMonth(interval,grouping) {	
	simpleAjaxCall('bargraph/action?pie_chart=&interval='+interval+'&grouping='+grouping, 'post', 'res_chart',
			'Loading Details', 'Something Went Wrong');
	$("#jtable").html('');
}

/*function changeChart4Grouping(grouping) {
	searchMonth4Grouping($('#interval2').val(),grouping);
}*/
function changeChart(interval) {
	var r = /-/;
	if(r.test(interval)) {
		searchMonth(interval,$('#interval3').val());
	} else {
		searchYear(interval);
	}
}
function setRadio(rdo) {
	if(rdo=='year') {		
		$("#div1").css("display", "block");
		$("#div2").css("display", "none");
		$("#div3").css("display", "none");
		
		simpleAjaxCall('bargraph/action?search_year=&year='+$('#interval1').val(), 'post', 'res_chart',
				'Loading Details', 'Something Went Wrong');
		$("#jtable").html('');
	} else if(rdo=='month') {
		$("#div3").css("display", "block");
		$("#div2").css("display", "block");
		$("#div1").css("display", "none");		
		simpleAjaxCall('bargraph/action?pie_chart=&interval='+$('#interval2').val()+'&grouping='+$('#interval3').val(), 'post', 'res_chart',
				'Loading Details', 'Something Went Wrong');
		$("#jtable").html('');	
	}
}