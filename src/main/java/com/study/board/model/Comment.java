package com.study.board.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@RequiredArgsConstructor
@ToString
public class Comment {
    @Id private ObjectId id;
    @NonNull private ObjectId postId;
    @NonNull private ObjectId ownerId;
    @NonNull private String content;
    @CreatedDate private Date createdAt;
    @LastModifiedDate private Date updatedAt;
}
