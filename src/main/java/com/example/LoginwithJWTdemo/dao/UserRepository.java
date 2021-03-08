package com.example.LoginwithJWTdemo.dao;


import com.example.LoginwithJWTdemo.entity.User;

public interface UserRepository {

	 public User findById(int user_id);
	 public User findByUserName(String username);
	 public void save(User user);
}
