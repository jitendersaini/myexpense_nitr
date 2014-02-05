$(function() {
	// the form wrapper (includes all forms)
	var $form_wrapper = $('#form_wrapper'),
	// the current form is the one with class active
	$currentForm = $form_wrapper.children('form.active');
	// the change form links
	//$linkform = $form_wrapper.find('.linkform');

	// get width and height of each form and store them for later
	$form_wrapper.children('form').each(function(i) {
		var $theForm = $(this);
		// solve the inline display none problem when using fadeIn fadeOut
		if (!$theForm.hasClass('active'))
			$theForm.hide();
		$theForm.data({
			width : $theForm.width(),
			height : $theForm.height()
		});
	});
	// set width and height of wrapper (same of current form)
	setWrapperWidth();

	/*
	 * clicking a link (change form event) in the form makes the current form
	 * hide. The wrapper animates its width and height to the width and height
	 * of the new current form. After the animation, the new form is shown
	 */
	/*$linkform.bind('click', function(e) {
		var $link = $(this);
		var target = $link.attr('rel');	
		$currentForm.fadeOut(400, function() {
			// remove class active from current form
			$currentForm.removeClass('active');
			// new current form
			$currentForm = $form_wrapper.children('form.' + target);
			// animate the wrapper
			$form_wrapper.stop().animate({
				width : $currentForm.data('width') + 'px',
				height : $currentForm.data('height') + 'px'
			}, 500, function() {
				// new form gets class active
				$currentForm.addClass('active');
				// show the new form
				$currentForm.fadeIn(400);
			});
		});
		e.preventDefault();
	});*/
	
	function setWrapperWidth() {
		$form_wrapper.css({
			width : $currentForm.data('width') + 'px',
			height : $currentForm.data('height') + 'px'
		});
	}

	/*
	 * for the demo we disabled the submit buttons if you submit the form, you
	 * need to check the which form was submited, and give the class active to
	 * the form you want to show
	 */
	
	$form_wrapper.find('#fgt-pass-button').click(function(e) {
		sendReminder();
	});
	
	$form_wrapper.find('#register-button').click(function(e) {
		saveusers();	
	});
	$form_wrapper.find('#join-acc-button').click(function(e) {
		findJoinAccount();
	});
	$form_wrapper.find('button').click(function(e) {
		e.preventDefault();
	});
		
});

