var appContext = context;
/**
 * @param divName
 * @param msg
 */

/*function addrows() {	
	if($(".div_content").size()<8) {
		$("#fieldset").append($("div#1").clone());
	}
}*/

function isMobileUserAgent() {
var mobile = (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase()));
         if (mobile) {
             //alert("MOBILE DEVICE DETECTED");
             //var userAgent = navigator.userAgent.toLowerCase();
             /*if ((userAgent.search("android") > -1) && (userAgent.search("mobile") > -1))
                    document.write("<b> ANDROID MOBILE <br>")
              else if ((userAgent.search("android") > -1) && !(userAgent.search("mobile") > -1))
                  document.write("<b> ANDROID TABLET <br>")
              else if ((userAgent.search("blackberry") > -1))
                  document.write("<b> BLACKBERRY DEVICE <br>")
              else if ((userAgent.search("iphone") > -1))
                  document.write("<b> IPHONE DEVICE <br>")              
              else if ((userAgent.search("ipod") > -1))
                  document.write("<b> IPOD DEVICE <br>")
          else if ((userAgent.search("ipadd") > -1))
                  document.write("<b> IPAD DEVICE <br>")*/
               return true;   
           }
         else
             return false;
 }       
$.ajaxSetup({
	  complete: function (xhr) {
		  
		  /*alert("xhr.getResponseHeader('E_TIMEOUT'): "+xhr.getResponseHeader('E_TIMEOUT'));
		  alert("xhr.getResponseHeader('_admin'): "+xhr.getResponseHeader('_admin'));*/
	    if (xhr.responseText.indexOf("My Budget Management") !=-1) {
	      location.href="login/logout";
	    }
	  }
});

function addrows() {	
	if($(".div_content").size()<8) {
		var curMaxInput = $('.div_content').size();		
		$("#fieldset").append($("div#1").clone().attr({'id': (curMaxInput + 1)
        }));
		/*if(isDatepicker) {
			deInitilizeDatePicker(date_id,'datepicker',1);      
            initilizeDatePicker3Months(date_id,'datepicker',1);
		}*/
	}
}
function removerows() {	
	if($(".div_content").size()>1) {
		$('#fieldset .div_content:last')
        .remove();	
	}
	/*if($(".div_content").size()>1) {
		$("#1").remove();	
	}*/
	//alert($('.div_content').length);
	/*
	if ($('.div_content').length > 1) {
        $('#fieldset div#1:last')
            .remove();
    }
    */
	/*if ($('#fieldset div').length > 1) {
        $('#fieldset div:last')
            .remove();
    }*/
    /*if ($('#fieldset div').length <= 1) {
        $('#removerows')
            .attr('disabled', true);
    }
    else if ($('#fieldset div').length < 5) {
        $('#addrows')
            .removeAttr('disabled');

    }*/
    return false;

}

function checkUncheckAll() {
	$("#selectAll").change(function(e) {
		$('.rdo').attr('checked', this.checked);
	});

	$(".rdo").click(function(){
		 
	    if($(".rdo").length == $(".rdo:checked").length) {
	        $("#selectAll").attr("checked", "checked");
	    } else {
	        $("#selectAll").removeAttr("checked");
	    }

	});
}
function jsonArr() {
	//reports/action?jqgrid=

$.ajax(
        {
          url:"reports/action?jqgrid=", 
          type: "POST", 
          data: JSON.stringify( objecdt ), 
          success: callback, 
          dataType: "json",
          contentType: "application/json"
        } );    
/*$.getJSON('reports/action?jqgrid=', function(data) {
	this.gotData( data );
	var items = [];
	alert(data);  
	  $.each(data, function(key, val) {
	    items.push(val);	    
	  });
	  //items.join('');
	});*/



/*jQuery.getJSON('reports/action?jqgrid=', {
		queryString : term
	}, function(data, status, xhr) {
		//cache[term] = data;
		if (xhr === lastXhr) {
			response(data);
		}
	});*/
}


function isAlphaKey(evt) {	
	var charCode = (evt.which) ? evt.which : event.keyCode;
	 
	if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123)) {
	   return true;
	}
	return false;
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

	return true;
}

