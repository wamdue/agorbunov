package ru.job4j.mapping.carshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created on 14.02.18.
 * Security configuration.
 * @author Wamdue
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Data source link.
     */
    private final DataSource dataSource;

    /**
     * Main constructor.
     * @param dataSource - data source.
     */
    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Choosing method of authentication.
     * @param auth - main manager.
     * @throws Exception - exception.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery("select name, password, true from users where name=?")
                .authoritiesByUsernameQuery("select name, authority from users, authorities where id=user_id and name=?")
                .passwordEncoder(this.passwordEncoder());
    }

    /**
     * Password encryption method.
     * @return - encryption method.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Access configuration.
     * @param http - http
     * @throws Exception - exception.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signin.do", "/newUser.do").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/signin.do")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
