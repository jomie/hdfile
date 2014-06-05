package org.niko.actions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.niko.utils.PropUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AdminUserAction extends ActionSupport { 

	String resetUsername;
	String delUsername;
	List<String> userList = new ArrayList<String>();
	private static final Log LOG = LogFactory.getLog(AdminUserAction.class);
	
	@Override
	public void validate(){
		
	}
	
	//resetuser
	public String resetUser(){
		String path = ServletActionContext.getServletContext().getRealPath("/");
LOG.info("reseting path:"+path+"account\\"+resetUsername+".properties");
		File f = new File(path+"account\\"+resetUsername+".properties");
		PropUtil pp = new PropUtil();
		pp.loadFile(f);
		pp.setValue("password", "12345678");
		pp.writeFileAndClose(f);
		
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8"); 
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print("÷ÿ÷√≥…π¶!");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String delUser(){
		String path = ServletActionContext.getServletContext().getRealPath("/");
LOG.error("deleting path:"+path+"account\\"+delUsername+".properties");
		File f = new File(path+"account\\"+delUsername+".properties");
System.out.println("out:"+f.getAbsolutePath());
		
		LOG.error("LOG:"+f.getAbsolutePath());
		if(f.exists())
			f.delete();
		return Action.SUCCESS;
	}
	
	public String listUser(){
		String path = ServletActionContext.getServletContext().getRealPath("/");
LOG.error("listing path:"+path+"account\\");
		File f = new File(path+"account\\");
		if( ! f.exists())
			f.mkdirs();
		else{
			for(File e:f.listFiles()){
				if(e.isFile())
					userList.add(e.getName().replace(".properties", ""));
			}
		}
		return Action.SUCCESS;
	}
	
	public String getDelUsername() {
		return delUsername;
	}

	public void setDelUsername(String delUsername) {
		this.delUsername = delUsername;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public String getResetUsername() {
		return resetUsername;
	}

	public void setResetUsername(String resetUsername) {
		this.resetUsername = resetUsername;
	}
}
