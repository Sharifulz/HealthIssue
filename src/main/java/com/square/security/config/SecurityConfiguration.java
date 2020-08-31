package com.square.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Step 1: Make a configuration class that extends WebSecurityConfigurerAdapter
 * 
 * @Override two main method to initiate AuthenticationManager using AuthenticationManagerBuilder for authentication
 * and for Authorization HttpSecurity from WebSecurityConfigurerAdapter
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//--------- Authentication -------------------------------
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	//--------- Authorization --------------------------------
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin", "/user/delete").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.antMatchers("/user/add").permitAll()
			.antMatchers(com.square.security.config.SecurityConstraint.H2_CONSOLE).permitAll()
			.anyRequest().authenticated()
        .and()
	        .formLogin()
	        .loginPage("/login")
	        .defaultSuccessUrl("/user")
	        .permitAll()
        .and()
	        .logout()
	        .invalidateHttpSession(true)
	        .clearAuthentication(true)
	        .logoutSuccessUrl("/")
	        .permitAll()
	    .and()
	    	.exceptionHandling().accessDeniedPage("/noaccess")
        .and()
	        .csrf()
	        .disable();
		
		//http.headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder getPAsswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	
}
