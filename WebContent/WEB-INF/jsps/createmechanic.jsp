<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
		<h2>Dodaj nowego mechanika</h2>
		<p>Prosze wypelnij wszystkie pola:</p>
		<sf:form method="post"
			action="${pageContext.request.contextPath}/docreatemechanic"
			commandName="mechanic">
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
			<%-- <input id="id" name="id" type="hidden" value="${mechanic.id}"/> --%>
			<p>
				<button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
			</p>
		</sf:form>
		<p>
			<a class="btn btn-default btn-lg btn-block"
				href="${pageContext.request.contextPath}/welcome">Back</a>
		</p>
	</div>
</body>
</html>