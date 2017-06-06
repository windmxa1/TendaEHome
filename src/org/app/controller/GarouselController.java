package org.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GarouselDao;
import org.dao.imp.GarouselDaoImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VGarouselId;

@Controller("/app/GarouselController")
@RequestMapping("/app/garousel")
public class GarouselController {
	GarouselDao gDao;
	Map<String, Object> data;

	@RequestMapping("/getGarousels")
	@ResponseBody
	public Object getGarousels(Integer catalogId) {
		gDao = new GarouselDaoImp();
		data = new HashMap<String, Object>();
		if (catalogId == null) {
			catalogId = 1;
		}
		List<VGarouselId> list = gDao.getListByCatalog(catalogId);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
		} else {
			data.put("list", list);
		}
		return ResultUtils.toJson(100, "", data);
	}
}
