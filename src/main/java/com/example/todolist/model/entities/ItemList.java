package com.example.todolist.model.entities;

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
    @OneToMany
    @JoinColumn
    private List<TodoItem> itemList;
}
