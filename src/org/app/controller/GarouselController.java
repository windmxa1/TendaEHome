package org.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/app/GarouselController")
@RequestMapping("/app/garousel")
public class GarouselController {
	@RequestMapping("/getGarouselCatalog")
	@ResponseBody
	public Object getGarouselCatalog() {
		
		return null;
	}
	@RequestMapping("/getGarouselList")
	@ResponseBody
	public Object getGarouselList(Integer id) {
		
		return id;
	}
	
}
