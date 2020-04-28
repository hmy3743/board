package com.study.board.service;

import com.study.board.model.Post;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

public interface PostService {
    @NonNull Optional<ObjectId> createPost(@NonNull Post post);

    @NonNull List<Post> getPosts(int page, @Max(100) int count);
    @NonNull List<Post> getPostsByOwner(int page, @Max(100) int count, ObjectId ownerId);
    @NonNull Optional<Post> getPostsById(@NonNull ObjectId postId);

    @NonNull Optional<Post> setPost(@NonNull ObjectId postId, @NonNull Post post);
    @NonNull Optional<Post> setPostTitle(@NonNull ObjectId postId, @NonNull String title);
    @NonNull Optional<Post> setPostContent(@NonNull ObjectId postId, @NonNull String content);
}
