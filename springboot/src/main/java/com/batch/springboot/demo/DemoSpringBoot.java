package com.batch.springboot.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoSpringBoot {
	
	@GetMapping("/hello")
	public String display() {
		
		return "Hello World";
	}

}
