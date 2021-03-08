package com.example.LoginwithJWTdemo.service;



import com.example.LoginwithJWTdemo.dto.CrmUser;
import com.example.LoginwithJWTdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

	 public User findByUserName(String username);
	 public void save(CrmUser crmUser);

	 public void update(User user);
}
