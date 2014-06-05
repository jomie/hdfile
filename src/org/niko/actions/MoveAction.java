package org.niko.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.FileDAOImpl;
import org.niko.dao.impl.UploadDAOImpl;
import org.niko.utils.Constants;
import org.niko.utils.HDUtils;
import org.niko.utils.StringUtil;

import test.bean.File;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "nikoPackage")
@Results({@Result(name="success000", location="move-success.jsp"),
			@Result(name="error", location="/errors.jsp"),
			@Result(name="success", type="redirect", location="filelist?dir=${dir}"),
@Result(name="success-bxx", type="redirect", location="filelist-bxx?dir=${dir}"),
@Result(name="success-revert", type="redirect", location="${rediLoc}?dir=${dir}")
})
public class MoveAction extends BaseAction {

	private static final long serialVersionUID = 8571032498969135349L;
	String dir;
	String oldName="";
	String newDir = "";
	String newName="";
	String filename = "";
	
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	private short ifPrivate = Constants.IS_SHARE_FILE;
	private short ifPrivateToUpdate = Constants.IS_SHARE_FILE;
	//self
	private String rediLoc;
	private FileDAOImpl fileDAO = new FileDAOImpl();
	private final static  Logger logger = Logger.getLogger("HDFile");
	
//	public String tomove(){
//		newDir = dir;
//		newName = oldName;
//		return Action.SUCCESS;
//	}
	
	@org.apache.struts2.convention.annotation.Action("/move")
	public String move() throws Exception {
		if(!valiReq())
			return "success";
		String moveURLStr = StringUtil.moveURL(this.username, this.dir, this.oldName, this.newDir);
		LOG.info("moveURLStr : " + moveURLStr) ;
		
		this.ifPrivate = Constants.IS_SHARE_FILE;
		this.ifPrivateToUpdate = Constants.IS_SHARE_FILE;
		doMove(moveURLStr);
		return ActionSupport.SUCCESS;
	}
	
	@org.apache.struts2.convention.annotation.Action("/move-bxx")
	public String moveBXX() throws Exception {
		if( ! doCommon())
			return "success-bxx";
		String moveURLStr = StringUtil.moveURLBXX(this.username, this.dir, this.oldName, this.newDir);
		LOG.info("moveURLStr : " + moveURLStr) ;
		this.ifPrivate = Constants.IS_PRIVATE_FILE;
		this.ifPrivateToUpdate = Constants.IS_PRIVATE_FILE;
		doMove(moveURLStr);
		return "success-bxx";
	}
	
	@org.apache.struts2.convention.annotation.Action("/share-to-bxx")
	public String toBXX() throws Exception {
		if( ! doCommon())
			return "success-bxx";
		
		// newDir 和 dir 相同
		String moveURLStr = StringUtil.moveURLShare2BXX(this.username, this.dir, this.oldName);
		LOG.info("/share-to-bxx URLStr : " + moveURLStr) ; 
		
		String mkdirUri = StringUtil.mkdirURLBXX(username, dir); 
		LOG.info("/share-to-bxx mkdir before move : " + mkdirUri);
		HDUtils.doMkdir(mkdirUri);
		
		//更新数据库会用到
		this.newDir = this.dir;
		this.ifPrivate = Constants.IS_SHARE_FILE;
		this.ifPrivateToUpdate = Constants.IS_PRIVATE_FILE;
		doMove(moveURLStr);
		return "success-bxx";
	}
	
	@org.apache.struts2.convention.annotation.Action("/bxx-to-share")
	public String bxxToShare() throws Exception {
		if( ! doCommon())
			return "success";
		
		// newDir 和 dir 相同
		String moveURLStr = StringUtil.moveURLBXX2Share(this.username, this.dir, this.oldName);
		LOG.info("/bxx-to-share URLStr : " + moveURLStr) ; 
		
		String mkdirUri = StringUtil.mkdirURL(username, dir); 
		LOG.info("/bxx-to-share mkdir before move : " + mkdirUri);
		HDUtils.doMkdir(mkdirUri);
		
		//更新数据库会用到
		this.newDir = this.dir; 
		this.ifPrivate = Constants.IS_PRIVATE_FILE;
		this.ifPrivateToUpdate = Constants.IS_SHARE_FILE;
		doMove(moveURLStr);
		return "success";
	}
	
