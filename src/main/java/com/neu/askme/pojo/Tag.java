package com.neu.askme.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue
	@Column(name = "tagID")
	private Long tagID;
	
	@Column(name="name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tag")
    private Collection<Post> posts = new ArrayList<>();
    
    public Tag(){
    	
    }
    
    public Tag(String name) {
        this.name = name;
    }

	public Long getTagID() {
		return tagID;
	}

	public void setTagID(Long tagID) {
		this.tagID = tagID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Post> getPosts() {
		return posts;
	}

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}
}
