package com.xander.rest.controller;
import java.net.URI;
import java.util.ArrayList; // import the ArrayList class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xander.rest.model.ItemList;
import com.xander.rest.dao.Itemdao;
import com.xander.rest.model.Item;
import com.xander.rest.exception.*;


@Configuration
@ConfigurationProperties(prefix = "com.xander.rest")
@RestController
public class ItemController {
    @GetMapping("/")
    public String index(){
        return "home page";
    }
    @Autowired
    private Itemdao itemDao;
//
    @Autowired
    private ErrorAttributes errorAttributes;
//
//    @Bean
//    public DefaultErrorAttributes appErrorController(){return new DefaultErrorAttributes();}



    @GetMapping(path="ItemList")
    public ArrayList getList(){
        return itemDao.getAllItems();
    }
    @PostMapping(path= "/", consumes = "ItemList/json", produces = "ItemList/json")
    public ResponseEntity<Item> addItem(
//            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
//            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "FRUIT") String headerLocation,
            @RequestBody Item item)
            throws Exception
    {
        //Generate resource id
        Integer num = itemDao.getAllItems().size() + 1;
        String id = num.toString();
        item.setId(id);

        //add resource
        itemDao.addItem(item);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }

}
