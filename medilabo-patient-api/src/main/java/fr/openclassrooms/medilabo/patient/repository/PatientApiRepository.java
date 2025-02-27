package fr.openclassrooms.medilabo.patient.repository;

import fr.openclassrooms.medilabo.patient.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientApiRepository extends CrudRepository<Patient, String>
{
    Patient findPatientIdByNom( String patientName );
}