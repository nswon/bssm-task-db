package taskdb.taskdb.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import taskdb.taskdb.infrastructure.security.auth.CustomUserDetailsService;
import taskdb.taskdb.infrastructure.security.jwt.JwtAuthenticationFilter;
import taskdb.taskdb.infrastructure.security.jwt.JwtTokenProvider;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/user/join",
                        "/email/join",
                        "/question",
                        "/question/categories/**",
                        "/question/grade/**",
                        "/question/status/**",
                        "/test");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           JwtTokenProvider jwtTokenProvider,
                                           CustomUserDetailsService customUserDetailsService) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedOrigin("/**");
        configuration.setAllowedOrigins(List.of("localhost:3000", "http://13.209.39.0:3000", "https://13.209.39.0:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
