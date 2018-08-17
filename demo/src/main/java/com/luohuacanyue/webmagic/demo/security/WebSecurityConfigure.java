package com.luohuacanyue.webmagic.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter{
	
	Logger logger = LoggerFactory.getLogger(WebSecurityConfigure.class);
	
	@Autowired
	MyUserDetailsService  myUserDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.httpBasic();
		
		http
        .authorizeRequests()
        .antMatchers("/","/index").permitAll() //访问 '/' 和 '/index' 无需登录认证权限
        .anyRequest().authenticated();//其他所有资源都需要认证，登陆后访问
		
		
		/*
		http
		.formLogin()						//表单登陆
		.loginPage("/login.html")			//指定需要登录时发送用户的URL，根据需要重定向请求，需要自己写对应的请求路径
		.loginProcessingUrl("/login/form")			//指定验证凭据的URL，和表单路径一样
	.and()
		.authorizeRequests()
		.antMatchers("/login.html","/login/authentication")
		.permitAll()								//上面匹配的请求允许访问
		.anyRequest()
		.authenticated()							//其他请求需要认证才能访问
	.and()	
	.csrf().disable();*/
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("zhaoqi").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
//		auth.userDetailsService(myUserDetailsService);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

}
