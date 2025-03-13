package com.saurav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping
	public String homeControllerHandler() {
		return "this is home contoller";
	}
	@GetMapping("/home")
	public String homeController2() {
		return "this is controller 2";
	}
}
