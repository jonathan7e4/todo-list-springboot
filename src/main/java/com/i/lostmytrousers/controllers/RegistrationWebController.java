package com.i.lostmytrousers.controllers;

import com.i.lostmytrousers.model.entities.User;
import com.i.lostmytrousers.model.entities.UserDTO;
import com.i.lostmytrousers.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegistrationWebController
{
    private final UserService userService;

    @PostMapping( "/users" )
    public ResponseEntity<?> createUser( @RequestBody User newUser )
    {
        try
        {
            User response = userService.createNewUser( newUser );

            UserDTO usu = new UserDTO( response.getUsername(), response.getRole() );

            return new ResponseEntity<>( usu, HttpStatus.CREATED );

        } catch ( DataIntegrityViolationException exception )
        {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, exception.getMessage() );
        }
    }

    @GetMapping( "/users" )
    public ResponseEntity<?> readUsers()
    {
        List<User> response = userService.findUsers();
        List<UserDTO> auxiliaryResponse = new ArrayList();

        for ( User user : response ) auxiliaryResponse.add( new UserDTO( user.getUsername(), user.getRole() ) );

        if ( response.isEmpty() ) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok( auxiliaryResponse );
    }

    @GetMapping( "/me" )
    public UserDTO currentUser( @AuthenticationPrincipal User user )
    {
        return new UserDTO( user.getUsername(), user.getRole() );
    }
}