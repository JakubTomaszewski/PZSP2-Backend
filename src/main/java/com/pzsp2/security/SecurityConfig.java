/*
package com.pzsp2.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(datasource)
				.usersByUsernameQuery("select username,password,enabled "
					+ "from Users1 "
					+ "where username = ?")
				.authoritiesByUsernameQuery("select username,authority "
					+ "from Users1 " 
					+ "where username = ?");

		// Authentication With Local Memory Data
		// auth.inMemoryAuthentication()
		// 		.withUser("user")
		// 		.password("user")
		// 		.roles("USER")
		// 		.and()
		// 		.withUser("admin")
		// 		.password("admin")
		// 		.roles("ADMIN");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Use /login /logout to manage acc-s
		//Admin can go to /admin /user as we want
		//Start location is now show as Welcome
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin();

	}


}
*/
