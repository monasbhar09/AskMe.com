package com.neu.askme.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue
	@Column(name = "postID")
	private Long postID;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "fullPost", nullable = false)
	private String fullPost;

	@Column(name = "timestamp", nullable = false)
	private Timestamp timestamp;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL })
	@JoinTable(name = "post_to_tag", joinColumns = @JoinColumn(name = "postID", referencedColumnName = "postID"), inverseJoinColumns = @JoinColumn(name = "tagID", referencedColumnName = "tagID"))
	@OrderBy("name ASC")
	private Collection<Tag> tag = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.EXTRA)
	@OrderBy("timestamp ASC")
	private List<Comment> commentList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "post")
	private List<PostLikes> postLikes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPostID() {
		return postID;
	}

	public List<PostLikes> getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(List<PostLikes> postLikes) {
		this.postLikes = postLikes;
	}

	public void setPostID(Long postID) {
		this.postID = postID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullPost() {
		return fullPost;
	}

	public void setFullPost(String fullPost) {
		this.fullPost = fullPost;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Collection<Tag> getTag() {
		return tag;
	}

	public void setTag(Collection<Tag> tag) {
		this.tag = tag;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public int getLikesSum() {
        return postLikes.stream().mapToInt(Likes::getValue).sum();
    }

	public short getUserVoteValue(Long userId) {
        if (userId == null)
            return 0;

        Optional<PostLikes> likes = postLikes.stream().filter(r -> r.getUser().getUserID() == userId).findFirst();
        return likes.isPresent() ? likes.get().getValue() : 0;
    }
}
