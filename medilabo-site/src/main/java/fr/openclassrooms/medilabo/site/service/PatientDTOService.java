package fr.openclassrooms.medilabo.site.service;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PatientDTOService
{
    /**
     * Converts form data into a Patient object
     *
     * @param formData the json form data to create the patient
     */
    public PatientDTO convertFormDataIntoPatientDTO( MultiValueMap<String, String> formData )
    {
        PatientDTO newPatient = new PatientDTO( );

        if( formData.getFirst( "idpatient" ) != null )
        {
            newPatient.setIdPatient( Integer.parseInt( Objects.requireNonNull( formData.getFirst( "idpatient" ) ) ) );
        }
        newPatient.setPrenom( formData.getFirst( "prenom" ) );
        newPatient.setNom( formData.getFirst( "nom" ) );
        newPatient.setDateNaissance( formData.getFirst( "dateNaissance" ) );
        newPatient.setGenre( formData.getFirst( "genre" ) );
        newPatient.setAdresse( formData.getFirst( "adresse" ) );
        newPatient.setTelephone( formData.getFirst( "telephone" ) );

        return newPatient;
    }
}
