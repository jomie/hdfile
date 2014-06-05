package org.niko.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.niko.utils.StringUtil;

import test.bean.HibernateSessionFactory;
import test.bean.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Results({@Result(name="success", location="/register-success.jsp"), @Result(name="error", location="/register.jsp")})
public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 5069117376464773185L;
	String mail;
	String username;
	String password;
	
	@Override
	public String execute() {
		
		try {
			doRegister();
		} catch (Exception e) {
			super.addActionMessage("ºóÌ¨´íÎó."); 
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	private void doRegister() throws Exception {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setUsername(this.username);
		user.setEmail(this.mail);
		user.setPassword(this.password);
		session.save(user) ;
		tx.commit() ;
		
		makeUserDirectory();
		
		HibernateSessionFactory.closeSession();
	}

	private void makeUserDirectory() throws Exception {
		String urlStr = StringUtil.mkdirURL(username, "", ""); 
		URL url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.setRequestMethod("PUT");
		int r = urlConn.getResponseCode();
System.out.println("hd mkdir result code : "+r); 
		InputStream is = urlConn.getInputStream();
		int i = 0;
		while((i=is.read()) != -1 ) {
			System.out.print((char)i);
		} 
		is.close();
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
