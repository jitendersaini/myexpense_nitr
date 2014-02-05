$(function() {
	$("#button_actions_not button:first").button({
		icons : {
			primary : "ui-icon-plusthick"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-pencil"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-cancel"
		}
	}).next().button({
		icons : {
			primary : "ui-icon-trash"
		}
	});
	
});

$(function() {
	$("#create-notify").button().click(function() {
		
		loadpopupform('notification/action?create=');
		// $("#dialog-form").dialog("open");
	});

	$("#edit-notify").button().click(function() {
		edit('notification/action?edit=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
	
	$("#deactivate-notify").button().click(function() {
		//remove('notification/action?remove=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
		deactivate('notification/action?deactivate=&id=');
	});
	
	$("#delete-notify").button().click(function() {
		remove('notification/action?remove=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
});

function deactivate(action) {	
	var id = [];
    $('#rdo:checked').each(function() {
    	id.push($(this).val());
    });    
	if (id == undefined  || id == '') {
		loadCommonMsgDailog("No entry selected");
		return;
	} else {
		action += id;
		loadDialogDeactivate(id, action);
	}
}

function loadDialogDeactivate(id, action) {	
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm_deactivate").dialog({
		resizable : false,
		height : 140,
		modal : true,
		show : "blind",
		hide : "fold",
		buttons : {
			"Proceed" : function() {
				removeEntries(id, action);
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
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
				removeEntries(id, action);
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
	ajaxCallsWithPaging('notification/action?search=&userid='+usrid, 'post', 'jtable', 'nottable',
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
	} else {
		return true;
	}
}
function checkLengthCreate(o, n, min, max) {	
	var flag = true;
	$(".text_exp").removeClass('ui-state-error');
	$(".text_exp").each(function() {
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
function checkValueCreate(o, n, min) {
	var flag = true;
	$(".text_exp_value").removeClass('ui-state-error');
	$(".text_exp_value").each(function() {
		o = $(this);
		if (o == "") {
			o.addClass("ui-state-error");
			updateTips("Value field can not be empty.");
			flag = false;
			return false;
		}
		if (o.val() <= min) {
			o.addClass("ui-state-error");
			updateTips("Value " + n + " must be greater than " + min + ".");
			flag = false;
			return false;
		}
	});		
	return flag;
}
function checkValue(o, n, min) {
	if (o == "") {
		o.addClass("ui-state-error");
		updateTips("Value field can not be empty.");
		return false;
	}
	if (o.val() <= min) {
		o.addClass("ui-state-error");
		updateTips("Value " + n + " must be greater than " + min + ".");
		return false;
	} else {
		return true;

	}
}

function checkDateCreate(cssName,msg) {	
	var flag = true;
		$("."+cssName).removeClass('ui-state-error');
		$("."+cssName).each(function() {
		var o = $(this);
		if (o.val() == "") {
			o.addClass("ui-state-error");
			updateTips(msg+" field can not be empty.");
			flag = false;
			return false;
		}		
	});
	return flag;
}
function checkDate(o,msg) {	
	if (o.val() == "") {
		o.addClass("ui-state-error");
		updateTips(msg+" field can not be empty.");
		return false;
	} else {
		return true;
	}
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
function validateDates(date1,date2,notifyDays) {
	var dateDiff = getDateDiff(date1.val(),date2.val(),'days');
	var dateDifffromToday = getDateDiff(date2.val(),'now','days');
		if(dateDiff<0) {
		date2.addClass("ui-state-error");
		updateTips("End Date can not be less than Start Date");
		return false;
	} else if(dateDiff==0) {
		date2.addClass("ui-state-error");
		updateTips("End Date can not same as Start Date");
		return false;
	} else if(dateDifffromToday>=0) {
		date2.addClass("ui-state-error");
		updateTips("End Date has to more than today's date");
		return false;
	} else if(dateDiff <= notifyDays.val()) {
		notifyDays.addClass("ui-state-error");
		updateTips("Notify days are out of range for end date");
		return false;
	} else {
		return true;
	}	
}
function validateDatesCreate() {
	var flag = true;
	var i =0;
	$(".text_exp_value").each(function() {
		i++;
		var date1 = $('#dtStart'+i);
		var date2 = $('#dtEnd'+i);
		var notifyDays = $(this);		
		var dateDiff = getDateDiff(date1.val(),date2.val(),'days');
		var dateDifffromToday = getDateDiff(date2.val(),'now','days');
			if(dateDiff<0) {
			date2.addClass("ui-state-error");
			updateTips("End Date can not be less than Start Date");
			flag = false;
			return false;
		} else if(dateDiff==0) {
			date2.addClass("ui-state-error");
			updateTips("End Date can not same as Start Date");
			flag = false;
			return false;
		} else if(dateDifffromToday>=0) {
			date2.addClass("ui-state-error");
			updateTips("End Date has to more than today's date");
			flag = false;
			return false;
		} else if(dateDiff <= notifyDays.val()) {
			notifyDays.addClass("ui-state-error");
			updateTips("Notify days are out of range for end date");
			flag = false;
			return false;
		}
	});
	return flag;
}

function populateDialog() {
	// a workaround for a flaw in the demo system
	// (http://dev.jqueryui.com/ticket/4375), ignore!
	$("#dialog:ui-dialog").dialog("destroy");

	var notifyTitle = $("#notifyTitle"), notifyDays = $("#notifyDays"),notifyStartDate = $("#notifyStartDate"),notifyEndDate = $("#notifyEndDate"),

	allFields = $([]).add(notifyTitle).add(notifyStartDate).add(notifyEndDate).add(notifyDays);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 470,
		width : 795,
		show : "blind",
		hide : "fold",
		resizable : false,
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;				
				$(".text_exp").removeClass('ui-state-error');
				$(".datepicker").removeClass('ui-state-error');
				$(".text_exp_value").removeClass('ui-state-error');				
				allFields.removeClass("ui-state-error");

				bValid = bValid && checkLengthCreate(notifyTitle, "Notification Title", 3, 120);				
				bValid = bValid && checkDateCreate('datepickerStart',"Notification Start Date");
				bValid = bValid && checkDateCreate('datepickerEnd',"Notification End Date");
				bValid = bValid && checkValueCreate(notifyDays, "Notification Days", 0);
				bValid = bValid && validateDatesCreate();

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					save('notification/action?savemultiple=', 'frm');
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

	var notifyTitle = $("#notifyTitle"), notifyDays = $("#notifyDays"),notifyStartDate = $("#notifyStartDate"),notifyEndDate = $("#notifyEndDate"),

	allFields = $([]).add(notifyTitle).add(notifyStartDate).add(notifyEndDate).add(notifyDays);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 465,
		width : 475,
		show : "blind",
		hide : "fold",
		resizable : false,
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;
				allFields.removeClass("ui-state-error");

				bValid = bValid && checkLength(notifyTitle, "Notification Title", 3, 120);		
				bValid = bValid && checkDate(notifyStartDate,"Notification Start Date");
				bValid = bValid && checkDate(notifyEndDate,"Notification End Date");
				bValid = bValid && checkValue(notifyDays, "Notification Days", 0);
				bValid = bValid && validateDates(notifyStartDate,notifyEndDate,notifyDays);

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					save('notification/action?save=', 'frm');
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
function edit(action) {
	var id = $('input[name=rdo]:checked').val();

	if (id == undefined) {
		loadCommonMsgDailog("No entry selected");
		return;
	}else if($(".rdo:checked").length >1) {
		loadCommonMsgDailog("Only one record can be modified");
		return;
	} else {
		var radioButtons = $("input:checkbox[name=rdo]");
		var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));

		var notifyTitle = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(2)').text();
		var notifyDays = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(4)').text();
		
		var notifyVia = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(5)').text();
		
		var notifyStartDate = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(6)').text();
		
		var notifyEndDate = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(7)').text();	
		
		var createdDate = $(
				'#nottable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(11)').text();	
		
		notifyVia = $.trim(notifyVia);
		var createdById = document.getElementsByName('hidUserid')[selectedIndex].value;
		var categoryId ='';
		$('.clazz').each(function(index){
			var txtSplit = $(this).val().split(","); 
			if(index==selectedIndex)	 {
				categoryId = txtSplit;					
			   }
			   
			});
		
		if(notifyVia=='Email') {
			notifyVia = 1;
		}else if(notifyVia=='Sms') {
			notifyVia = 2;
		}else {
			notifyVia = 3;
		}

				action += id + '&notifyTitle=' + notifyTitle + '&notifyDays='
				+ notifyDays + '&notifyVia=' + notifyVia + '&notifyStartDate='
				+ notifyStartDate + '&notifyEndDate=' + notifyEndDate+'&categoryid='+categoryId+'&createdDate='+createdDate+'&userid='+createdById;		
		loadpopupformEdit(action);
	}
}