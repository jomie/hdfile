package org.niko.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;

public class FileListUtil {

	//String basePath = "http://192.168.0.106:50070/webhdfs/v1/allprivl";
	String basePath ;
	private Logger LOG = Logger.getLogger(FileListUtil.class);
	String xmlPath;
	public FileListUtil(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public FileListUtil() {
		
	}
	public InputStream getInputStream(String relativePath) throws FileNotFoundException
	{
		URL url;
		try {
		basePath = new ConfigReader().getBasePath();
		
			url = new URL(basePath+relativePath+"?op=OPEN");
			
			LOG.info("url :"+basePath+relativePath+"?op=OPEN");

			URLConnection urlConn = url.openConnection();
			return urlConn.getInputStream();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			return new FileInputStream("d:sorry.txt");
		} 
	}
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		URL url = new URL("http://192.168.0.106:50030/webhdfs/v1/?op=OPEN");
		
	}

}
