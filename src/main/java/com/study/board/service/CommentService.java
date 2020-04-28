package com.study.board.service;

import com.study.board.model.Comment;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    @NonNull Optional<ObjectId> createComment(@NonNull ObjectId postId, @NonNull Comment comment);

    @NonNull Optional<List<Comment>> getCommentsByPostId(@NonNull ObjectId postId);
    @NonNull Optional<Comment> getCommentById(@NonNull ObjectId postId, @NonNull ObjectId commentId);
    @NonNull Optional<Comment> getCommentByOwner(@NonNull ObjectId postId, @NonNull ObjectId userId);

    @NonNull Optional<Comment> setComment(@NonNull ObjectId commentId, @NonNull Comment comment);
    @NonNull Optional<Comment> setCommentContent(@NonNull ObjectId commentId, @NonNull String content);
}
