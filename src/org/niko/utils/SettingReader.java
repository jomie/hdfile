package org.niko.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

public class SettingReader {
	Map map = new HashMap();
	String currentTag = null; 
	String toFind = null;
	
	public class ContentHandler extends DefaultHandler
	{
		@Override
		public void characters(char[] ch, int start, int length)
		{
			String curValue = new String(ch, start, length);
			if(currentTag != null && currentTag.equals(toFind))
			{
				map.put(currentTag, curValue);
System.out.println("curValue:"+curValue);
				currentTag = null;
				toFind = null;
			}
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
		{
			currentTag = qName;
		}
	}
	
	public String ReadValue(String key)
	{
		toFind = key;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			File file = new File(ServletActionContext.getServletContext().getRealPath("/")+"Setting.xml");
//这种可能会有url编码转换符,  从而是路径出错
//System.out.println(getClass().getClassLoader().getResource("Setting.xml").getPath());
//System.out.println(ServletActionContext.getServletContext().getRealPath("/"));
			//ServletActionContext.getServletContext().getRealPath("");
			
			javax.xml.parsers.SAXParser parser = factory.newSAXParser();
			
//			parser.parse("Setting.xml", new ContentHandler());
			parser.parse(file, new ContentHandler());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String)map.get(key);
	}
	
	public static void main(String[] args) throws IOException
	{
		SettingReader r = new SettingReader();
		System.out.println("r.ReadValue():"+r.ReadValue("basepath"));
	}
}
