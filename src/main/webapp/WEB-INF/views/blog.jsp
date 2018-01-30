<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- jQuery -->
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="<c:url value="/resources/post.js" />"></script>
<style type="text/css">
.glyphicon-user {
	font-size: 45px;
	color: grey;
}
</style>
<title>Blog</title>
</head>
<body>
	<c:forEach var="post" items="${postList}">
		<div id="${post.postID}">
			<span>Posts tagged as</span>
			<c:forEach var="tag" items="${post.tag}">
				<span class="label label-primary">${tag.name}</span>
			</c:forEach>
			<h2>${post.title}</h2>
			<p>${post.fullPost}</p>
			<small style="color: #999"> <span
				class="glyphicon glyphicon-time"></span> Posted by ${post.user} at ${post.timestamp}
			</small>
			<hr>
			<c:if test="${empty user}">
				<p>
					Upvote <span class="glyphicon glyphicon-thumbs-up"></span> <span
						id="rating${post.postID}" class="badge">${post.getLikesSum()}</span>
				</p>
			</c:if>
			<c:if test="${not empty user}">
				<a href="" class="thumbsup">Upvote <span
					class="glyphicon glyphicon-thumbs-up"></span></a>
				<span id="rating${post.postID}" class="badge">${post.getLikesSum()}</span>
			</c:if>
			<div>
			<c:if test="${empty user}"><p>Login to view replies</p></c:if>
				<c:if test="${not empty user}">
					<p>Answers:</p>

					<div id="commentList${post.postID}">
						<c:forEach var="comment" items="${post.commentList}">
							<div class="row">
								<div class="col-sm-1 text-center">
									<span class="glyphicon glyphicon-user" class="font-size: 50px;"></span>
								</div>
								<div class="col-sm-10">
									<h4>${comment.user}
										<small>${comment.timestamp}</small>
									</h4>
									<p>${comment.commentText}</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<div>
						<form class="form">
							<div class="form-group">
								<input type="hidden" value="${post.postID}" />
								<textarea class="form-control" rows="2"
									id="comment${post.postID}" placeholder="Leave an answer"></textarea>
								<a href="" class="commentBtn btn btn-primary"
									style="margin-top: 10px;"><span
									class="glyphicon glyphicon-pencil"></span>Answer</a>

								<c:if test="${post.user.userID == user.userID}">
									<a href="post/delete/${post.postID}" style="margin-top: 10px;"
										class="btn btn-secondary"><span
										class="glyphicon glyphicon-remove"></span> Delete this post</a>
								</c:if>

							</div>
						</form>
					</div>
				</c:if>
			</div>
		</div>
		<hr>
	</c:forEach>
</body>
</html>