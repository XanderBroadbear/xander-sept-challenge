package com.xander.rest.model;
import java.util.ArrayList; // import the ArrayList class

public class ItemList {
    private ArrayList<Item> itemList;
    private Item item;

    public ItemList(){
         this.itemList= new ArrayList<Item>();
    }

    public void addItem(Item item){
        this.itemList.add(item);
    }

    public void deleteItem(Item item){
        this.itemList.remove(item);
    }

    public Item getItem(int index){
        return this.itemList.get(index);
    }

    public ArrayList getList(){
        return this.itemList;
    }
}
