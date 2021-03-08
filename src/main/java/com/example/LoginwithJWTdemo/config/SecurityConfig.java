package com.example.LoginwithJWTdemo.config;


import com.example.LoginwithJWTdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our user service
	 @Autowired
	 private UserService userService;

	 @Autowired
	 private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	 @Autowired
	 private UserDetailsService jwtUserDetailsService;

	 @Autowired
	 private JwtRequestFilter jwtRequestFilter;

	  
	  @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public CorsConfigurationSource CorsConfigurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://127.0.0.1:5500");
		configuration.addAllowedMethod("GET");
		configuration.addAllowedMethod("POST");
		configuration.addAllowedMethod("OPTIONS");
		configuration.addAllowedMethod("HEAD");
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",configuration);
		return source;
	}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {

			httpSecurity.csrf().disable()
					.cors().configurationSource(CorsConfigurationSource()).and()
					// dont authenticate this particular request
					.authorizeRequests().antMatchers("/authenticate").permitAll()
					.antMatchers("/register").permitAll()
					// all other requests need to be authenticated
					.anyRequest().authenticated().and().
					// make sure we use stateless session; session won't be used to
					// store user's state.
					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			// Add a filter to validate the tokens with every request
			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			
		}

}
