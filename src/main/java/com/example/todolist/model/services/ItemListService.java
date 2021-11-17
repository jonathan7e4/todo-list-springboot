package com.example.todolist.model.services;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.repositories.ItemListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemListService {
    private final ItemListRepository itemListRepository;

    // afegir una llista
    public ItemList afegirLlista(ItemList list){
        return itemListRepository.save(list);
    }
    // llistar totes les llistes
    public List<ItemList> llistarLlistes(){
        return itemListRepository.findAll();
    }
}
