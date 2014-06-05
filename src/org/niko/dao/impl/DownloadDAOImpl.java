package org.niko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.niko.utils.Pager;

import test.bean.HibernateSessionFactory;
import test.bean.User;

public class DownloadDAOImpl {
	
	UserDAOImpl userDAO = new UserDAOImpl();
	@SuppressWarnings("rawtypes")
	public Pager list(int pageNo,int pageSize, String username) {
		int rowCount = 0;
		List list = null;
		org.niko.utils.Pager pager = null;
		Session session = null;
		Transaction tx = null;
		try{
			
			User user = userDAO.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Query q1 = session.createQuery("select count(*) from File f where f.fileid in (" +
					"select d.file.fileid from Download d where d.user.userid=:userid)");
			q1.setString("userid", String.valueOf(user.getUserid()));
			rowCount = ((Long)q1.uniqueResult()).intValue();
			System.out.println("rowCount:"+rowCount); 
			
			Query q2 = session.createQuery("select f, d from File f, Download d " +
					"where f.fileid = d.file.fileid and d.user.userid=:userid");
			q2.setString("userid", String.valueOf(user.getUserid()));
			q2.setFirstResult(pageSize*(pageNo-1));
			q2.setMaxResults(pageSize);
			list = q2.list();
			pager = new Pager(pageNo,pageSize,rowCount,list);
			tx.commit();
			q1=null;
			q2=null;
		} catch (HibernateException e){
			pager = new org.niko.utils.Pager(pageNo, pageSize, 0, new ArrayList<Object>());
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return pager;
	}
}
