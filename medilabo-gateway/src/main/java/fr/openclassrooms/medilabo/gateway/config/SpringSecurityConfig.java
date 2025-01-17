package fr.openclassrooms.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig
{
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http )
    {
        return http.authorizeExchange( exchange
                        -> exchange
                            .anyExchange( )
                            .authenticated( ) )
                .httpBasic( withDefaults( ) )
                .formLogin( formLogin -> formLogin
                        .authenticationSuccessHandler(new CustomAuthenticationSuccessHandler()) // Enable redirection
                )
                .build( );
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService( )
    {
        UserDetails user = User.withDefaultPasswordEncoder( )
                .username( "user" )
                .password( "user" )
                .roles( "USER" )
                .build( );
        return new MapReactiveUserDetailsService( user );
    }
//
//    // TODO -
//    @Bean
//    public PasswordEncoder passwordEncoder( )
//    {
//        return new BCryptPasswordEncoder( );
//    }
}
