package org.niko.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class LoginUtil {

	public static boolean valid(String username, String password) {
		Properties ppts = new Properties();
		String path = ServletActionContext.getServletContext().getRealPath("/");
		
		try {
			File f = new File(path+"account/"+username+".properties");
			if(f.exists()){
				ppts.load(new FileInputStream(f));
				if(password.equals(ppts.get("password")))
					return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
