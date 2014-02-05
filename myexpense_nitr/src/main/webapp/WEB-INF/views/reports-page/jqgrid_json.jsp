<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
$(function(request, response){
	response('${arr}');
});
	
	/* function loadAutoComplete(id, actionName) {
		var cache = {}, lastXhr, valid = false;
		jQuery("#" + id).autocomplete({
			minLength : 2,
			source : function(request, response) {
				var term = request.term;
				valid = false;
				if (term in cache) {
					response(cache[term]);
					return;
				}

				lastXhr = jQuery.getJSON('${appContext}/' + actionName, {
					queryString : term
				}, function(data, status, xhr) {
					cache[term] = data;
					if (xhr === lastXhr) {
						response(data);
					}
				});
			},
			select : function(event, ui) {
				valid = true;
			},
			change : function(event, ui) {				
				if (!valid) {
					// remove invalid value, as it didn't match anything
					jQuery(this).val("");
					input.data("autocomplete").term = "";
					return false;
				}

			}
		});

	} */
</script>