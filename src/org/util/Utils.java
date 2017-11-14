package org.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	public static LinkedHashMap<String, Object> objectToMap(Object obj)
			throws Exception {
		if (obj == null) {
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

	public static String getFileUrl(String realBasePath,
			CommonsMultipartFile file, Long time, String path) {
		String filename = time + "_" + file.getOriginalFilename();
		String filePath = realBasePath + path.replace("/", File.separator) + File.separator + filename;
		System.out.println(filePath);
		File newFile = new File(filePath);
		if (!newFile.getParentFile().exists()) {
			System.out.println("目标文件的目录不存在，准备创建目录...");
			if (!newFile.getParentFile().mkdirs()) {
				System.out.println("创建目录失败");
			}
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		try {
			file.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return path + "/" + filename;
	}
}
