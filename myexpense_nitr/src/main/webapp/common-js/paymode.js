$(function() {
	$("#button_actions_paymode button:first").button({
		icons : {
			primary : "ui-icon-plusthick"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-pencil"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-trash"
		}
	});
	
});

$(function() {
	
	$("#create-pm").button().click(function() {
		
		loadpopupform('paymode/action?create=');
		// $("#dialog-form").dialog("open");
	});

	$("#edit-pm").button().click(function() {
		edit('paymode/action?edit=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
	
	$("#delete-pm").button().click(function() {
		remove('paymode/action?remove=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
	

});
function removePaymentMode(id, action) {
	$.ajax({
		type : 'post',
		url : action,
		success : function(response) {			
			if(response != "") {
				loadCommonMsgDailog("Enteries ("+response+") is/are used in further transactions");
				return;
			}
			search();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alertMessage("Something Went Wrong" + xhr.status + " "
					+ thrownError);
		}
	});
}
function loadDialog(id, action) {
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").dialog({
		resizable : false,
		height : 140,
		modal : true,
		show : "blind",
		hide : "fold",
		buttons : {
			"Proceed" : function() {
				removePaymentMode(id, action);
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
}


$(function() {
	search();
});

function search() {
	ajaxCallsWithPaging('paymode/action?search=&userid='+usrid, 'post', 'jtable', 'pmtable',
			'Loading Details', 'Something Went Wrong');
}
function updateTips(t) {
	var tips = $(".validateTips");
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}

function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("Length of " + n + " must be between " + min + " and " + max
				+ ".");		
		return false;
	}else {
		return true;
	}
}
function checkLengthCreate(o, n, min, max) {	
	var flag = true;
	$(".text_pay").removeClass('ui-state-error');
	$(".text_pay").each(function() {
		o = $(this);
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips("Length of " + n + " must be between " + min + " and " + max
					+ ".");			
			flag = false;
			return false;
		}
	});		
	return flag;
}

function checkRegexp(o, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n);
		return false;
	} else {
		return true;
	}
}

function populateDialog() {
	// a workaround for a flaw in the demo system
	// (http://dev.jqueryui.com/ticket/4375), ignore!
	$("#dialog:ui-dialog").dialog("destroy");
	
	var name = $(".text_cat"), allFields = $([]).add(name);
	$("#dialog-form").dialog({
		autoOpen : false,
		height : 480,
		width : 540,
		show : "blind",
		hide : "fold",
		resizable : false,		
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;
				//allFields.removeClass("ui-state-error");

				bValid = bValid && checkLengthCreate(name, "Payment Mode", 3, 45);

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/				
				if (bValid) {
					save('paymode/action?savemultiple=', 'frm');
					$(this).dialog("close");					
				}
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");			
			$("#dialog-form").remove();
		}
	});
}

function populateDialogEdit() {
	// a workaround for a flaw in the demo system
	// (http://dev.jqueryui.com/ticket/4375), ignore!
	$("#dialog:ui-dialog").dialog("destroy");
	
	var name = $("#paymentMode"), allFields = $([]).add(name);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 300,
		width : 440,
		show : "blind",
		hide : "fold",
		resizable : false,		
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;
				allFields.removeClass("ui-state-error");

				bValid = bValid && checkLength(name, "Payment Mode", 3, 45);

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					save('paymode/action?save=', 'frm');
					$(this).dialog("close");					
				}
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		},
		close : function() {
			allFields.val("").removeClass("ui-state-error");			
			$("#dialog-form").remove();
		}
	});
}

function changeResult(val) {
	search();
}

function edit(action) {	
	var id = $('input[name=rdo]:checked').val();
	if (id == undefined) {
		loadCommonMsgDailog("No entry selected");
		return;
	} else if($(".rdo:checked").length >1) {
		loadCommonMsgDailog("Only one record can be modified");
		return;
	} else {
		var radioButtons = $("input:checkbox[name=rdo]");
		var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));		
		var paymentModeType = $(
				'#pmtable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(3)').text();
		var paymentModeText = $(
				'#pmtable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(2)').text();
		var createdDate = $(
				'#pmtable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(6)').text();		
		paymentModeType = $.trim(paymentModeType);
		var createdById = document.getElementsByName('hidUserid')[selectedIndex].value;		
		if(paymentModeType == 'Cash') {
			paymentModeType = 0;
		} else if(paymentModeType == 'Credit Card') {
			paymentModeType = 1;
		} else if(paymentModeType == 'Debit Card') {
			paymentModeType = 2;
		} else if(paymentModeType == 'Cheque') {
			paymentModeType = 3;
		} else if(paymentModeType == 'Coupons') {
			paymentModeType = 4;
		}		
		action += id + '&paymentModeType='+paymentModeType+'&paymentMode=' + paymentModeText+'&createdDate='+createdDate+'&userid='+createdById;		
		loadpopupformEdit(action);
	}
}