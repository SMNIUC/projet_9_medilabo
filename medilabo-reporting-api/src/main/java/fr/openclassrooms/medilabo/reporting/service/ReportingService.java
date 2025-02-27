package fr.openclassrooms.medilabo.reporting.service;

import fr.openclassrooms.medilabo.reporting.constant.ReportingContant;
import fr.openclassrooms.medilabo.reporting.controller.ReportingInputController;
import fr.openclassrooms.medilabo.reporting.domain.ReportingNoteDTO;
import fr.openclassrooms.medilabo.reporting.domain.ReportingPatientDTO;
import fr.openclassrooms.medilabo.reporting.domain.ReportingSymptoms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportingService
{
    private final ReportingInputController reportingInputController;

    public Map<String, String> reportPatientStatus( )
    {
        Map<String, String> patientStatusMap = new HashMap<>( );

        List<ReportingPatientDTO> patients = reportingInputController.patientList( );
        List<ReportingNoteDTO> notes = reportingInputController.getPatientNotes( );

        if( patients != null && notes != null )
        {
            for( ReportingPatientDTO patient : patients )
            {
                List<ReportingNoteDTO> patientNotes = getPatientNotes( patient, notes );
                String status = analyzePatientStatus( patient, patientNotes );
                patientStatusMap.put( String.valueOf( patient.getIdPatient( ) ), status );
            }
        }

        return patientStatusMap;
    }

    public String analyzePatientStatus( ReportingPatientDTO patient, List<ReportingNoteDTO> notes )
    {
        String status = ReportingContant.STATUS_NONE;

        if( isBorderline( patient, notes ) )
        {
            status = ReportingContant.STATUS_BORDERLINE;
        }

        if( isInDanger( patient, notes ) )
        {
            status = ReportingContant.STATUS_IN_DANGER;
        }

        if( isEarlyOnset( patient, notes ) )
        {
            status = ReportingContant.STATUS_EARLY_ONSET;
        }

        return status;
    }

    // Le dossier du patient contient entre deux et cinq déclencheurs et le patient est âgé de plus de 30 ans
    private boolean isBorderline( ReportingPatientDTO patient, List<ReportingNoteDTO> notes )
    {
        return numberOfSymptoms( notes ) >= 2 && numberOfSymptoms( notes ) <= 5 && isAbove30YearsOld( patient );
    }

    // Si le patient est un homme de moins de 30 ans, alors trois termes déclencheurs doivent être présents.
    // Si le patient est une femme et a moins de 30 ans, il faudra quatre termes déclencheurs.
    // Si le patient a plus de 30 ans, alors il en faudra six ou sept.
    private boolean isInDanger( ReportingPatientDTO patient, List<ReportingNoteDTO> notes )
    {
        int symptomCount = numberOfSymptoms( notes );

        if ( isAbove30YearsOld( patient ) )
        {
            return symptomCount == 6 || symptomCount == 7;
        }

        boolean isMale = Objects.equals( patient.getGenre( ), "M" );
        return isMale ? symptomCount >= 3 : symptomCount >= 4;
    }

    // Si le patient est un homme de moins de 30 ans, alors au moins cinq termes déclencheurs sont nécessaires.
    // Si le patient est une femme et a moins de 30 ans, il faudra au moins sept termes déclencheurs.
    // Si le patient a plus de 30 ans, alors il en faudra huit ou plus.
    private boolean isEarlyOnset( ReportingPatientDTO patient, List<ReportingNoteDTO> notes )
    {
        int symptomCount = numberOfSymptoms( notes );

        if ( isAbove30YearsOld( patient ) )
        {
            return symptomCount >= 8;
        }

        boolean isMale = Objects.equals( patient.getGenre( ), "M" );
        return isMale ? symptomCount >= 5 : symptomCount >= 7;
    }

    private boolean isAbove30YearsOld( ReportingPatientDTO patient )
    {
        int patientYearOfBirth = Integer.parseInt( patient.getDateNaissance( ).substring( 0, 4 ) );
        int thresholdYear = LocalDate.now( ).minusYears( 30 ).getYear( );

        return patientYearOfBirth <= thresholdYear;
    }

    private int numberOfSymptoms( List<ReportingNoteDTO> notes )
    {
        List<String> symptoms = getListSymptoms( );

        return (int) notes.stream( )
                .flatMap( note -> symptoms.stream( )
                        .filter( symptom -> note.getNote( ).toLowerCase( ).contains( symptom.toLowerCase( ) ) )
                        .distinct( ) )
                .count( );
    }

    private List<ReportingNoteDTO> getPatientNotes( ReportingPatientDTO patient, List<ReportingNoteDTO> notes )
    {
        List<ReportingNoteDTO> patientNotes = new ArrayList<>( );

        for( ReportingNoteDTO note : notes )
        {
            if( Objects.equals( note.getPatient( ), patient.getNom( ) ) )
            {
                patientNotes.add( note );
            }
        }
        return patientNotes;
    }

    private List<String> getListSymptoms( )
    {
        ReportingSymptoms reportingSymptoms = new ReportingSymptoms( );
        return reportingSymptoms.getSymptoms( );
    }
}
