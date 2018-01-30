package com.neu.askme.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.neu.askme.exception.VoteException;
import com.neu.askme.pojo.Post;
import com.neu.askme.pojo.PostLikes;
import com.neu.askme.pojo.User;

public class PostLikesDAO extends DAOUtil{

	public PostLikes findUserLikes(Post post, User user) throws VoteException {
		try {
			begin();			
			Criteria crit = getSession().createCriteria(PostLikes.class);
			crit.add(Restrictions.eq("post", post));
			crit.add(Restrictions.eq("user", user));
			PostLikes postsLikes = (PostLikes) crit.uniqueResult();
			return postsLikes;
		} catch (HibernateException e) {
			rollback();
			throw new VoteException("Exception while retreiving likes:" + e.getMessage());
		}
	}
	
	public PostLikes create(PostLikes posLike) throws VoteException {
		try {
			begin();			
			getSession().saveOrUpdate(posLike);
			commit();
			return posLike;
		} catch (HibernateException e) {
			rollback();
			throw new VoteException("Exception while retreiving likes:" + e.getMessage());
		}
	}

	public void removeLike(PostLikes likes) throws VoteException {
		try{
			begin();
			getSession().delete(likes);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new VoteException("Exception while retreiving likes:" + e.getMessage());
		}
	}
}
