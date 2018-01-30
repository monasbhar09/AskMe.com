package com.neu.askme.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import com.neu.askme.exception.PostException;
import com.neu.askme.pojo.Post;

public class PostDAO extends DAOUtil {

	public PostDAO() {
	}

	public List<Post> getPostList() throws PostException {
		try {
			begin();			
			Criteria crit = getSession().createCriteria(Post.class);
			crit.addOrder(Order.desc("timestamp"));
			List<Post> posts = crit.list();
			return posts;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while retreiving posts:" + e.getMessage());
		}
	}

	public Post createPost(Post post) throws PostException {
		try {
			begin();
			getSession().saveOrUpdate(post);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while Adding post:" + e.getMessage());
		}
		return post;
	}

	public Post getPostById(Long postID) throws PostException {
		try {
			begin();
			Post post = getSession().get(Post.class, postID);
			return post;
		} catch (HibernateException e) {
			throw new PostException("Exception while retrieving post: " + e.getMessage());
		}
	}

	public void removePost(Post post) throws PostException {
		try {
			begin();
			getSession().delete(post);
			commit();
		} catch (HibernateException e) {
			throw new PostException("Exception while retrieving post: " + e.getMessage());
		}
	}
}
