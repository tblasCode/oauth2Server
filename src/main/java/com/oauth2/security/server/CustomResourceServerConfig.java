package com.oauth2.security.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.oauth2.security.config.AuthExceptionEntryPoint;
import com.oauth2.security.permit.PermitAllSecurityConfig;

/**
 * Created on 2018/1/17.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler appLoginInSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler appLoginFailureHandler;

    @Autowired
    private PermitAllSecurityConfig permitAllSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.formLogin()
                .successHandler(appLoginInSuccessHandler)
                .failureHandler(appLoginFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .apply(permitAllSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/forbidden").hasRole("ADMIN")
                .antMatchers("/permitAll").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        // @formatter:ON
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
