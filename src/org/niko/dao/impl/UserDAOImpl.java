package org.niko.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import test.bean.HibernateSessionFactory;
import test.bean.User;


public class UserDAOImpl {
	public test.bean.User getUserByName(String username){
		Session session = null;
		User user = null;
		try {
			session = HibernateSessionFactory.getSession();
//			session = hibernateTemplate.getSessionFactory().getCurrentSession();
			Query query = session.createQuery("from User where username=?");
			query.setString(0, username.trim());
			user = (User) query.uniqueResult();
			query = null;
		} catch (HibernateException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return user;
	}
}
