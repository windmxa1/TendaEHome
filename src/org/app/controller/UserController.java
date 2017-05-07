package org.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
import org.util.TokenUtils;
import org.view.VUserId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/app/UserController")
@RequestMapping("/app/user")
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	Map<String, Object> data;

	// Long userid;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String phone,
			String password) {
		uDao = new UserDaoImp();
		VUserId u = uDao.getVUser(phone, password);
		if (u != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			TokenUtils.rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String token = TokenUtils.buildJwt1(TokenUtils.getKey(),
					c.getTime(), u.getId());
			u.setPassword("");
			ObjectMapper mapper = new ObjectMapper();
			try {
				System.out.println(mapper.writeValueAsString(u));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.put("user", u);
			data.put("token", token);
			return ResultUtils.toJson(100, "登录成功", data);
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}

	}
	

	@RequestMapping("/register")
	@ResponseBody
	public Object register(String phone, String password) {
		uDao = new UserDaoImp();
		User u = uDao.getUser(phone);
		if (u != null) {
			return ResultUtils.toJson(101, "注册失败，该账号已被注册", "");
		}
		User user = new User(phone, password, System.currentTimeMillis() / 1000);
		Long userid = uDao.saveOrUpdate(user);
		if (userid > 0) {
			return ResultUtils.toJson(100, "注册成功", "");
		}
		return ResultUtils.toJson(101, "注册失败", "");
	}

	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(HttpServletRequest request, String password,String newPwd,
			String nickname) {
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		if (password != null) {
			if(uDao.getUser(userid,password)==null){
				return ResultUtils.toJson(101, "修改失败,旧密码不正确", "");
			}
			if (uDao.updatePassword(newPwd, userid)) {
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
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
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
	public Object getAddresses(HttpServletRequest request) {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		List<UserAddress> list = uAddressDao.getList(userid);
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/insertAddress")
	@ResponseBody
	public Object insertAddress(HttpServletRequest request, String address,
			String recevier) {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		UserAddress userAddress = new UserAddress(userid, address, recevier);
		if (uAddressDao.saveOrUpdate(userAddress) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Object deleteAddress(HttpServletRequest request, Long id) {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		if (uAddressDao.delete(userid, id)) {
			return ResultUtils.toJson(100, "删除成功", "");
		} else {
			return ResultUtils.toJson(101, "删除失败", "");
		}
	}

	@RequestMapping("/updateAddress")
	@ResponseBody
	public Object updateAddress(HttpServletRequest request, Long id,
			String address, String recevier) {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		UserAddress userAddress = new UserAddress(userid, address, recevier);
		userAddress.setId(id);
		if (uAddressDao.saveOrUpdate(userAddress) == 0) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}
	/**
	 * 修改默认地址
	 */
	@RequestMapping("/updateDefault")
	@ResponseBody
	public Object updateDefault(HttpServletRequest request,Long addressid){
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = (Long) TokenUtils.getValue(token, TokenUtils.getKey(),
				"userid");
		/*********************************/
		if (uAddressDao.updateDefault(addressid)) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

}
