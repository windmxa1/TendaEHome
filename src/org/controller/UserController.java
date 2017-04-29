package org.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.imp.UserDaoImp;
import org.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.test.TestBean;
import org.util.ResultUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	Map<String, Object> msg;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(String phone, String password) {

		uDao = new UserDaoImp();
		User u = uDao.getUser(phone, password);
		if (u != null) {
			msg = new HashMap<String, Object>();
			u.setPassword("");
			msg.put("user", u);
			
			return ResultUtils.toJson(100, "登录成功", msg);
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

	// @RequestMapping("/getUserInfo")
	// public @ResponseBody
	// Object getUserInfo(HttpSession session) {
	// User u = (User) session.getAttribute("user");
	// if (u != null) {
	// System.out.println("当前登录的用户为" + u.getPhone());
	// }
	// return ResultUtils.toJson(100, "", "");
	// }

	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(String password, String nickname) {
		System.out.println(password);
		System.out.println(nickname);
		if (password != null) {

		}
		if (nickname != null) {

		}

		return ResultUtils.toJson(100, "", "");
	}

	@RequestMapping("/updateHead")
	public @ResponseBody
	Object updateHead(@RequestParam CommonsMultipartFile file)
			throws IllegalStateException, IOException {
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);

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
