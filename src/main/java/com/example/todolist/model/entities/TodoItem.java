package com.example.todolist.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private long idItem;
    private String descripcio;
    private boolean fet = false;
    @GeneratedValue
    private long prioritat;
}
