package com.i.lostmytrousers.model.services;

import com.i.lostmytrousers.model.entities.Trouser;
import com.i.lostmytrousers.model.repositories.TrouserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrouserService
{
    private final TrouserRepository trouserRepository;


    public Trouser addTrouser( Trouser trouser )
    {
        return trouserRepository.save( trouser );
    }


    public Trouser updateTrouser( Trouser trouser )
    {
        Trouser response = null;
        if ( trouserRepository.existsById( trouser.getId() ) ) response = trouserRepository.save( trouser );
        return response;
    }


    public Trouser findById( Long id )
    {
        return trouserRepository.findById( id ).orElse( null );
    }


    public List<Trouser> findTrousers()
    {
        return trouserRepository.findAll();
    }


    public Trouser deleteTrouser( Long id )
    {
        Trouser trouser = trouserRepository.findById( id ).orElse( null );
        if ( trouser != null ) trouserRepository.deleteById( id );
        return trouser;
    }
}