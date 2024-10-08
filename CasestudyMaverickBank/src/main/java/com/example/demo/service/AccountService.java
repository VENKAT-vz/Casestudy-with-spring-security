package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TransactionRepository transRepository;
    

    public Account addAccount(Account account, String aadhaarNumber, String panNumber) throws ClassNotFoundException, SQLException {
    	account.setAccountNumber(generateAccountNo());
    	Date currentDate = new Date(System.currentTimeMillis());
    	account.setDateCreated(currentDate);
    	account.setStatus("NotApproved");
    	
    	//here we need to have a list like combinations of branches and their ifsc codes and 
    	//whenever they enter branchname it should automatically set the ifsc codes.
    	//account.setIfscCode(null);
    	
    	//get the pan number and aadhar only once ->check that here.
    	
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
	 
	 public String closeAccount(String accountNumber) {
		    int rowsAffected = accountRepository.closeAccount(accountNumber);
		    
		    if (rowsAffected > 0) {
		        return "Account closed successfully.";
		    }
		    else 
		        return "Account closed failed. Account not found.";
		    
		}
	 
	  public String generateAccountStatement(String accountNumber) {
	        StringBuilder statement = new StringBuilder();
	        
	        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
	        if (accountOpt.isPresent()) 
	        {
	            Account account = accountOpt.get();
	            statement.append("Account Statement for Account Number: ").append(accountNumber).append("\n")
	                     .append("Account Type: ").append(account.getAccountType()).append("\n")
	                     .append("Balance: ").append(account.getBalance()).append("\n")
	                     .append("Branch Name: ").append(account.getBranchName()).append("\n")
	                     .append("IFSC Code: ").append(account.getIfscCode()).append("\n")
	                     .append("Status: ").append(account.getStatus()).append("\n\n");
	        } 
	        else 
	            return "Account not found!";
	        

	     List<Transaction> transactions = transRepository.findByAccountNumberOrderByTransactionDateDesc(accountNumber);
	        statement.append("Transaction History:\n");
	        for (Transaction transaction : transactions) 
	        {
	            statement.append("Date: ").append(transaction.getTransactionDate()).append(", ")
	                     .append("Type: ").append(transaction.getTransactionType()).append(", ")
	                     .append("Amount: ").append(transaction.getAmount()).append("\n");
	          }
	        
	        
	        return statement.toString();
	    }
	  
	public String generateFinancialReport(String accountNumber) {
	        StringBuilder report = new StringBuilder();

	        report.append("Financial report of an account with this accountnumber ").append(accountNumber).append(":\n\n");
        Double totalDeposits = transRepository.sumDeposits(accountNumber);
	        report.append("Total Deposits: ").append(totalDeposits != null ? totalDeposits : 0).append("\n");

	        Double totalWithdrawals = transRepository.sumWithdrawals(accountNumber);
	       report.append("Total Withdrawals: ").append(totalWithdrawals != null ? totalWithdrawals : 0).append("\n");

	     Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
	        if (accountOpt.isPresent()) 
	        {
	            report.append("Current Balance: ").append(accountOpt.get().getBalance()).append("\n");
	        } 
	        else 
	            report.append("Account not found!\n");
	        
	        return report.toString();
	    }


}
