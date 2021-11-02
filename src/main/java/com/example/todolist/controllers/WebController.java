package com.example.todolist.controllers;

import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.services.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController {
    private final TodoItemService todoItemService;

    //TODO
    //Amb l'exemple de l'altre controlador cal canviar el retorn d'aquests endpoints
    //pel seu corresponent retornant un ResponseEntity


    @GetMapping("/tasques")
    public List<TodoItem> llistarUsuaris(){
        return todoItemService.llistarItems();
    }

    @GetMapping("/tasques/{id}")
    public TodoItem consultarUsuari(@PathVariable String id)
    {
        return todoItemService.consultarItem(id);
    }

    @PostMapping("/tasques")
    public TodoItem crearUsuari(@RequestBody TodoItem nou){
        return todoItemService.afegirItem(nou);
    }

    @DeleteMapping("/tasques/{id}")
    public TodoItem eliminarUsuari(@PathVariable String id){
        return todoItemService.eliminarItem(id);
    }

    //per modificar un usuari existent
    @PutMapping("/tasques")
    public TodoItem modificarUsuari(@RequestBody TodoItem mod){
        return todoItemService.modificarItem(mod);
    }
}
