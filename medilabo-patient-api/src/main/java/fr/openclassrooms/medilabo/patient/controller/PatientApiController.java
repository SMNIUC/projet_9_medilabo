package fr.openclassrooms.medilabo.patient.controller;

import fr.openclassrooms.medilabo.patient.domain.Patient;
import fr.openclassrooms.medilabo.patient.service.PatientApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PatientApiController
{
    private final PatientApiService patientApiService;

    @GetMapping("/patients/list")
    public List<Patient> getPatientList( )
    {
        return patientApiService.getAllPatients( );
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById( @PathVariable("id") int patientId )
    {
        return patientApiService.findPatientById( patientId );
    }

    @GetMapping("/patients/getPatientId/{patientName}")
    public String getPatientIdByName( @PathVariable String patientName )
    {
        return patientApiService.getPatientIdByName( patientName );
    }

    @PostMapping("/patients/addNewPatient")
    public ResponseEntity<?> addNewPatient( @Valid @RequestBody Patient newPatient )
    {
        try
        {
            Patient patientAdded = patientApiService.addNewPatient( newPatient );

            if ( patientAdded == null )
            {
                return ResponseEntity.status( HttpStatus.NO_CONTENT )
                        .body( Map.of( "error", "Unable to add patient. Please try again." ) );
            }

            // Build the location URI for the created resource
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest( )
                    .path( "/{id}" )
                    .buildAndExpand( patientAdded.getIdPatient( ) )
                    .toUri( );

            // Return a 201 Created response
            return ResponseEntity.created( location ).build( );
        }
        catch ( Exception e )
        {
            // Handle unexpected exceptions
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( Map.of( "error", "An unexpected error occurred: " + e.getMessage( ) ) );

        }
    }

    // TODO - need patientId?
    @PostMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient( @RequestBody Patient updatedPatient, @PathVariable String id )
    {
        ResponseEntity<Patient> response;

//        if ( result.hasErrors( ) )
//        {
//            response = ResponseEntity.status( HttpStatus.NOT_IMPLEMENTED ).body( null );
//        } else {
            response = ResponseEntity.status( HttpStatus.CREATED ).body( patientApiService.updatePatient( updatedPatient, id ) );
//        }

        return response;
    }

    @DeleteMapping("/patients/delete-patient/{id}")
    public void deletePatient( @PathVariable("id") int patientId )
    {
        patientApiService.deletePatient( patientId );
    }
}
