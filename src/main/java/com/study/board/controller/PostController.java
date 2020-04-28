package com.study.board.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import com.study.board.model.CreateResult;
import com.study.board.model.Post;
import com.study.board.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostController {
    PostService postService;

    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }

    @JsonView(View.Public.class)
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = "application/json")
    @NonNull CreateResult postPost(@RequestBody @Valid Post post) {
        final long executionBeginAt = System.currentTimeMillis();

        String id = postService.createPost(post)
                .map(ObjectId::toHexString)
                .orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new CreateResult(id != null, id, executionEndAt - executionBeginAt);
    }
}
