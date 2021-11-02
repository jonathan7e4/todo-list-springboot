package com.example.todolist.model.repositories;

import com.example.todolist.model.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, String> {
}
