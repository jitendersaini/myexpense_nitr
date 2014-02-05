<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
 <script type="text/javascript">    
		$(document).ready(function()
    		{

    		$(".account").click(function()
    		{
    		var X=$(this).attr('id');
    		if(X==1)
    		{
    		$(".submenu").hide();
    		$(this).attr('id', '0'); 
    		}
    		else
    		{
    		$(".submenu").show();
    		$(this).attr('id', '1');
    		}

    		});

    		//Mouse click on sub menu
    		$(".submenu").mouseup(function()
    		{
    		return false
    		});

    		//Mouse click on my account link
    		$(".account").mouseup(function()
    		{
    		return false
    		});


    		//Document Click
    		$(document).mouseup(function()
    		{
    		$(".submenu").hide();
    		$(".account").attr('id', '');
    		});
    		});
    </script>
    	<c:if test="${not empty sessionScope.joinedName}">
			<label style="float: left;color: #f6931f; font-weight: bold;font-size: small;  height: 1px;">Joined Account with:&nbsp;&nbsp;</label>
		</c:if>
		<div style="float: left;font-size: small; height: 0.1px;">
			<c:if test="${not empty sessionScope.joinedName}">
				 ${sessionScope.joinedName}
			</c:if>
		</div>
		<div class="dropdown" style="float: right;  height: 0.1px;">
			<a class="account">${sessionScope.name}</a>
			<div class="submenu">
				<ul class="root">
					<li><a href="javascript:changePassword();">Change Password</a></li>
					<li><a href="javascript:editAccount();">Change Settings</a></li>
					<li><a href="<c:url value="/login/logout" />">Logout</a></li>
				</ul>
			</div>
		</div>
		<div style="clear: both;"></div>