function fetch4period(val) {
	search();
}

$(function() {
	$("#update-budg").button({
		icons : {
			primary : "ui-icon-disk"
		}
	});

});

$(function() {
	$("#button_actions_exp button:first").button({
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
	$("#create-exp").button().click(function() {
		add('expense/action?create=');
		// $("#dialog-form").dialog("open");
	});

	$("#edit-exp").button().click(function() {
		edit('expense/action?edit=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});

	$("#delete-exp").button().click(function() {
		remove('expense/action?remove=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});

	$("#update-budg").button().click(function() {
		save_budget('expense/action?savebudget=', 'frmb');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});

});

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

function loadBudgetDailog() {
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-budget").dialog({
		modal : true,
		resizable : false,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
}

$(function() {
	search();
});

function search() {
	ajaxCallsWithPaging('expense/action?search=&interval='
			+ $('#interval').val(), 'post', 'jtable', 'exptable',
			'Loading Details', 'Something Went Wrong');
}
function updateTips(t) {
	var tips = $(".validateTips");
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
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
function checkEmptyCreate(o, n) {
	var flag = true;
	$(".text_datepicker").removeClass('ui-state-error');
	$(".text_datepicker").each(function() {
		o = $(this);		
		if (o.val() == "") {
			o.addClass("ui-state-error");
			updateTips(n + " field can not be empty.");
			flag = false;
			return false;
		}
	});		
	return flag;
}
function checkEmpty(o, n) {
	if ($("select[name='categoryid']").val() == null) {
		o.addClass("ui-state-error");
		updateTips(n + " field can not be empty.");
		return false;
	} else {
		return true;
	}
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

	var title = $("#expenseTitle"), value = $("#expenseValue"), date = $("#expenseDate"), category = $("#categoryid"),

	allFields = $([]).add(title).add(value).add(date).add(category);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 500,
		width : 700,
		show : "blind",
		hide : "fold",
		resizable : false,
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;
				allFields.removeClass("ui-state-error");
				$(".text_exp").removeClass('ui-state-error');
				$(".text_exp_value").removeClass('ui-state-error');
				$(".text_datepicker").removeClass('ui-state-error');
				
				bValid = bValid && checkLengthCreate(title, "title", 3, 45);				
				bValid = bValid && checkValueCreate(value, "value", 0);				
				bValid = bValid && checkEmptyCreate(date, "date");

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					//save('expense/action?save=', 'frm');
					save('expense/action?savemultiple=', 'frm');
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

	var title = $("#expenseTitle"), value = $("#expenseValue"), date = $("#expenseDate"), category = $("#categoryid"),

	allFields = $([]).add(title).add(value).add(date).add(category);

	$("#dialog-form").dialog({
		autoOpen : false,
		height : 400,
		width : 450,
		show : "blind",
		hide : "fold",
		resizable : false,
		modal : true,
		buttons : {
			"Save" : function() {
				var bValid = true;
				allFields.removeClass("ui-state-error");

				bValid = bValid && checkLength(title, "title", 3, 45);
				bValid = bValid && checkValue(value, "value", 0);
				bValid = bValid && checkEmpty(category, "category");

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					save('expense/action?save=', 'frm');
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

function getSlider(budgetValue) {
	if (budgetValue == '') {
		budgetValue = 0;
	}

	$("#slider").slider({
		value : budgetValue,
		min : 0,
		max : 100000,
		step : 500,
		slide : function(event, ui) {
			$("#value").val(ui.value);
		}
	});

	$("#value").val(budgetValue);

}

function save_budget(actionName, formName) {
	jQuery.ajax({
		type : 'post',
		url : actionName,
		method : 'post',
		onCreate : showLoader('loader', 'Please wait...'),
		data : $("#" + formName).serialize(),
		success : function(html) {
			updateTipsExpense('Saved Successfully', 'loader');
			search();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// updateTips('Saved Successfully','loader');
			// alert(xhr.status);
			// alert(thrownError);
			alert("failed: " + xhr.status + " " + thrownError);
		}
	});
}

function updateTipsExpense(t, divName) {
	var tips = $("." + divName);
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}
function add(action) {
	if (eval($('#value').val()) == 0) {
		loadCommonMsgDailog("Monthly Budget can not be 0.");
		return;
	}
	loadpopupform(action);
}
function edit(action) {
	var id = $('input[name=rdo]:checked').val();

	if (id == undefined) {
		loadCommonMsgDailog("No entry selected");
		return;
	} else if($(".rdo:checked").length >1) {
		loadCommonMsgDailog("Only one record can be modified");
		return;
	}  else {
		var radioButtons = $("input:checkbox[name=rdo]");		
		var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
		var expTitle = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(2)').text();
		var expValue = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(3)').text();
		var expCategory = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(4)').text();
		
		var expPaidVia = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(5)').text();				
		
		var expDate = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(6)').text();
		var expCreatedDate = $(
				'#exptable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(9)').text();
		
		expPaidVia = $.trim(expPaidVia);
		var createdById = document.getElementsByName('hidUserid')[selectedIndex].value;
		/*if(expPaidVia == 'Cash') {
			expPaidVia=1;
		} else if(expPaidVia == 'Credit Card') {
			expPaidVia=2;
		} else if(expPaidVia == 'Credit Card') {
			expPaidVia=2;
		} else if(expPaidVia == 'Debit Card') {
			expPaidVia=3;
		} else if(expPaidVia == 'Cheque') {
			expPaidVia=4;
		} else if(expPaidVia == 'Sodhexo Meal Pass') {
			expPaidVia=5;
		}*/		
		var paidViaId ='';
		$('.clazzPM').each(function(index){
			var txtSplit = $(this).val().split(","); 
			if(index==selectedIndex)	 {
				paidViaId = txtSplit;					
			   }
			   
			});
		
		var categoryId ='';
		$('.clazz').each(function(index){
			var txtSplit = $(this).val().split(","); 
			if(index==selectedIndex)	 {
				categoryId = txtSplit;					
			   }
			   
			});
		
		action += id + '&expenseTitle=' + expTitle + '&expenseValue='
				+ expValue + '&category.categoryName=' + expCategory +'&categoryid=' + categoryId 
				+ '&expenseDate=' + expDate + '&createdDate=' + expCreatedDate+'&userid='+createdById+'&paidViaId='+paidViaId;
		 
		//alert(action);
		loadpopupformEdit(action);
	}
}