package org.tdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.CorsFilter;
import org.tdb.security.AuthProvider;
import org.tdb.security.jwt.JWTAuthenticationFilter;
import org.tdb.security.jwt.JWTLoginFilter;
import org.tdb.service.UserService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
            .and()
            .csrf()
            .ignoringAntMatchers("/upload-junit4-xml", "/upload-junit4-xml-wrapped")
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/login", "/account", "/status/*").permitAll()
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/api-docs/**", "/uploadForm.html").permitAll()
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/upload-junit4-xml", "/upload-junit4-xml-wrapped").permitAll()
                .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), userService),
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");

        config.addAllowedHeader("Origin");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("x-id");
        config.addAllowedHeader("Content-Length");
        config.addAllowedHeader("X-Requested-With");

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        authProvider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(authProvider);
    }

}
