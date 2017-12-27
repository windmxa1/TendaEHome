package org.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.dao.GoodsCatalogDao;
import org.dao.GoodsDao;
import org.dao.imp.GoodsCatalogDaoImp;
import org.dao.imp.GoodsDaoImp;
import org.model.Goods;
import org.model.GoodsCatalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.util.Utils;
import org.view.VGarouselId;
import org.view.VGoodsId;

@Controller
@RequestMapping("/back/goods")
public class GoodsController {
	GoodsDao gDao;
	GoodsCatalogDao gcDao;
	Map<String, Object> data;

	// 获取商品信息
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public Object getGoodsList(Integer start, Integer limit) throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1, (short) 0 };
		List<VGoodsId> list = gDao.getList(start, limit, state);
		if (list != null) {
			data.put("list", list);
			data.put("total", gDao.getCount(state));
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 搜索商品
	@RequestMapping("/searchGoods")
	@ResponseBody
	public Object searchGoods(Integer start, Integer limit, String key)
			throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		Short[] state = { (short) 1, (short) 0 };
		List<VGoodsId> list = gDao.getGoodsByKey(start, limit, key, state, 0);
		if (list != null) {
			data.put("list", list);
			data.put("total", gDao.getCountByKey(key, state, 0));
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取目录列表
	@RequestMapping("/getRestaurantList")
	@ResponseBody
	public Object getRestaurantList() throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<GoodsCatalog> list = gDao.getCatalog(2);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙", "");
		} else {
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	// 获取目录列表
	@RequestMapping("/getCatalog")
	@ResponseBody
	public Object getCatalog() throws Exception {
		gDao = new GoodsDaoImp();
		data = new HashMap<String, Object>();
		List<GoodsCatalog> list = gDao.getCatalog();
		if (list != null) {
			data.put("list", list);
			data.put("total", gDao.getCatalogCount());
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/addCatalog")
	@ResponseBody
	public Object addCatalog(HttpServletRequest request, GoodsCatalog catalog,
			@RequestParam(required = false) CommonsMultipartFile file)
			throws Exception {
		gcDao = new GoodsCatalogDaoImp();
		if (gcDao.getCatalog(catalog.getCatalog()) != null) {
			return ResultUtils.toJson(101, "添加失败,该目录已存在", "");
		}
		String url = Utils.getFileUrl(request.getSession().getServletContext()
				.getRealPath("/"), file, System.currentTimeMillis() / 1000,
				"upload/catalog");
		GoodsCatalog goodsCatalog = new GoodsCatalog(catalog.getCatalog(), url,
				catalog.getDescription(), catalog.getType());
		if (gcDao.saveOrUpdate(goodsCatalog) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	// 删除商品目录，伪删除，但会删除图片
	@RequestMapping("/delCatalog")
	@ResponseBody
	public Object delCatalog(HttpServletRequest request, @RequestParam Long id)
			throws Exception {
		gcDao = new GoodsCatalogDaoImp();
		if (gcDao.delete(id)) {
			try {
				File file = new File(request.getSession().getServletContext()
						.getRealPath("/upload/goods/")
						+ File.separator + id + File.separator);
				System.out.println(file.getAbsolutePath());
				FileUtils.deleteDirectory(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	// 上下架商品
	@RequestMapping("/updateGoodsState")
	@ResponseBody
	public Object updateGoodsState(HttpServletRequest request, Long id,
			Short state) throws Exception {
		gDao = new GoodsDaoImp();
		if (gDao.updateGoodsState(id, (short) Math.abs(state - 1))) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "操作失败", "");
	}

	@RequestMapping("/updateCatalog")
	@ResponseBody
	public Object updateCatalog(HttpServletRequest request,
			GoodsCatalog catalog,
			@RequestParam(required = false) CommonsMultipartFile file)
			throws Exception {
		gcDao = new GoodsCatalogDaoImp();
		String url = Utils.getFileUrl(request.getSession().getServletContext()
				.getRealPath("/"), file, System.currentTimeMillis() / 1000,
				"upload/catalog");
		if (url == null) {
			url = catalog.getUrl();
		}
		GoodsCatalog goodsCatalog = new GoodsCatalog(catalog.getId(),
				catalog.getCatalog(), url, catalog.getDescription(),
				catalog.getType());
		if (gcDao.saveOrUpdate(goodsCatalog) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	@RequestMapping("/addGoods")
	@ResponseBody
	public Object addGoods(HttpServletRequest request, String name,
			Double price, @RequestParam CommonsMultipartFile file,
			Long catalogId, String description, String origin, String unit,
			Double originPrice, Integer type) throws Exception {
		Long time = System.currentTimeMillis() / 1000;

		String path = request.getSession().getServletContext()
				.getRealPath("/upload/goods/");
		String filename = time + "_" + file.getOriginalFilename();
		String filePath = path + File.separator + catalogId + File.separator
				+ filename;
		// System.out.println(filePath);
		File newFile = new File(filePath);
		if (!newFile.getParentFile().exists()) {
			System.out.println("目标文件的目录不存在，准备创建目录...");
			if (!newFile.getParentFile().mkdirs()) {
				System.out.println("创建目录失败");
				return ResultUtils.toJson(101, "服务器繁忙请重试", "");
			}
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		String url = "upload/goods/" + catalogId + "/" + filename;

		Goods goods = new Goods(name, price, url, catalogId, description, time,
				origin, (short) 1, "g", 500, 0L, originPrice, type);
		if (description != null) {
			goods.setDescription(description);
		}
		if (origin != null) {
			goods.setOrigin(origin);
		}
		if (unit != null) {
			try {
				goods.setUnitName(unit.replaceAll("\\d+", ""));
				goods.setUnitNum(Integer.parseInt(unit.replaceAll("\\D+", "")));
			} catch (Exception e) {
				return ResultUtils.toJson(101, "添加失败，单位格式错误", "");
			}
		}
		gDao = new GoodsDaoImp();
		if (gDao.saveOrUpdate(goods) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		}
		return ResultUtils.toJson(101, "添加失败", "");
	}

	// 删除商品，伪删除
	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Object deleteGoods(HttpServletRequest request, Long id)
			throws Exception {
		gDao = new GoodsDaoImp();
		// 删除之前上传的商品图片
		Goods g = gDao.getGoods(id);
		if (g != null) {
			File f = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ g.getUrl());
			if (f.exists()) {
				f.delete();
			}
		}
		if (gDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		}
		return ResultUtils.toJson(101, "删除失败", "");
	}

	@RequestMapping("/updateGoods")
	@ResponseBody
	public Object updateGoods(HttpServletRequest request, Long id, String name,
			Double price,
			@RequestParam(required = false) CommonsMultipartFile file,
			Long catalogId, String description, String origin, String unit,
			Short state, Long saleNum, Double originPrice, Integer type)
			throws Exception {
		Long time = System.currentTimeMillis() / 1000;
		gDao = new GoodsDaoImp();
		String url = "";
		if (file.getOriginalFilename().equals("")) {
			Goods g = gDao.getGoods(id);
			if (g != null) {
				url = g.getUrl();
			} else {
				return ResultUtils.toJson(101, "修改失败", "原纪录不存在");
			}
		} else {
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/goods/");
			String filename = time + "_" + file.getOriginalFilename();

			String filePath = path + File.separator + catalogId
					+ File.separator + filename;
			File newFile = new File(filePath);
			if (!newFile.getParentFile().exists()) {
				System.out.println("目标文件的目录不存在，准备创建目录...");
				if (!newFile.getParentFile().mkdirs()) {
					System.out.println("创建目录失败");
					return ResultUtils.toJson(101, "服务器繁忙请重试", "");
				}
			}
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			url = "upload/goods/" + catalogId + "/" + filename;
			// 删除之前上传的商品图片
			Goods g = gDao.getGoods(id);
			if (g != null) {
				File f = new File(request.getSession().getServletContext()
						.getRealPath("/")
						+ g.getUrl());
				if (f.exists()) {
					f.delete();
				}
			}
		}
		Goods goods = new Goods(name, price, url, catalogId, description, time,
				origin, state, "g", 500, saleNum, originPrice, type);
		if (unit != null) {
			try {
				goods.setUnitName(unit.replaceAll("\\d+", ""));
				goods.setUnitNum(Integer.parseInt(unit.replaceAll("\\D+", "")));
			} catch (Exception e) {
				return ResultUtils.toJson(101, "修改失败，单位格式错误", "");
			}
		}
		goods.setId(id);
		if (gDao.saveOrUpdate(goods) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	// 获取目录对应的商品列表
	@RequestMapping("/getCataGoods")
	@ResponseBody
	public Object getCataGoods(Integer start, Integer limit, Long catalogId)
			throws Exception {
		gDao = new GoodsDaoImp();
		Short[] state = { (short) 1, (short) 0 };
		data = new HashMap<String, Object>();
		List<VGoodsId> list = gDao.getCataGoods(start, limit, catalogId, state);
		if (list != null) {
			data.put("list", list);
			data.put("total", gDao.getCountByCatalog(catalogId, state));
		} else {
			data.put("list", new ArrayList<>());
		}
		return ResultUtils.toJson(100, "", data);
	}

}
