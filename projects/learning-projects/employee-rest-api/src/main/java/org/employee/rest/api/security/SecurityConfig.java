package org.employee.rest.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * - Deshabilitar CSRF para REST API
     * - Configurar reglas para proteger los endpoints
     * - Configurar HTTP Basic auth
     * - Se configura la session de tipo STATELESS
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/api/v1/employees").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/employees").hasRole("ADMIN")
                                .requestMatchers("/api/v1/employees/analytics/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       return http.build();
    }

    /**
     * Para hashear las password
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * - Define usuarios y roles en memoria
     * - Carga los usuarios en memoria
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
