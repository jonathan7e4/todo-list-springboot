package com.i.lostmytrousers.model.services;

import com.i.lostmytrousers.model.entities.User;
import com.i.lostmytrousers.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User createNewUser( User newUser )
    {
        newUser.setPassword( passwordEncoder.encode( newUser.getPassword() ) );
        userRepository.save( newUser );
        return newUser;
    }


    public User findByUsername( String username )
    {
        return userRepository.findByUsername( username ).orElse( null );
    }


    public List<User> findUsers()
    {
        return userRepository.findAll();
    }
}