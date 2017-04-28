package org.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class GoodsController {
	//获取商品信息
	@RequestMapping("/getGoodsList")
	public @ResponseBody
	Object getGoodsList(Long catalogId,Integer start,Integer limit) {
		return null;
	}
	//获取折扣商品信息
	@RequestMapping("/getDiscounts")
	public @ResponseBody
	Object getDiscounts(Integer start,Integer limit){
		
		return null;
	}
}
