package org.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.TvDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.ResultUtils;

@Controller("/back/TvController")
@RequestMapping("/back/tv")
public class TvController {
	Map<String, Object> data;
	TvDao tDao;

	/**
	 * 获取直播列表
	 */
	@RequestMapping("/getTvList")
	@ResponseBody
	public Object getRepairOrderList(HttpServletRequest request) {

		return ResultUtils.toJson(100, "", "");
	}
}
