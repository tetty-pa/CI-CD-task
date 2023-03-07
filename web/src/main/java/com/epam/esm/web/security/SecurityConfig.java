package com.epam.esm.web.security;

import com.epam.esm.model.entity.Role;
import com.epam.esm.service.security.PersonUserDetailsService;
import com.epam.esm.web.exception.ExceptionResponse;
import com.epam.esm.web.exception.RestResponseEntityExceptionHandler;
import com.epam.esm.web.filter.JwtRequestFilter;
import com.epam.esm.web.filter.ServletJsonResponseSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USER = Role.RoleType.USER.name();
    public static final String ADMIN = Role.RoleType.ADMIN.name();

    PersonUserDetailsService userDetailsService;
    JwtRequestFilter jwtRequestFilter;
    RestResponseEntityExceptionHandler handler;
    ServletJsonResponseSender jsonResponseSender;

    @Autowired
    public SecurityConfig(PersonUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter, RestResponseEntityExceptionHandler handler, ServletJsonResponseSender jsonResponseSender) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.handler = handler;
        this.jsonResponseSender = jsonResponseSender;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()

                .antMatchers(GET, "/gift-certificates/**").permitAll()
                .antMatchers(POST, "/authenticate", "/signup").permitAll()

                .antMatchers("/orders/**").hasAnyRole(USER, ADMIN)
                .antMatchers(GET, "/users/**", "/tags/**").hasRole( ADMIN)

                .anyRequest().hasRole(ADMIN)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> handleNoJwt(request, response)
                )
                .and().formLogin();
    }

    private void handleNoJwt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Locale locale = request.getLocale();
        ExceptionResponse responseObject = handler.buildNoJwtResponseObject(locale);
        jsonResponseSender.send(response, responseObject);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
