package com.sps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api/v1")
//@PreAuthorize("hasRole('ROLE_USER')")
@ResponseBody
public class Api {
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init() {
		return "{msg: \"hello\"}";
	}
}