function loadDialog(id, action) {
	$("#dialog:ui-dialog").dialog("destroy");
	$("#dialog-confirm").dialog({
		resizable : false,
		height : 140,
		show : "blind",
		hide : "fold",
		modal : true,
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

function search() {
	ajaxCallsWithPaging('users/action?search=', 'post', 'jtable', 'usrtable',
			'Loading Details', 'Something Went Wrong');
}
function switchTo(currentForm,target) {	
	updateJoinId('joinid-display','');
	$('#currency').removeAttr('readonly');
	$('#currency').val('');
	var $form_wrapper = $('#form_wrapper');
	var $currentForm = $('.'+currentForm);
	$currentForm.fadeOut(400, function() {
		// remove class active from current form
		$currentForm.removeClass('active');
		// new current form
		$currentForm = $form_wrapper.children('form.' + target);
		// animate the wrapper
		$form_wrapper.stop().animate({
			width : $currentForm.data('width') + 'px',
			height : $currentForm.data('height') + 'px'
		}, 500, function() {
			// new form gets class active
			$currentForm.addClass('active');
			// show the new form
			$currentForm.fadeIn(400);
		});
	});
}

function updateTips(t,err) {
	/*
	 * var tips = $(".validateTips");
	 * tips.text(t).addClass("ui-state-highlight");
	 */
	//$('#loader_register').html(t);	
	$('#'+err).text(t).css("visibility","visible");
	//$('#username_err').html("hello world");

	/*
	 * setTimeout(function() { tips.removeClass("ui-state-highlight", 1500); },
	 * 500);
	 */
}

function checkRetypePwdlogin(pwd, pwdagn,err, n) {
	if ((pwd.val() != pwdagn.val())) {
		pwdagn.addClass("ui-state-error");
		updateTips("Retyping password doesn't match.",err);
		return false;
	} else {
		return true;
	}
}

function checkLength(o,err, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		// o.addClass("ui-state-error");
		updateTips(" length must be between " + min + " and " + max
				+ ".",err);
		return false;
	} else {
		return true;
	}
}

function checkRegexp(o,err, regexp, n) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n,err);
		return false;
	} else {
		return true;
	}
}
function sendReminder() {
	$.ajax({
		type : 'post',
		url : "users/action?sendemail=",
		method : 'post',
		onCreate : showLoader('emailid_err', 'Please wait...'),
		data : $(".forgot_password").serialize(),
		success : function(html) {
			if (html == 'not done') {
				//$('#loader').html('Invalid Email ID');
				updateTips('Invalid Email ID',"emailid_err");
			} else {
				// objName.dialog("close");
				//$('#loader').html('Email Sent');
				updateTips('Email Sent',"success_email");
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			// alert("failed: " + xhr.status + " " + thrownError);
			loadCommonMsgDailog("failed: " + xhr.status + " " + thrownError);
		}
	});
}

function findJoinAccount() {
	$.ajax({
		type : 'post',
		url : "users/action?findacc=",
		method : 'post',
		onCreate : showLoader('emailid_err', 'Please wait...'),
		data : $(".join").serialize(),
		success : function(html) {
			//alert(html);
			if (html == '') {
				//$('#loader').html('Invalid Email ID');
				updateTips('Invalid UserName / Email ID','join_emailid_err');
			} else if(html[0] == 'child'){
				updateTips('Only Primary Account Can Be Joined','join_emailid_err');
			} else {
				// objName.dialog("close");
				//$('#loader').html('Email Sent');				
				//updateTips(html[0]+","+html[1],'join_success_email');
				switchTo('join','register');
				updateJoinId('joinid-display','(Joining Account: '+html[1]+')',html[0],html[2]);
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// alert(xhr.status);
			// alert(thrownError);
			// alert("failed: " + xhr.status + " " + thrownError);
			loadCommonMsgDailog("failed: " + xhr.status + " " + thrownError);
		}
	});
}
function updateJoinId(id,text,userid, currency) {
	$('#'+id).html(text);
	$('#joinedid').val(userid);		
	if(currency != undefined) {
		$('#currency').val(currency);
		$('#currency').attr('readonly', 'readonly');
	}
}
function saveusers() {
	/*
	 * if($('.uname').val() =="") { $('#loader_register').html('Username can not
	 * be empty'); return; }
	 */
	
	var username = $("#username"), name = $("#name"), email = $("#email"), password = $("#password"), pwdagain = $("#pwdagain"), currency = $("#currency"), allFields = $(
			[]).add(username).add(name).add(email).add(password).add(pwdagain)
			.add(currency);
	
	var username = $("#username"), name = $("#name"), email = $("#email"), password = $("#password"), pwdagain = $("#pwdagain"), currency = $("#currency"), 
	allFields_err = $(
			[]).add($("#username_err")).add($("#name_err")).add($("#email_err")).add($("#password_err")).add($("#pwdagain_err"))
			.add($("#currency_err"));
	
	//allFields.removeClass("ui-state-error");
	allFields_err.text("");
	var bValid = true;
	bValid = bValid && checkLength(username,"username_err", "username", 3, 45);
	bValid = bValid && checkLength(name, "name_err","name", 3, 45);
	bValid = bValid && checkLength(email, "email_err","email", 6, 80);
	bValid = bValid
			&& checkRegexp(
					email,"email_err",
					/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
					"eg. jsaini@gmail.com");
	bValid = bValid && checkLength(currency,"currency_err", "currency code", 3, 5);
	bValid = bValid && checkLength(password,"password_err", "password", 5, 50);
	bValid = bValid
			&& checkRegexp(password,"password_err", /^([0-9a-zA-Z])+$/,
					"Password field only allow : a-z 0-9");
	bValid = bValid
			&& checkRetypePwdlogin(password, pwdagain,"pwdagain_err",
					"Retype Password doesn't match");

	if (bValid) {
		$
				.ajax({
					type : 'post',
					url : 'users/action?save=',				
					onCreate : showLoader('loader', 'Please wait...'),
					data : $(".register").serialize(),
					success : function(html) {
						if (html == 'username') {
							//$('#loader_register').html('Username already exist');
							updateTips('Username already exist',"username_err");
						} else if (html == 'email') {
							//$('#loader_register').html('Email ID already exist');
							updateTips('Email ID already exist',"email_err");
						} else {
							//$('#loader_register').html('Registration Successful');
							updateTips('Registration Successful',"success");							
							allFields.val("").removeClass("ui-state-error");
							allFields_err.val("").removeClass("ui-state-error");
							setTimeout(function(){switchTo('register','login');},4000);
							//switchTo('register','login');
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
}
