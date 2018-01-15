package org.app.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.dao.ScrollNewsDao;
import org.dao.imp.ScrollNewsDaoImp;
import org.model.ScrollNews;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.ResultUtils;

@RestController("/app/ScrollNewsController")
@RequestMapping("/app/scrollNews")
public class ScrollNewsController {
	Map<String, Object> data;
	ScrollNewsDao sDao;

	@RequestMapping("/getLastNews")
	public Object getLastNews(Integer usage) {
		sDao = new ScrollNewsDaoImp();
		data = new HashMap<String, Object>();
		data.put("news", sDao.getLastNews());
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/getNewsForCook")
	public Object getNews(Integer usage) {
		sDao = new ScrollNewsDaoImp();
		data = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		Integer day = calendar.get(calendar.DAY_OF_WEEK);
		ScrollNews news = sDao.getNews(usage, (day + 6) > 7 ? day - 1 : day);
		if (news != null) {
			news.setUrl("http://39.108.82.55:8080/TendaEHome/" + news.getUrl());
		}
		data.put("news", news);
		return ResultUtils.toJson(100, "", data);
	}
}
