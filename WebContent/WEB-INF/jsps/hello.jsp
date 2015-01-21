<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>Welcome : ${pageContext.request.userPrincipal.name}</h2>
			<p>You can choose one of the following options</p>
		</c:if>
		<p>
			<a class="btn btn-primary btn-lg btn-block"
				href="${pageContext.request.contextPath}/offers">Show current
				offers.</a>
		</p>
		<p>
			<a class="btn btn-default btn-lg btn-block"
				href="${pageContext.request.contextPath}/createoffer">Add new
				offer.</a>
		</p>
		<p>
			<a class="btn btn-primary btn-lg btn-block"
				href="${pageContext.request.contextPath}/mechanics">Show
				mechanics offers.</a>
		</p>
		<p>
			<a class="btn btn-default btn-lg btn-block"
				href="${pageContext.request.contextPath}/createmechanic">Add new
				mechanic.</a>
		</p>
		<p>
			<a class="btn btn btn-danger btn-lg btn-block"
				href="javascript:formSubmit()">Logout</a>
		</p>
	</div>
</body>
</html>