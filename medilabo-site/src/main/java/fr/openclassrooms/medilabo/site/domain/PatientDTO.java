package fr.openclassrooms.medilabo.site.domain;

//import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientDTO
{
    private int    idPatient;

//    @NotBlank(message = "Le champ Nom ne peut pas etre vide.")
    private String nom;

//    @NotBlank(message = "Le champ Prenom ne peut pas etre vide.")
    private String prenom;

//    @NotBlank(message = "Le champ Date de Naissance ne peut pas etre vide.")
    private String dateNaissance;

//    @NotBlank(message = "Le champ Genre ne peut pas etre vide.")
    private String genre;

    private String adresse;

    private String telephone;
}
