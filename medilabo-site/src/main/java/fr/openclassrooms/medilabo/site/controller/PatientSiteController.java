package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientSiteController
{
    private final RestTemplate restTemplate;

    @Value( "${gateway.url}" )
    private String gatewayUrl;

    @GetMapping("/patients/list")
    public String patientList( Model model )
    {
        try
        {
            String endpoint = "/patients/list";
            String url = gatewayUrl + endpoint;

            // Fetch the response as a List of Products
            ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>( )
                    {
                    }
            );

            model.addAttribute( "patients", response.getBody( ) );

            return "patient/patient-list";
        } catch ( HttpClientErrorException e) {
            if ( e.getStatusCode( ) == HttpStatus.UNAUTHORIZED )  {
                // Redirect the user to the login page
                return "redirect:" + gatewayUrl + "/login";
            }
            throw e;
        }
    }

    @GetMapping("/patients/{id}")
    public String patientProfilePage( Model model, @PathVariable int id )
    {
        String endpoint = "/patients/" + id;
        String url = gatewayUrl + endpoint;

        PatientDTO patient = restTemplate.getForObject( url, PatientDTO.class );

        model.addAttribute("patient", patient );

        return "patient/patient-profile";
    }

    @GetMapping("/patients/newPatientForm")
    public String getNewPatientForm( )
    {
        return "patient/new-patient-form";
    }

    @PostMapping("/patients/addNewPatient")
    public String addNewPatient( @RequestBody MultiValueMap<String, String> formData, Model model )
    {
        HttpHeaders headers = new HttpHeaders( );
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );

        String endpoint = "/patients/addNewPatient";
        String url = gatewayUrl + endpoint;

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>( formData, headers );
        ResponseEntity<PatientDTO> response = restTemplate.exchange( url, HttpMethod.POST, request, PatientDTO.class );

        model.addAttribute("patient", response.getBody( ) );

        return patientList( model );
    }

    @PostMapping("/patients/{id}")
    public String updatePatientInfo( @RequestBody MultiValueMap<String, String> formData, @PathVariable int id, Model model )
    {
        HttpHeaders headers = new HttpHeaders( );
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );

        String endpoint = "/patients/" +id ;
        String url = gatewayUrl + endpoint;

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>( formData, headers );
        restTemplate.exchange( url, HttpMethod.POST, request, PatientDTO.class );

        return patientProfilePage( model, id );
    }

    @GetMapping("/patients/delete-patient/{id}")
    public String deletePatient( @PathVariable int id, Model model )
    {
        String endpoint = "/patients/delete-patient/" +id ;
        String url = gatewayUrl + endpoint;

        restTemplate.delete( url );

        return patientList( model );
    }
}
