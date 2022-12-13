package com.ProjectPlatform.ProjectPlatform.config;

import com.ProjectPlatform.ProjectPlatform.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationSuccessHandler roleSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        String[] staticResources = {
                "/sign.css",
                "/home.css",
                "/js/**",
                "/fonts/**",
                "/scripts/**",};

        http.csrf().disable().authorizeHttpRequests().requestMatchers("/", "/authorization/**").permitAll()
                .requestMatchers(staticResources).permitAll()
                .requestMatchers("/lecturer-home/new-lecture","/lecturer-home/{id}", "/lecturer-home/lecture/{id}", "/lecturer-home/new-project").hasAuthority("LECTURER")
                .requestMatchers("/home", "/home/{id}/{name}").hasAuthority("STUDENT")
                .anyRequest().authenticated().and().formLogin().loginPage("/authorization").successHandler(roleSuccessHandler).permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();
        return http.build();
    }

}