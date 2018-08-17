package com.luohuacanyue.webmagic.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class MyUserDetailsService implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = new User("zhaoqi", /*encode.replace("{bcrypt}", "")*/"123456",
				AuthorityUtils.commaSeparatedStringToAuthorityList("1234"))  ;
        logger.info("密:");
        return user;
	}

}
