package fr.openclassrooms.medilabo.site.webclient;

import fr.openclassrooms.medilabo.site.domain.PatientDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PatientWebClient
{
    private final WebClient webClient;

    public PatientWebClient( WebClient.Builder webClientBuilder )
    {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8080/")
                .defaultHeader( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE )
                .build( );
    }

    public Flux<PatientDTO> getPatientList( )
    {
        return webClient.get( )
                .uri( "patient/list" )
                .retrieve( )
                .bodyToFlux( PatientDTO.class );
    }

    public Mono<PatientDTO> getPatientInfo( int patientId )
    {
        return webClient.get( )
                        .uri( "patient/{patientId}", patientId )
                        .retrieve( )
                        .bodyToMono( PatientDTO.class );
    }

    public PatientDTO addNewPatient( PatientDTO newPatient )
    {
        return webClient.post( )
                .uri( "patient/addNewPatient" )
                .body( BodyInserters.fromValue( newPatient ) )
                .retrieve( )
                .bodyToMono( PatientDTO.class )
                .block( );
    }

    public PatientDTO updatePatientInfo( int patientId, PatientDTO updatedPatient )
    {
        return webClient.post( )
                        .uri( "patient/updatePatient/{id}", patientId )
                        .body( BodyInserters.fromValue( updatedPatient ) )
                        .retrieve( )
                        .bodyToMono( PatientDTO.class )
                        .block( );
    }

    public void deletePatient( int patientId )
    {
        webClient.delete( )
                .uri( "patient/deletePatient/{id}", patientId )
                .retrieve( )
                .toBodilessEntity( )
                .block( );
    }
}
