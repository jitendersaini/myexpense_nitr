<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="accordion">
	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach items="${list}" var="v" varStatus="status">
				<c:forEach items="${listkeys[v]}" var="v1" varStatus="status1">
					<h3>
						<a class="yr_gtotal${status.index}${status1.index}" href="#"></a>
					</h3>
					<div>
					<table id="list${v1}-${v}"><tr><td/></tr></table> 
					<div id="pager${v1}-${v}"></div>									
								<c:set var="gtotal" value="0" />
								<c:set var="budget" value="0" />
								<c:forEach items="${map[v][v1]}" var="v2" varStatus="status2">									
									<c:set var="gtotal" value="${gtotal+(v2.expenseValue)}" />
									<c:forEach var="vv" items="${budgets}" varStatus="status11">
										<c:if test="${vv.month == v1 and vv.year == v}">																																																																																																																																																																																																																																																																																																																																																										
											<c:set var="budget" value="${vv.value}" />																																																																																																																																																	
										</c:if>
									</c:forEach>

								</c:forEach>
								<script>								
									$(
											".yr_gtotal${status.index}${status1.index}")
											.html(
													'${arr[(v1-1)]} - ${v} (Total Expense Value: ${sessionScope.currency} ${gtotal}) (Monthly Budget for the month: ${sessionScope.currency} ${budget})');
								
									$("#list${v1}-${v}").jqGrid({
										url:'reports/action?month=${v1}&year=${v}&jqgrid=',
										datatype: "json",
										//datatype: "local",
										mtype: 'POST',
										//height: 250, 
									   	colNames:['createdSortDate','modifiedSortDate','expenseSortDate','Expense Title','Expense Value(${sessionScope.currency})','Payment Mode','Payment Type', 'Category','Expense Date','Created By','Modified By','Created Date','Modified Date'],
									   	colModel:[											
											{name:'createdDateSort',index:'createdDateSort', width:150,hidden:true,sorttype:"date"},
											{name:'modifiedDateSort',index:'modifiedDateSort', width:150,hidden:true,sorttype:"date"},
											{name:'expenseDateSort',index:'expenseDateSort', width:150,hidden:true,sorttype:"date"},
									   		{name:'title',index:'title', width:150,summaryType:'count', summaryTpl : '({0}) count'},
									   		{name:'value',index:'value', width:120,align:"right",sorttype:"float", formatter:"number", summaryType:'sum'},
									   		{name:'paidVia',index:'paidVia', width:120},
									   		{name:'paidViaType',index:'paidViaType', width:100},
									   		{name:'category',index:'category', width:150},
									   		{name:'expenseDate',index:'expenseDateSort', align:"center",width:150,sorttype:'date'},
									   		{name:'createdBy',index:'createdBy',width:100,sorttype:"date"},
									   		{name:'modifiedBy',index:'modifiedBy',width:100,sorttype:"date"},
									   		{name:'createdDate',index:'createdDateSort', align:"center",width:100,sorttype:"date"},
									   		{name:'modifiedDate',index:'modifiedDateSort', align:"center",width:100,sorttype:"date"}
									   		
									   	],
									   	rownumbers: true,
									   	rowNum:100000000000000,
									   	rowList:[20,40,60],
									   	pager: 'pager${v1}-${v}',
									   	sortname: 'expenseDateSort',																																																																																
									    viewrecords: true,        
									    sortorder: "desc",
									    caption:"Report Data for month ${arr[v1-1]}, ${v}",
									    emptyrecords: "Empty records",
									    loadonce: true,
									   	height: 'auto',
									    grouping:true,
									   	groupingView : {
									   		groupField : ['${grouping}'],
									   		groupColumnShow : [false],
									   		groupText : ["<b><font color='#8D38C9'>{0}</b></font><font color='#C12283'> Total: ${sessionScope.currency} {value}</font>"],
									   		groupCollapse : true,
											groupOrder: ['asc'],
											groupSummary : [true],
									    	groupDataSorted : true
									   	},									    
									    jsonReader : {
									        root: "rows",
									        page: "page",
									        total: "total",
									        records: "records",
									        repeatitems: false,
									        cell: "cell"
									    }
									});								
									</script>															
					</div>
				</c:forEach>

			</c:forEach>
			<script>
				var icons = {
					header : "ui-icon-circle-arrow-e",
					headerSelected : "ui-icon-circle-arrow-s"
				};
				$("#accordion").accordion({
					icons : icons,
					autoHeight : false,
					navigation : true,
					collapsible : true
				});
			</script>
		</c:when>
		<c:otherwise>
		<table id="list"><tr><td/></tr></table> 
		<div id="pager"></div>			
			<script>
			$("#list").jqGrid({
				url:'reports/action?jqgrid=',
				datatype: "json",
				//datatype: "local",
				mtype: 'POST',
				//height: 250,
			   	colNames:['Expense Title', 'Expense Value(${sessionScope.currency})', 'Payment Mode','Payment Type','Category','Expense Date','Modified By','Created Date','Created Date','Modified Date'],
			   	colModel:[
			   		{name:'title',index:'title', width:150,summaryType:'count', summaryTpl : '({0}) count'},									   											   		
			   		{name:'value',index:'value', width:120,align:"right",sorttype:"float", formatter:"number", summaryType:'sum'},   		
			   		{name:'paidVia',index:'paidVia', width:120},
			   		{name:'paidViaType',index:'paidViaType', width:100},
			   		{name:'category',index:'category', width:150},
			   		{name:'expenseDate',index:'expenseDate', align:"center",width:150},
			   		{name:'createdBy',index:'createdBy', width:100,sorttype:"date"},
			   		{name:'modifiedBy',index:'modifiedBy', width:100,sorttype:"date"},
			   		{name:'createdDate',index:'createdDate', align:"center",width:100},
			   		{name:'modifiedDate',index:'modifiedDate', align:"center",width:100}
			   		
			   	],
			   	rowNum:100000000000000,
			   	rowList:[20,40,60],
			   	pager: '#pager',
			   	sortname: 'expenseDate',
			    viewrecords: true,        
			    sortorder: "asc",
			    caption:"Report Data for current month",
			    emptyrecords: "Empty records",
			    loadonce: true,
			   	height: 'auto',
			    grouping:true,
			   	groupingView : {
			   		groupField : ['category'],
			   		groupColumnShow : [false],
			   		groupText : ["<b><font color='#8D38C9'>{0}</b></font><font color='#C12283'> Total: ${sessionScope.currency} {value}</font>"],
			   		groupCollapse : true,
					groupOrder: ['asc'],
					groupSummary : [true]
			   	},									    
			    jsonReader : {
			        root: "rows",
			        page: "page",
			        total: "total",
			        records: "records",
			        repeatitems: false,
			        cell: "cell"
			    }
			});
			</script>
		</c:otherwise>
	</c:choose>
</div>