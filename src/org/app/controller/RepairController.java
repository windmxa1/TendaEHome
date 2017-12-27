package org.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dao.RepairDao;
import org.dao.StaffDao;
import org.dao.imp.RepairDaoImp;
import org.dao.imp.StaffDaoImp;
import org.model.RepairComment;
import org.model.RepairOrder;
import org.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.Coordinate;
import org.util.JsonUtils;
import org.util.RedisUtil;
import org.util.ResultUtils;
import org.util.TokenUtils;
import org.view.VRepairOrderId;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.GeoRadiusResponse;

@Controller("/app/RepairController")
@RequestMapping("/app/repair")
public class RepairController {
	Map<String, Object> data;
	RepairDao rDao;

	/**
	 * 用户提交报修单
	 */
	@RequestMapping("/addRepairOrder")
	@ResponseBody
	public Object addRepairOrder(HttpServletRequest request,
			String appointmentTime, String address, String description,
			String phone, String lat, String lon) throws Exception {
		rDao = new RepairDaoImp();

		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		System.out.println(lat + "," + lon);
		RepairOrder repairOrder = new RepairOrder(appointmentTime, address,
				description, phone, userid, lat, lon);
		if (rDao.saveOrUpdate(repairOrder) > 0) {
			return ResultUtils.toJson(100, "提交成功，我们会尽快处理", "");
		}
		return ResultUtils.toJson(101, "提交失败，服务器繁忙，请重试", "");
	}

	/**
	 * 用户查看报修单
	 */
	@RequestMapping("/getRepairOrderList")
	@ResponseBody
	public Object getRepairOrderList(HttpServletRequest request, Integer start,
			Integer limit) throws Exception {
		rDao = new RepairDaoImp();
		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/

		List<VRepairOrderId> list = rDao.getList(start, limit, userid);
		if (list == null || list.size() == 0) {
			return ResultUtils.toJson(101, "您还没有提交过报修单", "");
		}
		return ResultUtils.toJson(100, "", list);
	}

