<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="EDIT PAYMENT-MODE">
        <p class="validateTips">Payment Mode is required.</p>
        <form:form id="frm" name="frm" modelAttribute="paymode" method="post">
                <fieldset>
                        <form:label for="paymentMode" path="paymentMode">Payment Mode</form:label>
                        <form:input path="paymentMode"
                                class="text ui-widget-content ui-corner-all" />         
                        <form:label for="paymentModeType" path="paymentModeType">Payment Mode Type</form:label>
                        <br>
                        <c:choose>
                                <c:when test="${isPayModeMapped}">
                                        <form:select path="paymentModeType" id="paymodeType" disabled="true" items="${map}" class="selectbox ui-widget-content"></form:select> <font color="red"> (Payment Mode is used in transactions)</font>
                                        <form:hidden path="paymentModeType"/>
                                </c:when>
                                <c:otherwise>
                                        <form:select path="paymentModeType" items="${map}" class="selectbox ui-widget-content"></form:select>
                                </c:otherwise>  
                        </c:choose>
                                                
                        <c:if test="${empty paymode.id }">                             
                                <form:hidden path="userid" value="${sessionScope.login_id}" />
                                <form:hidden path="users.id" value="${sessionScope.login_id}" />
                                <form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
                        </c:if>
                        <c:if test="${not empty paymode.id }">
                                <form:hidden path="userid" />
                                <form:hidden path="users.id"/>                                          
                                <form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
                                                                
                                <form:hidden path="id" />
                        </c:if>
                        <c:if test="${paymode.createdDate != null}">
                        <fmt:formatDate value='${paymode.createdDate}' type='both' var="dt"/>                          
                                <form:hidden path="createdDate" value="${dt}"/>
                        </c:if>                 
                </fieldset>
        </form:form>
</div>