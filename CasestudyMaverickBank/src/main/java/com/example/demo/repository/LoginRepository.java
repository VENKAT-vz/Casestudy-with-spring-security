package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,String>{

    Login findByUsername(String username);
    Login findByEmailid(String emailid);

}
