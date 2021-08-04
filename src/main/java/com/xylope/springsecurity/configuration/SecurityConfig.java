package com.xylope.springsecurity.configuration;

import com.xylope.springsecurity.configuration.security.CustomUserDetailsService;
import com.xylope.springsecurity.configuration.security.SecurityAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityAuthenticationFilter securityAuthenticationFilter() {
        return new SecurityAuthenticationFilter(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/v1/test/permit-all").permitAll()
                .antMatchers("/api/v1/test/auth").authenticated()
                .antMatchers("/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin().disable();
        http
                .addFilterBefore(securityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}