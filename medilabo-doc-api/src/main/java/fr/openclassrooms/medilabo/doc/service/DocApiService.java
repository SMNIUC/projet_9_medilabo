package fr.openclassrooms.medilabo.doc.service;

import fr.openclassrooms.medilabo.doc.domain.NotePatient;
import fr.openclassrooms.medilabo.doc.repository.NotePatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocApiService
{
    private final NotePatientRepository notePatientRepository;

    /**
     * Gets all the available patient notes from the database
     *
     * @return a list of patient notes
     */
    public List<NotePatient> getAllNotesPatient( )
    {
        List<NotePatient> allNotesPatientList;

        notePatientRepository.findAll( );

        if ( !notePatientRepository.findAll( ).isEmpty( ) )
        {
            allNotesPatientList = new ArrayList<>( notePatientRepository.findAll( ) );
        }
        else
        {
            throw new IllegalArgumentException( "No patient notes found" );
        }

        return allNotesPatientList;
    }

    /**
     * Finds the list of all the patient's notes in the database from the patient's name
     *
     * @param patientName the name of the patient's notes to be found
     * @return a list of patient notes
     */
    public List<NotePatient> findNotesPatientByPatientName( String patientName )
    {
        return notePatientRepository.findByPatient( patientName );
    }

    /**
     * Creates and saves a new patient note in the database
     *
     * @param newNote the NotePatient object to be saved
     */
    @Transactional
    public NotePatient addNewPatientNote( NotePatient newNote )
    {
        return notePatientRepository.insert( newNote );
    }
}
