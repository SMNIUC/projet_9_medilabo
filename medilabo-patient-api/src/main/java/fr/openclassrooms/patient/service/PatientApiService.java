package fr.openclassrooms.patient.service;

import fr.openclassrooms.patient.domain.Patient;
import fr.openclassrooms.patient.repositories.PatientApiRepository;
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
     * @param patient the patient to be saved
     */
    @Transactional
    public Patient addNewPatient( Patient patient )
    {
        return patientRepository.save( patient );
    }

    /**
     * Updates a patient in the database with new info
     *
     * @param idPatient the id of the patient to be updated
     * @param patient a patient object that holds the new patient information
     */
    @Transactional
    public Patient updatePatient( int idPatient, Patient patient )
    {
        patient.setIdPatient( idPatient );
        patientRepository.save( patient );

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
