package com.BookStoreAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	// 로그인
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
