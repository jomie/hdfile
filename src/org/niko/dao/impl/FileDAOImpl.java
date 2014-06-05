package org.niko.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.niko.utils.Pager;


import test.bean.Download;
import test.bean.File;
import test.bean.HibernateSessionFactory;
import test.bean.User;

public class FileDAOImpl {
	Logger logger = Logger.getLogger("HDFile");

	public boolean deleteFileRec(String dir, String filename, int fileId) {

		Session session = null;
		Transaction tx = null;
		File file = null;
		try{
			//打开session, 注意session状态, 储存book记录
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			file = (File)session.load(File.class, fileId);
			session.delete(file);
			tx.commit();
			return true;
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean deleteFileRec(String dirStartWithUser, String filename) {
		
		Session session = null;
		Transaction tx = null;
		try{
			//打开session, 注意session状态, 储存book记录
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Query q1 = session.createQuery("from File f where f.filename = :filename and f.filepath = :filepath");
			q1.setString("filename", filename);
			q1.setString("filepath", dirStartWithUser);
			List<File> list = q1.list(); 
System.out.println("delete hb list : "+list.size());
			for (File f : list) {
System.out.println("f : "+f.getFilename()); 
				session.delete(f);
			}
			tx.commit();
			return true;
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 不可用于目标是回收站的操作
	 * @param filename
	 * @param oldDirWithUser
	 * @param newDirWithUser
	 * @param ifPrivateToUpdate
	 * @param ifPrivateToUpdate2 
	 */
	public void move(String filename, String oldDirWithUser, String newDirWithUser,
			Short ifPrivate, short ifPrivateToUpdate) {
		Session session = null;
		Transaction tx = null;
		try{
			//打开session, 注意session状态, 储存book记录
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Query q1 = session.createQuery("update File f set f.filepath = :newFilePath, f.isPrivate = :isPrivateUpdate, f.isPrivateOrigin = :isPrivateUpdate where f.filename = :filename and f.filepath = :filepath and f.isPrivate = :is_private");
			q1.setString("filename", filename);
			q1.setString("filepath", oldDirWithUser);
			q1.setShort("is_private", ifPrivate);
			q1.setShort("isPrivateUpdate", ifPrivateToUpdate); 
			q1.setString("newFilePath", newDirWithUser);
			logger.info("rows updated : "+q1.executeUpdate());
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void moveAndRename(String filename, String newFilename,  String oldDirWithUser, String newDirWithUser,
			Short ifPrivate, short ifPrivateToUpdate) {
		Session session = null;
		Transaction tx = null;
		try{
			//打开session, 注意session状态, 储存book记录
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Query q1 = session.createQuery("update File f set f.filepath = :newFilePath, f.filename = :newFilename, f.isPrivate = :ifPrivateToUpdate  where f.filename = :filename and f.filepath = :filepath and f.isPrivate = :is_private");
			q1.setString("filename", filename);
			q1.setString("newFilename", newFilename);
			q1.setString("filepath", oldDirWithUser);
			q1.setString("newFilePath", newDirWithUser);
			q1.setShort("is_private", ifPrivate);
			q1.setShort("ifPrivateToUpdate", ifPrivateToUpdate);
			
			logger.info("rows updated : "+q1.executeUpdate());
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 更新
	 * @param pathWithUsername
	 * @param oldName
	 * @param newName
	 */
	public void rename(String pathWithUsername, String oldName, String newName) {
		Session session = null;
		Transaction tx = null;
		try{
			//打开session, 注意session状态, 储存book记录
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Query q1 = session.createQuery("update File f set f.filename = :newName where f.filename = :oldName and f.filepath = :filepath");
			q1.setString("oldName", oldName);
			q1.setString("filepath", pathWithUsername);
			q1.setString("newName", newName);
			logger.info("rows updated : "+q1.executeUpdate());
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public File getTrashFile(String username, String dir, String oldName) {
		Session session=null; 
		try{
			
			session = HibernateSessionFactory.getSession();
			Query q1 = session.createQuery("from File f where f.filename = :filename and f.filepath = :filepath and f.isPrivate = -1");
			q1.setString("filename", oldName);
			q1.setString("filepath", username + dir);
			
			return (File) q1.uniqueResult();

		}catch(HibernateException e){
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public Pager searchFileByTagId(int pageNo, int pageSize, Integer tagId) {
		List list;
		Pager pager;
		Session session=null;
		int rowCount;
		try{
			session = HibernateSessionFactory.getSession();
			Query q1 = session.createQuery("select count(*) from File f where f.isPrivate = 0 and f.fileid in (" +
					"select ft.file.fileid from FileTag ft, Tags t where ft.tags.tagid = :tagId)");
			q1.setInteger("tagId", tagId);
			rowCount = ((Long)q1.uniqueResult()).intValue();
			
			Query q2 = session.createQuery("from File f where f.isPrivate = 0 and f.fileid in (" +
					"select ft.file.fileid from FileTag ft, Tags t where ft.tags.tagid = :tagId)");
			q2.setInteger("tagId", tagId);
			q2.setFirstResult(pageSize*(pageNo-1));
			q2.setMaxResults(pageSize);
			list = q2.list();
			
			pager = new Pager(pageNo, pageSize, rowCount , list);
			
//			new File().getFilename();
		}catch(HibernateException e){
			pager = new Pager(pageNo, pageSize, 0 , new ArrayList<Object>());
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return pager;
	}
	
	public Pager search(int pageNo, int pageSize, String keyword){
		List list;
		Pager pager;
		Session session=null; 
		int rowCount;
		try{
			session = HibernateSessionFactory.getSession();
			Query q1 = session.createQuery("select count(*) from File f where f.filename like :keyword and f.isPrivate = 0");
			q1.setString("keyword", "%"+keyword+"%");
			rowCount = ((Long)q1.uniqueResult()).intValue();
			
			Query q2 = session.createQuery("from File f where f.filename like :keyword and f.isPrivate = 0");
			q2.setString("keyword", "%"+keyword+"%");
			q2.setFirstResult(pageSize*(pageNo-1));
			q2.setMaxResults(pageSize);
			list = q2.list();
			
			pager = new Pager(pageNo, pageSize, rowCount , list);
			
//			new File().getFilename();
		}catch(HibernateException e){
			pager = new Pager(pageNo, pageSize, 0 , new ArrayList<Object>());
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return pager;
	}

	public void saveDown(String username, String dir, String downname, int bxx) { 
		
		Session session = null;
		Transaction tx = null;
		
		try{
			User user = new UserDAOImpl().getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from File f where f.filename = :filename and f.filepath = :filepath and f.isPrivate = :bxx");
			query.setString("filename", downname);
			query.setString("filepath", username + dir);
			query.setShort("bxx", (short)bxx);
			File f = (File) query.uniqueResult();
			System.out.println("unique() file : "+f);
			if (f != null) {
				
				Download down = new Download();
				down.setUser(user);
				down.setFile(f);
				down.setDowndate(new Date());
				session.save(down);
				tx.commit();
			}
		} catch(HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
}
