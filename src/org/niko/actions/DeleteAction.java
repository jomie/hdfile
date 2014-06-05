package org.niko.actions;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.FileDAOImpl;
import org.niko.utils.SettingReader;
import org.niko.utils.StringUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
@Results
({
	@Result(name="success", type="redirect", location="filelist?dir=${dir}"), 
	@Result(name="success-trash", type="redirect", location="filelist-trash?dir=${dir}") 
})
public class DeleteAction extends ActionSupport {

	private static final long serialVersionUID = 5880069785200797197L;
	String dir;
	String filename;
	int fileId;
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	String basePath;
	
	private FileDAOImpl fileDAO = new FileDAOImpl();
	
	@org.apache.struts2.convention.annotation.Action("/rmdelete-trash")
	public String deleteBXX() throws Exception {
		
		validateReq();
		
		String urlStr = StringUtil.deleteURLTrash(username, dir, filename);
		doDelete(urlStr);
		return "success-trash";
	}
	
	private void validateReq() {

		try {
			dir = new String(dir.getBytes("ISO-8859-1"), "UTF-8");
			filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			LOG.info("dir(utf8) : "+dir);
			LOG.info("filename(utf8) : "+filename);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

//	@org.apache.struts2.convention.annotation.Action("/rmdelete")
	public String deleteShare() throws Exception {
		
		validateReq();
		String urlStr = StringUtil.deleteURL(username, dir, filename);
		return doDelete(urlStr);
	}
	
	private String doDelete(String urlStr) {
		LOG.info("deleting url : "+urlStr);
		URL url;
//		basePath = new SettingReader().ReadValue("basePath");
		
		
		
		fileDAO.deleteFileRec(this.username+this.dir, this.filename);
		
		try {
			
			//÷ÿ±‡¬Î
			
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("DELETE");
			int r = urlConn.getResponseCode();
System.out.println("resp code:"+r);
			InputStream is = urlConn.getInputStream();
			int i = 0;
			while((i=is.read()) != -1 )
			{
				System.out.print((char)i);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return Action.SUCCESS;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	} 
}
