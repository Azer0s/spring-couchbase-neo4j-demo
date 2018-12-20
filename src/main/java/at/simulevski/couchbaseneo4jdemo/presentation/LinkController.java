package at.simulevski.couchbaseneo4jdemo.presentation;

import at.simulevski.couchbaseneo4jdemo.service.LinkService;
import at.simulevski.couchbaseneo4jdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/link/{user}/{item}")
    public HttpEntity createLink(@PathVariable("user") String user, @PathVariable("item") String item){
        return linkService.createItemLink(user,item) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/link/{user}/{item}")
    public HttpEntity deleteLink(@PathVariable("user") String user, @PathVariable("item") String item){
        return linkService.deleteItemLink(user,item) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
