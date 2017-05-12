package org.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GarouselCatalogDao;
import org.dao.GarouselDao;
import org.dao.imp.GarouselCatalogDaoImp;
import org.dao.imp.GarouselDaoImp;
import org.model.Garousel;
import org.model.GarouselCatalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public Object getGarousels(Integer start, Integer limit) {
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
	public Object getCatalog(Integer start, Integer limit) {
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
	public Object deleteGarousel(Long id) {
		gDao = new GarouselDaoImp();
		if (gDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(101, "删除失败", "");
		}
	}

	@RequestMapping("/insertGarousel")
	@ResponseBody
	public Object insertGarousel(String title, String url, Integer catalogId,
			String hyperlink, Timestamp createTime) {
		gDao = new GarouselDaoImp();
		Garousel garousel = new Garousel(title, url, catalogId, hyperlink,
				createTime);

		if (gDao.saveOrUpdate(garousel) == 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/updateGarousel")
	@ResponseBody
	public Object updateGarousel(Long id, String title, String url,
			Integer catalogId, String hyperlink) {
		gDao = new GarouselDaoImp();
		Garousel garousel = new Garousel(title, url, catalogId, hyperlink);
		garousel.setId(id);
		if (gDao.saveOrUpdate(garousel) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	@RequestMapping("/insertCatalog")
	@ResponseBody
	public Object insertCatalog(String catalog) {
		gcDao = new GarouselCatalogDaoImp();
		GarouselCatalog gCatalog = new GarouselCatalog(catalog);
		if (gcDao.saveOrUpdate(gCatalog) == 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/updateCatalog")
	@ResponseBody
	public Object updateCatalog(Integer id, String catalog) {
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
	public Object deleteCatalog(Integer id) {
		gcDao = new GarouselCatalogDaoImp();
		if (gcDao.delete(id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(101, "删除失败", "");
		}

	}

}
