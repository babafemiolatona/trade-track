package com.springboot.tradetrack.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.tradetrack.Service.CustomUserDetailsService;
import com.springboot.tradetrack.Utils.CustomAccessDeniedHandler;
import com.springboot.tradetrack.Utils.CustomAuthEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    private CustomAccessDeniedHandler CustomAccessDeniedHandler;

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;

    @Autowired
    public SecurityConfig(@Lazy CustomUserDetailsService userDetailsService, @Lazy JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/v1/products").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/v1/products/{id}").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/products/{id}").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/auth/**").permitAll()
            .requestMatchers("/api/v1/cart/add").permitAll()
            .requestMatchers("/api/v1/cart/remove/{productId}").permitAll()
            .requestMatchers("/api/v1/cart/clear").permitAll()
            .requestMatchers("/api/v1/products/search").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(CustomAccessDeniedHandler)
            .authenticationEntryPoint(customAuthEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            
            return http.build();
            }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
