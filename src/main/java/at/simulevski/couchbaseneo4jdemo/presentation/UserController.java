package at.simulevski.couchbaseneo4jdemo.presentation;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.User;
import at.simulevski.couchbaseneo4jdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public HttpEntity createUser(@RequestParam("name") String username){
        return userService.createUser(username) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{name}")
    public @ResponseBody User getUser(@PathVariable("name") String name){
        return userService.getUser(name);
    }

    @DeleteMapping("/user/{name}")
    public HttpEntity deleteUser(@PathVariable("name") String name){
        return userService.deleteUser(name) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
