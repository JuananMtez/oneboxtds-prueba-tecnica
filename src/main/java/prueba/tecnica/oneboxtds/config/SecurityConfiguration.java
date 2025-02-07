package prueba.tecnica.oneboxtds.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (authz) ->
                authz
                    .requestMatchers(HttpMethod.GET, "/api/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.PATCH, "/api/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/*")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/*")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .httpBasic(withDefaults());
    http
        // ...
        .csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }
}
