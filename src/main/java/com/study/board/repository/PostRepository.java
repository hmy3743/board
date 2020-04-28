package com.study.board.repository;

import com.study.board.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, ObjectId> {
    @Query("{ 'ownerId': ?0 }")
    Page<Post> findByOwner(ObjectId ownerId, Pageable pageable);
}
