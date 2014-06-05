package org.niko.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropUtil {
	Properties pp = new Properties();
	InputStream is;
	OutputStream os;
	
	public void loadFile(File file){
		try {
			is = new FileInputStream(file);
			pp.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setValue(String key, String value){
		
		pp.setProperty(key, value);
		
	}
	
	public void writeFileAndClose(File file){
		try {
			os = new FileOutputStream(file); 
			pp.store(os, "");
			
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
