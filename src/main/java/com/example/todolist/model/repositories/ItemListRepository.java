package com.example.todolist.model.repositories;

import com.example.todolist.model.entities.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemListRepository extends JpaRepository<ItemList, Integer> {
}
