<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<div class="container">
	<form name="loginForm" class="form-signin"
		action="<c:url value='j_spring_security_check' />" method='POST'>
		<h2 class="form-signin-heading">Please sign in</h2>
		<p>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="text" name="username" class="form-control" placeholder="Login"
				required autofocus>
		</p>
		<p>
			<label for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="password" class="form-control"
				placeholder="Password" required>
		</p>
		<p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</p>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<h2>Lista wszystkich zlecen</h2>
	<p>Lista wszystkich zatrudnionych mechanikow:</p>
	<table class="table table-condensed">
		<tr>
			<td>Name</td>
			<td>Email</td>
			<td>Offer</td>
			<td>Mechanic</td>
		</tr>
		<c:forEach var="offer" items="${offers}">
			<tr class="success">
				<td><c:out value="${offer.name}"></c:out></td>
				<td><c:out value="${offer.email}"></c:out></td>
				<td><c:out value="${offer.text}"></c:out></td>
				<td><c:out value="${offer.mechanic.name}"></c:out>
			</tr>
		</c:forEach>
	</table>

	<h2>Lista wszystkich mechanikow</h2>
	<p>Lista wszystkich zatrudnionych mechanikow:</p>
	<table class="table table-condensed">
		<tr>
			<td>Name</td>
			<td>Email</td>
		</tr>
		<c:forEach var="mechanic" items="${mechanics}">
			<tr class="success">
				<td><c:out value="${mechanic.name}"></c:out></td>
				<td><c:out value="${mechanic.email}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
