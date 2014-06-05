package org.niko.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

@SuppressWarnings("deprecation")
public class DownloadUtil {

	//String basePath = "http://192.168.0.106:50070/webhdfs/v1/allprivl";
	String basePath ;
	String xmlPath;
	public DownloadUtil(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public DownloadUtil() {
		
	}
	
	
	public InputStream getInputStream(String relativePath) throws FileNotFoundException
	{
		try {
		basePath = new SettingReader().ReadValue("basePath");
		
System.out.println("url :"+basePath+relativePath+"?op=OPEN");
		GetMethod get = new GetMethod(basePath+relativePath+"?op=OPEN"); 
		HttpClient hc = new HttpClient();
		hc.executeMethod(get);
System.out.println("status code:"+get.getStatusCode());

		String reLocation = get.getResponseHeader("location").getValue();
System.out.println("redirect:"+reLocation);

		GetMethod get2 = new GetMethod(reLocation); 
		hc.executeMethod(get2);
		
		return get2.getResponseBodyAsStream();
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return new FileInputStream("d:sorry.txt");
		}catch (IOException e) {
			e.printStackTrace();
			return new FileInputStream("d:sorry.txt");
		}
	}
	
	public InputStream getShareIs(String username, String relativePath) throws Exception {
		String urlStr = StringUtil.downloadURL(username, relativePath);
		return getInputStream(urlStr, username, relativePath);
	}
	
	public InputStream getBXXIs(String username, String relativePath) throws Exception {
		String urlStr = StringUtil.downloadURLBXX(username, relativePath);
		return getInputStream(urlStr, username, relativePath);
	}
	
	/**
	 * 用这个
	 * 
	 * @param username
	 * @param relativePath
	 * @return
	 * @throws FileNotFoundException
	 */
	private InputStream getInputStream(String urlStr, String username, String relativePath) throws FileNotFoundException
	{
		try {
			
System.out.println("step1 url :"+urlStr);
			GetMethod get1 = new GetMethod(urlStr);
			HttpClient hc = new HttpClient();
			hc.executeMethod(get1);
System.out.println("step 1 status code:"+get1.getStatusCode());
			// HC will do that step
			return get1.getResponseBodyAsStream();
		} catch (Exception e1) {
			e1.printStackTrace();
			return new StringBufferInputStream("sorry, some errors happened.");
		}
	}
	
	/**
	 * 用这个
	 * 
	 * @param username
	 * @param relativePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStreamOT(String pathWithUsername) throws FileNotFoundException
	{
		try {
			String urlStr = StringUtil.downloadOTURL(pathWithUsername);
System.out.println("step1 url :"+urlStr);
			GetMethod get1 = new GetMethod(urlStr);
			HttpClient hc = new HttpClient();
			hc.executeMethod(get1);
System.out.println("step 1 status code:"+get1.getStatusCode());
			// HC will do that step
			return get1.getResponseBodyAsStream();
		} catch (Exception e1) {
			e1.printStackTrace();
			return new StringBufferInputStream("sorry, some errors happened.");
		}
	}
	
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		URL url = new URL("http://192.168.0.106:50030/webhdfs/v1/allprivl/niko/2013-12-7------.txt?op=OPEN");
		
	}
}
