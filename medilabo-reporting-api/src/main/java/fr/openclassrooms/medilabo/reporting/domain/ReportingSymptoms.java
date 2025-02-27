package fr.openclassrooms.medilabo.reporting.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportingSymptoms
{
    List<String> symptoms = new ArrayList<>( );

    public ReportingSymptoms( )
    {
        symptoms.addAll( List.of(
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Fumeuse",
                "Anormal",
                "Cholestérol",
                "Vertiges",
                "Rechute",
                "Réaction",
                "Anticorps"));
    }
}
