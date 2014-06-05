package org.niko.actions;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.DownloadDAOImpl;
import org.niko.dao.impl.FileDAOImpl;
import org.niko.utils.DownloadUtil;
import org.niko.utils.Pager;
import org.niko.utils.StringCoder;

import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
@SuppressWarnings("deprecation")
@Results({ 
	@Result(name="success", type="stream", params = {
			"inputName", "inputStream",
			"contentType", "application/octet-stream",
			"contentDisposition", "attachment;filename=\"${downname}\"",
			"bufferSize", "8192"
	}),
	@Result(name="ot_success", type="stream", params = {
			"inputName", "inputStream",
			"contentType", "application/octet-stream",
			"contentDisposition", "attachment;filename=\"${downname}\"",
			"bufferSize", "8192"
	}), 
	@Result(name="down-list", location="my_down_list.jsp")
})
public class DownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3564732561515411673L;

	int pageNo = 1;
	int pageSize = 10;
	String dir;
	String downname;
	String pathWithUsername;
	int bxx = 0;
	private Pager pager; 
	//
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	InputStream is;

	
//	@Action("/download")
//	private String executeDownload() {
//		// 重编码
//		try {
//			//
//System.out.println("down actin catch downname:"+downname);
//System.out.println("enc1:"+new String(downname.getBytes("ISO-8859-1"), "UTF-8"));
//System.out.println("enc2:"+new String(downname.getBytes("UTF-8"), "ISO-8859-1"));
//
//			this.downname = URLEncoder.encode(new String(downname.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
//			
//			is = new DownloadUtil().getShareIs(username, dir+downname);
//		
//			if (is == null) {
//				System.out.println("enter getInputStream, is is null");
//				is = new StringBufferInputStream("sorry, some error happened.");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "success";
//	}
	
	@Action("/download-bxx")
	public String downBXX() throws Exception {
		// 重编码
		try {
			//
System.out.println("down actin req downname:" + downname);
			valiReq();
			saveDownRec();
			
			is = new DownloadUtil().getBXXIs(username, dir+downname);
		
			if (is == null) {
				System.out.println("enter getInputStream, is is null");
				is = new StringBufferInputStream("sorry, some error happened.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	private void saveDownRec() {
		new FileDAOImpl().saveDown(username, dir, downname, this.bxx);
	}

	@Action("/myDown")
	public String listMyDown() {
		this.pager = new DownloadDAOImpl().list(pageNo, pageSize, username);
		return "down-list";
	}
	
	@Action("/download")
	public String downSelf() throws UnsupportedEncodingException {
		// 重编码
		try {
			//
System.out.println("down actin catch downname:"+downname);
			valiReq();
			saveDownRec();
			
			is = new DownloadUtil().getShareIs(username, dir+downname);
		
			if (is == null) {
				System.out.println("enter getInputStream, is is null");
				is = new StringBufferInputStream("sorry, some error happened.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	// 通过pathWithUsername下载
	@Action("/downOT")
	public String downOT() {
		// 重编码
		try {
			
			valiReq();
			
			System.out.println("pathWithUsername utf : "+pathWithUsername);
			// FIXME 如果找不到"/", 会越界错误
			// 供下载时的文件名, 必须使用URL编码
			downname = this.pathWithUsername.substring(this.pathWithUsername.lastIndexOf("/")+1); 
			downname = StringCoder.utf82URL(downname);
			System.out.println("downname : "+downname);
			
			this.pathWithUsername = StringCoder.utf82URL(this.pathWithUsername);
			System.out.println("pathWithUsername jetty url : "+pathWithUsername);
			
			is = new DownloadUtil().getInputStreamOT(pathWithUsername);
			
			if (is == null) {
				System.out.println("enter getInputStream, is is null");
				is = new StringBufferInputStream("sorry, some error happened.");
			}
		} catch (Exception e) {
			LOG.error("", e);
			is = new StringBufferInputStream("sorry, some error happened.");
		}
		return "ot_success";
	}
	
	private void valiReq() throws Exception { 
		if (this.pathWithUsername != null) {
			this.pathWithUsername = StringCoder.url2UTF8(this.pathWithUsername);
		}
		dir = StringCoder.url2UTF8(dir);
		downname = StringCoder.url2UTF8(downname);
	}

	@Action("/downloadByDB")
	public String downloadByDB() {
		
		return "success";
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String ss = URLEncoder.encode("niko/linode_百度百科.mhtml", "UTF-8");
		System.out.println(ss.lastIndexOf("/"));
		System.out.println(ss.substring(4));
//		System.out.println("abc".lastIndexOf("/"));
//		System.out.println("/abc/".substring("abc".lastIndexOf("/")));
	}
	
	public InputStream getInputStream(){
		return this.is;
	}

	public String getDownname() {
		return downname;
	}

	public void setDownname(String downname) {
		this.downname = downname;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getPathWithUsername() {
		return pathWithUsername;
	}

	public void setPathWithUsername(String pathWithUsername) {
		this.pathWithUsername = pathWithUsername;
	}

	public int getBxx() {
		return bxx;
	}

	public void setBxx(int bxx) {
		this.bxx = bxx;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
