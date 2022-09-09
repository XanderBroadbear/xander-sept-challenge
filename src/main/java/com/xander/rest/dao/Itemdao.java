package com.xander.rest.dao;
import java.util.ArrayList; // import the ArrayList class

import com.xander.rest.model.Item;
import com.xander.rest.model.ItemList;
import org.springframework.stereotype.Repository;

@Repository
public class Itemdao {

    private static ItemList items = new ItemList();
    static
    {
        items.getList().add(new Item("0", "Cheese", "Dairy", 5));
        items.getList().add(new Item("1", "Watermelon", "Fruit", 6));
        items.getList().add(new Item("2", "Chicken", "Meat", 10));
        items.getList().add(new Item("3", "Apple", "Fruit", 1.5));

    }

    public ArrayList getAllItems(){
        return items.getList();
    }

    public void addItem(Item item){
        items.addItem(item);
    }

    public void deleteItem(Item item){
        items.deleteItem(item);
    }
    public Item getItem(int index){
        return items.getItem(index);
    }
}
