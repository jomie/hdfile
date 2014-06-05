package org.niko.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.niko.dao.impl.UploadDAOImpl;

public class UploadUtil {
	
	String basePath = new SettingReader().ReadValue("basePath");

	
	@SuppressWarnings("deprecation")
	public void uploadToHDFS(String username, String relativePath, InputStream inputStream){
		//
		//PutMethod put = new PutMethod("http://192.168.0.180:50070/webhdfs/v1/allprivl/niko/novels/toup.txt?op=OPEN");
System.out.println("PUT URL :"+basePath+"/"+username+relativePath+"?op=OPEN");		
		PutMethod put = new PutMethod(basePath+"/"+username+relativePath+"?op=CREATE");
		try {
			
			HttpClient hc = new HttpClient();
			int httpCode = hc.executeMethod(put);
			System.out.println("CODE:"+httpCode);
			
			if(httpCode ==307){
				Header h = put.getResponseHeader("location");
				if(h != null){
					String reLocation = h.getValue();
					PutMethod put2 = new PutMethod(reLocation);
					put2.setRequestBody(inputStream);
//					new FileRequestEntity(file, contentType)
//					put2.setRequestEntity(null);
					System.out.println("after redirect code:"+hc.executeMethod(put2));
				}
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public void uploadShare(String username, String relativePath, File file, String contentType) throws Exception{
		System.out.println("relativePath"+relativePath); 
		String urlStr = StringUtil.uploadURL(username, relativePath);
		uploadEntityToHDFS( urlStr,  file,  contentType);
	}
	
	public void uploadBXX(String username, String relativePath, File file, String contentType) throws Exception{
		System.out.println("relativePath"+relativePath); 
		String urlStr = StringUtil.uploadURLBXX(username, relativePath);
		uploadEntityToHDFS( urlStr,  file,  contentType);
	}
	
	private void uploadEntityToHDFS(String urlStr, File file, String contentType){
		//
		//PutMethod put = new PutMethod("http://192.168.0.180:50070/webhdfs/v1/allprivl/niko/novels/toup.txt?op=OPEN");
		try {
			
			
			
			System.out.println("PUT URL :"+urlStr);	
//			urlStr = URLEncoder.encode(urlStr, "UTF-8");
//			System.out.println("PUT URL encoded :"+urlStr);	
			PutMethod put = new PutMethod(urlStr);
			
			HttpClient hc = new HttpClient();
			int httpCode = hc.executeMethod(put);
			System.out.println("CODE:"+httpCode);
			
			if (httpCode == 307) {
				Header h = put.getResponseHeader("location");
				if(h != null){
					String reLocation = h.getValue();
					PutMethod put2 = new PutMethod(reLocation);
//					put2.setRequestBody(inputStream);
					
					put2.setRequestEntity(new FileRequestEntity(file, contentType));
					System.out.println("after redirect code:"+hc.executeMethod(put2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		
	}

}
