<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create/Edit Post Here</title>
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
				<c:if test="${not empty user}">
    				Logged as <c:out value="${user.firstName} ${user.lastName}"></c:out>
					<div>
						<a href="logout">Logout</a>
					</div>
					<div>
						<a href="create" class="btn btn-primary"> <span
							class="glyphicon glyphicon-pencil"></span> Create post
						</a>
					</div>
				</c:if>
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				<div class="col-sm-8 no-padding-col">
					<c:if test="${edit}">
						<h2>Edit Post</h2>
					</c:if>
					<c:if test="${ not edit }">
						<h2>Create Post</h2>
					</c:if>

					<form:form action="create" commandName="post" method="post"
						id="postForm">

						<div class="form-group">
							<label for="title">Title:</label>
							<form:input path="title" class="form-control" size="30" required="required" />
							<font color="red"><form:errors path="title" /></font>
						</div>

						<div>
							<label for="fullPost">Description:</label>
							<form:textarea path ="fullPost" name="fullPost" class="form-control" required="required"></form:textarea>
							<font color="red"><form:errors path="fullPost" /></font>
						</div>

						<div class="form-group">
							<label for="tags">Tags:</label> <form:input path="tag" type="text"
								class="form-control" name="tags" required="required"></form:input>
						</div>

						<button type="submit" class="btn btn-default">Save</button>

					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>