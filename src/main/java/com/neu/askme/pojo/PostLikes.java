package com.neu.askme.pojo;

import javax.persistence.*;

@Entity
@Table(name = "post_to_like", uniqueConstraints = @UniqueConstraint(columnNames = { "postID", "userID" }))
public class PostLikes extends Likes {

	@Id
	@GeneratedValue
	private Long postRatingID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postID", nullable = false)
	private Post post;

	public PostLikes(User user, short value, Post post) {
		super(user, value);
		this.post = post;
	}

	public PostLikes() {
		super();
	}

	public Long getPostRatingID() {
		return postRatingID;
	}

	public void setPostRatingID(Long postRatingID) {
		this.postRatingID = postRatingID;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
