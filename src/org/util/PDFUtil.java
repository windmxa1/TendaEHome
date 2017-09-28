package org.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.table.TableColumn;

import org.apache.commons.io.FileUtils;
import org.view.VOrdersDetailsId;
import org.view.VOrdersId;
import org.view.VRepairOrderId;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * pdf工具类
 * 
 * @author Administrator
 * 
 */
public class PDFUtil {
	/**
	 * 生成pdf文件
	 * 
	 * @param imageFile
	 *            水印文件的绝对路径
	 * @param data
	 *            数据数组
	 * @param type
	 *            0商品订单，1报修订单，2暂无
	 */
	public static String buidPDF(String imageFile, List data, Integer type) {
		// File file = File.createTempFile("tempFile", ".pdf"); // 创建临时文件
		File file = new File(Constants.pdfDir + "test.pdf");// 模板文件
		Double a = 100000 + Math.random() * 899999;// 6位随机数
		String filename = ChangeTime.timeStamp() + a.intValue();
		Integer currentDate = ChangeTime.currentDate();
		String pdfFile = Constants.pdfDir + currentDate + Constants.dot
				+ filename + ".pdf"; // 输出文件
		File file1 = new File(pdfFile);
		if (!file1.getParentFile().exists()) {
			System.out.println("目标文件的目录不存在，准备创建目录...");
			if (!file1.getParentFile().mkdirs()) {
				System.out.println("创建目录失败");
			}
			try {
				System.out.println(file1.createNewFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("删除失败的旧文件数：" + deleteTemp() + "个");
		switch (type) {
		case 0:
			if (createOrdersPDF(file, data)) {
				System.out.println("添加水印");
				waterMark(file.getPath(), imageFile, pdfFile); // 添加水印
			}
			break;
		case 1:
			if (createRepairOrdersPDF(file, data)) {
				System.out.println("添加水印");
				waterMark(file.getPath(), imageFile, pdfFile); // 添加水印
			}
			break;
		default:
			break;
		}

		return Constants.pdfUrl + currentDate + "/" + filename + ".pdf";
	}
	/**
	 * 删除今天以前的所有临时文件夹
	 * 
	 * @return 返回未成功删除的文件夹个数
	 */
	private static int deleteTemp() {
		File file = new File(Constants.pdfDir);
		int i = 0;
		for (File f : file.listFiles()) {
			if (!f.getName().equals("" + ChangeTime.currentDate())) {
				try {
					if (f.isDirectory()) {
						FileUtils.deleteDirectory(f);
					} else {
						f.delete();
					}
				} catch (IOException e) {
					e.printStackTrace();
					i++;
				}
			}
		}
		return i;
	}

	/**
	 * 创建PDF文件
	 */
	private static boolean createOrdersPDF(File file, List<VOrdersId> list) {
		System.out.println("生成pdf");
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			// 字体处理，中文需要用到
			FontSelector selector = new FontSelector();
			// 设置英文显示字体TIMES_ROMAN及大小
			selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
			// 设置中文显示字体STSong-Light
			Font cf1 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED, 10);
			selector.addFont(cf1);
			// Font cf2 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H",
			// BaseFont.NOT_EMBEDDED, 14);

//			// 设置标题显示字体STSong-Light及大小
//			Font headerFont = FontFactory.getFont("STSong-Light",
//					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 18);
//			for (int index=0;index<list.size();index+=2) {
//				PdfPTable table2 = new PdfPTable(4);
//				PdfPCell nocell1 = new PdfPCell(selector.process("订单编号:"+list.get(index).getOrderNum()));
//				nocell1.setColspan(2);
//				table2.addCell(nocell1);
//				PdfPCell nocell2 = new PdfPCell(selector.process("订单编号:"+list.get(index+1).getOrderNum()));
//				nocell2.setColspan(2);
//				table2.addCell(nocell2);
//				
//				PdfPCell acell1 = new PdfPCell(selector.process("收货地址:"+list.get(index).getAddress()));
//				acell1.setColspan(2);
//				table2.addCell(acell1);
//				PdfPCell acell2 = new PdfPCell(selector.process("收货地址:"+list.get(index+1).getAddress()));
//				acell2.setColspan(2);
//				table2.addCell(acell2);
//				
//				String content1  = "";
//				for (VOrdersDetailsId vDetails :list.get(index).getDetails()) {
//					content1 = content1+vDetails.getGoodsName()+" "+vDetails.getTotalNum()+"\n";
//				}
//				table2.addCell(selector.process(content1));
//				String pic1 = MatrixToImageWriter.buildQRCode("http://39.108.82.55:8080/TendaEHome/mobileHtml/staffOrder.html?orderNum="+list.get(index).getOrderNum());
//				Image jpg1 = Image.getInstance(pic1);
//				jpg1.setAlignment(Image.ALIGN_CENTER);
//				jpg1.scaleAbsolute(50, 50);
//				PdfPCell pcell1 = new PdfPCell(jpg1, false);
//				pcell1.setHorizontalAlignment(1);
//				table2.addCell(pcell1);
//				
//				String content2  = "";
//				for (VOrdersDetailsId vDetails :list.get(index+1).getDetails()) {
//					content2 = content2+vDetails.getGoodsName()+" "+vDetails.getTotalNum()+"\n";
//				}
//				table2.addCell(selector.process(content2));
//				String pic2 = MatrixToImageWriter.buildQRCode("http://39.108.82.55:8080/TendaEHome/mobileHtml/staffOrder.html?orderNum="+list.get(index+1).getOrderNum());
//				Image jpg2 = Image.getInstance(pic2);
//				jpg2.setAlignment(Image.ALIGN_CENTER);
//				jpg2.scaleAbsolute(50, 50);
//				PdfPCell pcell2 = new PdfPCell(jpg2, false);
//				pcell2.setHorizontalAlignment(1);
//				table2.addCell(pcell2);
//				table2.setHeaderRows(2);//防止表头被分割
//				doc.add(table2);
//			}
			for (VOrdersId v : list) {
				PdfPTable table2 = new PdfPTable(2);
				PdfPCell cell = new PdfPCell(selector.process("订单编号:"+v.getOrderNum()));
				cell.setColspan(2);
				table2.addCell(cell);
				
				PdfPCell cell1 = new PdfPCell(selector.process("收货地址:"+v.getAddress()));
				cell1.setColspan(2);
				table2.addCell(cell1);
				
				String content  = "";
				for (VOrdersDetailsId vDetails : v.getDetails()) {
					content = content+vDetails.getGoodsName()+" "+vDetails.getTotalNum()+"\n";
				}
				table2.addCell(selector.process(content));
				String pic = MatrixToImageWriter.buildQRCode("http://39.108.82.55:8080/TendaEHome/mobileHtml/staffOrder.html?orderNum="+v.getOrderNum());
				Image jpg = Image.getInstance(pic);
				jpg.setAlignment(Image.ALIGN_CENTER);
				jpg.scaleAbsolute(50, 50);
				PdfPCell cell2 = new PdfPCell(jpg, false);
				cell2.setHorizontalAlignment(1);
				table2.addCell(cell2);
				table2.setHeaderRows(2);//防止表头被分割
				doc.add(table2);
			}
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建PDF文件
	 */
	private static boolean createRepairOrdersPDF(File file,
			List<VRepairOrderId> list) {
		System.out.println("生成pdf");
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			// 字体处理，中文需要用到
			FontSelector selector = new FontSelector();
			// 设置英文显示字体TIMES_ROMAN及大小
			selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
			// 设置中文显示字体STSong-Light
			Font cf1 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED, 10);
			selector.addFont(cf1);
			// 设置标题显示字体STSong-Light及大小
			Font headerFont = FontFactory.getFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 18);
			PdfPTable table = new PdfPTable(4);
			PdfPCell cell = new PdfPCell(new Paragraph("订单信息", headerFont));
			cell.setHorizontalAlignment(1);
			cell.setColspan(4);
			table.addCell(cell);
			table.addCell(selector.process("维修内容"));
			table.addCell(selector.process("预定上门时间"));
			table.addCell(selector.process("维修地址"));
			table.addCell(selector.process("预留电话"));
			for (VRepairOrderId v : list) {
				table.addCell(selector.process("" + v.getDescription()));
				table.addCell(selector.process("" + v.getAppointmentTime()));
				table.addCell(selector.process("" + v.getAddress()));
				table.addCell(selector.process("" + v.getPhone()));
			}
			doc.add(table);
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void waterMark(String inputFile, String imageFile,
			String outputFile) {
		try {
			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
					outputFile));
			// // 获取总页数
			// int total = reader.getNumberOfPages() + 1;
			// Image image = Image.getInstance(imageFile);
			//
			// // 图片位置
			// image.setAbsolutePosition(0, 0);
			// image.scaleToFit(100, 200);
			// PdfContentByte under;
			// for (int i = 1; i < total; i++) {
			// under = stamper.getUnderContent(i);
			// under.beginText();
			//
			// // 添加水印文字
			// under.endText();
			//
			// // 添加水印图片
			// PdfGState gs = new PdfGState();
			// gs.setFillOpacity(0.15f);// 设置透明度为0.2
			// under.setGState(gs);
			//
			// // under.addImage(image);
			// for (int l = 0; l < 6; l++) {
			// for (int t = 0; t < 16; t++) {
			// image.setAbsolutePosition(l * 100, t * 50);
			// under.addImage(image);
			// }
			// }
			// // 画个圈
			// // under.ellipse(250, 450, 350, 550);
			// under.setLineWidth(1f);
			// under.stroke();
			// }
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// String watermark = "C:\\Users\\Administrator\\Desktop\\timg.jpg"; // 水印图片
	//
	// ZAlarmDao aDao = new ZAlarmDaoImp();
	// buidPDF( watermark, "正版授权", aDao.getAlarmList(0, -1, 2), 0);
	// ZExceptionDao eDao = new ZExceptionDaoImp();
	// buidPDF( watermark, "正版授权", eDao.getExceptionList(0, -1, 2), 1);
	// ZLogDao lDao = new ZLogDaoImp();
	// buidPDF(source, watermark, "正版授权", lDao.getLogList(0, -1), 2);
	//
	// }
}
