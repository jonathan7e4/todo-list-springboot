package com.example.todolist.controllers;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.services.ItemListService;
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
    private final ItemListService itemListService;

    //TODO
    //Amb l'exemple de l'altre controlador cal canviar el retorn d'aquests endpoints
    //pel seu corresponent retornant un ResponseEntity

    //LIST-ENDPOINTS
    @PostMapping("/todolists")
    public ResponseEntity<?> crearLlista(@RequestBody ItemList list){
        ItemList _list = itemListService.afegirLlista(list);
        return new ResponseEntity<ItemList>(_list,HttpStatus.CREATED);
    }
    @DeleteMapping("/todolists/{id}")
    public ResponseEntity<?> eliminarLlista(@PathVariable Integer id){
        ItemList task = itemListService.consultarLlista(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            itemListService.eliminarItem(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/todolists")
    public ResponseEntity<?> modificarLlista(@RequestBody ItemList list){
        if(list == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(itemListService.modificarLlista(list));
        }
    }
    @GetMapping("/todolists")
    public ResponseEntity<?> llistarLlistes(){
        List<ItemList> list = itemListService.llistarLlistes();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/todolists/{id}")
    public ResponseEntity<?> consultarLlista(@PathVariable Integer id)
    {
        ItemList list = itemListService.consultarLlista(id);
        if (list == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/todolists/{id}/todoitems")
    public ResponseEntity<?> consultarItemsLlista(@PathVariable Integer id){
        List<TodoItem> list = itemListService.consultarItemsLlista(id);
        if (list == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }


    @GetMapping("/todolists/{listId}/todoitems/{taskId}")
    public ResponseEntity<?> getListItem( @PathVariable Integer listId, @PathVariable Long taskId )
    {
        List<TodoItem> todoItems = itemListService.consultarItemsLlista(listId);
        TodoItem task = null;

        for ( TodoItem todoItem : todoItems ) if ( todoItem.getIdItem() == taskId ) task = todoItem;

        if ( task == null ) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok( task );
    }


    //ITEMS-ENDPOINTS
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
