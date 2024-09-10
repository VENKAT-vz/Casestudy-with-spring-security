package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Login;
import com.example.demo.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService lservice;
	
	 @PostMapping("/register")
	    public String registeruser(@RequestBody Login login) throws ClassNotFoundException, SQLException {
	        return lservice.registerUser(login);
	    }
//	 
//	 @GetMapping("/login/{username}/{password}")
//	 public String loginuser(@PathVariable String username,@PathVariable String password) throws ClassNotFoundException, SQLException {
//		 return lservice.loginuser(username, password);
//	 }
	
	 @GetMapping("/correctEncode/{username}/{password}")
	 public String loginuser(@PathVariable String username,@PathVariable String password) throws ClassNotFoundException, SQLException {
		 return lservice.correctEncode(username, password);
	 }
	 
	 @PutMapping("/updatePassword/{username}")
	 public String updatePassword(@PathVariable String username,@RequestBody String password) throws ClassNotFoundException, SQLException {
		 return lservice.updatePassword(username, password);
	 }

}
