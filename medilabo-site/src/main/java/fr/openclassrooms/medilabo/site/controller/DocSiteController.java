package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.domain.NotePatientDTO;
import fr.openclassrooms.medilabo.site.domain.User;
import fr.openclassrooms.medilabo.site.service.NotePatientDTOService;
import fr.openclassrooms.medilabo.site.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DocSiteController
{
    private final RestTemplate restTemplate;
    private final NotePatientDTOService notePatientDTOService;
    private final PatientSiteController patientSiteController;
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

    @GetMapping("/doc/note-patient/{patientName}")
    public String historiqueNotesPatient( Model model, @PathVariable String patientName )
    {
        try
        {
            String endpoint = "/doc/note-patient/" + patientName;

            HttpEntity<?> entity = new HttpEntity<>( getAuthHeaders( ) );

            ResponseEntity<List<NotePatientDTO>> response = restTemplate
                    .exchange(
                            gatewayUrl + endpoint,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<>( ) {}
                    );

            model.addAttribute( "patientId", patientSiteController.getPatientIdByName( patientName) );
            model.addAttribute( "notes", response.getBody( ) );

            return "doc/historique-notes-patient";
        }
        catch ( HttpClientErrorException e )
        {
            model.addAttribute( "error", "Error occurred while fetching patient notes: " + e.getResponseBodyAsString( ) );
            return "doc/historique-notes-patient";
        }
        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "doc/historique-notes-patient";
        }
    }

    @GetMapping("/doc/newPatientNoteForm/{patientName}&{patientId}")
    public String getNewPatientNoteForm( Model model, @PathVariable String patientName, @PathVariable String patientId )
    {
        model.addAttribute( "patientName", patientName );
        model.addAttribute( "patientId", patientId );
        return "doc/new-patient-note-form";
    }

    // TODO - error msgs
    @PostMapping("/doc/write-note")
    public String addNewPatientNote( @RequestBody MultiValueMap<String, String> formData, Model model )
    {
        try
        {
            HttpHeaders headers = getAuthHeaders( );
            String url = gatewayUrl + "/doc/write-note";

            NotePatientDTO notePatientDTO = notePatientDTOService.convertFormDataIntoNotePatientDTO( formData );

            HttpEntity<NotePatientDTO> requestEntity = new HttpEntity<>( notePatientDTO, headers );

            ResponseEntity<?> response = restTemplate.postForEntity( url, requestEntity, String.class );

            // Check the response status
            if ( response.getStatusCode( ) == HttpStatus.NO_CONTENT )
            {
                model.addAttribute( "error", "Unable to add patient note. Please try again." );
                return "doc/new-patient-note-form";
            }
            else if ( response.getStatusCode( ) == HttpStatus.CREATED )
            {
                return "redirect:/doc/note-patient/" + notePatientDTO.getPatient( );
            }
            else
            {
                model.addAttribute( "error", "Unexpected response from gateway: " + response.getStatusCode( ) );
                return "doc/new-patient-note-form";
            }
        }

        catch ( HttpClientErrorException e )
        {
            if ( e.getStatusCode( ) == HttpStatus.BAD_REQUEST )
            {
                // Pass validation errors back to the Thymeleaf template
                Map<String, String> errors = e.getResponseBodyAs( Map.class );
                model.addAttribute("errors", errors );

                return "doc/new-patient-note-form";
            }
            else
            {
                model.addAttribute( "error", "Failed to add new patient note: " + e.getResponseBodyAsString( ) );
                return "doc/new-patient-note-form";
            }
        }

        catch ( Exception e )
        {
            model.addAttribute( "error", "An unexpected error occurred: " + e.getMessage( ) );
            return "doc/new-patient-note-form";
        }
    }
}
