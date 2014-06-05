package org.niko.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.UploadDAOImpl;
import org.niko.utils.Constants;
import org.niko.utils.UploadUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
@Results({@Result(name="success", location="/upload-success.jsp"), @Result(name="success_FOO", location="/upload.jsp") })
public class UploadAction extends ActionSupport {
	
	private static final long serialVersionUID = 816870731510875200L;
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	String dir;
	String tags;
	
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	@org.apache.struts2.convention.annotation.Action("/upload") 
	public void uploadShare() {
		LOG.info("==>start /upload uploadShare..2014-5-21 0:05:13");
		try {

			validateReq();
			
			saveRec(Constants.IS_SHARE_FILE);
			
			//FIXME 转码 escaped absolute path not valid
			// FIXME 路径名未转码 

			new UploadUtil().uploadShare(username, dir+URLEncoder.encode(uploadFileName, "UTF-8").replaceAll("\\+", "%20"), upload, uploadContentType);
		} catch (Exception e) {
			LOG.info("", e);
		} finally {
			LOG.info("==>stop /upload uploadShare..2014-5-21 0:05:13");
		}
//		return Action.SUCCESS;
	}
	
	@org.apache.struts2.convention.annotation.Action("/upload-bxx") 
	public void uploadBXX() {
		try {

			validateReq();
			
			LOG.info("==>start /upload-bxx.");
			saveRec(Constants.IS_PRIVATE_FILE);
			
			//FIXME 转码 escaped absolute path not valid
			// FIXME 路径名未转码 

			new UploadUtil().uploadBXX(username, dir+URLEncoder.encode(uploadFileName, "UTF-8").replaceAll("\\+", "%20"), upload, uploadContentType);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOG.info("==>stop /upload-bxx.");
		}
//		return Action.SUCCESS;
	}
	
	private void saveRec(Short isPrivate) throws Exception {
		for (Entry<String, String[]> entry : ServletActionContext.getRequest().getParameterMap().entrySet()) {
			System.out.println(entry.getKey()+":");
			new Runnable() {
				
				@Override
				public void run() {
					
				}

				public void ppStrArr(String[]	values) {
					for(String str : values)
						System.out.println(str+","); 
				}
			}.ppStrArr(entry.getValue()); 
		}
		
System.out.println("before encode:"+uploadFileName);
System.out.println("test null charset encode:"+URLEncoder.encode(uploadFileName, "UTF-8"));
System.out.println("uploadContentType : "+uploadContentType); 
System.out.println("after decode:"+uploadFileName);

		UploadDAOImpl uploadDAO = new UploadDAOImpl();
		test.bean.File fileRec = new test.bean.File();
		fileRec.setFilename(this.uploadFileName);
		fileRec.setSharedate(new Date());
		fileRec.setIsPrivate(isPrivate);
		fileRec.setIsPrivateOrigin(isPrivate);
		
		fileRec.setFilepath(username + this.dir);
		System.out.println("tags : "+tags);
		uploadDAO.saveUpload(fileRec, username, tags.split("\\s*,\\s*"));
		
	}

	private void validateReq() {
		if(dir==null || "".equals(dir))
		{
			dir = "/";
		}
		if (tags == null || "".equals(tags)) {
			tags = "无";
		}
	}

	public String toUpload()
	{
		validateReq();
		return Action.SUCCESS;
	}
	
	public static void main(String[] args)
	{
//		UploadAction up = new UploadAction();
//		up.execute();
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
