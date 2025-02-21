package fr.openclassrooms.medilabo.doc.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection = "notes_patient" )
public class NotePatient
{
    @Id
    private String _id;
    private String patientId;
    private String patient;
    private String note;
}
