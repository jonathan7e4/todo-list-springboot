package com.example.todolist.model.entities;

import com.example.todolist.model.services.ItemListService;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // strategia declarada por errores en postgres
    private long idItem;
    private String descripcio;
    private boolean fet = false;
    @GeneratedValue
    private long prioritat;
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemList list;
}
