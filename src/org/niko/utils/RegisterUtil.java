package org.niko.utils;

import java.io.File;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;


public class RegisterUtil {
	public boolean valid(String username){
		String path = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(path+"account"+File.separator+username+".properties");
		if( ! file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			return false;
		}
		return true;
	}
}
