package com.freshvotes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder  getPassWordEncoder(){
		return new BCryptPasswordEncoder();
		 
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(getPassWordEncoder());
		
//		auth.inMemoryAuthentication()
//		.passwordEn coder(getPassWordEncoder())
//		.withUser("")
//		.password(getPassWordEncoder().encode(""))
//		.roles("USER");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http 
		/* .csrf().disable() */ // need to add code in html page for csrf to be work
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/register").permitAll()
			.anyRequest().hasRole("USER")
		.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
		.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll();
		
		
	}
	
}
