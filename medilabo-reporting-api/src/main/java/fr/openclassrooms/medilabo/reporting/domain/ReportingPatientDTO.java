package fr.openclassrooms.medilabo.reporting.domain;

import lombok.Data;

@Data
public class ReportingPatientDTO
{
    private int    idPatient;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String genre;
    private String adresse;
    private String telephone;
}
