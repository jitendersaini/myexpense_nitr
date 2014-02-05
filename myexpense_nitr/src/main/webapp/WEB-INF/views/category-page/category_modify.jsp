<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="dialog-form" title="EDIT CATEGORY">
<!-- <div class="dialog-form" title="Create new category"> -->
        <p class="validateTips">Category field is required.</p>
        <form:form id="frm" name="frm" modelAttribute="category" method="post">
                <fieldset>
                        <form:label for="categoryName" path="categoryName">Category name</form:label>
                        <form:input path="categoryName"
                                class="text ui-widget-content ui-corner-all" />         
                        <form:label for="categoryType" path="categoryType">Category Type</form:label>
                        <br>
                        <c:choose>
                                <c:when test="${isCategoryMapped}">
                                        <form:select path="categoryType" id="catType" disabled="true" items="${map}" class="selectbox ui-widget-content"></form:select> <font color="red"> (Category is used in transactions)</font>
                                        <form:hidden path="categoryType"/>
                                </c:when>
                                <c:otherwise>
                                        <form:select path="categoryType" items="${map}" class="selectbox ui-widget-content"></form:select>
                                </c:otherwise>  
                        </c:choose>
                                                
                        <c:if test="${empty category.id }">                             
                                <form:hidden path="userid" value="${sessionScope.login_id}" />
                                <form:hidden path="users.id" value="${sessionScope.login_id}" />
                                <form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
                        </c:if>
                        <c:if test="${not empty category.id }">
                                <form:hidden path="userid" />
                                <form:hidden path="users.id"/>                                          
                                <form:hidden path="usersModified.id" value="${sessionScope.login_id}" />
                                                                
                                <form:hidden path="id" />
                        </c:if>
                        <c:if test="${category.createdDate != null}">
                        <fmt:formatDate value='${category.createdDate}' type='both' var="dt"/>                          
                                <form:hidden path="createdDate" value="${dt}"/>
                        </c:if>                 
                </fieldset>
        </form:form>
</div>