package org.niko.actions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.utils.ConfigReader;
import org.niko.utils.Pager;
import org.niko.utils.StringCoder;
import org.niko.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

//@Result(name="success", location="filelist.jsp")
@ParentPackage(value = "nikoPackage")		//应用全局包
@Results({
	@Result(name="success", location="niko_filelist.jsp"), 
	@Result(name="error", location="/errors.jsp"),
	@Result(name="bxx_success", location="niko_bxxlist.jsp"),
	@Result(name="trash_success", location="niko_trashlist.jsp")
})
public class FileListAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6241323811308122019L;
//	private String basePathStr ;
	private Logger logger = Logger.getLogger(FileListAction.class) ;
	// get set
	int pageNo = 0;
	int lastPageNo;
	int nextPageNo;
	int pageCount;
	Pager pager;
	/*
	 *  /allprivl/niko/dir(with suffix"/")
	 */
	private String dir;
	private String emptyStr = "";
	//list1, 目录
	List<Map<String,String>> list1 = new ArrayList<Map<String, String>>();
	List<Map<String,String>> list2 = new ArrayList<Map<String, String>>();
	private File[] fileArr;
	
	// FIXME
	private String username = (String) ServletActionContext.getContext().getSession().get("username");
	private String[] folderLevelArr;
	
	// 列出文件夹
	@Action("/filelist")
	public String filelist() {
		try {
			return doShareList();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	// 分享列表
	public String doShareList() throws Exception  {
			//URL url = new URL("http://192.168.0.180:50070/webhdfs/v1/allprivl?op=LISTSTATUS");
		valiReq();
		LOG.info("request dir : " + dir);
		this.folderLevelArr = dir.split("/") ;
		// 文件夹的路径
		String urlStr = StringUtil.listStatusURL(username, dir);
		LOG.info("share list url : "+urlStr);
		URL url = new URL(urlStr);//baseUrlStr+"/"+username+dir+"?op=LISTSTATUS"
		doList(url);
		return "success";
	}
	
	@Override
	boolean valiReq() throws Exception { 
		if(dir == null || dir.equals("")){
			dir = "/";
		}
		
		username = StringCoder.url2UTF8(username);
		dir = StringCoder.url2UTF8(dir);
		
		return  false;
	}

	@Action("/filelist-bxx")
	public String bxxList() {
		try {
			valiReq();
			
			LOG.info("request dir : " + dir);
			this.folderLevelArr = dir.split("/") ;
			// 文件夹的路径
			String urlStr = StringUtil.listStatusURLBXX(username, dir);
			LOG.info("bxx list url : "+urlStr);
			URL url = new URL(urlStr);//baseUrlStr+"/"+username+dir+"?op=LISTSTATUS"
			doList(url);
			return "bxx_success";
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage("找不到该文件.");
			return "error";
		}
	}
	
	@Action("/filelist-trash")
	public String trashList() {
		try {
			valiReq();
			
			LOG.info("request dir : " + dir);
			this.folderLevelArr = dir.split("/") ;
			// 文件夹的路径
			String urlStr = StringUtil.listStatusURLTrash(username, dir);
			LOG.info("trash list url : "+urlStr);
			URL url = new URL(urlStr);//baseUrlStr+"/"+username+dir+"?op=LISTSTATUS"
			doList(url);
			return "trash_success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	private void getTreeId2Dir() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String idStr = StringUtils.stripToEmpty(request.getParameter("id")) ;
		LOG.info("request tree_id : "+idStr) ;
		if ("".equals(idStr)) {
			idStr = "/" ;
		}
		this.dir = idStr;
	}

	@SuppressWarnings("rawtypes")
	private void doList(URL url) throws IOException { 
		URLConnection urlConn = url.openConnection();
		//urlConn.setRequestProperty("", "");
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			is = urlConn.getInputStream();
			for (String line : IOUtils.readLines(is, "UTF-8")) {
				sb.append(line) ;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (is != null)
				is.close() ;
		}
		
		LOG.info("json:"+sb.toString());

		//开始解析
		JSONObject fileStatusesObj = JSONObject.fromObject(sb.toString());
		JSONObject fileStatusObj = JSONObject.fromObject(fileStatusesObj.get("FileStatuses"));
		JSONArray arr = JSONArray.fromObject(fileStatusObj.get("FileStatus"));
		
		// 分类排序
		// 目录类型
		List<Map<String,String>> l1 = new ArrayList<Map<String, String>>();
		List<Map<String,String>> l2 = new ArrayList<Map<String, String>>();
		for(int i=0;i<arr.size();i++)
		{
			JSONObject jsonObject = arr.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for(Iterator it = (Iterator) jsonObject.keys();it.hasNext();){
				String key = (String)it.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
			}
			
			if("DIRECTORY".equals(map.get("type")) )
				l1.add(map);
			else
				l2.add(map);
		}
		//设置分页
		int finishNum = 0;
		for(int i=(pageNo*10); i < l1.size() && finishNum<10; i++,finishNum++){
			list1.add(l1.get(i));
		}
		//在
		if(finishNum == 0){//只需从list1取
			int startIndex = pageNo*10-l1.size();
			for(int i=startIndex;i<l2.size() && finishNum<10;i++,finishNum++)
				list2.add(l2.get(i));
		} else if(finishNum == 10) {
			// 十个了, do nothing
		} else {//需从两个list取
			int needNum = 10-finishNum;
			for (int i=0;i<l2.size() && i<needNum;i++, finishNum++) {
				list2.add(l2.get(i));
			}
		}
		
		if(pageNo == 0)
			lastPageNo = pageNo;
		else
			lastPageNo = pageNo - 1;
		
		if (pageNo*10+10 >= l1.size() + l2.size())
			nextPageNo = pageNo;
		else
			nextPageNo = pageNo + 1;
		
		if ((l1.size() + l2.size()) % 10 == 0) {
			pageCount = (l1.size() + l2.size()) / 10;
		} else
			pageCount = 1 + (l1.size() + l2.size()) / 10;
		is.close();
	}
	
	
	
	/**
	 * 返回JSON Object, 供树形菜单调用 
	 * @throws Exception 
	 */
	@Action("/querySubFolder")
	public void listSubFolderNoRecur(){
		try {
			valiReq();
			getTreeId2Dir();
			
			String queryURL = StringUtil.listStatusURL(username, dir);//this.baseUrlStr+"/"+this.username+this.dir+"?op=LISTSTATUS";
			
			doQuerySubFolder(queryURL);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Action("/querySubFolder-bxx")
	public void listSubFolderNoRecurBXX(){
		try {
			valiReq();
			getTreeId2Dir();
			String queryURL = StringUtil.listStatusURLBXX(username, dir);//this.baseUrlStr+"/"+this.username+this.dir+"?op=LISTSTATUS";
			doQuerySubFolder(queryURL);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	private void doQuerySubFolder(String queryURL) throws Exception {
		
		init();
		
		JSONArray subsArr = new JSONArray() ;
		JSONObject subsJo = new JSONObject() ;
		String queryResult = querySonsFromHD(queryURL) ;
		
		JSONObject fileStatusesObj = JSONObject.fromObject(new String(queryResult.getBytes("ISO-8859-1"), "UTF-8"));
		JSONObject fileStatusObj = JSONObject.fromObject(fileStatusesObj.get("FileStatuses"));
		JSONArray fileStatusArr = JSONArray.fromObject(fileStatusObj.get("FileStatus"));
		for (Object fooJo : fileStatusArr) {
			JSONObject jsonObject = (JSONObject) fooJo;
			if ("DIRECTORY".equals(jsonObject.get("type"))) {
				subsJo.put("id", dir+jsonObject.get("pathSuffix")+"/") ;
				subsJo.put("text", jsonObject.get("pathSuffix")) ;
				subsJo.put("state", "closed") ;
				
				subsArr.add(subsJo) ;
			}
		}
		
		ServletActionContext.getResponse().getWriter().write(subsArr.toString()) ;
	}


	/**
	 * 查询HDFS, 返回JSON结果
	 * @return
	 * @throws Exception 
	 */
	private String querySonsFromHD(String queryURL) throws Exception {
		
		StringBuilder sb = new StringBuilder(); 
		
		 
		URL url = new URL(queryURL); 
		URLConnection urlConn = url.openConnection();
		//urlConn.setRequestProperty("", "");
		InputStream is = urlConn.getInputStream();
		List<String> linesNotUTF = IOUtils.readLines(is) ;
		for (String line : linesNotUTF) {
			sb.append(line) ;
		}
		return sb.toString() ;
	}



	public List<Map<String, String>> getList1() {
		return list1;
	}
	public void setList1(List<Map<String, String>> list1) {
		this.list1 = list1;
	}
	public List<Map<String, String>> getList2() {
		return list2;
	}
	public void setList2(List<Map<String, String>> list2) {
		this.list2 = list2;
	}
	public File[] getFileArr() {
		return fileArr;
	}
	public void setFileArr(File[] fileArr) {
		this.fileArr = fileArr;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String[] getFolderLevelArr() {
		return folderLevelArr;
	}
	public void setFolderLevelArr(String[] folderLevelArr) {
		this.folderLevelArr = folderLevelArr;
	}
	public String getEmptyStr() {
		return emptyStr;
	}
	public void setEmptyStr(String emptyStr) {
		this.emptyStr = emptyStr;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@Override
	void init() {
		// TODO Auto-generated method stub
		
	}
}
