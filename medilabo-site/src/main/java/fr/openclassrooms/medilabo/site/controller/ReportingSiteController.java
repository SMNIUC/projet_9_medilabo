package fr.openclassrooms.medilabo.site.controller;

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

    // TODO - implement spring security
    private HttpHeaders getAuthHeaders( )
    {
        HttpHeaders headers = new HttpHeaders( );
        headers.setBasicAuth("user", "user");
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
            // If the endpoint is not found (404), log and display a friendly message
//            model.addAttribute("error", "Unable to fetch the reporting status. Please check the backend API.");
            System.err.println("Error fetching reporting status: " + e.getMessage());
            response.put( "error", e.getMessage( ) );
        }
        catch (Exception e)
        {
            // Handle other exceptions gracefully
//            model.addAttribute("error", "An unexpected error occurred while fetching the reporting status.");
            System.err.println("Unexpected error: " + e.getMessage());
            response.put( "error", e.getMessage( ) );
        }

        return response;
    }

//    @GetMapping("/reporting/status")
//    public String patientList( Model model )
//    {
//        String endpoint = "/patients/list";
//        HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );
//
//        // Fetch the response as a List of Products
//        ResponseEntity<List<PatientDTO>> response = restTemplate
//                .exchange(
//                        gatewayUrl + endpoint,
//                        HttpMethod.GET,
//                        entity,
//                        new ParameterizedTypeReference<>( ) {}
//                );
//
//        model.addAttribute( "patients", response.getBody( ) );
//
//        return "patient/patient-list";
//    }
}
