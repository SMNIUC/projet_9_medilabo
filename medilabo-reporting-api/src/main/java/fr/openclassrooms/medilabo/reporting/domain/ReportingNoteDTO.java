package fr.openclassrooms.medilabo.reporting.domain;

import lombok.Data;

@Data
public class ReportingNoteDTO
{
    private String _id;
    private String patientId;
    private String patient;
    private String note;
}
