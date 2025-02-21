package fr.openclassrooms.medilabo.site.service;

import fr.openclassrooms.medilabo.site.domain.NotePatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotePatientDTOService
{
    /**
     * Converts form data into a NotePatient object
     *
     * @param formData the json form data to create the patient note
     */
    public NotePatientDTO convertFormDataIntoNotePatientDTO( MultiValueMap<String, String> formData )
    {
        NotePatientDTO newNotePatient = new NotePatientDTO( );

        newNotePatient.setPatientId( Objects.requireNonNull( formData.getFirst( "patientId" ) ) );
        newNotePatient.setPatient( formData.getFirst( "patientName" ) );
        newNotePatient.setNote( formData.getFirst( "note" ) );

        return newNotePatient;
    }
}
