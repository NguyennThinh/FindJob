package com.edu.job.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;





@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public UserDetailsService userDetailsService() {

        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http.csrf(_csrf-> _csrf.disable())

                .formLogin(form -> form.loginPage("/auth/login-page").loginProcessingUrl("/auth/login")
                        .usernameParameter("email")
                       .defaultSuccessUrl("/index")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()   )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "user")
                        .permitAll())

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/profile/**", "/user/**", "/recruitment/**", "/save/**","/delete/**","/apply-job/**",
                                "/save-job/**").authenticated()
                        .anyRequest().permitAll())
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                );
        ;
        return http.build();
    }

}
