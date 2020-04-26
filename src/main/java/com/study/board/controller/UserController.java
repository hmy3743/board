package com.study.board.controller;

import com.study.board.exception.UserNotFoundException;
import com.study.board.model.User;
import com.study.board.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody User getUser(@PathVariable("id") String id) {
        return userRepository.findById(new ObjectId(id)).orElseThrow(UserNotFoundException::new);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public void postUser(@RequestBody User user) {
        userRepository.insert(user);
    }
}
