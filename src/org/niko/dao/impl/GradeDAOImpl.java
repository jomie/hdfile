package org.niko.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import test.bean.File;
import test.bean.Grade;
import test.bean.HibernateSessionFactory;
import test.bean.User;

public class GradeDAOImpl {
	
	public boolean isExist(int fileid, String username){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Long count = (Long) session.createQuery("select count(gradeid) from Grade g where g.file.fileid=:fileid and g.user.username=:username")
			.setString("fileid", String.valueOf(fileid))
			.setString("username", username)
			.uniqueResult();
			System.out.println(count);
			if(count.compareTo(new Long(1)) >= 0){
				return true;
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return false;
	}
	
	public boolean SubmitGrade(short grade, String username, int fileid) {
		boolean b=false;
		Session session = null;
		Transaction tx = null;
		try{
			UserDAOImpl userDAOImpl = new UserDAOImpl();  
			User user = userDAOImpl.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			File file = (File)session.load(File.class, Integer.valueOf(fileid));
			Grade g = new Grade();
			g.setGrade(grade);
			g.setUser(user);
			g.setFile(file);
			session.save(g);
			tx.commit();
			b = true;
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return b;
	}

	public short UpdateAvgGrade(int fileid) {
			Session session = null;
			Transaction tx = null;
			try{				
				session = HibernateSessionFactory.getSession();
				tx = session.beginTransaction();
				File file = (File)session.load(File.class, Integer.valueOf(fileid));
				Query query = session.createQuery("select avg(g.grade) from Grade g where g.file.fileid=?");
				query.setString(0, String.valueOf(fileid));
				short newGrade = ((Double)query.uniqueResult()).shortValue();
				file.setGrade(newGrade);
				session.saveOrUpdate(file);
				tx.commit();
				return newGrade;
			}catch(HibernateException e){
				if(tx != null){
					tx.rollback();
				}
				throw e;
			}finally{
				HibernateSessionFactory.closeSession();
			}		
	}
}
