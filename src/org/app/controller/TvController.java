package org.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.TvDao;
import org.dao.imp.TvDaoImp;
import org.model.Tv;
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
	public Object getTvList(Integer start, Integer limit)throws Exception {
		tDao = new TvDaoImp();
		data = new HashMap<>();
		List<VTvId> list = tDao.getTVList(start, limit);
		if (list == null || list.size() == 0) {
			data.put("list", new ArrayList<>());
			return ResultUtils.toJson(100, "暂无直播内容", data);
		} else {
			data.put("list", list);
			return ResultUtils.toJson(100, "", data);
		}
	}

}
