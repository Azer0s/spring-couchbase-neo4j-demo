package at.simulevski.couchbaseneo4jdemo.presentation;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.Item;
import at.simulevski.couchbaseneo4jdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/item")
    public HttpEntity createItem(@RequestParam("name") String name, @RequestParam("description") String description){
        return itemService.createItem(name, description) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/item/{name}")
    public @ResponseBody Item getItem(@PathVariable("name") String name){
        return itemService.getItem(name);
    }

    @DeleteMapping("/item/{name}")
    public HttpEntity deleteItem(@PathVariable("name") String name){
        return itemService.deleteItem(name) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}