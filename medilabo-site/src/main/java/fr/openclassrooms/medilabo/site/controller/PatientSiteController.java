package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import fr.openclassrooms.medilabo.site.domain.ReportingPatientDTO;
import fr.openclassrooms.medilabo.site.service.PatientDTOService;
import fr.openclassrooms.medilabo.site.service.ReportingPatientDTOService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PatientSiteController
{
    private final RestTemplate restTemplate;
    private final PatientDTOService patientDTOService;
    private final ReportingPatientDTOService reportingPatientDTOService;

    // TODO - implement spring security
    private HttpHeaders getAuthHeaders( )
    {
        HttpHeaders headers = new HttpHeaders( );
        headers.setBasicAuth("user", "user");
        return headers;
    }

    @Value( "${gateway.url}" )
    private String gatewayUrl;

    @GetMapping("/patients/list")
    public String patientList( Model model )
    {
        String endpoint = "/patients/list";
        HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

        try {
            // Fetch the response as a List of Products
            ResponseEntity<List<PatientDTO>> response = restTemplate
                    .exchange(
                            gatewayUrl + endpoint,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<>( ) {
                            }
                    );

            List<ReportingPatientDTO> reportingPatientDTOList = new ArrayList<>( );
            if ( response.getBody( ) != null && !response.getBody( ).isEmpty( ) )
            {
                reportingPatientDTOList = reportingPatientDTOService.getReportingPatientDTOList( response.getBody( ) );
            }

            model.addAttribute( "patients", reportingPatientDTOList );
        }
        catch ( HttpClientErrorException e )
        {
            model.addAttribute("error", "Unable to fetch the patient list. Please check the backend API.");
        }
        catch ( Exception e )
        {
            model.addAttribute("error", "An unexpected error occurred while fetching the patient list.");
            throw new RuntimeException( e );
        }

        return "patient/patient-list";
    }

    @GetMapping("/patients/{id}")
    public String patientProfilePage( Model model, @PathVariable int id )
    {
        try
        {
            String endpoint = "/patients/" + id;

            HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

            PatientDTO patient = restTemplate.exchange(
                    gatewayUrl + endpoint,
                            HttpMethod.GET,
                            entity,
                            PatientDTO.class
                    ).getBody( );

            model.addAttribute("patient", patient );
            
            return "patient/patient-list";
        }
        catch ( HttpClientErrorException e )
        {
            model.addAttribute( "error", "Error occurred while fetching patient: " + e.getResponseBodyAsString( ) );
            return "patient/patient-profile";
        }
        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "patient/patient-profile";
        }
    }

    @GetMapping("/patients/newPatientForm")
    public String getNewPatientForm( )
    {
        return "patient/new-patient-form";
    }

    // TODO - error msgs
    @PostMapping("/patients/addNewPatient")
    public String addNewPatient( @RequestBody MultiValueMap<String, String> formData, Model model )
    {
        try
        {
            HttpHeaders headers = getAuthHeaders( );
            String url = gatewayUrl + "/patients";

            HttpEntity<PatientDTO> requestEntity = new HttpEntity<>( patientDTOService.convertFormDataIntoPatientDTO( formData ), headers );

            ResponseEntity<?> response = restTemplate.postForEntity( url, requestEntity, String.class );

            // Check the response status
            if ( response.getStatusCode( ) == HttpStatus.NO_CONTENT )
            {
                model.addAttribute( "error", "Unable to add patient. Please try again." );
                return "patient/new-patient-form";
            }
            else if ( response.getStatusCode( ) == HttpStatus.CREATED )
            {
                return "redirect:/patients/list";
            }
            else
            {
                model.addAttribute( "error", "Unexpected response from gateway: " + response.getStatusCode( ) );
                return "patient/new-patient-form";
            }
        }

        catch ( HttpClientErrorException e )
        {
            if ( e.getStatusCode( ) == HttpStatus.BAD_REQUEST )
            {
                // Pass validation errors back to the Thymeleaf template
                Map<String, String> errors = e.getResponseBodyAs( Map.class );
                model.addAttribute("errors", errors );

                return "patient/new-patient-form";
            }
            else
            {
                model.addAttribute( "error", "Failed to add new patient: " + e.getResponseBodyAsString( ) );
                return "patient/new-patient-form";
            }
        }

        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "patient/new-patient-form";
        }
    }

    @PostMapping("/patients/{id}")
    public String updatePatientInfo( @RequestBody MultiValueMap<String, String> formData, @PathVariable int id, Model model )
    {
        try
        {
            HttpHeaders headers = getAuthHeaders( );
            String endpoint = "/patients/" + id;
            String url = gatewayUrl + endpoint;

            PatientDTO updatedPatient = patientDTOService.convertFormDataIntoPatientDTO( formData );

            HttpEntity<PatientDTO> requestEntity = new HttpEntity<>( updatedPatient , headers );

            ResponseEntity<String> response = restTemplate.postForEntity( url, requestEntity, String.class );

            if ( response.getStatusCode( ) == HttpStatus.CREATED )
            {
                model.addAttribute("patient", updatedPatient );
                return "patient/patient-profile";
            }
            else
            {
                model.addAttribute( "error", "Unexpected response from gateway: " + response.getStatusCode( ) );
                return "patient/patient-profile";
            }
        }

        catch ( HttpClientErrorException e )
        {
            model.addAttribute( "error", "Failed to add new patient: " + e.getResponseBodyAsString( ) );
            return "patient/patient-profile";
        }

        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "patient/patient-profile";
        }
    }

    @GetMapping("/patients/delete-patient/{id}")
    public String deletePatient( @PathVariable int id, Model model )
    {
        try
        {
            String endpoint = "/patients/delete-patient/" + id;
            String url = gatewayUrl + endpoint;

            // Create new headers dynamically to ensure they are fresh for this specific request
            HttpHeaders headers = getAuthHeaders( );
            HttpEntity<?> entity = new HttpEntity<>( headers );

            restTemplate.exchange( url, HttpMethod.DELETE, entity, Void.class );

            return patientList( model );
        }
        catch ( HttpClientErrorException e )
        {
            model.addAttribute( "error", "Error occurred while deleting patient: " + e.getResponseBodyAsString( ) );
            return "patient/patient-list";
        }
        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "patient/patient-list";
        }
    }
}
