package org.dao;

import org.model.AppVersion;

public interface VersionDao {
	/**
	 * 获取最新的版本号
	 */
	public AppVersion getLastVersion();	
}
