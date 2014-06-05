package org.niko.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.niko.dao.impl.GradeDAOImpl;

import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value = "nikoPackage")
@Results({
	@Result(name="fff", location="search_result.jsp"),
	@Result(name="ffffff", location="search_ft_result.jsp")
})
public class GradeAction extends ActionSupport {
	
	private int fileid;
	private short grade;
	private String username = (String) ServletActionContext.getContext().getSession().get("username");
	
	@Action("submitGrade")
	public void submitGrade() throws IOException { 
		new GradeDAOImpl().SubmitGrade(grade, username, fileid);
		short newGrade = new GradeDAOImpl().UpdateAvgGrade(fileid);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.getWriter().print("{\"status\":\"success\", \"msg\":\"评分成功\", \"newGrade\":\""+newGrade+"\"}");
		} catch (IOException e) {
			resp.getWriter().print("{\"status\":\"fail\", \"msg\":\"评分成功\", \"newGrade\":\""+newGrade+"\"}");
			LOG.info("", e);
		}
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}
	
}
