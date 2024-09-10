package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
		
		@Autowired
		private UserService userservice;
		
	    @PutMapping("/approve-user-account/{username}")
	    public String approveAccount(@PathVariable String username) {
	    	return userservice.approveUserAccounts(username);
	    }

	}

