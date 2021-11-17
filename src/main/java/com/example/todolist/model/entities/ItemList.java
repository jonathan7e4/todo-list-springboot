package com.example.todolist.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ItemList {
    @Id
    @GeneratedValue
    private int listId;
    private String name;
    @OneToMany(mappedBy = "list") // mapea toda item que contenga la variable list
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Permita escribir la variable pero no leerla en el request.evita crear un bucle infinito en el request.
    private List<TodoItem> items;
}
