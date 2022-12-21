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
                .antMatchers(
                        AccessUrl.JOIN.url,
                        AccessUrl.EMAIL_CONFIRM.url);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           JwtTokenProvider jwtTokenProvider,
                                           CustomUserDetailsService customUserDetailsService) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(AccessUrl.LOGIN.url).permitAll()
                .antMatchers(AccessUrl.QUESTION_GET_ALL.url).permitAll()
                .antMatchers(AccessUrl.QUESTION_GET_BY_CATEGORY.url).permitAll()
                .antMatchers(AccessUrl.QUESTION_GET_BY_GRADE.url).permitAll()
                .antMatchers(AccessUrl.QUESTION_GET_BY_STATUS.url).permitAll()
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
        configuration.setAllowedOrigins(List.of(AccessUrl.ALLOW_ORIGIN.url));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private enum AccessUrl {
        JOIN("/user/join"),
        LOGIN("/auth/login"),
        EMAIL_CONFIRM("/email/confirm"),
        QUESTION_GET_ALL("/question"),
        QUESTION_GET_BY_CATEGORY("/question/categories/**"),
        QUESTION_GET_BY_GRADE("/question/grade/**"),
        QUESTION_GET_BY_STATUS("/question/status/**"),
        ALLOW_ORIGIN("localhost:3000")
        ;

        private final String url;

        AccessUrl(String url) {
            this.url = url;
        }
    }
}
