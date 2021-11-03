package com.example.todolist.controllers;

import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.services.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController {
    private final TodoItemService todoItemService;

    //TODO
    //Amb l'exemple de l'altre controlador cal canviar el retorn d'aquests endpoints
    //pel seu corresponent retornant un ResponseEntity


    @GetMapping("/todoitems")
    public ResponseEntity<?> llistarItems(){
        List<TodoItem> itemList = todoItemService.llistarItems();
        if (itemList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(itemList);
        }
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity<?> consultarItem(@PathVariable Long id)
    {
        TodoItem task = todoItemService.consultarItem(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }
    }

    @PostMapping("/todoitems")
    public ResponseEntity<?> crearItem(@RequestBody TodoItem nou){
        TodoItem task =  todoItemService.afegirItem(nou);
        return new ResponseEntity<TodoItem>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity<?> eliminarItem(@PathVariable Long id){
        TodoItem task = todoItemService.consultarItem(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            todoItemService.eliminarItem(id);
            return ResponseEntity.noContent().build();
        }
    }

    //per modificar un usuari existent
    @PutMapping("/todoitems")
    public ResponseEntity<?> modificarItem(@RequestBody TodoItem mod){
        if (mod == null) {
            return ResponseEntity.notFound().build();
        } else {
            todoItemService.modificarItem(mod);
            return ResponseEntity.ok(mod);
        }
    }

}
