package org.niko.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.niko.dao.impl.FileDAOImpl;
import org.niko.utils.SettingReader;
import org.niko.utils.StringCoder;
import org.niko.utils.StringUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
public class RenameAction extends ActionSupport {

	private static final long serialVersionUID = 4684624749436683513L;
	String dir;
	String oldName;
	String newName;
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	// self
	FileDAOImpl fileDAO = new FileDAOImpl();
	
	
	@org.apache.struts2.convention.annotation.Action("/rename-bxx")
	public void renameBXX() throws Exception {
		String urlStr = StringUtil.renameURLBXX(username, dir, oldName, newName);
		doRename(urlStr);
	}
	
	@org.apache.struts2.convention.annotation.Action("/rename")
	public void rename() throws Exception {
		String urlStr = StringUtil.renameURL(username, dir, oldName, newName);
		doRename(urlStr);
	}
	
	
	public void doRename(String urlStr) throws Exception {
	
		
		valiReq();
		
		try {
			
			fileDAO.rename(this.username+this.dir, this.oldName, this.newName);
			
			doHDRename(urlStr);
			ServletActionContext.getResponse().getWriter().print("{\"status\":\"success\"}");
		} catch (Exception e) {
			ServletActionContext.getResponse().getWriter().print("{\"status\":\"fail\"}");
			e.printStackTrace();
		} 
	}
	
	private void doHDRename(String urlStr) throws Exception { 
		System.out.println(urlStr); 
		URL url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.setRequestMethod("PUT");
		int r = urlConn.getResponseCode();
System.out.println("rename put code:"+r);
		InputStream is = urlConn.getInputStream();
		int i = 0;
		while((i=is.read()) != -1 )
		{
			System.out.print((char)i);
		}
		is.close();
	}

	private void valiReq() throws UnsupportedEncodingException {
		System.out.println("params un decoded : ");
		System.out.println(dir);
		System.out.println(oldName);
		System.out.println(newName);
		
		// 通过$.post()提交, 所以下面不用转码了
		
//		this.dir = StringCoder.url2UTF8(this.dir);
//		this.oldName = StringCoder.url2UTF8(this.oldName);
//		this.newName = StringCoder.url2UTF8(this.newName);
//		
//		System.out.println("params decoded : ");
//		System.out.println(dir);
//		System.out.println(oldName);
//		System.out.println(newName);
	}

	public String execute111()
	{
		URL url;
		String basePath = new SettingReader().ReadValue("basePath");
		try {
			//url = new URL("http://192.168.0.180:50070/webhdfs/v1/allprivl/niko/novels/chinese?op=MKDIRS");
			url = new URL(basePath+"/"+username+dir+oldName+"?op=RENAME&destination=/allprivl/"+username+dir+newName);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("PUT");
			int r = urlConn.getResponseCode();
System.out.println("rename put code:"+r);
			InputStream is = urlConn.getInputStream();
			int i = 0;
			while((i=is.read()) != -1 )
			{
				System.out.print((char)i);
			}
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String toRename()
	{
		return Action.SUCCESS;
	}
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://192.168.0.180:50070/webhdfs/v1/allprivl/toup.txt?op=RENAME&destination=/allprivl/toup_v2.txt");
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("PUT");
			
			int r = urlConn.getResponseCode();
			System.out.println("code:"+r);
			InputStream is = urlConn.getInputStream();
			int i = 0;
			while((i=is.read()) != -1 )
			{
				System.out.print((char)i);
			}
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
