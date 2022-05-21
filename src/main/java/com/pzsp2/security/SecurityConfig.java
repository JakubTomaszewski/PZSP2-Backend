//package com.pzsp2.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.sql.DataSource;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource datasource;
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication()
////                .dataSource(datasource)
////                .usersByUsernameQuery("SELECT teachers.teach_login AS \"username\", teachers.teach_password AS \"password\", teachers.enabled AS \"enabled\" "
////                        + "FROM teachers WHERE teachers.teach_login = ?")
////                .authoritiesByUsernameQuery("SELECT TEACH_LOGIN as \"username\", AUTHORITY as \"authority\" "
////                        + "FROM Teachers WHERE teachers.teach_login = ?")
////                .passwordEncoder(getPasswordEncoder());
////    }
//
//    @Bean
//    public BCryptPasswordEncoder getPasswordEncoder() {
//        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
//        return passEncoder;
//        //return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.authorizeRequests()
////                // URL for gain access
////                .antMatchers("/home").permitAll()
////                //.antMatchers("/login").permitAll()
////                //.antMatchers("/register").permitAll()
////                //.antMatchers("/home/**").hasRole("ADMIN")
////                .anyRequest().authenticated()
////                .and()
////                //form login
////                .formLogin();
//        http.authorizeRequests().antMatchers("/").permitAll();
//    }
//
//    // TO DO - take into consideration blocking some of those sites from public access by ADMIN
//    // @Override
//    // public void configure(WebSecurity web) throws Exception {
//    // 	web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    // }
//
//}
//
