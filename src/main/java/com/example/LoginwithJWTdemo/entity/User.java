package com.example.LoginwithJWTdemo.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User {

	// define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="level")
	private String level;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	
	public User() {}

    public User(String level, String username, String email, String password) {
        this.level = level;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String level, String username, String email, String password, Collection<Role> roles) {
        this.level = level;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + username + '\'' + ", password='" + "*********" 
	            + '\''+ email + '\''
				+ ", roles=" + roles + '}';
		}
	
	
	
}
