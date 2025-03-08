package fr.openclassrooms.medilabo.doc.controller;

import fr.openclassrooms.medilabo.doc.domain.NotePatient;
import fr.openclassrooms.medilabo.doc.service.DocApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class DocApiController
{
    private final DocApiService docApiService;

    @GetMapping( "/doc/notes" )
    public List<NotePatient> getPatientNoteList( )
    {
        return docApiService.getAllNotesPatient( );
    }

    @GetMapping("/doc/note-patient/{patientName}")
    public List<NotePatient> getNotesPatientById( @PathVariable("patientName") String patientName )
    {
        return docApiService.findNotesPatientByPatientName( patientName );
    }

    @PostMapping("/doc/write-note")
    public ResponseEntity<?> addNewPatientNote(@Valid @RequestBody NotePatient newNote )
    {
        try
        {
            NotePatient addedNote = docApiService.addNewPatientNote( newNote );

            if ( addedNote == null )
            {
                return ResponseEntity.status( HttpStatus.NO_CONTENT )
                        .body( Map.of( "error", "Unable to add patient note. Please try again." ) );
            }

            // Build the location URI for the created resource
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest( )
                    .path( "/{id}" )
                    .buildAndExpand( addedNote.get_id( ) )
                    .toUri( );

            // Return a 201 Created response
            return ResponseEntity.created( location ).build( );
        }
        catch ( Exception e )
        {
            // Handle unexpected exceptions
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( Map.of( "error", "An unexpected error occurred: " + e.getMessage( ) ) );

        }
    }
}
