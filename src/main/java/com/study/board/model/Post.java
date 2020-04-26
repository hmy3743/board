package com.study.board.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@ToString
@Document(collection = "post")
public class Post {
    @Id private ObjectId id;
    @NonNull private ObjectId ownerId;
    @NonNull private String title;
    @NonNull private String content;
    @CreatedDate private Date createdAt;
    @LastModifiedDate private Date updatedAt;
    private List<Comment> comments = new ArrayList<Comment>();
}
