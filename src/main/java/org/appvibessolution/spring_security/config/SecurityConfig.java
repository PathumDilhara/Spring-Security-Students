package org.appvibessolution.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // This annotation indicates that this class contains Spring Security configuration
@EnableWebSecurity // Dont go to default security configuration
public class SecurityConfig {

    // This method configures the security filter chain for the application
    // Since build() throws Exception, your method must also declare throws Exception unless you:
    //- Catch the exception inside the method
    //- Or wrap it in a try-catch
    //- especially in Spring config classes, it's common and cleaner to just declare throws Exception
    @Bean
    public SecurityFilterChain securityFIlterChain(HttpSecurity http) throws Exception {
        return http.build();
    }
}
