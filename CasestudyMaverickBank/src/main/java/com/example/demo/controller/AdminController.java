package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
		
		@Autowired
		private UserService userservice;
		
		@Autowired
		private UserService service;
		
	    @PutMapping("/approve-user-account/{username}")
	    public String approveAccount(@PathVariable String username) {
	    	return userservice.approveUserAccounts(username);
	    }
	    
		@GetMapping(value = "/showAll")
		public List<User> showAllUsers() {
		    return service.showAllUsers();
		}
		
		@GetMapping(value="/searchUser/{username}")
		public User searchuser(@PathVariable String username) {
			return service.searchUser(username);
		}
		
		 @DeleteMapping("/delete/{username}")
		public String deleteUser(@PathVariable String username) {
		      boolean isDeleted = service.deleteUser(username);
		        if (isDeleted) {
		            return "User deleted successfully.";
		        } else 
		            return "User not found.";
		    }
	}

