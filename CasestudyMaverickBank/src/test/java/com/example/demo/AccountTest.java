//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test;
//
//import com.example.demo.domain.Account;
//
//import java.sql.Date;
//
//public class AccountTest {
//
//    private Account account;
//
//    @BeforeClass
//    public void setUp() {
//        account = new Account();
//    }
//
//    @Test
//    public void testSetAndGetAccountNumber() {
//        account.setAccountNumber("12345");
//        assertEquals("12345", account.getAccountNumber());
//    }
//
//    @Test
//    public void testSetAndGetAccountType() {
//        account.setAccountType("Savings");
//        assertEquals("Savings", account.getAccountType());
//    }
//
//    @Test
//    public void testSetAndGetBalance() {
//        account.setBalance(5000.00);
//        assertEquals(5000.00, account.getBalance());
//    }
//
//    @Test
//    public void testSetAndGetBranchName() {
//        account.setBranchName("Main Branch");
//        assertEquals("Main Branch", account.getBranchName());
//    }
//
//    @Test
//    public void testSetAndGetIfscCode() {
//        account.setIfscCode("ABC12345");
//        assertEquals("ABC12345", account.getIfscCode());
//    }
//
//    @Test
//    public void testSetAndGetDateCreated() {
//        Date date = new Date(System.currentTimeMillis());
//        account.setDateCreated(date);
//        assertEquals(date, account.getDateCreated());
//    }
//
//    @Test
//    public void testSetAndGetStatus() {
//        account.setStatus("Active");
//        assertEquals("Active", account.getStatus());
//    }
//
//    @Test
//    public void testSetAndGetUsername() {
//        account.setUsername("user1");
//        assertEquals("user1", account.getUsername());
//    }
//
//    @Test
//    public void testSetAndGetEmailid() {
//        account.setEmailid("user1@example.com");
//        assertEquals("user1@example.com", account.getEmailid());
//    }
//}
