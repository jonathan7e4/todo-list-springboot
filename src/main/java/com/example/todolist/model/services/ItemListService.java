package com.example.todolist.model.services;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.entities.TodoItem;
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

    // modificar llista
    public ItemList modificarLlista(ItemList list){
        ItemList _list = null;
        if(itemListRepository.existsById(list.getListId())){
            _list = itemListRepository.save(list);
        }
        return _list;
    }

    // Eliminar llista
    public ItemList eliminarItem(Integer id){
        ItemList res=itemListRepository.findById(id).orElse(null);
        if(res!=null) itemListRepository.deleteById(id);
        return res;
    }

    public ItemList consultarLlista(Integer id){
        return itemListRepository.findById(id).orElse(null);
    }
}
