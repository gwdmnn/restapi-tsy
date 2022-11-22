package com.aulas.restAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    JwtTokenStore tokenStore;

    @Autowired
    private Environment env;

    private static final String[] PUBLIC = {"/oauth/token","/h2-console","/usuarios"};
    private static final String[] ADMIN = {"/categorias"};
    private static final String[] USUARIO = {"/produtos"};



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests().antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, USUARIO).permitAll()
                .antMatchers(HttpMethod.POST, USUARIO).hasRole("USUARIO")
                .antMatchers(HttpMethod.PUT, ADMIN).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, ADMIN).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
