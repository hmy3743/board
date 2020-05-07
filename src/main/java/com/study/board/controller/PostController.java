package com.study.board.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import com.study.board.model.Post;
import com.study.board.model.Response;
import com.study.board.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    PostService postService;

    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @NonNull
    Response<String> postPost(@RequestBody @Valid Post post) {
        final long executionBeginAt = System.currentTimeMillis();

        String id = postService.createPost(post)
                .map(ObjectId::toHexString)
                .orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(id != null, id, executionEndAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @NonNull
    Response<Post> getPost(@PathVariable(value = "id") String id) {
        final long executionBeginAt = System.currentTimeMillis();

        Post post = postService.getPost(new ObjectId(id)).orElse(null);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(post != null, post, executionBeginAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", params = { "page", "per_page" })
    @NonNull
    Response<List<Post>> getPosts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "per_page") int count) {
        final long executionBeginAt = System.currentTimeMillis();

        List<Post> posts;
        posts = postService.getPosts(page, count);

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(true, posts, executionBeginAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", params = { "page", "per_page", "owner" })
    @NonNull
    Response<List<Post>> getPostsByOwner(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "per_page") int count,
            @RequestParam(value = "owner") String ownerId) {
        final long executionBeginAt = System.currentTimeMillis();

        List<Post> posts;
        posts = postService.getPostsByOwner(page, count, new ObjectId(ownerId));

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(true, posts, executionBeginAt - executionBeginAt);
    }

    @JsonView(View.Public.class)
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @NonNull
    Response<Post> setPost(@RequestParam(value = "id") String id, @RequestBody Post post) {
        final long executionBeginAt = System.currentTimeMillis();

        Optional<Post> result = Optional.empty();
        if(post.getTitle() != null && post.getContent() != null)
            result = postService.setPost(new ObjectId(id), post);
        else if(post.getTitle() != null)
            result = postService.setPostTitle(new ObjectId(id), post.getTitle());
        else if(post.getContent() != null)
            result = postService.setPostContent(new ObjectId(id), post.getContent());

        final long executionEndAt = System.currentTimeMillis();
        return new Response<>(result.isPresent(), result.orElse(null), executionBeginAt - executionBeginAt);
    }
}
