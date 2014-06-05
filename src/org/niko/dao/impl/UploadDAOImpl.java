package org.niko.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import test.bean.File;
import test.bean.FileTag;
import test.bean.HibernateSessionFactory;
import test.bean.Tags;
import test.bean.Upload;
import test.bean.User;

public class UploadDAOImpl {
	
	UserDAOImpl userDAO = new UserDAOImpl();
	
	public Integer saveUpload(File file, String username, String[] splited){ 
		Session session = null;
		Transaction ts = null;
		Integer id = null;
		
		try{
			
			User user = userDAO.getUserByName(username);
			
			session = HibernateSessionFactory.getSession();
			ts = session.beginTransaction();
			
			id = (Integer)session.save(file);

			//储存upload记录
			Upload upload = new Upload();
			upload.setFile(file);
			upload.setUser(user);
			session.save(upload);
			
			//判断存储file_tag记录
			if (splited != null) {
				Tags t;
				for(int i=0;i < splited.length;i++){
					t = (Tags)session.createQuery("from Tags t where t.tagname=:tagname")
							.setString("tagname", splited[i])
							.uniqueResult();
					if(t == null){	// tag不存在, 则新建一个
						t = new Tags();
						t.setTagname(splited[i]);
						session.save(t);
					}
					FileTag ft = new FileTag();
					ft.setFile(file);
					ft.setTags(t);
					
					session.save(ft);
				} 
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
		return id;
	}
}
