package com.lzkj.lxzb.rest.modular.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController  {

	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "/", method = { RequestMethod.GET,RequestMethod.POST})
	private String index(String username,String password) {

		return "redirect: /index.html";
	}

}
