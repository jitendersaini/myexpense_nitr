$(function() {
	$("#button_actions button:first").button({
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
	
	$("#create").button().click(function() {
		
		loadpopupform('category/action?create=');
		// $("#dialog-form").dialog("open");
	});

	$("#edit").button().click(function() {
		edit('category/action?edit=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
	
	$("#delete").button().click(function() {
		remove('category/action?remove=&id=');
		// remove('action?remove=');
		// $("#dialog-form").dialog("open");
	});
	

});
function removeCategories(id, action) {
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
				removeCategories(id, action);
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
	ajaxCallsWithPaging('category/action?search=&userid='+usrid+'&categoryType='+$('#type').val(), 'post', 'jtable', 'cattable',
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
	$(".text_cat").removeClass('ui-state-error');
	$(".text_cat").each(function() {
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

				bValid = bValid && checkLengthCreate(name, "Category", 3, 45);

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/				
				if (bValid) {
					save('category/action?savemultiple=', 'frm');
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
	
	var name = $("#categoryName"), allFields = $([]).add(name);

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

				bValid = bValid && checkLength(name, "Category", 3, 45);

				/*
				 * bValid = bValid && checkRegexp(categoryName,
				 * /^[a-z]([0-9a-z_])+$/i, "Category Name may consist of a-z,
				 * 0-9, underscores, begin with a letter.");
				 */
				// From jquery.validate.js (by joern),
				// contributed by Scott Gonzalez:
				// http://projects.scottsplayground.com/email_address_validation/
				if (bValid) {
					save('category/action?save=', 'frm');
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
		var categoryType = $(
				'#cattable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(3)').text();
		var categoryText = $(
				'#cattable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(2)').text();
		var createdDate = $(
				'#cattable tr:nth-child(' + (selectedIndex + 1)
						+ ') td:nth-child(6)').text();		
		categoryType = $.trim(categoryType);
		var createdById = document.getElementsByName('hidUserid')[selectedIndex].value;
		if(categoryType == 'Expense') {
			categoryType = 1;
		} else {
			categoryType = 2;
		}
		action += id + '&categoryType='+categoryType+'&categoryName=' + categoryText+'&createdDate='+createdDate+'&userid='+createdById;		
		loadpopupformEdit(action);
	}
}