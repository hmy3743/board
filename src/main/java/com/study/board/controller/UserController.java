package com.study.board.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import com.study.board.exception.UserNotFoundException;
import com.study.board.model.Response;
import com.study.board.model.User;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    UserService userService;

    @Autowired
    UserController(
            UserService userService
    ){
        this.userService = userService;
    }

    @JsonView(View.Public.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Response<User> getUser(@PathVariable("id") String id) {
        final long executionBeginAt = System.currentTimeMillis();

        User user = userService.getUser(new ObjectId(id)).orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(user != null, user, executionEndAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Response<String> postUser(@RequestBody User user) {
        final long executionBeginAt = System.currentTimeMillis();

        String id = userService.createUser(user).orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(id != null, id, executionEndAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public Response<User> setUser(@PathVariable("id") String id, @RequestBody User user) {
        final long executionBeginAt = System.currentTimeMillis();

        User modifiedUser = userService.setUser(new ObjectId(id), user).orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(modifiedUser != null, modifiedUser, executionEndAt - executionBeginAt);
    }
}
