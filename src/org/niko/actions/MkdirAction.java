package org.niko.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.niko.utils.HDUtils;
import org.niko.utils.StringCoder;
import org.niko.utils.StringUtil;

import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
public class MkdirAction extends BaseAction {
	
	private static final long serialVersionUID = -2349371871119475162L;
	String dir;
	String newDirName;
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	
	
	@Override
	void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	boolean valiReq() throws Exception {
		if (dir == null) {
			dir = "/";
		}
		if (newDirName == null) 
			newDirName = "/";
		
		username = StringCoder.url2UTF8(username);
		dir = StringCoder.url2UTF8(dir);
		newDirName = StringCoder.url2UTF8(newDirName);
		
		return true;
	}
	
	@Action("/mkdir-bxx")
	public void executeMkdir() throws IOException {
		String urlStr;
		try {
			doCommon();
			urlStr = StringUtil.mkdirURLBXX(username, dir, newDirName);
			LOG.info("mkUrl : "+urlStr);
			HDUtils.doMkdir(urlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Action("/mkdir")
	public void mkdir() {
		
		
		String urlStr;
		try {
			doCommon();
			urlStr = StringUtil.mkdirURL(username, dir, newDirName);
			LOG.info("mkUrl : "+urlStr);
			HDUtils.doMkdir(urlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String toMkdir()
	{
		return "success";
	}
	
	public static void main(String[] args) {
		
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getNewDirName() {
		return newDirName;
	}

	public void setNewDirName(String newDirName) {
		this.newDirName = newDirName;
	}
	
}
