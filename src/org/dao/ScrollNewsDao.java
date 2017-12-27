package org.dao;

import org.model.ScrollNews;

public interface ScrollNewsDao {
	/**
	 * 获取最新消息
	 */
	public ScrollNews getLastNews();
}
