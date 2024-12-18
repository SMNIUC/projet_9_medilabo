package fr.openclassrooms.patient.controller;

import fr.openclassrooms.patient.domain.Patient;
import fr.openclassrooms.patient.service.PatientApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientApiController
{
    private final PatientApiService patientApiService;

    @RequestMapping("/patient/list")
    public List<Patient> getPatientList( )
    {
        return patientApiService.getAllPatients( );
    }

    @RequestMapping("/patient/{id}")
    public Patient getPatientById( @PathVariable int id )
    {
        return patientApiService.findPatientById( id );
    }

    @PostMapping("/patient/addNewPatient")
    public Patient addNewPatient( @RequestBody Patient newPatient )
    {
        // TODO - update error
//        if ( result.hasErrors( ) )
//        {
//            throw new RuntimeException();
//        }

        return patientApiService.addNewPatient( newPatient );
    }

    @PostMapping("/patient/updatePatient/{id}")
    public Patient updatePatient( @RequestBody Patient patient, @PathVariable int id )
    {
        // TODO - update error
//        if ( result.hasErrors( ) )
//        {
//            throw new RuntimeException();
//        }

        return patientApiService.updatePatient( id, patient );
    }

    @DeleteMapping("/patient/deletePatient/{id}")
    public void deletePatient( @PathVariable( "id" ) Integer patientId )
    {
        patientApiService.deletePatient( patientId );
    }
}
