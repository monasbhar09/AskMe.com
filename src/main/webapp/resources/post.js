/**
 * 
 */


$(document).on('click', '.thumbsup', function(event) {
	event.preventDefault();
	var postId = $(this).parent().attr("id");
	$.ajax({
		type : 'POST',
		url : 'post/vote/like',
		data : ({
			postId : postId
		}),
		success : function(result) {
			console.debug(result);
			$('#rating'+postId).html(result);
		},
		error : function(req, status, err) {
			console.log('Something went wrong', status, err);
		}

	});
});

$(document)
		.on(
				'click',
				'.commentBtn',
				function(event) {
					event.preventDefault();
					var comment = $(this).prev().val();
					var postId = $(this).prev().prev().val();

					$
							.ajax({
								type : 'POST',
								url : 'comment/add',
								data : ({
									comment : comment,
									postId : postId
								}),
								success : function(result) {
									if(result === 'error'){
										$('#comment'+postId).val('');
									}
									else {
										var myArr = jQuery.parseJSON(result);
										console.debug(myArr.commentId);
										var htmlContent = '<div class="row">'
												+ ' <div class="col-sm-1 text-center">'
												+ '<span class="glyphicon glyphicon-user" class="font-size: 50px;"></span>'
												+ ' </div>'
												+ '<div class="col-sm-10">'
												+ '<h4>' + myArr.user+' ' + '<small>'
												+   myArr.timestamp + '</small>'
												+ '</h4>' + '<p>'
												+ 	myArr.commentText + '</p>'
												+ '</div>' + '</div>';

										$('#commentList' + postId).append(
												htmlContent);
										$('#comment'+postId).val('');
									}	
								},
								error : function(req, status, err) {
									console.log('Something went wrong', status,
											err);
								}

							});
				});