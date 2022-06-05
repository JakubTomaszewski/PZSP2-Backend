package com.pzsp2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.pzsp2.filter.CustomAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   DataSource datasource;

    private final UserDetailsService teacherDetailsService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.jdbcAuthentication()
    //            .dataSource(datasource)
    //            .usersByUsernameQuery("SELECT teachers.teach_login AS \"username\", teachers.teach_password AS \"password\", teachers.enabled AS \"enabled\" "
    //                    + "FROM teachers WHERE teachers.teach_login = ?")
    //            .authoritiesByUsernameQuery("SELECT TEACH_LOGIN as \"username\", AUTHORITY as \"authority\" "
    //                    + "FROM Teachers WHERE teachers.teach_login = ?")
    //            .passwordEncoder(getPasswordEncoder());
        auth.userDetailsService(teacherDetailsService).passwordEncoder(getPasswordEncoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {

       //http.authorizeRequests()
            // .antMatchers("/login").permitAll()
            // .anyRequest().authenticated()
            // .and()
            // .formLogin();
    //    http.authorizeRequests().antMatchers("/").permitAll();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
   }

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }


   @Bean
   public BCryptPasswordEncoder getPasswordEncoder() {
       BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
       return passEncoder;
       //return NoOpPasswordEncoder.getInstance();
   }
}

