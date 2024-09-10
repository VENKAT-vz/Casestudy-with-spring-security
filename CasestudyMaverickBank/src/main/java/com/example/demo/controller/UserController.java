package com.example.demo.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Account;
import com.example.demo.domain.Login;
import com.example.demo.domain.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.BankService;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private LoginService lservice;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankService bankService;
	
    
	 @GetMapping("/login/{username}/{password}")
	 public String loginuser(@PathVariable String username,@PathVariable String password) throws ClassNotFoundException, SQLException {
		 return lservice.loginuser(username, password);
	 }
	 
	@PostMapping(value = "/addUser")
	public void addUser(@RequestBody Map<String, Object> requestBody) throws ClassNotFoundException, SQLException {

		Map<String, Object> userMap = (Map<String, Object>) requestBody.get("users");
	    User user = new User();
	    user.setFirstname((String) userMap.get("firstname"));
	    user.setLastname((String) userMap.get("lastname"));

	    Date dateOfBirth = Date.valueOf((String) userMap.get("dateOfBirth")); 
	    user.setDateOfBirth(dateOfBirth);

	    String genderString = (String) userMap.get("gender");
	    if (genderString != null && genderString.length() == 1) {
	        char gender = genderString.charAt(0); 
	        user.setGender(gender); 
	    }
	    
	    user.setContactNumber((String) userMap.get("contactNumber"));
	    user.setAddress((String) userMap.get("address"));
	    user.setCity((String) userMap.get("city"));
	    user.setState((String) userMap.get("state"));
	    user.setUsername((String) userMap.get("username"));
	    user.setEmailid((String) userMap.get("emailid"));

	    String password = (String) requestBody.get("password");
	    String role = (String) requestBody.get("role");

	    service.addUser(user);

	    Login login = new Login();
	    login.setUsername(user.getUsername());
	    login.setEmailid(user.getEmailid());
	    login.setPassword(password);
	    login.setRole(role);
	    
	    lservice.registerUser(login);
	}
	

    @PostMapping("/addAccounts")
    public Account addAccount(@RequestBody Map<String, Object> userdet) throws ClassNotFoundException, SQLException {
        Map<String, Object> accountdet = (Map<String, Object>) userdet.get("account");
        String aadhaarNumber = (String) userdet.get("aadhaarNumber");
        String panNumber = (String) userdet.get("panNumber");

        Account account = new Account();
        account.setAccountType((String) accountdet.get("accountType"));
        account.setBalance((Double) accountdet.get("balance"));
        account.setBranchName((String) accountdet.get("branchName"));
        account.setIfscCode((String) accountdet.get("ifscCode"));
        account.setUsername((String) accountdet.get("username"));
        account.setEmailid((String) accountdet.get("emailid"));

        return accountService.addAccount(account, aadhaarNumber, panNumber);
    }
    
    @GetMapping("/myaccounts/{username}")
    public List<Account> getAccountByNumberByusername(@PathVariable String username) {
        return accountService.getAccountByNumberByusername(username);
    }
    
    @PostMapping("/deposit/{accountNumber}/{amount}")
    public String deposit(@PathVariable String accountNumber, @PathVariable double amount) throws ClassNotFoundException, SQLException {
        return bankService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw/{accountNumber}/{amount}")
    public String withdraw(@PathVariable String accountNumber, @PathVariable double amount) throws ClassNotFoundException, SQLException {
        return bankService.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer/{phnum1}/{phnum2}/{amount}")
    public String moneyTransfer(@PathVariable String phnum1, @PathVariable String phnum2, @PathVariable double amount) throws ClassNotFoundException, SQLException {
        return bankService.moneyTransfer(phnum1, phnum2, amount);
    }
	
}
