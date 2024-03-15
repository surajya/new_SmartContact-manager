package com.test.scm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig{
	
	@Bean
	public UserDetailsService getUserDetailsService() {
//		UserDetails normalUser=User
//				.withUsername("suraj")
//				.password(passwordEncoder().encode("password"))
//				.roles("normal")
//				.build();
//		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser);
//
//		return inMemoryUserDetailsManager;
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//http.authenticationProvider(authenticationProvider());
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/admin/**")
		.hasRole("admin")
		.requestMatchers("/user/**")
		.hasRole("USER")
		.requestMatchers("/**")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/doLogin")
		.defaultSuccessUrl("/user/normal");
		
		
		return http.build();
	}

}
