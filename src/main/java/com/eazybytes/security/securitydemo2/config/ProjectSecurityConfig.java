package com.eazybytes.security.securitydemo2.config;

import com.eazybytes.security.securitydemo2.filters.JWTTokenGenerationFilter;
import com.eazybytes.security.securitydemo2.filters.JWTTokenValidationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // Only HTTPS
                .csrf(csrfConfig -> csrfConfig.disable())
                .cors(corsConfig -> corsConfig.disable())
                //.addFilterAfter(new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class)
                //.addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
                //.formLogin((formLogin) ->
                //        formLogin
                //                .usernameParameter("username")
                //                .passwordParameter("password")
                //                .loginPage("/authentication/login")
                //                .failureUrl("/authentication/login?failed")
                //                .loginProcessingUrl("/authentication/login/process"))
                .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/orders", "/clients").authenticated()
                        .requestMatchers("/clients").hasRole("ADMIN")
                        .requestMatchers("/contactUs","/notices","/error").permitAll())
                .addFilterAfter(new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource datasource) {

        return new JdbcUserDetailsManager(datasource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
