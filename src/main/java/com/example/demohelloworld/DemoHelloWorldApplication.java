package com.example.demohelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class DemoHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHelloWorldApplication.class, args);
	}

	@GetMapping("/health")
	public HashMap<String, Object> healthCheck() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", "200");
		map.put("message", "OK");
		return map;
	}

	@RequestMapping(value = "/*")
	public HashMap<String, Object> redirect() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", "404");
		map.put("message", "unavailable");
		return map;
	}

}
