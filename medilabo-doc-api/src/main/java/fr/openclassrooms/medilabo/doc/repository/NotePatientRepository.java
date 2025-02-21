package fr.openclassrooms.medilabo.doc.repository;

import fr.openclassrooms.medilabo.doc.domain.NotePatient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotePatientRepository extends MongoRepository<NotePatient, String>
{
    List<NotePatient> findByPatient( String patient );
}
