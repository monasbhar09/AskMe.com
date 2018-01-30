package com.neu.askme.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "commentID")
	private Long commentID;
	
	@Column(name = "commentText", nullable = false)
	private String commentText;
	
	@Column(name = "timestamp", nullable = false)
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postID", nullable = false)
    private Post post;
	
	@Column(nullable = false)
    private boolean deleted = false;

	public Long getCommentID() {
		return commentID;
	}

	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
