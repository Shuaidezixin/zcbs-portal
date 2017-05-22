package com.zcbspay.platform.portal.website.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConfigParamsExcelHeader {
	private static Map<String, String> params=new HashMap<>();
	
	public  Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public ConfigParamsExcelHeader(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			InputStream in = ConfigParamsExcelHeader.class.getResourceAsStream("/excelheader.properties");
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
		setParams(map);
	} 
	
	public String getName(String pathName){
		return getParams().get(pathName);
	}
}
