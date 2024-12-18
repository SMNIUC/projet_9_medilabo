package fr.openclassrooms.patient.repositories;

import fr.openclassrooms.patient.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientApiRepository extends CrudRepository<Patient, String>
{
}