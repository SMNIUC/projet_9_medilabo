package fr.openclassrooms.medilabo.site.service;

import fr.openclassrooms.medilabo.site.controller.ReportingSiteController;
import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import fr.openclassrooms.medilabo.site.domain.ReportingPatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportingPatientDTOService
{
    private final ReportingSiteController reportingSiteController;

    public List<ReportingPatientDTO> getReportingPatientDTOList( List<PatientDTO> patientDTOList )
    {
        List<ReportingPatientDTO> reportingPatientDTOList = new ArrayList<>( );

        for( PatientDTO patientDTO : patientDTOList )
        {
            reportingPatientDTOList.add( getReportingPatientDTO( patientDTO) );
        }

        return reportingPatientDTOList;
    }

    public ReportingPatientDTO getReportingPatientDTO( PatientDTO patientDTO )
    {
        ReportingPatientDTO reportingPatientDTO = new ReportingPatientDTO( );

        reportingPatientDTO.setIdPatient( patientDTO.getIdPatient( ) );
        reportingPatientDTO.setPrenom( patientDTO.getPrenom( ) );
        reportingPatientDTO.setNom( patientDTO.getNom( ) );
        reportingPatientDTO.setDateNaissance( patientDTO.getDateNaissance( ) );
        reportingPatientDTO.setGenre( patientDTO.getGenre( ) );
        reportingPatientDTO.setAdresse( patientDTO.getAdresse( ) );
        reportingPatientDTO.setTelephone( patientDTO.getTelephone( ) );
        reportingPatientDTO.setStatut( getPatientStatut( String.valueOf( patientDTO.getIdPatient( ) ) ) );

        return reportingPatientDTO;
    }

    private String getPatientStatut( String patientId )
    {
        Map<String, String> patientStatusMap = reportingSiteController.reportingStatus( );

        return patientStatusMap.get( patientId );
    }
}
