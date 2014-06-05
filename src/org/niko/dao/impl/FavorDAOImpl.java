package org.niko.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.niko.utils.Pager;

import test.bean.FavorUserFile;
import test.bean.File;
import test.bean.FileTag;
import test.bean.HibernateSessionFactory;
import test.bean.Tags;
import test.bean.Upload;
import test.bean.User;

public class FavorDAOImpl {
	Logger logger = Logger.getLogger("HDFile");
	UserDAOImpl userDAO = new UserDAOImpl();
	
	@SuppressWarnings("unchecked")
	public void favor(String username, String relatePath, String filename) {
		Session session = null;
		Transaction ts = null;
		Integer id = null;
		
		try{
			
			User user = userDAO.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			ts = session.beginTransaction();
			List<File> list = session.createQuery("from File f where f.filename = :filename and f.filepath = :filepath")
					.setString("filename", filename)
					.setString("filepath", relatePath)
					.list();
			for (File file : list) {
				//´¢´æupload¼ÇÂ¼
				FavorUserFile favor = new FavorUserFile();
				favor.setFile(file);
				favor.setUser(user);
				session.save(favor);
			}
			
			ts.commit();
		}catch(HibernateException e){
			if(ts != null){
				ts.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void favor(String username, int fileId) {
		Session session = null;
		Transaction ts = null;
		
		try{
			
			User user = userDAO.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			ts = session.beginTransaction();
			File file = (File) session.load(File.class, fileId);
			//´¢´æfavor¼ÇÂ¼
			FavorUserFile favor = new FavorUserFile();
			favor.setFile(file);
			favor.setUser(user);
			session.save(favor);
			
			ts.commit();
		}catch(HibernateException e){
			if(ts != null){
				ts.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void unfavor(String username, int fileId) {
		Session session = null;
		Transaction ts = null;
		
		try{
			
			User user = userDAO.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			ts = session.beginTransaction();
			//´¢´æfavor¼ÇÂ¼
			Query q1 = session.createQuery("delete from FavorUserFile fuf where fuf.file.fileid = :fileid and fuf.user.userid = :userid");
			q1.setInteger("fileid", fileId);
			q1.setInteger("userid", user.getUserid());
			System.out.println("deleted count : "+q1.executeUpdate());
			ts.commit();
		}catch(HibernateException e){
			if(ts != null){
				ts.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

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
					"select fv.file.fileid from FavorUserFile fv where fv.user.userid=:userid)");
			q1.setString("userid", String.valueOf(user.getUserid()));
			rowCount = ((Long)q1.uniqueResult()).intValue();
			System.out.println("rowCount:"+rowCount); 
			
			Query q2 = session.createQuery("from File f where f.fileid in (" +
					"select fv.file.fileid from FavorUserFile fv where fv.user.userid=:userid)");
			q2.setString("userid", String.valueOf(user.getUserid()));
			q2.setFirstResult(pageSize*(pageNo-1));
			q2.setMaxResults(pageSize);
			list = q2.list();
			pager = new Pager(pageNo,pageSize,rowCount,list);
			tx.commit();
			q1=null;
			q2=null;
		}catch(HibernateException e){
			pager = new org.niko.utils.Pager(pageNo,pageSize,0,new ArrayList<Object>());
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
