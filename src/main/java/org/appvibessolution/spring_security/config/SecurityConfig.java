package org.appvibessolution.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // This annotation indicates that this class contains Spring Security configuration
@EnableWebSecurity // Dont go to default security configuration
public class SecurityConfig {

    @Autowired
    public UserDetailsService userDetailsService;

    // This method configures the security filter chain for the application
    // Since build() throws Exception, your method must also declare throws Exception unless you:
    //- Catch the exception inside the method
    //- Or wrap it in a try-catch
    //- especially in Spring config classes, it's common and cleaner to just declare throws Exception
    @Bean
    public SecurityFilterChain securityFIlterChain(HttpSecurity http) throws Exception {

        // we can do this lambda function same as below
        //  http.csrf(customizer -> customizer.disable());
        // http.authorizeHttpRequests(request ->
        //         request.anyRequest().authenticated());
        // // http.formLogin(Customizer.withDefaults());
        // http.httpBasic(Customizer.withDefaults());
        // http.sessionManagement(session->
        //        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Expanded version of the above lambda disabling line
        // Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>(){
        //    @Override
        //    public void customize(CsrfConfigurer<HttpSecurity> customizer){
        //       customizer.disable();
        //    }
        // };
        // http.csrf(csrfCustomizer);

        // return http.build();

        // Another way to write the above same code
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request ->
                        request.anyRequest().authenticated())
                // .formLogin(Customizer.withDefaults()) // ask always if uncommented
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    // Custom hard coded configuration of user details service
    //    @Bean
    //    public UserDetailsService userDetailsService() {
    //        UserDetails user1 = User
    //                .withDefaultPasswordEncoder()
    //                .username("user1")
    //                .password("123")
    //                .roles("USER")
    //                .build();
    //
    //        UserDetails user2 = User
    //                .withDefaultPasswordEncoder()
    //                .username("user2")
    //                .password("456")
    //                .roles("ADMIN")
    //                .build();
    //
    //        return new InMemoryUserDetailsManager(user1, user2);
    //    }

    // Custom configuration with authentication
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return  provider;
    }
}
