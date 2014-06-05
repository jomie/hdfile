package org.niko.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

//@ParentPackage("json-default") 
//@Result(name = "json" , type = "json")  
public class AsyncAction extends ActionSupport {

//	private JSONObject outObj ;
//	private String outStr ;
	
	@Action("/querySonsTest")
	public void asyncTree() throws FileNotFoundException, IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String queryStr = request.getQueryString() ;
		System.out.println(queryStr); 
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameterNames());
		Enumeration enumer = request.getParameterNames();
		
		while (enumer.hasMoreElements()) {
			System.out.println(enumer.nextElement());
		}
		List<String> list = IOUtils.readLines(new FileInputStream("d:\\li.txt")) ;
		for (String line : list) {
			ServletActionContext.getResponse().getWriter().print(line) ;
		}
		
//		return "json";
	}
//	public JSONObject getOutObj() {
//		return outObj;
//	}
//	public void setOutObj(JSONObject outObj) {
//		this.outObj = outObj;
//	}
//	public String getOutStr() {
//		return outStr;
//	}
//	public void setOutStr(String outStr) {
//		this.outStr = outStr;
//	}
}
