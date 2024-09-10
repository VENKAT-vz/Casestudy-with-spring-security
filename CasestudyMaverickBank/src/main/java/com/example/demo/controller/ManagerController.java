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

import com.example.demo.domain.Account;
import com.example.demo.domain.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private AccountService accountService;
	


    @GetMapping("/unapproved-accounts")
    public ResponseEntity<List<Map<String, Object>>> getNotApprovedAccounts() {
        List<Map<String, Object>> notApprovedAccounts = accountService.getNotApprovedAccounts();
        return ResponseEntity.ok(notApprovedAccounts);
    }
    
    @PutMapping("/approve-account/{accountNumber}")
    public String approveAccount(@PathVariable String accountNumber) {
    	return accountService.approveAccounts(accountNumber);
    }
    
    @GetMapping("/showAccounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/searchAccounts/{accountNumber}")
    public Account getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }
    
    @DeleteMapping("/delete/{accountNumber}")
    public String deleteAccount(@PathVariable String accountNumber) {
        boolean isDeleted = accountService.deleteAccount(accountNumber);
        if (isDeleted) {
            return "Account deleted successfully.";
        } else {
            return "Account not found.";
        }
    }
    

}
