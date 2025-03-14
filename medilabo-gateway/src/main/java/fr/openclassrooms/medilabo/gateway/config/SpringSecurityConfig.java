package fr.openclassrooms.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig
{
    @Bean
    public MapReactiveUserDetailsService userDetailsService( )
    {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder( );

        UserDetails user = User.builder( )
                .username( "user" )
                .password( encoder.encode( "user" ) )
                .roles( "USER" )
                .build( );

        return new MapReactiveUserDetailsService( user );
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http )
    {
        http
            .csrf( ServerHttpSecurity.CsrfSpec::disable )
            .authorizeExchange(exchanges -> exchanges
                    .pathMatchers( "/login" ).permitAll( )
                    .anyExchange( ).authenticated( )  // Secure everything else
            )
            .httpBasic( Customizer.withDefaults( ) )
            .formLogin( Customizer.withDefaults( ) );

        return  http.build( );
    }
}