	// bxx to trash
	@org.apache.struts2.convention.annotation.Action("/delete-share")
	public String deleteShare() throws Exception {
		LOG.info("==>start /delete-share deleteShare to trash...2014-5-19 21:20:14");
		
		if( ! doCommon())
			return "success";
		
		
		String mkdirUri = StringUtil.mkdirURLTrash(username, dir);
		LOG.info("/delete-bxx mkdir url before move : " + mkdirUri);
		HDUtils.doMkdir(mkdirUri);
		
		
		//更新数据库会用到
		this.newDir = this.dir;
		// remove 后 加上时间戳
		long timeStamp = new Date().getTime();
		String moveURLStr = StringUtil.moveURLShare2Trash(this.username, this.dir, this.oldName, timeStamp);
		LOG.info("/delete-share URLStr : " + moveURLStr);
		
		this.ifPrivate = Constants.IS_SHARE_FILE;
		this.ifPrivateToUpdate = Constants.IS_TRASH_FILE;
		doDelMoveAndRename(moveURLStr, timeStamp);
		
		LOG.info("==>stop /delete-share deleteShare to trash...");
		return "success";
	}
	
	// bxx to trash
	@org.apache.struts2.convention.annotation.Action("/delete-bxx")
	public String deleteBXX() throws Exception {
		LOG.info("==>start /delete-bxx deleteBXX to trash...2014-5-19 21:20:14");
		
		if( ! doCommon())
			return "success-bxx";

		this.ifPrivate = Constants.IS_TRASH_FILE;
		
		//调用通用处理需要
		
		String mkdirUri = StringUtil.mkdirURLTrash(username, dir);
		LOG.info("/delete-bxx mkdir url before move : " + mkdirUri);
		HDUtils.doMkdir(mkdirUri);
		
		
		//更新数据库会用到
		this.newDir = this.dir;
		// remove 后 加上时间戳
		long timeStamp = new Date().getTime();
		String moveURLStr = StringUtil.moveURLBXX2Trash(this.username, this.dir, this.oldName, timeStamp);
		LOG.info("/delete-bxx URLStr : " + moveURLStr);
		
		this.ifPrivate = Constants.IS_PRIVATE_FILE;
		this.ifPrivateToUpdate = Constants.IS_TRASH_FILE;
		doDelMoveAndRename(moveURLStr, timeStamp);
		
		LOG.info("==>stop /delete-bxx deleteBXX to trash...");
		return "success-bxx";
	}
	
	// bxx to trash
	@org.apache.struts2.convention.annotation.Action("/revert")
	public String revert() throws Exception {
		LOG.info("==>start /revert revert()...2014-5-19 21:20:14");
		
		if( ! doCommon())
			return "success-revert";
		
		// 查询原先属于哪种文件
		File delFile = fileDAO.getTrashFile(username, dir, oldName);
		// 无记录, 则默认恢复到私密文件
		if (delFile == null) {
			super.addActionMessage("找不到删除文件.");
			LOG.info("找不到删除文件.");
			
			// 修复旧数据导致的记录, 默认私密文件
			UploadDAOImpl uploadDAO = new UploadDAOImpl();
			test.bean.File fileRec = new test.bean.File();
			
			String newName = oldName;
			if (Constants.IS_SHARE_FILE.equals(this.ifPrivateToUpdate)) {
				// 查询恢复位置文件是否重名, 不会则去除时间戳.
				newName = StringUtil.revertShareNewName(username , dir, oldName);
			} else {
				// private
				newName = StringUtil.revertBXXNewName(username , dir, oldName);
			}
			fileRec.setFilename(newName);
			fileRec.setSharedate(new Date());
			fileRec.setIsPrivate(Constants.IS_PRIVATE_FILE);
			fileRec.setIsPrivateOrigin(Constants.IS_PRIVATE_FILE);
			
			fileRec.setFilepath(username + this.dir);
			uploadDAO.saveUpload(fileRec, username, new String[]{"无"});
			delFile = fileRec;
		}
		this.ifPrivateToUpdate = delFile.getIsPrivateOrigin();
		LOG.info("ifPrivateToUpdate : "+this.ifPrivateToUpdate);
		chooseLoc(this.ifPrivateToUpdate);
		
		// FIXME roll back r_b
		String mkdirUri = StringUtil.mkdirURLRevert(username, dir, this.ifPrivateToUpdate);
		LOG.info("//revert mkdir url before move : " + mkdirUri);
		HDUtils.doMkdir(mkdirUri);
		
		
		//更新数据库会用到, 因为doMove()是通用的
		this.newDir = this.dir;
		// remove 后 加上时间戳
		String moveURLStr = StringUtil.moveURLRevert(this.username, this.dir, this.oldName, this.ifPrivateToUpdate);
		LOG.info("//revert URLStr : " + moveURLStr);
		
		this.ifPrivate = Constants.IS_TRASH_FILE;
//		this.ifPrivateToUpdate
		doMove(moveURLStr);
		
		LOG.info("==>stop /revert revert()...");
		return "success-revert";
	}
	
