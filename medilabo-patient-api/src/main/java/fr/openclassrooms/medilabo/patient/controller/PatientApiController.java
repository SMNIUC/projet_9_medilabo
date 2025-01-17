package fr.openclassrooms.medilabo.patient.controller;

import fr.openclassrooms.medilabo.patient.domain.Patient;
import fr.openclassrooms.medilabo.patient.exceptions.PatientNotFoundException;
import fr.openclassrooms.medilabo.patient.service.PatientApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PatientApiController
{
    private final PatientApiService patientApiService;

    @GetMapping("/patients/list")
    public List<Patient> getPatientList( )
    {
        List<Patient> patientList = patientApiService.getAllPatients( );

        if( patientList.isEmpty( ) )
            throw new PatientNotFoundException( "Aucun patient n'est disponible.");

        return patientList;
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById( @PathVariable("id") int patientId )
    {
        Patient patient = patientApiService.findPatientById( patientId );

        if( patient == null )
            throw new PatientNotFoundException( "Le patient avec l'id " + patientId + " est introuvable.");

        return  patient;
    }

    @PostMapping("/patients/addNewPatient")
    public ResponseEntity<Patient> addNewPatient( @Valid @RequestBody MultiValueMap<String, String> formData )
    {
        // TODO - update error
//        if ( result.hasErrors( ) )
//        {
//            throw new RuntimeException();
//        }

        Patient patientAdded = patientApiService.addNewPatient( formData );

        if( Objects.isNull( patientAdded ) )
        {
            // TODO - return generic error that'll be interpreted by the front w/ throw
//            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande"); ->> 500 error - internal error
            return ResponseEntity.noContent( ).build( );
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest( )
                .path( "/{id}" )
                .buildAndExpand( patientAdded.getIdPatient( ) )
                .toUri( );

        // TODO - returns a Patient? Is it needed?
        return ResponseEntity.created( location ).build( );
    }

//    // TODO - need patientId?
    @PostMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient( @RequestBody MultiValueMap<String, String> formData, @PathVariable int id )
    {
        ResponseEntity<Patient> response;

//        if ( result.hasErrors( ) )
//        {
//            response = ResponseEntity.status( HttpStatus.NOT_IMPLEMENTED ).body( null );
//        } else {
            response = ResponseEntity.status( HttpStatus.CREATED ).body( patientApiService.updatePatient( id, formData ) );
//        }

        return response;
    }

    @DeleteMapping("/patients/delete-patient/{id}")
    public void deletePatient( @PathVariable("id") int patientId )
    {
        patientApiService.deletePatient( patientId );
    }
}
