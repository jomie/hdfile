package org.niko.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;

public class StringUtil {
	
	public static String getParentURL(String url){
		int i = url.lastIndexOf("/");
		if(i == 0)
			return "/";
		else{
			i = url.substring(0, url.length()-1).lastIndexOf("/");
			return url.substring(0, i+1);
		}
	}
	
	public static void main(String[] args){
		
		
		String url = "/gher/djsiodjfios/";
		int i = url.lastIndexOf("/");
		if(i == 0)
			url = "/";
		else{
			i = url.substring(0, url.length()-1).lastIndexOf("/");
			url = url.substring(0, i+1);
		}
		System.out.println(url);
		
	}

	public static String listStatusURL(String username, String dir) throws Exception { 
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		return baseUrlStr + username + dir + "?op=LISTSTATUS";
	}
	
	public static String renameURL(String username, String dir, String oldName, String newName) throws ConfigurationException, UnsupportedEncodingException {
		
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newName = StringCoder.utf82URL(newName);
		
		String baseUrlStr = new ConfigReader().getBasePath(); 
		String str = baseUrlStr +username+dir+oldName+"?op=RENAME&destination=/allprivl/"+username+dir+newName;
		return str;
	}
	
	public static String renameURLBXX(String username, String dir, String oldName, String newName) throws ConfigurationException, UnsupportedEncodingException {
		
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newName = StringCoder.utf82URL(newName);
		
		String baseUrlStr = new ConfigReader().getBXXPath(); 
		String str = baseUrlStr +username+dir+oldName+"?op=RENAME&destination=/allprivl/"+username+dir+newName;
		return str;
	}
	public static String moveURL(String username, String dir, String oldName, String newDir) throws Exception {
		
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newDir = StringCoder.utf82URL(newDir);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_NORMAL_DIR+username+newDir+oldName;
		return str;
	}
	
	public static String moveURLBXX(String username, String dir, String oldName, String newDir) throws Exception {
		
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newDir = StringCoder.utf82URL(newDir);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_PRIVATE_DIR+username+newDir+oldName;
		return str;
	}
	
	// share url to bxx url
	public static String moveURLShare2BXX(String username, String dir,
			String oldName) throws Exception {
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_PRIVATE_DIR+username+dir+oldName;
		return str;
	}
	
	public static String moveURLBXX2Share(String username, String dir,
			String oldName) throws Exception {
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_NORMAL_DIR+username+dir+oldName;
		return str;
	}
	
	/**
	 * move and rename
	 * @param username
	 * @param dir
	 * @param oldName
	 * @param timeStamp 
	 * @return
	 * @throws Exception
	 */
	public static String moveURLBXX2Trash(String username, String dir, String oldName, long timeStamp) throws Exception {

		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_TRASH_DIR+username+dir
					+ oldName +"."+timeStamp;
		return str;
	}
	
	public static String moveURLShare2Trash(String username, String dir, String oldName, long timeStamp) throws Exception {
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getBasePath();			// share path
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_TRASH_DIR + username+dir
				+ oldName +"."+timeStamp;
		return str;
	}
	
	public static String moveURLTrash2BXX(String username, String dir,
			String oldName) throws Exception { 
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getTrashPath();		// TRASH
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_PRIVATE_DIR+username+dir
					+ oldName;
		return str;
	}
	
	public static String moveURLTrash2BXX(String username, String dir,
			String oldName, String newName) throws Exception { 
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newName = StringCoder.utf82URL(newName);
		
		String baseUrlStr = new ConfigReader().getTrashPath();		// TRASH
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_PRIVATE_DIR+username+dir
				+ newName;
		return str;
	}
	
	public static String moveURLTrash2Share(String username, String dir, String oldName) throws Exception { 
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		
		String baseUrlStr = new ConfigReader().getTrashPath();		// TRASH
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_NORMAL_DIR+username+dir
				+ oldName;
		return str;
	}
	
	public static String moveURLTrash2Share(String username, String dir, String oldName, String newName) throws Exception { 
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		oldName = StringCoder.utf82URL(oldName);
		newName = StringCoder.utf82URL(newName);
		
		String baseUrlStr = new ConfigReader().getTrashPath();		// TRASH
		String str = baseUrlStr+username+dir+oldName+"?op=RENAME&destination="+Constants.BASE_NORMAL_DIR+username+dir
				+ newName;
		return str;
	}
	
	public static String moveURLRevert(String username, String dir, String oldName, Short type) throws Exception { 
		
		if (Constants.IS_SHARE_FILE.equals(type)) {
			// 查询恢复位置文件是否重名, 不会则去除时间戳.
			String newName = revertShareNewName(username , dir, oldName);
			return moveURLTrash2Share(username, dir, oldName, newName);
		} else {
			// private
			String newName = revertBXXNewName(username , dir, oldName);
			return moveURLTrash2BXX(username, dir, oldName, newName);
		}
		
	}

