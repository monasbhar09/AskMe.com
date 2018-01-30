<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Ask Me</title>
<style type="text/css">
.sidenav {
	background-color: #f1f1f1;
	height: 500px;
	border-radius: 10px;
	padding: 20px;
}
</style>
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
				<jsp:include page="login.jsp" />
			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-8">
				<jsp:include page="blog.jsp" />
			</div>
		</div>
	</div>
</body>


<footer class="page-footer">
	<p>&copy; 2017 Monas Bhar</p>
</footer>
</html>