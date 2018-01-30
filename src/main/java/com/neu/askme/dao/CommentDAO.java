package com.neu.askme.dao;

import org.hibernate.HibernateException;

import com.neu.askme.exception.CommentException;
import com.neu.askme.pojo.Comment;

public class CommentDAO extends DAOUtil{
	public Long addComment(Comment comment) throws CommentException {

		try {
			begin();
			getSession().saveOrUpdate(comment);
			commit();
			return comment.getCommentID();
		} catch (HibernateException e) {
			throw new CommentException("Exception while adding comment: " + e.getMessage());
		}
	}
}
