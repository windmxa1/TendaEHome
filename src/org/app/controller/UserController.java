package org.app.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.UserAddressDao;
import org.dao.UserDao;
import org.dao.UserFeedbackDao;
import org.dao.VersionDao;
import org.dao.imp.UserAddressDaoImp;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserFeedbackDaoImp;
import org.dao.imp.VersionDaoImp;
import org.model.AppVersion;
import org.model.User;
import org.model.UserAddress;
import org.model.UserFeedback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VUserId;

@Controller("/app/UserController")
@RequestMapping("/app/user")
public class UserController {
	UserDao uDao;
	UserAddressDao uAddressDao;
	UserFeedbackDao uFeedbackDao;
	Map<String, Object> data;

	// Long userid;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String phone,
			String password) throws Exception {
		uDao = new UserDaoImp();
		VUserId u = uDao.getVUser(phone, password);
		if (u != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			String token = TokenUtils.buildJwt1(TokenUtils.getKey(),
					c.getTime(), u.getId());
			// ObjectMapper mapper = new ObjectMapper();
			data.put("token", token);
			return ResultUtils.toJson(100, "登录成功", data);
		} else {
			return ResultUtils.toJson(101, "登录失败", "");
		}
	}

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Object getUserInfo(HttpServletRequest request) throws Exception {
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		data = new HashMap<String, Object>();
		VUserId u = uDao.getVUser(userid);
		if (u == null) {
			return ResultUtils.toJson(101, "系统错误，请重试", data);
		}
		u.setPassword("");
		data.put("user", u);
		return ResultUtils.toJson(100, "", data);

	}

	@RequestMapping("/phoneVerify")
	@ResponseBody
	public Object phoneVerify(HttpServletRequest request, String phone)
			throws Exception {
		uDao = new UserDaoImp();
		User u = uDao.getUser(phone);
		if (u != null) {
			data = new HashMap<String, Object>();
			// 验证通过并生成token,c为过期时间，暂时用不到
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, 14);
			String token = TokenUtils.buildJwt1(TokenUtils.getKey(),
					c.getTime(), u.getId());
			data.put("token", token);
			return ResultUtils.toJson(101, "该手机号已注册", data);
		} else {
			return ResultUtils.toJson(100, "该手机号还未注册", "");
		}
	}

	@RequestMapping("/updatePassword")
	@ResponseBody
	public Object updatePassword(HttpServletRequest request, String newPwd)
			throws Exception {
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (uDao.updatePassword(newPwd, userid)) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	@RequestMapping("/register")
	@ResponseBody
	public Object register(String phone, String password) throws Exception {
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
	public Object updateUserInfo(HttpServletRequest request, String password,
			String newPwd, String nickname) throws Exception {
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		if (password != null) {
			if (uDao.getUser(userid, password) == null) {
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
			HttpServletRequest request) throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath("/upload/headimg/");
		String filename = (System.currentTimeMillis() / 1000) + "_"
				+ file.getOriginalFilename();
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

		String url = "upload/headimg/" + filename;

		// 删除之前的头像
		uDao = new UserDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		String usedHead = uDao.getUsedHead(userid);
		if (usedHead != null) {
			File f = new File(request.getSession().getServletContext()
					.getRealPath("/")
					+ usedHead);
			if (f.exists()) {
				f.delete();
			}
		}
		// 更新头像
		uDao.updateHead(url, userid);
		return ResultUtils.toJson(100, "修改成功", "");
	}

	@RequestMapping("/getAddresses")
	@ResponseBody
	public Object getAddresses(HttpServletRequest request) throws Exception {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		// System.out.println(token);
		Long userid = Long.parseLong(TokenUtils.getValue(token,
				TokenUtils.getKey(), "userid")
				+ "");
		/*********************************/
		List<UserAddress> list = uAddressDao.getList(userid);
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	@RequestMapping("/insertAddress")
	@ResponseBody
	public Object insertAddress(HttpServletRequest request, String address,
			String receiver, String tel, String sex) throws Exception {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		UserAddress userAddress = new UserAddress(userid, address, receiver,
				tel, sex);

		if (uAddressDao.saveOrUpdate(userAddress) > 0) {
			return ResultUtils.toJson(100, "添加成功", "");
		} else {
			return ResultUtils.toJson(101, "添加失败", "");
		}
	}

	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Object deleteAddress(HttpServletRequest request, Long id)
			throws Exception {
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
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
			String address, String receiver, String tel, String sex,
			Short default_) throws Exception {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		// System.out.println(default_);
		UserAddress userAddress = new UserAddress(userid, address, receiver,
				default_, tel, sex);
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
	@RequestMapping("/updateDefaultAddress")
	@ResponseBody
	public Object updateDefaultAddress(HttpServletRequest request, Long id,
			Boolean default_) throws Exception {
		uAddressDao = new UserAddressDaoImp();
		// System.out.println(default_);
		if (uAddressDao.updateDefault(id, default_)) {
			return ResultUtils.toJson(100, "修改成功", "");
		} else {
			return ResultUtils.toJson(101, "修改失败", "");
		}
	}

	/**
	 * 检测版本是否为最新版
	 */
	@RequestMapping("/checkVersion")
	@ResponseBody
	public Object checkVersion(HttpServletRequest request, String version)
			throws Exception {
		VersionDao vDao = new VersionDaoImp();
		AppVersion v = vDao.getLastVersion();
		if (v == null || v.getVersion().equals(version)) {
			return ResultUtils.toJson(100, "", "");
		}
		data = new HashMap<>();
		data.put("version", v);
		return ResultUtils.toJson(101, "生态宜家的最新版本" + v.getVersion()
				+ "已发布,当前版本为" + version, data);
	}

	/**
	 * 获取默认地址
	 */
	@RequestMapping("/getDefaultAddress")
	@ResponseBody
	public Object getDefaultAddress(HttpServletRequest request)
			throws Exception {
		uAddressDao = new UserAddressDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		UserAddress userAddress = uAddressDao.getDefaultAddress(userid);
		if (userAddress == null) {
			return ResultUtils.toJson(101, "您没有设置默认地址", "");
		}
		data = new HashMap<>();
		data.put("address", userAddress);
		return ResultUtils.toJson(100, "", data);

	}

	/**
	 * 用户反馈
	 */
	@RequestMapping("/addFeedback")
	@ResponseBody
	public Object addFeedback(HttpServletRequest request, String message)
			throws Exception {
		uFeedbackDao = new UserFeedbackDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		Long time = System.currentTimeMillis() / 1000;
		// System.out.println(message);
		// System.out.println(userid);
		UserFeedback userFeedback = new UserFeedback(message, userid, time);
		if (uFeedbackDao.saveOrUpdate(userFeedback) > 0) {
			return ResultUtils.toJson(100, "反馈成功，我们会尽快处理", "");
		}
		return ResultUtils.toJson(101, "反馈失败，服务器繁忙，请重试", "");
	}
}
