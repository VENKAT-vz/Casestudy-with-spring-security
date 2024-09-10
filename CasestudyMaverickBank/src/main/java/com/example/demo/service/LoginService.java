package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Login;
import com.example.demo.repository.LoginRepository;


@Service
public class LoginService {

	@Autowired
	private LoginRepository lrepo;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public String registerUser(Login login) throws ClassNotFoundException, SQLException {
        if (lrepo.findByEmailid(login.getEmailid()) == null && lrepo.findByUsername(login.getUsername()) == null) {
            
            String encryptedPassword = bCryptPasswordEncoder.encode(login.getPassword());
            login.setPassword(encryptedPassword);
            
            lrepo.save(login);
            return "New Login successfully registered";
        }
        return "User with credentials already exists";
    }
    

    public String correctEncode(String username, String rawPassword) {
        Login login = lrepo.findByUsername(username);
        
        if (login != null) {
            String storedPasswordHash = login.getPassword();
            
            if (bCryptPasswordEncoder.matches(rawPassword, storedPasswordHash)) {
                return "Password matches!";
            } else {
                return "Incorrect password!";
            }
        }
        return "User not found!";
    }
    
    public String updatePassword(String username,String password) {
        Login login = lrepo.findByUsername(username);
        if (login != null) {
        	String Encodedpassword=bCryptPasswordEncoder.encode(password);
        	login.setPassword(Encodedpassword);
        	lrepo.save(login);
        	return "Password updated";
        }
        return "No user account found";


    }
//	public  String getCode(String password) {
//        String encryptedpassword = null;  
//        try   
//        {  
//            MessageDigest m = MessageDigest.getInstance("MD5");  
//              
//            m.update(password.getBytes());  
//              
//            byte[] bytes = m.digest();  
//              
//            StringBuilder s = new StringBuilder();  
//            for(int i=0; i< bytes.length ;i++)  
//            {  
//                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
//            }  
//              
//            encryptedpassword = s.toString();  
//        }   
//        catch (NoSuchAlgorithmException e)   
//        {  
//            e.printStackTrace();  
//        }  
//          
//        return encryptedpassword;
//	}
//	
//	public String loginuser(String username, String password) throws ClassNotFoundException, SQLException {
//	    String encryptedPass = getCode(password);
//	    String query = "SELECT COUNT(*) FROM login WHERE username = ? AND password = ?";
//
//	    int count = jdbctemplate.queryForObject(query, new Object[]{username, encryptedPass}, Integer.class);
//	    
//	    if (count > 0) {
//	        List<Account> accounts = accountService.getAccountByNumberByusername(username);
//
//	        StringBuilder result = new StringBuilder("Login successful\n");
//
//	        for (Account account : accounts) {
//	            result.append("In the ")
//	                  .append(account.getAccountType())
//	                  .append(" account of account number ")
//	                  .append(account.getAccountNumber())
//	                  .append("\nThe available balance is ")
//	                  .append(account.getBalance())
//	                  .append("\n\n");
//	        }
//
//	        return result.toString();  
//	    }
//
//	    return "Invalid Credentials. Try again.";
//	}

}
