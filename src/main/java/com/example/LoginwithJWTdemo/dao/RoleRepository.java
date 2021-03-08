package com.example.LoginwithJWTdemo.dao;


import com.example.LoginwithJWTdemo.entity.Role;

public interface RoleRepository {

	public Role findRoleByName(String theRoleName);
}