function showLoader(divName, msg) {
	if (!msg) {
		msg = "";
	}
	target = document.getElementById(divName);

	if (target) {
		target.innerHTML = "<img src=" + appContext
				+ "/images/ajax-loader.gif>&nbsp;" + msg
				+ "</img>&nbsp;<img src=" + appContext
				+ "/images/ajax-loader-progress.gif></img>";
	}
}

function loadPaging(tableid) {
	if (tableid == 'cattable') {
		$(document).ready(function() {
			$('#' + tableid).dataTable({
				"bJQueryUI" : true,
				"sPaginationType" : "full_numbers",
				"aaSorting" : [ [ 5, "desc" ] ],
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null,null,null,null ]
			});
		});
	}else if (tableid == 'pmtable') {
		$(document).ready(function() {
			$('#' + tableid).dataTable({
				"bJQueryUI" : true,
				"sPaginationType" : "full_numbers",
				"aaSorting" : [ [ 5, "desc" ] ],
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null,null,null,null ]
			});
		});
	} else if (tableid == 'exptable') {
		$(document).ready(function() {
			$('#' + tableid).dataTable({
				"bJQueryUI" : true,
				"sPaginationType" : "full_numbers",
				"aaSorting" : [ [ 5, "desc" ] ],
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null, null, null, null,null,null,null ]
			});
		});
	} else if (tableid == 'nottable') {
		$(document).ready(function() {
			$('#' + tableid).dataTable({
				"bJQueryUI" : true,
				"sPaginationType" : "full_numbers",
				"aaSorting" : [ [ 8, "desc" ] ],
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null,null,null,null,null,null,null,null,null ]
			});
		});
	}
}

function goURL() {
	$.ajax({
		type : 'post',
		url : 'action',

		success : function(response) {
			/*
			 * if (response.indexOf("j_username") != -1 &&
			 * response.indexOf("j_password") != -1 &&
			 * response.indexOf("j_spring_security_check") != -1) {
			 * location.replace(appContext + "/login/logout"); return; }
			 */

		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(failureMessage + " " + xhr.status + " " + thrownError);
		}
	});
}
function ajaxCallsWithPaging(actionName, methodName, divName, tableid,
		loaderMessage, failureMessage) {
	$("#" + divName).html('');
	$.ajax({
		type : methodName,
		url : actionName,
		onCreate : showLoader(divName, loaderMessage),
		success : function(response) {
			/*
			 * if (response.indexOf("j_username") != -1 &&
			 * response.indexOf("j_password") != -1 &&
			 * response.indexOf("j_spring_security_check") != -1) {
			 * location.replace(appContext + "/login/logout"); return; }
			 */
			$("#" + divName).html(response);
			loadPaging(tableid);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(failureMessage + " " + xhr.status + " " + thrownError);
		}
	});
}


function simpleAjaxCall(actionName, methodName, divName,
		loaderMessage, failureMessage) {	
	$.ajax({
		type : methodName,
		url : actionName,
		onCreate : showLoader(divName, loaderMessage),
		success : function(html) {
			$("#" + divName).html(html);			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert(failureMessage + " " + xhr.status + " " + thrownError);
		}
	});
}

function loadpopupformEdit(actionName) {
	$.ajax({
		type : 'post',
		url : actionName,
		success : function(html) {
			// $(".demo_form").html('');
			$(".demo_form").html(html);
			populateDialogEdit('edit');
			$("#dialog-form").dialog("open");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			alert("failed");
		}
	});
}

function loadpopupform(actionName) {
	$.ajax({
		type : 'post',
		url : actionName,
		success : function(html) {
			// $(".demo_form").html('');
			$(".demo_form").html(html);
			populateDialog();
			$("#dialog-form").dialog("open");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			alert("failed");
		}
	});
}

function loadpopupformusers(actionName) {
	$.ajax({
		type : 'post',
		url : actionName,
		success : function(html) {
			// alert(html);
			// $(".demo_form").html('');
			$(".demo_form").html(html);
			populateDialogEditUsers();
			$("#dialog-form").dialog("open");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			alert("failed");
		}
	});
}

function loadpopupformchangepassword(actionName) {
	$.ajax({
		type : 'post',
		url : actionName,
		success : function(html) {
			// alert(html);
			// $(".demo_form").html('');
			$(".demo_form").html(html);
			populateDialogChangePass();
			$("#dialog-form").dialog("open");
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			alert("failed");
		}
	});
}

