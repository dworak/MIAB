<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Lista wszystkich zlecen</h2>
		<p>Lista wszystkich zatrudnionych mechanikow:</p>
		<table class="table table-condensed">
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Offer</td>
				<td>Mechanic</td>
				<td>Created on</td>
				<td>Usunac z bazy?</td>
				<td>Edytowac?</td>
			</tr>
			<c:forEach var="offer" items="${offers}">
				<tr class="success">
					<td><c:out value="${offer.name}"></c:out></td>
					<td><c:out value="${offer.email}"></c:out></td>
					<td><c:out value="${offer.text}"></c:out></td>
					<td><c:out value="${offer.mechanic.name}"></c:out>
					<td><c:out value="${offer.createDate}"></c:out><td><a
						href="${pageContext.request.contextPath}/dodelete?id=${offer.id}">Usun</a>
					</td><td><a
						href="${pageContext.request.contextPath}/doedit?id=${offer.id}">Edytuj</a></td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<a class="btn btn-default btn-lg btn-block"
				href="${pageContext.request.contextPath}/welcome">Back</a>
		</p>
	</div>
</body>
</html>