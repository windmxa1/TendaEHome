package org.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.dom4j.DocumentException;
import org.util.XmlUtils;

public class Test03 {
	public static void main(String[] args) throws DocumentException, IOException {
		Map<String, Object> data = new HashMap<>();
		data.put("return_code", "FAIL");
		data.put("return_msg", "校验失败，请重试");
		System.out.println(XmlUtils.formatXml(XmlUtils.map2xml(data, "xml")));
		
	}
}
