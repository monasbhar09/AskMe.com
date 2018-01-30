<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Register User</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-3">
				<header class="page-header">
				<div class="col-padding">
					<h1>Ask Me</h1>
					<h4>
						<small>RECENT POSTS</small>
					</h4>
				</div>
				</header>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 sidenav">
				<form action="login" method="post">
					<div>
						<label class="error" id="loginError"></label>
					</div>
					<div class="form-group">
						<label for="username">User Name or E-mail:</label> <input
							type="text" class="form-control" name="username" id="username"
							required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" name="password" id="password"
							required="required" />
					</div>
					<button type="submit" class="btn btn-default" id="loginbtn">Log
						in</button>
				</form>
				<p class="lead reg-link">
					<a href="register">or Register</a>
				</p>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				<div class="col-sm-4 no-padding-col">
					<h2>Registration</h2>

					<form:form action="register" commandName="user" method="post"
						class="form-group">

						<div class="form-group">
							<label for="firstName">First Name:</label>
							<form:input path="firstName" size="30" required="required"
								class="form-control" pattern="[a-zA-Z]{3,30}" />
							<font color="red"><form:errors path="firstName" /></font>
						</div>

						<div class="form-group">
							<label for="lastName">Last Name:</label>
							<form:input path="lastName" size="30" required="required"
								class="form-control" />
							<font color="red"><form:errors path="lastName" /></font>
						</div>

						<div class="form-group">
							<label for="sex">Sex:</label>
							<form:radiobutton path="sex" value="male" checked="true" />
							Male
							<form:radiobutton path="sex" value="female" />
							Female <font color="red"><form:errors path="sex" /></font>
						</div>
						<div class="form-group">
							<label for="username">User Name:</label>
							<form:input path="username" size="30" required="required"
								class="form-control"
								pattern="^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+$" />
							<font color="red"><form:errors path="username" /></font>
						</div>

						<div class="form-group">
							<label for="email">Email Id:</label>
							<form:input path="email" size="30" type="email"
								required="required" class="form-control" />
							<font color="red"><form:errors path="email" /></font>
						</div>

						<div class="form-group">
							<label for="password">Password:</label>
							<form:password path="password" size="30" required="required"
								class="form-control" />
							<font color="red"><form:errors path="password" /></font>
						</div>

						<div class="form-group">
							<input type="submit" value="Register User"
								class="btn btn-primary" />
						</div>

					</form:form>
				</div>
			</div>
		</div>

	</div>
</body>
<footer class="page-footer">
	<p>&copy; 2017 Monas Bhar</p>
</footer>
</html>