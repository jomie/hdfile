package org.niko.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

public class HC1st extends ActionSupport {

	static {
		DOMConfigurator.configureAndWatch("D:log4j.xml", 1000);
	}
	
	@Action("/log")
	public void logLvl() {
//		DOMConfigurator.configureAndWatch("log4j.xml");
		
		Logger log = Logger.getLogger(HC1st.class);
		
		log.debug("2014-5-12 14:49:33");
		log.info("2014-5-12 14:49:33");
		log.warn("2014-5-12 14:49:33");
		log.error("2014-5-12 14:49:33");
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main11(String[] args) {
		// TODO Auto-generated method stub
		PutMethod put = new PutMethod("http://192.168.0.180:50070/webhdfs/v1/allprivl/niko/novels/toup.txt?op=CREATE");
		try {
			put.setRequestBody(new FileInputStream("d:\\toup.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpClient hc = new HttpClient();
		try {
			System.out.println(hc.executeMethod(put));
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//重定向的地址
		Header respHeader = put.getResponseHeader("location");
		String s = respHeader.getValue().toString();
		System.out.println(s);
		try {
			put.setURI(new URI(s));
			
			PutMethod put2 = new PutMethod(s);
			put2.setRequestBody(new FileInputStream("d:\\toup.txt"));
			System.out.println(hc.executeMethod(put2));
			System.out.println(put2.getResponseBodyAsString());
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		String path = HC1st.class.getClassLoader().getResource("").getPath();
		System.out.println(path); 
		System.out.println(System.getProperty("os.name"));
	}

}