	/**
	 * 用户提交报修人员评价
	 */
	@RequestMapping("/addRepairComment")
	@ResponseBody
	public Object addRepairComment(HttpServletRequest request, Integer point,
			String description, String suggestion,
			@RequestParam(value = "staffId") String staffNo) throws Exception {
		rDao = new RepairDaoImp();

		/**** 获取header中的token并取出userid ****/
		String token = request.getHeader("token");
		Long userid = Long.parseLong(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "userid"));
		/*********************************/
		RepairComment repairComment = new RepairComment(point, description,
				suggestion, staffNo, userid);
		if (rDao.saveOrUpdate(repairComment) > 0) {
			return ResultUtils.toJson(100, "感谢您的评价，我们将继续努力提供让您满意的服务", "");
		}
		return ResultUtils.toJson(101, "提交失败，服务器繁忙，请重试", "");
	}

	/**
	 * 实时上传员工坐标
	 */
	@RequestMapping("/uploadStaffLocation")
	@ResponseBody
	public Object uploadStaffLocation(HttpServletRequest request,
			Integer staffId, Double lat, Double lon) throws Exception {
		Coordinate coordinate = new Coordinate(lat, lon, "" + staffId);
		String value = JsonUtils.getMapperInstance().writeValueAsString(
				coordinate);
		// 先获取旧值，如果有旧值，则说明已经插入到list中，那么就要移除旧值，添加新值，否则直接添加新值
		String origin = RedisUtil.getData("staffId" + staffId);
		RedisUtil.addData("staffId" + staffId, value, null);
		if (origin == null) {// 添加新元素
			RedisUtil.pushList("staffList", value);
		} else {// 移除旧元素，添加新元素
			RedisUtil.popList("staffList", origin);
			RedisUtil.pushList("staffList", value);
		}
		RedisUtil.addReo(coordinate, "staff");
		return ResultUtils.toJson(100, "", "");
	}

	/**
	 * 获取分配到员工的维修单
	 */
	@RequestMapping("/getRepairListByStaffId")
	@ResponseBody
	public Object getRepairListByStaffId(HttpServletRequest request,
			Integer start, Integer limit, Integer status) throws Exception {
		/**** 获取header中的token并取出staffNo ****/
		String token = request.getHeader("token");
		Integer staffId = Integer.parseInt(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "staffId"));
		/*********************************/
		rDao = new RepairDaoImp();
		List<VRepairOrderId> list = rDao.getRepairListByStaffId(staffId,
				status, start, limit);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 完成维修工作，更新维修单接口
	 */
	@RequestMapping("/updateRepairOrder")
	@ResponseBody
	public Object updateRepairOrder(HttpServletRequest request, Long id,
			Integer status, String handleResult) throws Exception {
		rDao = new RepairDaoImp();
		if (rDao.updateRepairOrder(id, status, handleResult)) {
			return ResultUtils.toJson(100, "修改成功", "");
		}
		return ResultUtils.toJson(101, "修改失败，请重试", "");
	}

	/**
	 * 返回新消息通知接口
	 */
	@RequestMapping("/newMsgNotify")
	@ResponseBody
	public Object newMsgNotify(HttpServletRequest request) throws Exception {
		rDao = new RepairDaoImp();
		/**** 获取header中的token并取出staffNo ****/
		String token = request.getHeader("token");
		Integer staffId = Integer.parseInt(""
				+ TokenUtils.getValue(token, TokenUtils.getKey(), "staffId"));
		/*********************************/
		if (rDao.getUnRead(staffId) > 0) {
			return ResultUtils.toJson(100, "有新的维修单需要处理", "");
		} else {
			return ResultUtils.toJson(101, "", "");
		}
	}

	/**
	 * 获取维修单列表(管理员获取列表用于分配任务)
	 */
	@RequestMapping("/getRepairList")
	@ResponseBody
	public Object getRepairList(HttpServletRequest request, Integer start,
			Integer limit, Integer status[]) throws Exception {
		rDao = new RepairDaoImp();
		List<VRepairOrderId> list = rDao.getList(start, limit, status);
		if (list == null) {
			return ResultUtils.toJson(101, "服务器繁忙，请重试", "");
		}
		data = new HashMap<String, Object>();
		data.put("list", list);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 获取附近员工列表
	 */
	@RequestMapping("/getNearStaff")
	@ResponseBody
	public Object getNearStaff(HttpServletRequest request, Double lat,
			Double lon, Integer staffId) throws Exception {
		System.out.println(staffId + "--" + lat + "--" + lon);
		Coordinate coordinate = new Coordinate(lat, lon, "");
		List<GeoRadiusResponse> list = RedisUtil.geoQuery(coordinate, "staff",
				(double) 5);
		List<Integer> list2 = new ArrayList<>();
		List<Double> distanceList = new ArrayList<>();
		for (GeoRadiusResponse geo : list) {
			list2.add(Integer.parseInt(geo.getMemberByString()));
			distanceList.add(geo.getDistance());
		}
		int k = 0;
		if ((k = list2.indexOf(staffId)) != -1) {
			distanceList.remove(k);
		}
		list2.remove(staffId);
		if (list2.size() == 0) {
			return ResultUtils.toJson(101, "该订单附近5KM没有维修人员，无法进行派单", "");
		}
		StaffDao sDao = new StaffDaoImp();
		List<Staff> list3 = sDao.getStaffList(list2);
		if (list3 == null || list3.size() == 0) {
			return ResultUtils.toJson(101, "该订单附近5KM没有维修人员，无法进行派单", "");
		}
		for (int i = 0; i < list3.size(); i++) {
			list3.get(i).setDistance("" + distanceList.get(i) * 1000);
		}
		data = new HashMap<>();
		data.put("list", list3);
		return ResultUtils.toJson(100, "", data);
	}

	/**
	 * 获取员工定位信息接口(前端获取定位信息显示在地图上)
	 */
	@RequestMapping("/getStaffLocation")
	@ResponseBody
	public Object getStaffLocation(HttpServletRequest request) throws Exception {
		try {
			List<String> list = RedisUtil.getList("staffList");
			ObjectMapper mapper = JsonUtils.getMapperInstance();
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(List.class, Coordinate.class);
			List<Coordinate> list2 = (List<Coordinate>) mapper.readValue(
					list.toString(), javaType);
			data = new HashMap<>();
			data.put("list", list2);
			return ResultUtils.toJson(100, "", list2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResultUtils.toJson(101, "", "");
	}

	/**
	 * 订单分配到员工接口
	 */
	@RequestMapping("/deliverOrder")
	@ResponseBody
	public Object deliverOrder(HttpServletRequest request, Integer staffId,
			Long repairOrderId) throws Exception {
		rDao = new RepairDaoImp();
		if (rDao.updateRepairOrder(staffId, repairOrderId)) {
			return ResultUtils.toJson(100, "派单成功", "");
		}
		return ResultUtils.toJson(101, "派单失败，请重试", "");
	}
}
