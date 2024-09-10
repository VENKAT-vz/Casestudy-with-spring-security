package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Account;
import com.example.demo.domain.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;

    public Account addAccount(Account account, String aadhaarNumber, String panNumber) throws ClassNotFoundException, SQLException {
    	account.setAccountNumber(generateAccountNo());
    	Date currentDate = new Date(System.currentTimeMillis());
    	account.setDateCreated(currentDate);
    	account.setStatus("NotApproved");
    	
    	//here we need to have a list like combinations of branches and their ifsc codes and 
    	//whenever they enter branchname it should automatically set the ifsc codes.
    	//account.setIfscCode(null);
    	Account savedAccount=accountRepository.save(account);
        User user = userRepository.findByUsername(account.getUsername()); 
        if (user != null) {
            user.setAadharNum(aadhaarNumber);
            user.setPanNum(panNumber);
            userRepository.save(user);
        }
    	return savedAccount;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findById(accountNumber).orElse(null);
    }
    
    public List<Account> getAccountByNumberByusername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    public boolean deleteAccount(String accountNumber) {
        if (accountRepository.existsById(accountNumber)) 
        {
          accountRepository.deleteById(accountNumber);
            return true;
        } 
        else 
            return false;
    }

    private String generateAccountNo() {
        String maxAccountNo = accountRepository.findMaxAccountNumber();
         if (maxAccountNo == null) {
            return "1000";
        }
        int nextAccountNo = Integer.parseInt(maxAccountNo) + 1;
        return String.valueOf(nextAccountNo);
    }
	 
	 public List<Map<String, Object>> getNotApprovedAccounts() {
		    return accountRepository.findNotApprovedAccounts();
		}
	 
	 public String approveAccounts(String accountNumber) {
		    int rowsAffected = accountRepository.approveAccount(accountNumber);
		    
		    if (rowsAffected > 0) {
		        return "Account approved successfully.";
		    } else {
		        return "Account approval failed. Account not found.";
		    }
		}

}