function save(actionName, formName) {
	$.ajax({
		type : 'post',
		url : actionName,
		method : 'post',
		// onCreate : showLoader(divId, 'Please wait...'),
		data : $("#" + formName).serialize(),
		success : function(html) {
			search();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			// alert("failed: " + xhr.status + " " + thrownError);
			loadCommonMsgDailog("failed: " + xhr.status + " " + thrownError);
		}
	});
}

function remove(action) {
	//var id = $('input[name=rdo]:checked').val();
	
	var id = [];
    $('#rdo:checked').each(function() {
    	id.push($(this).val());
    });
	if (id == undefined || id == '') {
		loadCommonMsgDailog("No entry selected");
		return;
	} else {
		action += id;
		loadDialog(id, action);
	}
}

function removeEntries(id, action) {
	$.ajax({
		type : 'post',
		url : action,
		success : function(response) {
			search();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alertMessage("Something Went Wrong" + xhr.status + " "
					+ thrownError);
		}
	});
}

function editAccount() {

	loadpopupformusers('users/action?edit=');
	// $("#dialog-form").dialog("open");
}

function changePassword() {

	loadpopupformchangepassword('users/action?loadchangepass=');
	// $("#dialog-form").dialog("open");
}

function populateDialogEditUsers() {
	$("#dialog:ui-dialog").dialog("destroy");

	var name = $("#name"), email = $("#email"), allFields = $([]).add(name)
			.add(email);

	$("#dialog-form")
			.dialog(
					{
						autoOpen : false,
						height : 310,
						width : 450,
						resizable : false,
						show : "blind",
						hide : "fold",
						modal : true,
						buttons : {
							"Save" : function() {
								var bValid = true;
								allFields.removeClass("ui-state-error");

								bValid = bValid
										&& checkLength(name, "name", 3, 45);
								bValid = bValid
										&& checkLength(email, "email", 6, 80);

								// bValid = bValid && checkRegexp( username,
								// /^[a-z]([0-9a-z_])+$/i, "Username may consist
								// of a-z, 0-9, underscores, begin with a
								// letter." );
								// From jquery.validate.js (by joern),
								// contributed by Scott Gonzalez:
								// http://projects.scottsplayground.com/email_address_validation/
								bValid = bValid
										&& checkRegexp(
												email,
												/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
												"eg. ui@jquery.com");

								if (bValid) {
									save('users/action?save=', 'frm');
									$(this).dialog("close");									
									location.reload(); 		
									loadCommonMsgDailog("Account Updated successfully.");}
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

/*function initilizeDatePicker3Months() {
	$( ".datepicker" ).datepicker({
		 showOn: "button",
		buttonImage: "images/calendar.gif",
		buttonImageOnly: true, 
		changeMonth: true,
		numberOfMonths: 2,
		showCurrentAtPos: 1,
		changeYear: true
	});
}
*/
function deInitilizeDatePicker(date_id,calendarClass, count,isFutureDiabled) {
	var dd = '+100Y';
	if(isFutureDiabled) {
		dd = '0';
	}	
	$('.'+calendarClass).each(function() {			        	
	  		$(this).removeClass('hasDatepicker').datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */
	  			maxDate: dd,
	 			altField: "#"+date_id+count,
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
	  		count++;
	  	});
}
function initilizeDatePicker3Months(date_id,calendarClass, count,isFutureDiabled) {	
	var i = 1;
	var dd = '+100Y';
	if(isFutureDiabled) {
		dd = '0';
	}
	$('.'+calendarClass).each(function() { 
  	   $(this).attr({'id': date_id+(count)});
     	if(i<$('.'+calendarClass).size()) {
	     	$(this).datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */
	     		maxDate: dd,
	 			altField: "#"+date_id+count,
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});  
     	}else {
	     	$('#'+date_id+i).removeClass('hasDatepicker').datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */
	     		maxDate: dd,
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
     		$('#'+date_id+i).datepicker({
	 			/* showOn: "button",
	 			buttonImage: "images/calendar.gif",
	 			buttonImageOnly: true, */	
     			maxDate: dd,
	 			changeMonth: true,
	 			numberOfMonths: 2,
	 			showCurrentAtPos: 1,
	 			changeYear: true
	 		});
     	}
     	count++;
     	i++;
  	});	
}
function checkRetypePwd(pwd, pwdagn, n) {
	if ((pwd.val() != pwdagn.val())) {
		pwdagn.addClass("ui-state-error");
		updateTips("Retyping password doesn't match.");
		return false;
	} else {
		return true;
	}
}

function checkEmpty(o, n) {
	if (o.val() == '') {
		o.addClass("ui-state-error");
		updateTips(n + " field can not be empty.");
		return false;
	} else {
		return true;
	}
}

function populateDialogChangePass() {
	$("#dialog:ui-dialog").dialog("destroy");

	var currentpassword = $("#password"), newpassword = $("#newpassword"), retypenewpassword = $("#retypenewpassword"), allFields = $(
			[]).add(currentpassword).add(newpassword).add(retypenewpassword);

	$("#dialog-form").dialog(
			{
				autoOpen : false,
				height : 350,
				width : 450,
				resizable : false,
				show : "blind",
				hide : "fold",
				modal : true,
				buttons : {
					"Save" : function() {
						var bValid = true;
						allFields.removeClass("ui-state-error");

						bValid = bValid
								&& checkEmpty(currentpassword, "Password");

						bValid = bValid
								&& checkLength(newpassword, "Password", 5, 50);

						bValid = bValid
								&& checkRegexp(newpassword, /^([0-9a-zA-Z])+$/,
										"Password field only allow : a-z 0-9");
						bValid = bValid
								&& checkRetypePwd(newpassword,
										retypenewpassword,
										"Retype Password doesn't match");

						if (bValid) {
							savepwdchaged('users/action?savechagepass=', 'frm',
									$(this), currentpassword);
							// $(this).dialog("close");
							// loadCommonMsgDailog("Password Changed
							// successfully.");
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

function savepwdchaged(actionName, formName, objName, currentpassword) {

	$
			.ajax({
				type : 'post',
				url : actionName,
				method : 'post',
				onCreate : showLoader('loader', 'Please wait...'),
				data : $("#" + formName).serialize(),
				success : function(html) {
					if (html == '0') {
						$('#loader').html('');
						currentpassword.addClass("ui-state-error");
						updateTips("Bad credentials.... Please enter a valid password");
					} else {
						objName.dialog("close");
						loadCommonMsgDailog("Password Changed successfully.");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					// alert(xhr.status);
					// alert(thrownError);
					// alert("failed: " + xhr.status + " " + thrownError);
					loadCommonMsgDailog("failed: " + xhr.status + " "
							+ thrownError);
				}
			});
}

function loadCommonMsgDailog(value) {
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-mesg-common").dialog({
		modal : true,
		resizable : false,
		show : "blind",
		hide : "fold",
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
	var str = '<p><span class="ui-icon ui-icon-circle-check"style="float: left; margin: 0 7px 50px 0;"></span> '
			+ value + '</p>';
	// alert(str);
	$("#dialog-mesg-common").html(str);

}

$(function() {
	$('.myMenu > li').bind('mouseover', openSubMenu);
	$('.myMenu > li').bind('mouseout', closeSubMenu);
	
	function openSubMenu() {
		$(this).find('ul').css('visibility', 'visible');	
	};
	
	function closeSubMenu() {
		$(this).find('ul').css('visibility', 'hidden');	
	};
			   
});

function getDateDiff(date1, date2, interval) {
    var second = 1000,
    minute = second * 60,
    hour = minute * 60,
    day = hour * 24,
    week = day * 7;
    date1 = new Date(date1).getTime();
    date2 = (date2 == 'now') ? new Date().getTime() : new Date(date2).getTime();
    var timediff = date2 - date1;
    if (isNaN(timediff)) return NaN;
    switch (interval) {
    case "years":
        return date2.getFullYear() - date1.getFullYear();
    case "months":
        return ((date2.getFullYear() * 12 + date2.getMonth()) - (date1.getFullYear() * 12 + date1.getMonth()));
    case "weeks":
        return Math.floor(timediff / week);
    case "days":
        return Math.floor(timediff / day);
    case "hours":
        return Math.floor(timediff / hour);
    case "minutes":
        return Math.floor(timediff / minute);
    case "seconds":
        return Math.floor(timediff / second);
    default:
        return undefined;
    }
}