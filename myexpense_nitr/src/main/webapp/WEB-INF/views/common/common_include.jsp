<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="appContext" value="${pageContext.request.contextPath}"
	scope="application" />
<script type="text/javascript">
	var context = "<%=request.getContextPath()%>";
</script>
<c:set var="colorTheme" value="redmond" scope="application" />
<c:if test="${sessionScope.colortheme ne null}">
	<c:set var="colorTheme" value="${sessionScope.colortheme}"
		scope="application" />
</c:if>
<script type="text/javascript"
	src="${appContext}/jquery-ui/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${appContext}/jquery-ui/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript"
	src="${appContext}/jquery-ui/js/fg.menu.js"></script>
<script type="text/javascript"
	src="${appContext}/jquery-ui/js/ui.selectmenu.js"></script>
<script type="text/javascript"
	src="${appContext}/jquery-table/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${appContext}/common-js/global.js"></script>

<script src="${appContext}/jq-grid/js/i18n/grid.locale-en.js"
	type="text/javascript"></script>
<script src="${appContext}/jq-grid/js/jquery.jqGrid.min.js"
	type="text/javascript"></script>

<!-- jQuery table css -->
<link type="text/css"
	href="${appContext}/jquery-ui/css/${colorTheme}/jquery-ui-1.8.19.custom.css"
	rel="stylesheet" />
<link type="text/css" href="${appContext}/jquery-ui/css/fg.menu.css"
	rel="stylesheet" />
<link type="text/css" href="${appContext}/common-css/custom.css"
	rel="stylesheet" />
<link type="text/css"
	href="${appContext}/jquery-ui/css/ui.selectmenu.css" rel="stylesheet" />
<link rel="STYLESHEET" type="text/css"
	href="${appContext}/jquery-table/css/demo_page.css" media="screen" />
<link rel="STYLESHEET" type="text/css"
	href="${appContext}/jquery-table/css/demo_table_jui.css" media="screen" />
<!-- jQuery table css -->
<!-- jq-grid css -->
<link rel="STYLESHEET" type="text/css"
	href="${appContext}/jq-grid/css/ui.jqgrid.css" media="screen" />
<!-- jq-grid css -->
<div id="dialog-mesg-common" title="Message" style="display: none;"></div>