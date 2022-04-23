package com.i.lostmytrousers.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
public class Trouser
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn( name = "userId" )
    private User user;
}