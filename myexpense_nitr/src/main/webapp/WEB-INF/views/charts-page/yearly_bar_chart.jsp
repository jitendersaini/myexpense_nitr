<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<div style="height: 400px">
	<div id="wrapper">
			<div class="chart">
				<h2>Expense List for ${year}</h2>
				<table id="data-table" border="1">
					<caption>Expenses in ${sessionScope.currency} </caption>
					<thead>
						<tr>
							<td>&nbsp;</td>
							<th scope="col">January</th>
							<th scope="col">February</th>
							<th scope="col">March</th>
							<th scope="col">April</th>
							<th scope="col">May</th>
							<th scope="col">June</th>
							<th scope="col">July</th>
							<th scope="col">August</th>
							<th scope="col">September</th>
							<th scope="col">October</th>
							<th scope="col">November</th>
							<th scope="col">December</th>
						</tr>
					</thead>
					<tbody>						
						<tr>
							<th scope="row">Monthly Expense</th>
							<c:forEach items="${MONTHS_ARRAY}" var="v" varStatus="status">
							 <c:choose>
								<c:when test="${map[status.index] ne null}">
									<td>${map[status.index]}</td>
								</c:when>
								<c:otherwise>
									<td>0</td>
								</c:otherwise>
							</c:choose>	
							</c:forEach>							
						</tr> 
						<tr>
							<th scope="row">Monthly Budget</th>
							<c:forEach items="${MONTHS_ARRAY}" var="v" varStatus="status">							 								 	
							 	<c:choose>
							 		<c:when test="${budget[status.count] ne null}">
										<td>${budget[status.count]}</td>
								   </c:when>
									<c:otherwise>
										<td>0</td>
									</c:otherwise>
							 </c:choose>
							 
							 </c:forEach>							
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		</div>
		<script>
		// Create our graph from the data table and specify a container to put the graph in
		
		$(document).ready(function() {
			$("#wrapper").css({"width":"1200px"});
			$("#wrapper").css({"left":"300px"});
			createGraph('#data-table', '.chart');
			$(".x-axis").css({"width":"1200"});
			$(".legend").css({"width":"1200"});
			$(".legend").css({"width":"1200"});
			$(".bar.fig0").css({"left":"13px"});
			$(".bar.fig1").css({"left":"37px"});
			$(".bar.fig2").css({"left":"63px"});
			$(".bar").css({"width":"24px"});
			$(".bar-group").css({"width":"57px"});
			$(".x-axis li").css({"width":"57px"});		
		});
		
</script>
  