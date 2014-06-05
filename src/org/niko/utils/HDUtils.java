package org.niko.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

public class HDUtils {
	public static void doMkdir(String urlStr) throws IOException {
		
		URL url;
		try {
			//url = new URL("http://192.168.0.180:50070/webhdfs/v1/allprivl/niko/novels/chinese?op=MKDIRS");
			
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("PUT");
			int r = urlConn.getResponseCode();
System.out.println("mkdir resp_code : "+r);
			InputStream is = urlConn.getInputStream();
			int i = 0;
			while((i=is.read()) != -1 )
			{
				System.out.print((char)i);
			}
			is.close();
			ServletActionContext.getResponse().getWriter().print("{\"status\":\"success\"}");
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getResponse().getWriter().print("{\"status\":\"fail\"}");
		}
	}
	
	private void doHDRename(String urlStr) throws Exception { 
		System.out.println(urlStr); 
		URL url = new URL(urlStr);
		
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		urlConn.setRequestMethod("PUT");
		
		int r = urlConn.getResponseCode();
System.out.println("rename put code:"+r);
		InputStream is = urlConn.getInputStream();
		
		int i = 0;
		while((i=is.read()) != -1 )
		{
			System.out.print((char)i);
		}
		System.out.println();
		is.close();
	}
	
	public static boolean notFileExist(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		URLConnection urlConn = url.openConnection();
		//urlConn.setRequestProperty("", "");
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			is = urlConn.getInputStream();
			for (String line : IOUtils.readLines(is, "UTF-8")) {
				sb.append(line) ;
			}
			
			System.out.println("isFileExist url resp : "+sb.toString()); 
			if (sb.toString().contains("\"FileNotFoundException\"") || sb.toString().contains("\"exception\"")) {
				return true;
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				return true;
			} else
				throw e;
		} finally {
			if (is != null)
				is.close() ;
		}
		return false;
	}
}
