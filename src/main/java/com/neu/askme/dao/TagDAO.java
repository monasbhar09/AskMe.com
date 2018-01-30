package com.neu.askme.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.askme.exception.TagException;
import com.neu.askme.pojo.Tag;

public class TagDAO extends DAOUtil{

	public Tag findByNameIgnoreCase(String tagname) throws TagException{
		try{
			begin();
			Query q = getSession().createQuery("from Tag where name = :name");
			q.setString("name", tagname);
			Tag tag = (Tag) q.uniqueResult();
			return tag;
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
