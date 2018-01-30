package com.neu.askme.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.neu.askme.exception.UserException;
import com.neu.askme.pojo.User;

public class UserDAO extends DAOUtil {

	public UserDAO() {
	}

	public User register(User user) throws UserException {
		try {
			begin();
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public User get(String username, String password) throws UserException {
		try {

			begin();
			Criteria crit = getSession().createCriteria(User.class);
			Criterion usrnm = Restrictions.eq("username", username);
			Criterion em = Restrictions.eq("email", username);
			LogicalExpression logex = Restrictions.or(usrnm, em);
			crit.add(logex);
			crit.add(Restrictions.eq("password", password));
			User user = (User) crit.uniqueResult();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while retreiving user" + e.getMessage());
		}
	}

	public boolean usernameExists(String username) throws UserException {
		try{
			begin();
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("username", username));
			User user = (User) crit.uniqueResult();
			if(user == null)
				return false;
			return true;
		}catch(HibernateException e){
			rollback();
			throw new UserException("Exception while retreiving user" + e.getMessage());
		}
	}

	public boolean emailExists(String email) throws UserException {
		try{
			begin();
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("email", email));
			User user = (User) crit.uniqueResult();
			if(user == null)
				return false;
			return true;
		}catch(HibernateException e){
			rollback();
			throw new UserException("Exception while retreiving user" + e.getMessage());
		}
	}
}