	public static String revertShareNewName(String username, String dir, String oldName) throws Exception {
		String urlStr = StringUtil.listStatusURL(username, dir + oldName);
		if (HDUtils.notFileExist(urlStr)) {
			return oldName.substring(0,  oldName.lastIndexOf("."));
		}
		return oldName;
	}
	
	public static String revertBXXNewName(String username, String dir, String oldName) throws Exception {
		String bxxUrlStr = StringUtil.listStatusURLBXX(username, dir + oldName);
		if (HDUtils.notFileExist(bxxUrlStr)) {
			return oldName.substring(0,  oldName.lastIndexOf("."));
		}
		return oldName;
	}

	public static String downloadURL(String username, String relativePath) throws ConfigurationException {
		String baseUrlStr = new ConfigReader().getBasePath(); 
		String urlStr = baseUrlStr+username+relativePath+"?op=OPEN";
		return urlStr;
	}
	
	public static String downloadURLBXX(String username, String relativePath) throws ConfigurationException {
		String baseUrlStr = new ConfigReader().getBXXPath(); 
		String urlStr = baseUrlStr+username+relativePath+"?op=OPEN";
		return urlStr;
	}

	public static String mkdirURL(String username, String dir, String newDirName) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		newDirName = StringCoder.utf82URL(newDirName);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		String urlStr = baseUrlStr+username+dir+newDirName+"?op=MKDIRS";
		return urlStr;
	}
	
	// for make dir
	public static String mkdirURL(String username, String dir) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		String urlStr = baseUrlStr+username+dir+"?op=MKDIRS";
		return urlStr;
	}
	
	public static String mkdirURLBXX(String username, String dir, String newDirName) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		newDirName = StringCoder.utf82URL(newDirName);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String urlStr = baseUrlStr+username+dir+newDirName+"?op=MKDIRS";
		return urlStr;
	}
	
	// for make dir
	public static String mkdirURLBXX(String username, String dir) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String urlStr = baseUrlStr+username+dir+"?op=MKDIRS";
		return urlStr;
	}
	
	public static String mkdirURLTrash(String username, String dir) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getTrashPath();
		String urlStr = baseUrlStr+username+dir+"?op=MKDIRS";
		return urlStr;
	}
	
	public static String mkdirURLRevert(String username, String dir, Short type) throws Exception {
		
		if (Constants.IS_SHARE_FILE.equals(type)) {
			return mkdirURL(username, dir);
		} else {
			// private
			return mkdirURLBXX(username, dir);
		}
	}

	public static String deleteURL(String username, String dir, String filename) throws ConfigurationException, UnsupportedEncodingException {
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		filename = StringCoder.utf82URL(filename);
		
		String baseUrlStr = new ConfigReader().getBasePath();
		String urlStr = baseUrlStr + username + dir
				+ filename +"?op=DELETE&recursive=true";
		return urlStr;
	}
	
	public static String deleteURLBXX(String username, String dir, String filename) throws ConfigurationException, UnsupportedEncodingException {
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		filename = StringCoder.utf82URL(filename);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		String urlStr = baseUrlStr + username + dir
				+ filename +"?op=DELETE&recursive=true";
		return urlStr;
	}
	
	public static String deleteURLTrash(String username, String dir, String filename) throws ConfigurationException, UnsupportedEncodingException {
		
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		filename = StringCoder.utf82URL(filename);
		
		String baseUrlStr = new ConfigReader().getTrashPath();
		String urlStr = baseUrlStr + username + dir
				+ filename +"?op=DELETE&recursive=true";
		return urlStr;
	}

	public static String uploadURL(String username, String relativePath) throws ConfigurationException {
		String baseUrlStr = new ConfigReader().getBasePath();
		String urlStr = baseUrlStr+username+relativePath+"?op=CREATE";
		return urlStr;
	}
	
	public static String uploadURLBXX(String username, String relativePath) throws ConfigurationException {
		String baseUrlStr = new ConfigReader().getBXXPath(); 
		String urlStr = baseUrlStr+username+relativePath+"?op=CREATE";
		return urlStr;
	}

	public static String downloadOTURL(String pathWithUsername) throws ConfigurationException {
		String baseUrlStr = new ConfigReader().getBasePath(); 
		String urlStr = baseUrlStr+pathWithUsername+"?op=OPEN";
		return urlStr;
	}
	

	public static String listStatusURLBXX(String username, String dir) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getBXXPath();
		return baseUrlStr + username + dir + "?op=LISTSTATUS";
	}
	
	public static String listStatusURLTrash(String username, String dir) throws Exception {
		username = StringCoder.utf82URL(username);
		dir = StringCoder.utf82URL(dir);
		
		String baseUrlStr = new ConfigReader().getTrashPath();
		return baseUrlStr + username + dir + "?op=LISTSTATUS";
	}

}
