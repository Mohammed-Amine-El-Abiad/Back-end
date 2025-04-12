package fst.GestionRessource.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("api/auth/**")
//                .requestMatchers("/api/v1/**")
                .permitAll()
                .requestMatchers("/api/**").hasAnyAuthority("SUPER_ADMIN", "PROJECT_MANAGER", "TEAM_MEMBER")
//                .requestMatchers("/api/v1/users/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/fournisseurs/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/marchesCommandes/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/departements/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/attachements/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/operationAttachements/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/detailsAA/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/articles/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/detailsArticleMarche/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/operationPiece/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
//                .requestMatchers("/api/v1/piece/**").hasAnyAuthority("ADMIN","BO","DCF","DAICG")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
