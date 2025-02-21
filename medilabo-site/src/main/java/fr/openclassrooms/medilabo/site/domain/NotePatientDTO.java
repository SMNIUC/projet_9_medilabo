package fr.openclassrooms.medilabo.site.domain;

import lombok.Data;

@Data
public class NotePatientDTO
{
    private String _id;
    private String patientId;
    private String patient;
    private String note;
}
