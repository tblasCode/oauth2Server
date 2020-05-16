package com.oauth2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@Order(1)
@Import(Encoders.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private PasswordEncoder userPasswordEncoder;
//	@Autowired
//	private CustomUserDetailsService userDetailsService;
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.requestMatchers()
		.antMatchers("/login")
		.antMatchers("/oauth/**")
		.and()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf()
		.and()
		.formLogin()
		.usernameParameter("username")
		.passwordParameter("password")
		.loginPage("/login")
		.permitAll()
		.and()
		.rememberMe()
		.rememberMeParameter("remember")
		.and()
		.httpBasic().disable()
		.logout().permitAll();

	}

}
