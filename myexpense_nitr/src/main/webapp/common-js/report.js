$(function() {
	$("#button_actions_report button:first").button({
		icons : {
			primary : "ui-icon-play"
		}
	});

	$("#gen-button").button().click(function() {
		accordianCallsDateRange('repform');
		// $("#dialog-form").dialog("open");
	});

});


/*$("#grouping").change(function(){	
	var arr = $('#hidId').val().split(',');
	alert($("#grouping").val());
	
	var vl = $("#grouping").val();
	//alert("v1--> "+v1);
	//if(vl) {
		for(var i=1;i<arr.length;i++) {
		
			if(vl == "clear") {
				jQuery("#"+arr[i]).jqGrid('groupingRemove',true);
			} else {
				jQuery("#"+arr[i]).jqGrid('groupingGroupBy','paidVia');
			}
		}
	//}
});

*/

$("#grouping").bind("change", function() {
	//alert("value changed");
	if($('#from').val() != "" && $('#to').val() !="") {
		accordianCallsDateRange('repform');
	}else {
		search();
	}
});

$(function() {
	search();
});

function search() {
	accordianCalls();
}

function accordianCalls() {
	$.ajax({
		type : 'POST',
		url : 'reports/action?search=&username=' + usrname+'&grouping='+$("#grouping").val(),
		onCreate : showLoader('jtable', 'Loading Details'),
		success : function(response) {
			$("#jtable").html(response);
			loadPaging('accrdn');
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(failureMessage + " " + xhr.status + " " + thrownError);
		}
	});
}
function accordianCallsDateRange(formName) {
	var bValid = true;
	$('#from').removeClass("ui-state-error");
	$('#to').removeClass("ui-state-error");
	
	bValid = bValid && validate($('#from'), "From");
	bValid = bValid && validate($('#to'), "To");
	if (bValid) {
		$.ajax({
			type : 'POST',
			url : 'reports/action?search=&grouping='+$("#grouping").val(),
			data : $("#" + formName).serialize(),
			onCreate : showLoader('jtable', 'Loading Details'),
			success : function(response) {				
				$("#jtable").html(response);				
				loadPaging('accrdn');
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(failureMessage + " " + xhr.status + " " + thrownError);
			}
		});
	}
}

$(function() {
	var dates = "";
	dates = $("#from, #to")
			.datepicker(
					{
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true,
						numberOfMonths : 3,
						showCurrentAtPos: 2,
						onSelect : function(selectedDate) {
							var option = this.id == "from" ? "minDate"
									: "maxDate", instance = $(this).data(
									"datepicker"), date = $.datepicker
									.parseDate(
											instance.settings.dateFormat
													|| $.datepicker._defaults.dateFormat,
											selectedDate, instance.settings);
							dates.not(this).datepicker("option", option, date);
						}
					});
});
function validate(o, n) {
	if (o.val() == '') {
		o.addClass("ui-state-error");
		updateTips(n + " must not be empty.");
		return false;
	} else {
		return true;
	}
}
function updateTips(t) {
	var tips = $(".validateTips");
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}
