package com.i.lostmytrousers.model.repositories;

import com.i.lostmytrousers.model.entities.Trouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrouserRepository extends JpaRepository<Trouser, Long>
{

}