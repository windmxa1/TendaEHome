package org.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.FranchiseeDao;
import org.dao.imp.FranchiseeDaoImp;
import org.model.Franchisee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.util.Utils;

@RestController("/app/FranchiseeController")
@RequestMapping("/app/franchisee")
public class FranchiseeController {
	Map<String, Object> data;
	FranchiseeDao fDao;

	/**
	 * 检测是否重名
	 */
	@RequestMapping("checkNickname")
	public Object checkNickName(HttpServletRequest request,
			Franchisee franchisee) {
		fDao = new FranchiseeDaoImp();
		if (fDao.getFranchisee(franchisee.getNickname(),
				franchisee.getCatalogId()) != null) {
			return ResultUtils.toJson(101, "您的昵称重复了，请重新输入", "");
		}
		return ResultUtils.toJson(100, "", "");
	}

	/**
	 * 加盟接口
	 */
	@RequestMapping("addFranchisee")
	public Object addFranchisee(HttpServletRequest request,
			Franchisee franchisee,@RequestParam(required=false) CommonsMultipartFile healthPic,
			@RequestParam(required=false) CommonsMultipartFile promissPic) {
		fDao = new FranchiseeDaoImp();
		Long time = System.currentTimeMillis() / 1000;
		if (healthPic != null) {
			String fileUrl = Utils.getFileUrl(request.getSession()
					.getServletContext().getRealPath("/"), healthPic, time,
					"upload/franchisee");
			if (fileUrl == null) {
				return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
			}
			franchisee.setHealthPic(fileUrl);
		}
		if (promissPic != null) {
			String fileUrl = Utils.getFileUrl(request.getSession()
					.getServletContext().getRealPath("/"), promissPic, time,
					"upload/franchisee");
			if (fileUrl == null) {
				return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
			}
			franchisee.setPromissPic(fileUrl);
		}
		String franchiseeNum = System.currentTimeMillis() + Utils.ran6();
		franchisee.setFranchiseeNum(franchiseeNum);
		if (fDao.saveOrUpdate(franchisee) > 0) {
			return ResultUtils.toJson(100, "申请成功，请等待客服人员与您联系", "");
		}
		return ResultUtils.toJson(101, "申请失败", "");
	}

	/**
	 * 获取加盟商昵称列表
	 */
	@RequestMapping("getFranchiseeNameList")
	public Object getFranchiseeNameByCatlog(HttpServletRequest request,
			Integer catalogId) {
		fDao = new FranchiseeDaoImp();
		List<String> list = fDao.getNicknameList(catalogId);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 获取加盟商列表
	 */
	@RequestMapping("getFranchiseeList")
	public Object getFranchiseeByCatlog(HttpServletRequest request,
			Integer catalogId) {
		fDao = new FranchiseeDaoImp();
		List<Franchisee> list = fDao.getList(catalogId);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

}
