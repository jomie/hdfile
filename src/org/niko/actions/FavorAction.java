package org.niko.actions;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.niko.dao.impl.FavorDAOImpl;
import org.niko.dao.impl.GradeDAOImpl;
import org.niko.utils.Pager;

import test.bean.FavorUserFile;
import test.bean.File;
import test.bean.FileTag;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "nikoPackage")
@Result(name="myFavor", location="my_favor_list.jsp")
public class FavorAction extends ActionSupport {
	
	private static final long serialVersionUID = -4448699549360369114L;
	// get set
	private int pageNo = 1;
	private int pageSize = 10;
	private String dir;
	private String filename;
	private int fileid = -1;
	private Pager pager ;
	//self
	private String username = (String) ServletActionContext.getContext().getSession().get("username"); 
	private FavorDAOImpl favorDAO = new FavorDAOImpl();
	
	@Action("/favor")
	public void favor() throws IOException {
		if (fileid == -1) {
			System.out.println("参数错误.");
			return ;
		}
		
		favorDAO.favor(username, fileid);
		
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print("收藏成功."); 
	}
	
	@Action("/unfavor")
	public void unfavor() throws IOException {
		if (fileid == -1) {
			System.out.println("参数错误.");
			return ;
		}
		
		favorDAO.unfavor(username, fileid);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print("取消收藏成功."); 
	}
	
	@Action("/myFavor")
	public String myFavor() {
		this.pager = favorDAO.list(pageNo, pageSize, username);
		
		for (Object fObj : this.pager.getResultList()) {
			File f = (File) fObj;
//			for (Object ftObj : f.getFileTags()) {
//				FileTag ft = (FileTag) ftObj;
//				this.tagsMap.put(ft.getTags().getTagid(), ft.getTags());
//			}
			
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
		return "myFavor";
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
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
