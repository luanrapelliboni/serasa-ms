package br.experian.com.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITELIST = {
        "/public/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/v3/api-docs",
        "/v2/api-docs",
        "/webjars/**",
        "/serasa-ms-openapi",
        "/serasa-ms-openapi/**",
        "/swagger-ui.html"
    };

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(WHITELIST);
//    }

    @Override
    public void configure(final HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.oauth2ResourceServer()
                .jwt();
    }
}