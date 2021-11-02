package com.example.todolist.model.services;

import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.repositories.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    //llistar tots els ítems
    public List<TodoItem> llistarItems(){
        return todoItemRepository.findAll();
    }

    //consultar ítem per id
    public TodoItem consultarItem(Long id){
        return todoItemRepository.findById(id).orElse(null);
    }

    //afegir ítem
    public TodoItem afegirItem(TodoItem it){
        return todoItemRepository.save(it);
    }

    //modificar sencer, si existeix el canvia, sino retorna null
    public TodoItem modificarItem(TodoItem it){
        TodoItem aux = null;
        if(todoItemRepository.existsById(it.getIdItem())) {
            aux = todoItemRepository.save(it);
        }
        return aux;
    }

    //eliminar ítem per id
    //si no existeix id retorna null
    public TodoItem eliminarItem(Long id){
        TodoItem res=todoItemRepository.findById(id).orElse(null);
        if(res!=null) todoItemRepository.deleteById(id);
        return res;
    }
}
