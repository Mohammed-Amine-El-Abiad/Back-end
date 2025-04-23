package fst.GestionRessource.Configuration;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Constantes pour les chemins d'endpoints
    private static final String AUTH_PATH = "/api/auth/**";
    // private static final String SWAGGER_PATH = "/swagger-ui/**";
    // private static final String API_DOCS_PATH = "/v3/api-docs/**";
    // private static final String ACTUATOR_PATH = "/actuator/health";
    private static final String RESOURCES_PATH = "/api/resources/**";
    private static final String PANIC_REPORTS_PATH = "/api/panic-reports/**";
    private static final String PROPOSALS_PATH = "/api/proposals/**";
    private static final String TENDERS_PATH = "/api/tenders/**";
    private static final String REQUESTS_PATH = "/api/requests/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .cors().and().csrf()
//                 .disable()
//                 .authorizeHttpRequests()
//                 .requestMatchers("api/auth/**")
// //                .requestMatchers("/api/v1/**")
//                 .permitAll()
//                 .requestMatchers("/api/**").hasAnyAuthority("SUPER_ADMIN")
//                 .anyRequest()
//                 .authenticated()
//                 .and()
//                 .sessionManagement()
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authenticationProvider(authenticationProvider)
//                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//         ;

                http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Endpoints publics
                        // .requestMatchers(AUTH_PATH, SWAGGER_PATH, API_DOCS_PATH, ACTUATOR_PATH)
                        .requestMatchers(AUTH_PATH)
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, RESOURCES_PATH)
                        .hasAnyAuthority("TEACHER", "DEPARTMENT_HEAD", "RESOURCE_MANAGER")
                        .requestMatchers(HttpMethod.POST, RESOURCES_PATH)
                        .hasAuthority("RESOURCE_MANAGER")
                        .requestMatchers(HttpMethod.POST, PANIC_REPORTS_PATH)
                        .hasAuthority("TEACHER")
                        .requestMatchers(PANIC_REPORTS_PATH)
                        .hasAuthority("RESOURCE_MANAGER")
                        .requestMatchers(HttpMethod.POST, PROPOSALS_PATH)
                        .hasAuthority("SUPPLIER")
                        .requestMatchers(PROPOSALS_PATH)
                        .hasAuthority("RESOURCE_MANAGER")
                        .requestMatchers(TENDERS_PATH)
                        .hasAuthority("RESOURCE_MANAGER")
                        .requestMatchers(HttpMethod.POST, REQUESTS_PATH)
                        .hasAuthority("DEPARTMENT_HEAD")
                        .requestMatchers(REQUESTS_PATH)
                        .hasAuthority("RESOURCE_MANAGER")
                        .requestMatchers("/api/**")
                        .hasAuthority("SUPER_ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Authentication failed: " + authException.getMessage() + "\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Access denied: " + accessDeniedException.getMessage() + "\"}");
                        })
                );

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://your-frontend.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

