<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>Utworz nowe zlecenie naprawy</h2>
		<div class="alert alert-success"">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Uwaga!</strong>
			Prosze wypelnij wszystkie pola:
		</div>
		<sf:form method="post"
			action="${pageContext.request.contextPath}/docreate"
			commandName="offer">
			<div class="form-group">
				<label for="name">Name:</label>
				<sf:input class="form-control" id="name" path="name" name="name"
					type="text" />
				<sf:errors path="name" cssClass="error"></sf:errors>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<sf:input class="form-control" id="email" path="email" name="email"
					type="text" />
				<sf:errors path="email" cssClass="error"></sf:errors>
			</div>
			<div class="form-group">
				<label for="datepicker">Due date:</label>
				<div id="datepicker"
					class="controls input-append date form_datetime"
					data-date-format="dd MM yyyy - HH:ii p" data-link-field="dueDate">
					<sf:input class="form-control" path="dueDateString" type="text"
						value="" />
					<span class="add-on"><i class="icon-remove"></i></span> <span
						class="add-on"><i class="icon-th"></i></span>
				</div>
			</div>
			<div class="form-group">
				<label for="text">Text:</label>
				<sf:textarea class="form-control" id="text" path="text" name="text"
					rows="10" cols="10"></sf:textarea>
				<sf:errors path="text" cssClass="error"></sf:errors>
			</div>
			<div class="form-group">
				<label for="mechanicId">Mechanic:</label>
				<sf:select class="form-control" path="mechanicId" id="mechanicId"
					name="mechanicId">
					<c:forEach items="${mechanics}" var="s">
						<sf:option value="${s.id}" label="${s.name}" />
					</c:forEach>
				</sf:select>
			</div>
			<input id="id" name="id" type="hidden" value="${offer.id}"/>
			<p>
				<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
			</p>
		</sf:form>
		<p>
			<a class="btn btn-default btn-lg btn-block"
				href="${pageContext.request.contextPath}/welcome">Back</a>
		</p>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/jquery/jquery-1.8.3.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/locales/bootstrap-datetimepicker.fr.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			//language:  'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		$('.form_date').datetimepicker({
			language : 'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		$('.form_time').datetimepicker({
			language : 'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});
	</script>
</body>
</html>