package com.example.LoginwithJWTdemo.dto;

import com.example.LoginwithJWTdemo.validation.FieldMatch;
import com.example.LoginwithJWTdemo.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {


        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String level;

        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String username;

        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String password;

        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String matchingPassword;

        @ValidEmail
        @NotNull(message = "is required")
        @Size(min = 1, message = "is required")
        private String email;

        public CrmUser() {

        }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
