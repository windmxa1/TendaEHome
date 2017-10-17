package org.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class Utils {
	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String ran6() {
		Double a = 100000 + Math.random() * 899999;
		return "" + a.intValue();
	}
	/**
	 * 生成4位随机数
	 * 
	 * @return
	 */
	public static String ran4() {
		Double a = 1000 + Math.random() * 8999;
		return "" + a.intValue();
	}
	/**
	 * 实体转Map
	 */
	public static LinkedHashMap<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    }   
}
