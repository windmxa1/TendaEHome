package org.test;

import java.util.ArrayList;
import java.util.List;

import org.dao.GoodsDao;
import org.dao.OrdersDao;
import org.dao.imp.GoodsDaoImp;
import org.dao.imp.OrdersDaoImp;
import org.util.Constants;
import org.util.JsonUtils;
import org.util.MatrixToImageWriter;
import org.util.PDFUtil;
import org.view.VGoods;
import org.view.VOrdersId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class Test05 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {

		try {
			String content = "http://39.108.82.55:8080/TendaEHome/back/admin/login";
			// String path = "D:/tt";
			String path = "D:\\";
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "gb2312");
			BitMatrix bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, 200, 200, hints);
			// 生成二维码
			File outputFile = new File(path, "1.png");
			MatrixToImageWriter.writeToFile(bitMatrix, "png", outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
