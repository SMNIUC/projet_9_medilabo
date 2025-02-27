package fr.openclassrooms.medilabo.reporting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig
{
    @Bean
    public RestTemplate restTemplate( )
    {
        return new RestTemplate( );  // Default RestTemplate (blocking, synchronous)
    }
}
