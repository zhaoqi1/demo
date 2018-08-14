package com.luohuacanyue.webmagic.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter{
	
	Logger logger = LoggerFactory.getLogger(WebSecurityConfigure.class);
	
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("");

		/*http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.httpBasic();*/
		
		http
        .authorizeRequests()
        .antMatchers("/","/index").permitAll() //访问 '/' 和 '/index' 无需登录认证权限
        .anyRequest().authenticated()//其他所有资源都需要认证，登陆后访问
        .and()
        .formLogin()
        .loginPage("/login") //指定登陆页面是login
        .defaultSuccessUrl("/index")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/loginout")
        .permitAll();
		
		http
        .authorizeRequests()
        .antMatchers("/","/index");
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	
	

}
