package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.domain.User;
import fr.openclassrooms.medilabo.site.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ReportingSiteController
{
    private final RestTemplate restTemplate;
    private final UserService userService;

    private HttpHeaders getAuthHeaders( )
    {
        HttpHeaders headers = new HttpHeaders( );
        User user = userService.getLastUser( );
        String username = user.getUsername( );
        String password = user.getPassword( );
        headers.setBasicAuth( username, password );
        return headers;
    }

    @Value( "${gateway.url}" )
    private String gatewayUrl;

    @GetMapping("/reporting/status")
    public Map<String, String> reportingStatus( )
    {
        String endpoint = "/reporting/status";
        String fullUrl = gatewayUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

        Map<String, String> response = new HashMap<>( );

        try
        {
            // Make the REST call
            ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>( ) {}
            );

            response = responseEntity.getBody();
            // Add the response body to the model
        }
        catch (HttpClientErrorException e)
        {
            System.err.println("Error fetching reporting status: " + e.getMessage());
            response.put( "error", e.getMessage( ) );
        }
        catch (Exception e)
        {
            // Handle other exceptions gracefully
            System.err.println("Unexpected error: " + e.getMessage());
            response.put( "error", e.getMessage( ) );
        }

        return response;
    }
}
