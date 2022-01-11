package com.example.todolist.controllers;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.services.ItemListService;
import com.example.todolist.model.services.TodoItemService;
//import com.sun.tools.javac.comp.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class WebController {
    private final TodoItemService todoItemService;
    private final ItemListService itemListService;

    /*LIST-ENDPOINTS*/

    /**
     * Retorna todas las listas de items de la base de datos
     * @return  List of ItemList
     */
    @GetMapping("/todolists")
    public ResponseEntity<?> llistarLlistes(){
        List<ItemList> list = itemListService.llistarLlistes();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }

    /**
     * Devuelve una lista de la base de datos por medio de un identificador
     * @param id identificador de la lista
     * @return ItemList
     */
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

    /**
     * Añade una lista a la base de datos
     * @param list variable ItemList
     * @return un ResponseEntity que confirme el corrcto funcionamiento de la llamada
     */
    @PostMapping("/todolists")
    public ResponseEntity<?> crearLlista(@RequestBody ItemList list){
        ItemList _list = itemListService.afegirLlista(list);
        return new ResponseEntity<>(_list,HttpStatus.CREATED);
    }

    /**
     * Modifica una lista de la base de datos
     * @param list variable tipo ItemList con el contenido a reemplazar
     * @return ResponseEntity
     */
    @PutMapping("/todolists")
    public ResponseEntity<?> modificarLlista(@RequestBody ItemList list){
        if(list == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(itemListService.modificarLlista(list));
        }
    }

    /**
     * Elimina una lista de la base de datos
     * @param id identificador de la lista a eliminar
     * @return ResponseEntity
     */
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

    // ITEMS BY LIST

    /**
     * Añade un item a una lista determinada por medio de su identificador
     * @param todoItem variable TodoItem a añadir
     * @param id identificador de la lista
     * @return un ResponseEntity que confirme el corrcto funcionamiento de la llamada
     */
    @PostMapping("todolists/{id}/todoitems")
    public ResponseEntity<?> createListItem( @RequestBody TodoItem todoItem, @PathVariable int id ){
        TodoItem newItem = itemListService.createTask( todoItem, id );
        return new ResponseEntity<>( newItem, HttpStatus.CREATED );
    }

    /**
     * Modifica un item de una lista determinada por un identificador
     * @param todoItem variable TodoItem a modificar
     * @param listId identificador de la lista
     * @param taskId identificador del item
     * @return un ResponseEntity que confirme el correcto funcionamiento de la llamada
     */
    @PutMapping("/todolists/{listId}/todoitems/{taskId}")
    public ResponseEntity<?> updateListItem( @RequestBody TodoItem todoItem, @PathVariable Integer listId, @PathVariable int taskId ){
        if ( todoItem == null ) return ResponseEntity.notFound().build();
        else
        {
            ItemList itemList = itemListService.consultarLlista( listId );

            for ( int i = 0 ; i < itemList.getItems().size() ; i ++ ) if ( itemList.getItems().get( i ).getIdItem() == taskId ) itemList.getItems().remove( i );

            todoItem.setIdItem( taskId );
            todoItem.setList( itemList );

            todoItemService.modificarItem( todoItem );
            return ResponseEntity.ok( todoItem );
        }
    }

    /**
     * Elimina un item de una lista
     * @param listId identificador de lista
     * @param taskId identificador de item
     * @return ResponseEntity
     */
    @DeleteMapping("/todolists/{listId}/todoitems/{taskId}")
    public ResponseEntity<?> removeListItem( @PathVariable Integer listId, @PathVariable Long taskId ) {
        ItemList itemList = itemListService.consultarLlista( listId ); // get list by id
        //TodoItem todoItem = list.getItems().get(taskId-1); // WARNING: LA ID  NO ES DEL ITEM, ES DE  LA POSICION DEL ITEM EN LA LISTA
        TodoItem todoItem = itemList.getItems().stream().filter( item -> item.getIdItem() == taskId ).findFirst().orElse( null );
        if ( todoItem == null ) return ResponseEntity.notFound().build();
        else {
            todoItemService.eliminarItem( taskId ); // erase item by id
            return ResponseEntity.noContent().build();
        }
    }


    /**
     * Devuelve los items de una lista
     * @param id
     * @return
     */
    @GetMapping("/todolists/{id}/todoitems")
    public ResponseEntity<?> consultarItemsLlista(@PathVariable Integer id){
        List<TodoItem> list = itemListService.consultarItemsLlista(id);
        if (list == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * Devuelve un item de una lista
     * @param listId
     * @param taskId
     * @return
     */
    @GetMapping("/todolists/{listId}/todoitems/{taskId}")
    public ResponseEntity<?> getListItem( @PathVariable Integer listId, @PathVariable Long taskId )
    {
        List<TodoItem> todoItems = itemListService.consultarItemsLlista(listId);
        TodoItem task = null;

        for ( TodoItem todoItem : todoItems ) if ( todoItem.getIdItem() == taskId ) task = todoItem;
        if ( task == null ) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok( task );
    }


    /*ITEMS-ENDPOINTS*/

    /**
     * Devuelve todos los items de la base de datos
     * @return
     */
    @GetMapping("/todoitems")
    public ResponseEntity<?> llistarItems(){
        List<TodoItem> itemList = todoItemService.llistarItems();
        if (itemList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(itemList);
        }
    }

    /**
     * Devuelve un item de la base de datos
     * @param id
     * @return
     */
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

    /**
     * Añade un item a la base de datos
     * @param nou
     * @return
     */
    @PostMapping("/todoitems")
    public ResponseEntity<?> crearItem(@RequestBody TodoItem nou){
        TodoItem task = todoItemService.afegirItem(nou);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * Elimina un item de la base de datos
     * @param id
     * @return
     */
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

    /**
     * Modifica un item de la base de datos
     * @param mod
     * @return
     */
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