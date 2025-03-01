package fr.openclassrooms.medilabo.patient.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient
{
    @Id
    @Column(name = "idpatient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    idPatient;

    @NotBlank(message = "Le champ Nom ne peut pas etre vide.")
    @Column(name = "nom")
    private String nom;

    @NotBlank(message = "Le champ Prenom ne peut pas etre vide.")
    @Column(name = "prenom")
    private String prenom;

    @NotBlank(message = "Le champ Date de Naissance ne peut pas etre vide.")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "La date doit suivre le format YYYY-MM-DD.")
    @Column(name = "date_naissance")
    private String dateNaissance;

    @NotBlank(message = "Le champ Genre ne peut pas etre vide.")
    @Column(name = "genre")
    private String genre;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;
}
