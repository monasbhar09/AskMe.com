<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<c:if test="${empty user}">
		<form action="login" method="post">
			<div>
				<label class="error" id="loginError"></label>
			</div>
			<div class="form-group">
				<label for="username">User Name or E-mail:</label> <input
					type="text" class="form-control" name="username" id="username" required="required"/>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" name="password" id="password" required="required"/>
			</div>
			<button type="submit" class="btn btn-default" id="loginbtn">Log
				in</button>
		</form>
		<p class="lead reg-link">
			<a href="register">or Register</a>
		</p>
	</c:if>
	<c:if test="${not empty user}">
    	Logged as <c:out value="${user.firstName} ${user.lastName}"></c:out>

		<div>
			<a href="logout">Logout</a>
		</div>
		<div>
			<a href="create" class="btn btn-primary">
				<span class="glyphicon glyphicon-pencil"></span> Create post
			</a>
		</div>
	</c:if>
</body>
</html>