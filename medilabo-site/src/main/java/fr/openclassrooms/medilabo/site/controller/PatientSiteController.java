package fr.openclassrooms.medilabo.site.controller;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import fr.openclassrooms.medilabo.site.service.PatientSiteService;
import fr.openclassrooms.medilabo.site.webclient.PatientWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientSiteController
{
    private final PatientWebClient patientWebClient;
    private final PatientSiteService patientSiteService;

    @GetMapping("/patient/list")
    public String patientList( Model model )
    {
        Flux<PatientDTO> patientListResult = patientWebClient.getPatientList( );
        List<PatientDTO> patientList = patientListResult.collectList( ).block( );
        model.addAttribute("patients", patientList );

        return "patient/patient-list";
    }

    @GetMapping("/patient/profile/{id}")
    public String patientProfilePage( Model model, @PathVariable int id )
    {
        Mono<PatientDTO> patient = patientWebClient.getPatientInfo( id );
        PatientDTO patientInfo = patient.share( ).block( );
        model.addAttribute("patient", patientInfo );

        return "patient/patient-profile";
    }

    @GetMapping("/patient/newPatientForm")
    public String getNewPatientForm( )
    {
        return "patient/new-patient-form";
    }

    @PostMapping("/patient/addNewPatient")
    public String addNewPatient( @RequestBody MultiValueMap<String, String> formData, Model model )
    {
        PatientDTO newPatientInfo = patientSiteService.createNewPatient( formData );
        PatientDTO newPatient = patientWebClient.addNewPatient( newPatientInfo );
        model.addAttribute("patient", newPatient );

        return patientList( model );
    }

    @PostMapping("/patient/updatePatient/{id}")
    public String updatePatientInfo( @RequestBody MultiValueMap<String, String> formData, @PathVariable int id, Model model )
    {
        PatientDTO updatedPatientInfo = patientSiteService.updatePatientInfo( formData );
        PatientDTO updatedPatient = patientWebClient.updatePatientInfo( id, updatedPatientInfo );
        model.addAttribute("patient", updatedPatient );

        return "patient/patient-profile";
    }

    @GetMapping("/patient/deletePatient/{id}")
    public String deletePatient( @PathVariable int id, Model model )
    {
        patientWebClient.deletePatient( id );

        return patientList( model );
    }
}
