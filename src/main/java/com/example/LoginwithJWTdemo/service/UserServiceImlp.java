package com.example.LoginwithJWTdemo.service;


import com.example.LoginwithJWTdemo.dao.RoleRepository;
import com.example.LoginwithJWTdemo.dao.UserRepository;
import com.example.LoginwithJWTdemo.dto.CrmUser;
import com.example.LoginwithJWTdemo.entity.Role;
import com.example.LoginwithJWTdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImlp implements UserService {
	
	
	// need to inject user dao
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String username) {
		// check the database if the user already exists	
		return userRepository.findByUserName(username) ;
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		user.setId(0);
		user.setUsername(crmUser.getUsername());
		user.setLevel(crmUser.getLevel());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setEmail(crmUser.getEmail());
		user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_USER")));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void update(User user){
		User user1 = new User();
		user1.setId(user.getId());
		user1.setUsername(user.getUsername());
		user1.setLevel(user.getLevel());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		user1.setEmail(user.getEmail());
		user1.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_USER")));
		userRepository.save(user1);
	}

	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles())) ;			
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}