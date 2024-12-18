package fr.openclassrooms.medilabo.site.service;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Service
public class PatientSiteService
{
    public PatientDTO createNewPatient( MultiValueMap<String, String> formData )
    {
        PatientDTO newPatient = new PatientDTO( );

        newPatient.setPrenom( formData.getFirst( "prenom" ) );
        newPatient.setNom( formData.getFirst( "nom" ) );
        newPatient.setDateNaissance( formData.getFirst( "dateNaissance" ) );
        newPatient.setGenre( formData.getFirst( "genre" ) );
        newPatient.setAdresse( formData.getFirst( "adresse" ) );
        newPatient.setTelephone( formData.getFirst( "telephone" ) );

        return newPatient;
    }

    public PatientDTO updatePatientInfo( MultiValueMap<String, String> formData )
    {
        PatientDTO updatedPatient = new PatientDTO( );

        updatedPatient.setIdPatient( Integer.parseInt( Objects.requireNonNull( formData.getFirst( "idPatient" ) ) ) );
        updatedPatient.setPrenom( formData.getFirst( "prenom" ) );
        updatedPatient.setNom( formData.getFirst( "nom" ) );
        updatedPatient.setDateNaissance( formData.getFirst( "dateNaissance" ) );
        updatedPatient.setGenre( formData.getFirst( "genre" ) );
        updatedPatient.setAdresse( formData.getFirst( "adresse" ) );
        updatedPatient.setTelephone( formData.getFirst( "telephone" ) );

        return updatedPatient;
    }
}
