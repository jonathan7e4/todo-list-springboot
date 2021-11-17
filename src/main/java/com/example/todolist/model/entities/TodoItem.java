package com.example.todolist.model.entities;

import com.example.todolist.model.services.ItemListService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ManyToOne
    @JoinColumn(name = "list_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Permita escribir la variable pero no leerla en el request.evita crear un bucle infinito en el request.
    private ItemList list;
}
