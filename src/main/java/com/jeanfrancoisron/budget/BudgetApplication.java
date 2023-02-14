package com.jeanfrancoisron.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class BudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplication.class, args);
	}

	@GetMapping("/login")
	String login() {
		return  "login";
	}
	@GetMapping("/password")
	String password() {
		return  "password";
	}
	@GetMapping("/register")
	String register() {
		return  "register";
	}

}