	private void chooseLoc(short ifPrivateToUpdate2) {
		if (Constants.IS_PRIVATE_FILE.equals(ifPrivateToUpdate2)) {
			this.rediLoc = "filelist-bxx";
		} else {
			this.rediLoc = "filelist";
		}
	}

	public void doMove(String moveURLStr)
	{
		this.newName = this.oldName;
		try {
			
			LOG.info("moveURLStr : " + moveURLStr);
			fileDAO.move(this.oldName, this.username+this.dir, this.username+this.newDir, this.ifPrivate, this.ifPrivateToUpdate);
			
			doHDMove(moveURLStr);
			
		} catch (Exception e) {
			LOG.info("move()", e) ;
		}
	}
	
	public void doDelMoveAndRename(String moveURLStr, long timeStamp)
	{
		try {
			LOG.info("ifPrivate : "+ifPrivate);
			fileDAO.moveAndRename(this.oldName, this.newName+"."+timeStamp, this.username+this.dir, this.username+this.dir, this.ifPrivate, this.ifPrivateToUpdate);
			
			LOG.info("moveURLStr : " + moveURLStr);
			doHDMove(moveURLStr);
			
		} catch (Exception e) {
			LOG.info("move()", e) ;
		}
	}
	
	private void doHDMove(String moveURLStr) throws Exception {

		URL url = new URL(moveURLStr);
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.setRequestMethod("PUT");
		int rslCode = urlConn.getResponseCode();
		LOG.info("rename put code:"+rslCode) ;
		
		InputStream is = urlConn.getInputStream();
		
		List<String> lines = IOUtils.readLines(is) ;
		for (String line : lines) {
			System.out.println(line); 
			if (line.contains("false")) {
//				super.addActionMessage("文件已存在.");
			}
		}
		
		is.close();
	}

	@Override
	boolean valiReq() throws Exception {
		
		
		if(oldName == null)
			oldName="";
		if(newName == null)
			newName="";
		if (newDir == null)
			newDir = "";
		if (filename == null)
			filename = "";
		if(this.ifPrivate != 0 || this.ifPrivate != 1)
			this.ifPrivate = 0;
		
		logger.info("dir raw:"+dir);
		logger.info("newDir raw:"+newDir);
		logger.info("oldName raw:"+oldName);
		logger.info("filename raw:"+filename);
		
		dir = new String(dir.getBytes("ISO-8859-1"), "UTF-8");
		newDir = new String(newDir.getBytes("ISO-8859-1"), "UTF-8");
		oldName = new String(oldName.getBytes("ISO-8859-1"), "UTF-8");
		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		
		// 
		newName = oldName;
		
		logger.info("dir enc:"+dir);
		logger.info("newDir enc:"+newDir);
		logger.info("oldName enc:"+oldName);
		logger.info("filename enc:"+filename);
		
		return true;
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

	public String getNewDir() {
		return newDir;
	}

	public void setNewDir(String newDir) {
		this.newDir = newDir;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public short getIfPrivate() {
		return ifPrivate;
	}

	public void setIfPrivate(short ifPrivate) {
		this.ifPrivate = ifPrivate;
	}

	@Override
	void init() {
		// TODO Auto-generated method stub
		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRediLoc() {
		return rediLoc;
	}

	public void setRediLoc(String rediLoc) {
		this.rediLoc = rediLoc;
	}
	
}
