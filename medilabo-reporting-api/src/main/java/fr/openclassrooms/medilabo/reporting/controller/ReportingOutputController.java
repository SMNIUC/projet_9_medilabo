package fr.openclassrooms.medilabo.reporting.controller;

import fr.openclassrooms.medilabo.reporting.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReportingOutputController
{
    private final ReportingService reportingService;

    @GetMapping("/reporting/status")
    public Map<String, String> reportPatientStatus( )
    {
        return reportingService.reportPatientStatus( );
    }
}
