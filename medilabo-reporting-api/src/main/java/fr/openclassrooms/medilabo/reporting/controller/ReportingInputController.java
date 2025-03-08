package fr.openclassrooms.medilabo.reporting.controller;

import fr.openclassrooms.medilabo.reporting.domain.ReportingNoteDTO;
import fr.openclassrooms.medilabo.reporting.domain.ReportingPatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportingInputController
{
    private final RestTemplate restTemplate;

    private HttpHeaders getAuthHeaders( )
    {
        HttpHeaders headers = new HttpHeaders( );
        headers.setBasicAuth("user", "user");
        return headers;
    }

    @Value( "${gateway.url}" )
    private String gatewayUrl;

    @GetMapping("/patients/list")
    public List<ReportingPatientDTO> patientList( )
    {
        String endpoint = "/patients/list";
        HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

        ResponseEntity<List<ReportingPatientDTO>> response = restTemplate
                .exchange(
                        gatewayUrl + endpoint,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>( ) {}
                );

        return response.getBody( );
    }

    @GetMapping("/doc/notes")
    public List<ReportingNoteDTO> getPatientNotes( )
    {
        String endpoint = "/doc/notes";
        HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

        ResponseEntity<List<ReportingNoteDTO>> response = restTemplate
                .exchange(
                        gatewayUrl + endpoint,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>( ) {}
                );

        return response.getBody( );
    }
}
