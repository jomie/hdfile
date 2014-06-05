package org.niko.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.FileDAOImpl;
import org.niko.dao.impl.GradeDAOImpl;
import org.niko.utils.Pager;
import org.niko.utils.StringCoder;

import test.bean.FavorUserFile;
import test.bean.File;
import test.bean.FileTag;
import test.bean.Tags;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "nikoPackage")
@Results({
	@Result(name="input", location="/errors.jsp"),
	@Result(name="search_result", location="search_result.jsp"),
	@Result(name="search_ft_result", location="search_ft_result.jsp")
})
public class HDSearchAction extends BaseAction {
	private static final long serialVersionUID = -8735484148508459952L;
	
	int pageNo = 1;
	int pageSize = 10;
	int prePage;
	int nextPage;
	String keyword;
	int tid;
	String tname;
	Pager pager;
	Map<Integer , Tags> tagsMap = new HashMap<Integer, Tags>();
	//
	String username = (String) ServletActionContext.getContext().getSession().get("username");
	FileDAOImpl fileDAO = new FileDAOImpl() ;
	

	@Override
	void init() {
		
	}

	@Override
	boolean valiReq() throws Exception {
		
		if (tname != null) {
			tname = StringCoder.url2UTF8(tname);
			System.out.println("tname"+tname);
		}
		return true;
	}
	
	@Action("/search")
	public String search() {
System.out.println("keyword:"+keyword);	
		this.pager = fileDAO.search(pageNo, pageSize, keyword);
		
		pageNumCalcu();
		
		for (Object fObj : this.pager.getResultList()) {
			File f = (File) fObj;
			for (Object ftObj : f.getFileTags()) {
				FileTag ft = (FileTag) ftObj;
				this.tagsMap.put(ft.getTags().getTagid(), ft.getTags());
			}
			
			f.setCurUserFavor(false);
			for (Object fvufObj : f.getFavorUserFiles()) {
				FavorUserFile fvuf = (FavorUserFile) fvufObj;
				if (ServletActionContext.getContext().getSession().get("username").equals(fvuf.getUser().getUsername())) {
					f.setCurUserFavor(true);
					f.setHasGraded(new GradeDAOImpl().isExist(f.getFileid(), username));
					break;
				}
			}
		}
		return "search_result";
	}
	
	private void pageNumCalcu() {
		if (pageNo == 1)
			prePage = 1;
		else
			prePage = pageNo - 1;
		
		if (pageNo < (pager.getRowCount()/pageSize + 1))
			nextPage = pageNo + 1;
		else
			nextPage = pageNo;
	}

	@Action("/search-ft")
	public String searchFileByTagId() {
		
		LOG.info("==>start searchFileByTagId()...2014-5-21 13:20:32");
		try {
			doCommon();
		} catch (Exception e) {
			LOG.info("", e);
		}
		
		System.out.println("keyword:"+keyword);		
		this.pager = fileDAO.searchFileByTagId(pageNo, pageSize, tid);
		
//		if (pageNo == 1)
//			prePage = 1;
//		else
//			prePage = pageNo - 1;
//		
//		if (pageNo < (pager.getRowCount()/pageSize + 1))
//			nextPage = pageNo + 1;
//		else
//			nextPage = pageNo;
		
		pageNumCalcu();
		
		attrPagerOt();
		
		LOG.info("==>stop searchFileByTagId()...2014-5-21 13:20:32");
		return "search_result";
	}
	
	private void attrPagerOt() {
		for (Object fObj : this.pager.getResultList()) {
			File f = (File) fObj;
			for (Object ftObj : f.getFileTags()) {
				FileTag ft = (FileTag) ftObj;
				this.tagsMap.put(ft.getTags().getTagid(), ft.getTags());
			}
			
			f.setCurUserFavor(false);
			for (Object fvufObj : f.getFavorUserFiles()) {
				FavorUserFile fvuf = (FavorUserFile) fvufObj;
				if (ServletActionContext.getContext().getSession().get("username").equals(fvuf.getUser().getUsername())) {
					f.setCurUserFavor(true);
					f.setHasGraded(new GradeDAOImpl().isExist(f.getFileid(), username));
					break;
				}
			}
		}
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public Map<Integer, Tags> getTagsMap() {
		return tagsMap;
	}

	public void setTagsMap(Map<Integer, Tags> tagsMap) {
		this.tagsMap = tagsMap;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
}
