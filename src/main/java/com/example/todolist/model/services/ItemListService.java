package com.example.todolist.model.services;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.repositories.ItemListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemListService {
    private final ItemListRepository todoItemRepository;

    // afegir una llista
    public ItemList afegirLlista(ItemList list){
        return todoItemRepository.save(list);
    }
}
