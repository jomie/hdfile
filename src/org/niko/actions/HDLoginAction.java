package org.niko.actions;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.niko.utils.LoginUtil;
import org.niko.utils.SettingReader;

import test.bean.HibernateSessionFactory;
import test.bean.User;

import com.opensymphony.xwork2.ActionSupport;

	/**
	 * @author niko
	 */
@Results({@Result(name="success", type="redirect", location="filelist?dir=/"), @Result(name="error", location="/login.jsp"),
		@Result(name="logout", location="/login.jsp")}) 
@SuppressWarnings("serial")
public class HDLoginAction extends ActionSupport {
	String username;
	String password;
	
	//admin
	String adminName;
	String adminPassword;
	
	public String execute1()
	{
		//if(username password)
		//管理员不该在这里登陆
		if("admin".equals(username)){
			return ActionSupport.INPUT;
		}
		if(LoginUtil.valid(username, password)){
			ServletActionContext.getContext().getSession().put("username", username);
			
			return ActionSupport.SUCCESS;
		}
		else{
			super.addActionMessage("用户名或密码错误!");
			return ActionSupport.INPUT;
		}
	}
	
	@Action("/login")
	public String login() {
		
		boolean b = false ;
		Session session = null;
//		User user = null;
		try {
			session = test.bean.HibernateSessionFactory.getSession();
			Query query = session.createQuery("from User where username=?");
			query.setString(0, this.username.trim()); 
			User user = (User) query.uniqueResult();
			if (user == null) {
				b = false;
			} else if (user.getPassword().equals(this.password)) {
				System.out.println("username : "+user.getUsername());
				b = true ;
			}
			query = null;
		} catch (HibernateException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		if (!b) {
			super.addActionMessage("密码错误!") ;
			return ActionSupport.ERROR;
		}
		ServletActionContext.getContext().getSession().put("username", this.username.trim()) ;
		return ActionSupport.SUCCESS ;
	}
	
	//logout
	@Action("/logout")
	public String logout(){
		ServletActionContext.getContext().getSession().clear();
		return "logout";
	}
	
	public String adminLogin(){
		
		SettingReader r = new SettingReader();
		String pwd = r.ReadValue("admin-pwd");
		if(adminName==null || adminPassword==null || (!pwd.equals(adminPassword)) || (!"admin".equals(adminName))){
			return ActionSupport.INPUT;
		}
		ServletActionContext.getContext().getSession().put("adminName", adminName);
		return ActionSupport.SUCCESS;
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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
