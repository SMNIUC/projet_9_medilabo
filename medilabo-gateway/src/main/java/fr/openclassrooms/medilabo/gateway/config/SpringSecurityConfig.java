package fr.openclassrooms.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig
{
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

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain( ServerHttpSecurity http )
    {
         http
             .csrf( ).disable( )
             .authorizeExchange()
                 .pathMatchers( "/login" ).permitAll( )
             .anyExchange().authenticated()
             .and()
             .httpBasic().and()
             .formLogin( formLogin -> formLogin
                    .authenticationSuccessHandler( new CustomAuthenticationSuccessHandler( ) ) // Enables redirection
             );

        return  http.build( );
    }
}
