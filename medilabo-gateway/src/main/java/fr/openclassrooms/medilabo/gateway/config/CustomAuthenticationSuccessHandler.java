package fr.openclassrooms.medilabo.gateway.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler
{
    private final RedirectServerAuthenticationSuccessHandler delegate = new RedirectServerAuthenticationSuccessHandler( );

    @Override
    public Mono<Void> onAuthenticationSuccess( WebFilterExchange webFilterExchange, Authentication authentication )
    {
        ServerWebExchange exchange = webFilterExchange.getExchange( );

        // Redirect to the original requested URL or fallback to "/"
        return exchange.getSession( ).flatMap( session ->
        {
            String targetUrl = "http://localhost:8084/patients/list";
            delegate.setLocation( URI.create( targetUrl ) );
            return delegate.onAuthenticationSuccess( webFilterExchange, authentication) ;
        });
    }
}
