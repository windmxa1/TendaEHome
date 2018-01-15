package org.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.TvDao;
import org.dao.imp.TvDaoImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;
import org.view.VTvId;

@Controller("/app/TvController")
@RequestMapping("/app/tv")
public class TvController {
	private TvDao tDao;
	private Map<String, Object> data;

	@RequestMapping("/getTvList")
	@ResponseBody
	public Object getTvList(Integer start, Integer limit) throws Exception {
		tDao = new TvDaoImp();
		data = new HashMap<>();
		List<VTvId> list = tDao.getTVList(start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		} else {
			data.put("list", list);
			return ResultUtils.toJson(100, "", data);
		}
	}

	/**
	 * 搜索直播
	 */
	@RequestMapping("/getTvByName")
	@ResponseBody
	public Object getTvByName(String name, Integer start, Integer limit)
			throws Exception {
		tDao = new TvDaoImp();
		List<VTvId> list = tDao.getTvByName(name, start, limit);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "搜索不到相关节目", "");
		}
		data = new HashMap<>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

}
