package com.gavs.test.helloController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavs.test.heloservice.resthelloService;

@RestController
@RequestMapping("/run")
public class resthelloController {
	
	@Autowired
	resthelloService hw;
	
	@GetMapping("/test1")
	public  String run() {
		return hw.hello();
	}

}
