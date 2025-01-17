package fr.openclassrooms.medilabo.patient.service;

import fr.openclassrooms.medilabo.patient.domain.Patient;
import fr.openclassrooms.medilabo.patient.repositories.PatientApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientApiService
{
    private final PatientApiRepository patientRepository;

    /**
     * Gets all the available patients from the database
     *
     * @return a list of patients
     */
    public List<Patient> getAllPatients( )
    {
        List<Patient> allPatientsList = new ArrayList<>( );
        patientRepository.findAll( ).forEach( allPatientsList::add );

        return allPatientsList;
    }

    /**
     * Finds a patient in the database from their id
     *
     * @param idPatient the id of the patient to be found
     * @return a patient
     */
    public Patient findPatientById( int idPatient )
    {
        return patientRepository.findById( String.valueOf( idPatient ) )
                .orElseThrow( ( ) -> new IllegalArgumentException( "Invalid patient Id:" + idPatient ) );
    }

    /**
     * Creates and saves a new patient in the database
     *
     * @param formData the json form data to create the patient
     */
    @Transactional
    public Patient addNewPatient( MultiValueMap<String, String> formData )
    {
        Patient newPatient = new Patient( );

        newPatient.setPrenom( formData.getFirst( "prenom" ) );
        newPatient.setNom( formData.getFirst( "nom" ) );
        newPatient.setDateNaissance( formData.getFirst( "dateNaissance" ) );
        newPatient.setGenre( formData.getFirst( "genre" ) );
        newPatient.setAdresse( formData.getFirst( "adresse" ) );
        newPatient.setTelephone( formData.getFirst( "telephone" ) );

        return patientRepository.save( newPatient );
    }

    /**
     * Updates a patient in the database with new info
     *
     * @param idPatient the id of the patient to be updated
     * @param formData the json form data to update the patient
     */
    @Transactional
    public Patient updatePatient( int idPatient, MultiValueMap<String, String> formData )
    {
        Patient updatedPatient = new Patient( );

        updatedPatient.setIdPatient( idPatient );
        updatedPatient.setPrenom( formData.getFirst( "prenom" ) );
        updatedPatient.setNom( formData.getFirst( "nom" ) );
        updatedPatient.setDateNaissance( formData.getFirst( "dateNaissance" ) );
        updatedPatient.setGenre( formData.getFirst( "genre" ) );
        updatedPatient.setAdresse( formData.getFirst( "adresse" ) );
        updatedPatient.setTelephone( formData.getFirst( "telephone" ) );

        patientRepository.save( updatedPatient );

        return findPatientById( idPatient );
    }

    /**
     * Deletes a patient in the database, based on their id
     *
     * @param idPatient the id of the patient to be deleted
     */
    @Transactional
    public void deletePatient( int idPatient )
    {
        Patient patientToDelete = findPatientById( idPatient );
        patientRepository.delete( patientToDelete );
    }
}
