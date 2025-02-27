package fr.openclassrooms.medilabo.patient.service;

import fr.openclassrooms.medilabo.patient.domain.Patient;
import fr.openclassrooms.medilabo.patient.repository.PatientApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param newPatient the Patient object to be saved
     */
    @Transactional
    public Patient addNewPatient( Patient newPatient )
    {
        return patientRepository.save( newPatient );
    }

    /**
     * Updates a patient in the database with new info
     *
     * @param idPatient the id of the patient to be updated
     * @param patient the Patient object to be updated
     */
    @Transactional
    public Patient updatePatient( Patient patient, String idPatient )
    {
        patientRepository.save( patient );
        return findPatientById( Integer.parseInt( idPatient ) );
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

    public String getPatientIdByName( String patientName )
    {
        return String.valueOf( patientRepository.findPatientIdByNom( patientName ).getIdPatient( ) );
    }
}
