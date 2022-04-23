package com.i.lostmytrousers.controllers;

import com.i.lostmytrousers.model.entities.Trouser;
import com.i.lostmytrousers.model.entities.User;
import com.i.lostmytrousers.model.services.TrouserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController
{
    private final TrouserService trouserService;

    @PostMapping( "trousers" )
    public ResponseEntity<?> createTrouser( @AuthenticationPrincipal User user, @RequestBody Trouser trouser )
    {
        try
        {
            trouser.setUser( user );
            trouserService.addTrouser( trouser );
            return new ResponseEntity<>( user, HttpStatus.CREATED );
        } catch ( DataIntegrityViolationException exception )
        {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, exception.getMessage() );
        }
    }

    @GetMapping( "/trousers/{id}" )
    public ResponseEntity<?> readTrouser( @PathVariable long id )
    {
        Trouser trouser = trouserService.findById( id );
        if ( trouser != null ) return ResponseEntity.ok( trouser );
        else return ResponseEntity.notFound().build();
    }

    @GetMapping( "/trousers" )
    public ResponseEntity<?> readTrousers()
    {
        List<Trouser> trousers = trouserService.findTrousers();
        if ( trousers != null ) return ResponseEntity.ok( trousers );
        else return ResponseEntity.notFound().build();
    }

    @PutMapping( "/trousers" )
    public ResponseEntity<?> updateTrouser( @RequestBody Trouser newTrouser )
    {
        Trouser trouser = trouserService.updateTrouser( newTrouser );
        if ( trouser != null ) return ResponseEntity.ok( trouser );
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/trousers/{id}" )
    public ResponseEntity<?> deleteTrouser( @PathVariable long id )
    {
        Trouser trouser = trouserService.deleteTrouser( id );
        if ( trouser!=null ) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}