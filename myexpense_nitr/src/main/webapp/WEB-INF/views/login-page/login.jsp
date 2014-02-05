<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>My Budget Management</title>
<c:set var="appContext" value="${pageContext.request.contextPath}"
	scope="application" />
<script type="text/javascript">
var context = '<%=request.getContextPath()%>';
</script>
<script type="text/javascript"
	src="${appContext}/jquery-ui/js/jquery-1.7.1.min.js"></script>
<script src="${appContext}/common-js/users.js" type="text/JavaScript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description"
	content="Expand, contract, animate forms with jQuery wihtout leaving the page" />
<meta name="keywords"
	content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript" />
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${appContext}/login-form/css/style.css" />
<script type="text/javascript" src="${appContext}/common-js/global.js"></script>
<script src="${appContext}/login-form/js/cufon-yui.js"
	type="text/javascript"></script>
<script src="${appContext}/login-form/js/ChunkFive_400.font.js"
	type="text/javascript"></script>
<script type="text/javascript">
	Cufon.replace('h1', {
		textShadow : '1px 1px #fff'
	});
	Cufon.replace('h2', {
		textShadow : '1px 1px #fff'
	});
	Cufon.replace('h3', {
		textShadow : '1px 1px #000'
	});
	Cufon.replace('.back');
</script>
</head>
<body>
	<div class="wrapper">
		<h1>My Budget Management</h1>
		<h2>
			Note: click the <span>orange links</span> to switch to alternate
			options.
		</h2>
		<div class="content">
			<div id="form_wrapper" class="form_wrapper">
				<form class="register">					
					<h3>
						<div style="float:left;">Register</div><div id="joinid-display" style="float: left;color:#ffa800; padding:10px 30px;text-shadow:1px 1px 1px #000; font-size: 12px;"></div><span id="success" class="error"></span>
						<div style="clear: both;"></div>
					</h3>										
					<div class="column">
						<input type="hidden" id="joinedid" name="joinedid"/>
						<div>
							<label for="username">Username:</label> <input type="text"
								id="username" class="uname" placeholder="USERNAME"
								name="username" /> <span id="username_err" class="error"></span>
						</div>
						<div>
							<label>Name:</label> <input type="text" id="name" name="name"
								class="name" placeholder="NAME" /> <span class="error"
								id="name_err"></span>
						</div>
						<div>
							<label>Email:</label> <input type="text" id="email" name="email"
								class="emailid" placeholder="a@.com" /> <span class="error"
								id="email_err"></span>
						</div>						
					</div>
					<div class="column">
						<div>
							<label>Operating Currency Code:</label> <input type="text"
								class="currency" id="currency" name="currency" maxlength="3" onkeypress="return isAlphaKey(event)"
								placeholder="e.g. USD/AUD/EUR" /> <span id="currency_err"
								class="error"></span>
						</div>
						<div>
							<label>Password:</label> <input type="password" id="password"
								class="pwd" placeholder="PASSWORD" name="password" /> <span
								class="error" id="password_err"></span>
						</div>
						<div>
							<label>Password Again:</label> <input type="password"
								class="pwdagn" placeholder="PASSWORD AGAIN" id="pwdagain"
								name="pwdagain" /> <span class="error" id="pwdagain_err"></span>
						</div>						
						<input type="hidden" id="access" name="access" value="1" /> <input
							type="hidden" id="deleted" name="deleted" value="1" /> <input
							type="hidden" id="enabled" name="enabled" value="1" /> <input
							type="hidden" id="colorTheme" name="colorTheme" value="redmond" />
					</div>
					<div class="bottom">
							<button id="register-button">Register</button>
						<a href="#" onclick="switchTo('register','login')" rel="login" class="linkform">You have an account
							already? Log in here</a>
						<div class="clear"></div>
					</div>
				</form>
				<form action="${appContext}/j_spring_security_check" method="post"
					class="login active">
					<h3>Login</h3>
					<div>
						<label for="j_username">Username / Email</label> <input id="j_username"
							tabindex="1" class="username" size="30" name="j_username"
							type="text" placeholder="username / email"
							value="<c:out value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" />" />
						<span class="error" style="visibility: visible;" id="uname_err">
							
							<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">

								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
									<c:if
										test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
										<c:set var="mycount" value="${sessionScope.mycount+1}"
											scope="session" />
										<c:out value="Invalid Username/Password" />
									</c:if>
									<c:if
										test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Captcha invalid!'}">
										<c:out
											value="The text you entered didn't match the security check. Please try again." />
									</c:if>
								</c:if>
							</c:if>
						</span>
					</div>
					<div>
						<label for="j_password">Password: <a href="#"
							rel="forgot_password" class="forgot linkform" onclick="switchTo('login','forgot_password')">Forgot your
								password?</a></label> <input id="j_password" tabindex="2" class="password"
							size="30" name="j_password" type="password"
							placeholder="password" />
					</div>
					<div class="bottom">
						<div class="remember">
							<input type='checkbox' name='_spring_security_remember_me'
								tabindex="4" /><span>Keep me logged in</span>
						</div>
						<input type="submit" value="Login"></input> <a href="#"
							rel="register" class="linkform" onclick="switchTo('login','register')">You don't have an account
							yet? Register here</a>
							<a href="#"
							rel="join" class="linkform" onclick="switchTo('login','join')">Join Account</a>
						<div>
							<c:if test="${mycount ne '' and mycount gt 2}">

								<%
									ReCaptcha c = ReCaptchaFactory.newReCaptcha(
											"6LeQ79ASAAAAANV1QUbGRgCO-9jefjvsN_3LdJ2O",
											"6LeQ79ASAAAAAIijFBBJQ74Ko_lUIVwcy73sYv87", false);
									out.println(c.createRecaptchaHtml(null, null));
								%>

							</c:if>
						</div>
						<div class="clear"></div>
					</div>
				</form>
				<form class="forgot_password" method="post">
					<h3>
						Forgot Password <span id="success_email" class="error"></span>
					</h3>
					<div>
						<label>Email:</label> <input type="text" id="email"
							placeholder="enter your registered email id" name="email" /> <span
							class="error" id="emailid_err"></span>
					</div>
					<div class="bottom">
						<button id="fgt-pass-button">Send</button>
						<a href="#" rel="login" class="linkform" onclick="switchTo('forgot_password','login')">Suddenly remebered?
							Log in here</a> <a href="#" rel="register" class="linkform" onclick="switchTo('forgot_password','register')">You
							don't have an account? Register here</a>
						<div class="clear"></div>
					</div>
				</form>
				
				<form class="join" method="post">
					<h3>
						Join Account <span id="join_success_email" class="error"></span>
					</h3>
					<div>
						<label>Email / Username:</label> <input type="text" size="30" id="usernameOrEmail"
							placeholder="email / username" name="usernameOrEmail" /> <span
							class="error" id="join_emailid_err"></span>
					</div>
					<div>
						<label>Password:</label> <input type="password" size="30" id="joinPassword"
							placeholder="password" name="joinPassword" /> <span
							class="error" id="join_password_err"></span>
					</div>
					<div class="bottom">
						<button id="join-acc-button">Find</button>
						<a href="#" rel="register" class="linkform" onclick="switchTo('join','register')">Register without join account</a>
						<div class="clear"></div>
					</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
		<a style="height: 30px; right: 0px; top: 675px; padding: 10px;"
			class="back" href="#">© My Budget Management 2012, All Rights
			Reserved.</a>
	</div>
</body>
</html>
