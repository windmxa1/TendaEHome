package org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GarouselController {
	@RequestMapping("/getGarousel")
	@ResponseBody
	public Object getGarousel() {

		return null;
	}
}
