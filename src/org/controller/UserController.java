package org.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.UserInfoDao;
import org.dao.imp.UserDaoImp;
import org.model.User;
import org.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;

@Controller
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	UserInfoDao uInfoDao;
	Map<String, Object> msg;

	@RequestMapping("/login")
	public @ResponseBody
	Object login(String phone, String password) {

		uDao = new UserDaoImp();
		if (uDao.getUser(phone, password) != null) {
			return ResultUtils.toJson(100, "登录成功", "");
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}

	}

	@RequestMapping("/register")
	public @ResponseBody
	Object register(String phone, String password) {
		uDao = new UserDaoImp();
		User user = new User(phone, password, System.currentTimeMillis() / 1000);
		Long userid = uDao.saveOrUpdate(user);
		if (userid > 0) {
			UserInfo userInfo = new UserInfo("", "", userid);
			if (uInfoDao.saveOrUpdate(userInfo) > 0) {
				return ResultUtils.toJson(100, "注册成功", "");
			}
		}
		return ResultUtils.toJson(101, "注册失败", "");
	}

	@RequestMapping("/updateUserInfo")
	public @ResponseBody
	Object updateUserInfo(String password, String nickName) {

		return null;
	}

	@RequestMapping("/updateHead")
	public @ResponseBody
	Object updateHead(CommonsMultipartFile file) throws IllegalStateException,
			IOException {
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

		return null;
	}

	@RequestMapping("/getUserInfo")
	public @ResponseBody
	Object getUserInfo() {

		return null;
	}

	@RequestMapping("/getAddresses")
	public @ResponseBody
	Object getAddresses() {

		return null;
	}

	@RequestMapping("/insertAddress")
	public @ResponseBody
	Object insertAddress() {

		return null;
	}

	@RequestMapping("/deleteAddress")
	public @ResponseBody
	Object deleteAddress() {

		return null;
	}
	
}
