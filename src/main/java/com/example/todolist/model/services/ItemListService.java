package com.example.todolist.model.services;

import com.example.todolist.model.entities.ItemList;
import com.example.todolist.model.entities.TodoItem;
import com.example.todolist.model.repositories.ItemListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
    /**
     * Creates a new task in a list from a given parameter id.
     * @param todoItem The new task to be created.
     * @param listId The id of the list to add the new task to.
     */
    public void createTask( TodoItem todoItem, Integer listId )
    {
        ItemList itemList = itemListRepository.findById( listId ).orElse( null );
        todoItem.setList( itemList );
        if ( itemList != null )
        {
            itemList.getItems().add( todoItem );
            itemListRepository.save( itemList );
        }
    }


    // consultar llista
    public ItemList consultarLlista(Integer id){
        return itemListRepository.findById(id).orElse(null);
    }

    // consultar items llista
    public List<TodoItem> consultarItemsLlista(Integer id){
        List<TodoItem> itemList = new ArrayList<>();
        ItemList il = itemListRepository.findById(id).orElse(null);
        assert il != null;
        List<TodoItem> aux = il.getItems();
        // filtrar por listID
        aux.stream().filter(x -> x.getList().getListId() == id).forEach(itemList::add);
        return itemList;
    }
}
