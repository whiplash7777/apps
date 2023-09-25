package com.vksprojects.blog.config;

import javax.security.sasl.AuthorizeCallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.vksprojects.blog.services.impl.DatabaseUserDetailService;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration  {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.
			csrf()
			.disable()
			.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
			
		return http.build();
		
		
	}
	

    @Override
	public Filter springSecurityFilterChain() throws Exception {
		// TODO Auto-generated method stub
		return super.springSecurityFilterChain();
	}


	@Bean
	public UserDetailsService userDetailsService() {
		return new DatabaseUserDetailService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder() ;
	}
	

}
