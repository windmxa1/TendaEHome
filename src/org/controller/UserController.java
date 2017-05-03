package org.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.User;
import org.model.UserAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.view.VUserId;

import sun.misc.BASE64Encoder;

@Controller
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	Map<String, Object> data;
	Long userid;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(String phone, String password) {
		BASE64Encoder encoder = new BASE64Encoder();
//		MessageDigest  
//		encoder.encode(arg0);
		uDao = new UserDaoImp();
		VUserId u = uDao.getUser(phone, password);
		if (u != null) {
			data = new HashMap<String, Object>();
			u.setPassword("");
			data.put("user", u);
			return ResultUtils.toJson(100, "登录成功", data);
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}

	}

	@RequestMapping("/register")
	@ResponseBody
	public Object register(String phone, String password) {
		uDao = new UserDaoImp();
		User user = new User(phone, password, System.currentTimeMillis() / 1000);
		Long userid = uDao.saveOrUpdate(user);
		if (userid > 0) {
			return ResultUtils.toJson(100, "注册成功", "");
		}
		return ResultUtils.toJson(101, "注册失败", "");
	}

	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(String password, String nickname) {
		uDao = new UserDaoImp();
		if (password != null) {
			if (uDao.updatePassword(password, userid)) {
				return ResultUtils.toJson(100, "修改成功", "");
			}
		}
		if (nickname != null) {
			if (uDao.updateNickname(nickname, userid)) {
				return ResultUtils.toJson(100, "修改成功", "");
			}
		}
		return ResultUtils.toJson(101, "修改失败", "");
	}

	@RequestMapping("/updateHead")
	@ResponseBody
	public Object updateHead(@RequestParam CommonsMultipartFile file,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/headphoto/");
		String filename = (System.currentTimeMillis() / 1000) + "_"
				+ file.getOriginalFilename();
		String filePath = path + filename;
		File newFile = new File(filePath);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		
		String url = "upload/headphoto/" + filename;

		// 删除之前的头像
		uDao = new UserDaoImp();
		String usedHead = uDao.getUsedHead(userid);
		if (usedHead != null) {
			File f = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ usedHead);
			if (f.exists()) {
				return f.delete();
			}
		}
		// 更新头像
		uDao.updateHead(url, userid);
		return ResultUtils.toJson(100, "修改成功", "");
	}

	

	@RequestMapping("/getAddresses")
	@ResponseBody
	public Object getAddresses() {
		uAddressDao = new UserAddressDaoImp();
		List<UserAddress> list = uAddressDao.getList(userid);
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/insertAddress")
	@ResponseBody
	public Object insertAddress(String address, String recevier) {
		uAddressDao = new UserAddressDaoImp();
		UserAddress userAddress = new UserAddress(userid, address, recevier);
		if (uAddressDao.saveOrUpdate(userAddress) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(100, "添加失败", "");
		}
	}

	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Object deleteAddress(Long id) {
		if (uAddressDao.delete(userid, id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(100, "删除失败", "");
		}
	}

	@RequestMapping("/updateAddress")
	@ResponseBody
	public Object updateAddress(Long id, String address, String recevier) {
		uAddressDao = new UserAddressDaoImp();
		UserAddress userAddress = new UserAddress(userid, address, recevier);
		userAddress.setId(id);
		if (uAddressDao.saveOrUpdate(userAddress) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(100, "修改失败", "");
		}
	}

}
