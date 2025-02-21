package fr.openclassrooms.medilabo.site.domain;

import lombok.Data;

@Data
public class PatientDTO
{
    private int    idPatient;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String genre;
    private String adresse;
    private String telephone;
}
