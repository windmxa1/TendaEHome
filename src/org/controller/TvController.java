package org.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.TvDao;
import org.dao.imp.TvDaoImp;
import org.model.Tv;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.view.VTvId;

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
	public Object getTvList(HttpServletRequest request, Integer start,
			Integer limit) throws Exception {
		tDao = new TvDaoImp();
		List<VTvId> list = tDao.getTVList1(start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		}
		data = new HashMap<>();
		data.put("list", list);
		data.put("total", tDao.getTVCount());
		return ResultUtils.toJson(100, "", data);

	}

	/**
	 * 添加直播
	 */
	@RequestMapping("/addTv")
	@ResponseBody
	public Object addTv(HttpServletRequest request, Tv tv,
			@RequestParam("file") CommonsMultipartFile file) throws Exception {
		tDao = new TvDaoImp();
		Long time = System.currentTimeMillis() / 1000;

		String path = request.getSession().getServletContext()
				.getRealPath("/upload/tv/");
		String filename = time + "_" + file.getOriginalFilename();
		String filePath = path + File.separator + filename;
		File newFile = new File(filePath);
		if (!newFile.getParentFile().exists()) {
			System.out.println("目标文件的目录不存在，准备创建目录...");
			if (!newFile.getParentFile().mkdirs()) {
				System.out.println("创建目录失败");
				return ResultUtils.toJson(101, "服务器繁忙请重试", "");
			}
		}
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		String url = "upload/tv/" + filename;
		tv.setImgUrl(url);
		tv.setTime(time);
		if (tDao.saveOrUpdate(tv) > 0) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
	}

	/**
	 * 修改直播
	 */
	@RequestMapping("/updateTvAvailalbe")
	@ResponseBody
	public Object updateTvAvailalbe(HttpServletRequest request,
			Integer available, Integer id) throws Exception {
		tDao = new TvDaoImp();
		if (tDao.updateTvAvailalbe(id, available)) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "操作失败", "");
	}

	/**
	 * 修改直播
	 */
	@RequestMapping("/updateTv")
	@ResponseBody
	public Object updateTv(HttpServletRequest request, Tv tv,
			@RequestParam("file") CommonsMultipartFile file) throws Exception {
		tDao = new TvDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		String url = "";
		if (file.getOriginalFilename().equals("")) {
			Tv t = tDao.getTv(tv.getId());
			if (t != null) {
				url = t.getImgUrl();
			} else {
				return ResultUtils.toJson(101, "修改失败", "原纪录不存在");
			}
		} else {
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/tv/");
			String filename = time + "_" + file.getOriginalFilename();

			String filePath = path + File.separator + filename;
			File newFile = new File(filePath);
			if (!newFile.getParentFile().exists()) {
				System.out.println("目标文件的目录不存在，准备创建目录...");
				if (!newFile.getParentFile().mkdirs()) {
					System.out.println("创建目录失败");
					return ResultUtils.toJson(101, "服务器繁忙请重试", "");
				}
			}
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			url = "upload/tv/" + filename;
			// 删除旧图片
			Tv t = tDao.getTv(tv.getId());
			if (t != null) {
				File f = new File(request.getSession().getServletContext()
						.getRealPath("/")
						+ t.getImgUrl());
				if (f.exists()) {
					f.delete();
				}
			}
		}
		tv.setImgUrl(url);
		tv.setTime(time);
		if (tDao.saveOrUpdate(tv) == 0) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
	}

	/**
	 * 删除直播
	 */
	@RequestMapping("/deleteTv")
	@ResponseBody
	public Object deleteTv(HttpServletRequest request, Integer id)
			throws Exception {
		tDao = new TvDaoImp();
		// 删除旧图片
		Tv t = tDao.getTv(id);
		if (t != null) {
			File f = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ t.getImgUrl());
			if (f.exists()) {
				f.delete();
			}
		}
		if (tDao.deleteTv(id)) {
			return ResultUtils.toJson(100, "操作成功", "");
		}
		return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
	}

}
