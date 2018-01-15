package org.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.GarouselCatalogDao;
import org.dao.GarouselDao;
import org.dao.imp.GarouselCatalogDaoImp;
import org.dao.imp.GarouselDaoImp;
import org.model.Garousel;
import org.model.GarouselCatalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.view.VGarouselId;

@Controller
@RequestMapping("/back/garousel")
public class GarouselController {
	GarouselDao gDao;
	GarouselCatalogDao gcDao;
	Map<String, Object> data;

	@RequestMapping("/getGarousels")
	@ResponseBody
	public Object getGarousels(Integer start, Integer limit)throws Exception {
		gDao = new GarouselDaoImp();
		data = new HashMap<String, Object>();
		List<VGarouselId> list = gDao.getList(start, limit);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getCatalog")
	@ResponseBody
	public Object getCatalog(Integer start, Integer limit)throws Exception {
		gcDao = new GarouselCatalogDaoImp();
		data = new HashMap<String, Object>();
		List<GarouselCatalog> list = gcDao.getCatalog(start, limit);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);

	}

	@RequestMapping("/deleteGarousel")
	@ResponseBody
	public Object deleteGarousel(Long id)throws Exception {
		gDao = new GarouselDaoImp();
		if (gDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(101, "删除失败", "");
		}
	}

	@RequestMapping("/addGarousel")
	@ResponseBody
	public Object insertGarousel(HttpServletRequest request, String title,
			@RequestParam CommonsMultipartFile file, Integer catalogId,
			String hyperlink) throws Exception {
		gDao = new GarouselDaoImp();

		Long time = System.currentTimeMillis() / 1000;

		String path = request.getSession().getServletContext()
				.getRealPath("/upload/garousel/");
		String filename = time + "_" + file.getOriginalFilename();
		String filePath = path + filename;
		File newFile = new File(filePath);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		String url = "upload/goods/" + filename;
		Garousel garousel = new Garousel(title, url, catalogId, hyperlink, time);
		if (gDao.saveOrUpdate(garousel) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/updateGarousel")
	@ResponseBody
	public Object updateGarousel(HttpServletRequest request, Long id,
			String title,
			@RequestParam(required = false) CommonsMultipartFile file,
			Integer catalogId, String hyperlink)  throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/garousel/");
		gDao = new GarouselDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		String url = "";
		if (file.getOriginalFilename().equals("")) {
			VGarouselId g = gDao.getGarousel(id);
			if (g != null) {
				url = g.getUrl();
			} else {
				return ResultUtils.toJson(101, "修改失败", "原纪录不存在");
			}
		} else {
			String filename = time + "_" + file.getOriginalFilename();
			String filePath = path + filename;
			File newFile = new File(filePath);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			url = "upload/goods/" + filename;
		}

		Garousel garousel = new Garousel(title, url, catalogId, hyperlink, time);
		garousel.setId(id);
		if (gDao.saveOrUpdate(garousel) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	@RequestMapping("/addCatalog")
	@ResponseBody
	public Object insertCatalog(String catalog) throws Exception{
		gcDao = new GarouselCatalogDaoImp();
		GarouselCatalog gCatalog = new GarouselCatalog(catalog);
		if (gcDao.saveOrUpdate(gCatalog) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/updateCatalog")
	@ResponseBody
	public Object updateCatalog(Integer id, String catalog) throws Exception{
		gcDao = new GarouselCatalogDaoImp();
		GarouselCatalog gCatalog = new GarouselCatalog(catalog);
		gCatalog.setId(id);
		if (gcDao.saveOrUpdate(gCatalog) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	@RequestMapping("/deleteCatalog")
	@ResponseBody
	public Object deleteCatalog(Integer id) throws Exception{
		gcDao = new GarouselCatalogDaoImp();
		if (gcDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(101, "删除失败", "");
		}

	}

}
