package org.dao;

import org.model.ScrollNews;

public interface ScrollNewsDao {
	/**
	 * 获取最新消息
	 */
	public ScrollNews getLastNews();
	/**
	 * 根据星期获取对象
	 */
	public ScrollNews getNews(Integer usage, Integer weekday);
}
