package com.study.board.service;

import com.study.board.model.Post;
import com.study.board.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@Component
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    MongoTemplate mongoTemplate;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, MongoTemplate mongoTemplate){
        this.postRepository = postRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<ObjectId> createPost(Post post) {
        Post created = null;
        try {
            created = postRepository.insert(post);
        } catch (DuplicateKeyException err) {
            /* logging */
        }
        return Optional.ofNullable(created).map(Post::getId).map(ObjectId::new);
    }

    @Override
    public List<Post> getPosts(int page, @Max(100) int count) {
        return postRepository
                .findAll(PageRequest.of(page, count, Sort.by("createdAt").descending()))
                .getContent();
    }

    @Override
    public List<Post> getPostsByOwner(int page, @Max(100) int count, ObjectId ownerId) {
        return postRepository
                .findByOwner(ownerId, PageRequest.of(page, count, Sort.by("createdAt").descending()))
                .getContent();
    }

    @Override
    public Optional<Post> getPost(ObjectId postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Optional<Post> setPost(ObjectId postId, Post post) {
        return Optional.ofNullable(
                mongoTemplate.findAndModify(
                        Query.query(Criteria.where("_id").is(postId)),
                        Update.update("title", post.getTitle()).addToSet("content", post.getContent()),
                        Post.class
                )
        );
    }

    @Override
    public Optional<Post> setPostTitle(ObjectId postId, String title) {
        return Optional.ofNullable(
                mongoTemplate.findAndModify(
                        Query.query(Criteria.where("_id").is(postId)),
                        Update.update("title", title),
                        Post.class
                )
        );
    }

    @Override
    public Optional<Post> setPostContent(ObjectId postId, String content) {
        return Optional.ofNullable(
                mongoTemplate.findAndModify(
                        Query.query(Criteria.where("_id").is(postId)),
                        Update.update("content", content),
                        Post.class
                )
        );
    }
}
