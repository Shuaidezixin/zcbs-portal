package com.zcbspay.platform.portal.website.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.jsp.jstl.core.Config;

public class ConfigParams {
	private Map<String, String> urls=new HashMap<>();
	
	public Map<String, String> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}
	public ConfigParams(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			InputStream in = ConfigParams.class.getResourceAsStream("/url.properties");
			prop.load(in);
			
			Iterator<Object> it = prop.keySet().iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				String value = prop.getProperty(key);
				map.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setUrls(map);
	} 
}
