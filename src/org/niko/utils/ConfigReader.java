package org.niko.utils;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.struts2.ServletActionContext;

public class ConfigReader {
	
//	private Logger log = Logger.getLogger(ConfigReader.class); 
	private XMLConfiguration conf ;
	
	public ConfigReader() throws ConfigurationException {
		String path = ServletActionContext.getServletContext().getRealPath("/")+"Setting.xml" ;
//		log.info("setting path : "+path);
		File file = new File(path) ; 
		this.conf = new XMLConfiguration(file) ;
	}
	
	public String getBasePath() {
		return this.conf.getString("basePath") ;
	}

	public String getBXXPath() {
		return this.conf.getString("baseBXXPath") ;
	}

	public String getTrashPath() {
		return this.conf.getString("baseTrashPath") ;
	}
}
