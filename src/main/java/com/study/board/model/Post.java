package com.study.board.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@ToString
@Document(collection = "post")
public class Post {
    @Id ObjectId id;
    @Indexed @NonNull @NotNull @NotEmpty ObjectId ownerId;
    @NonNull @NotNull @NotEmpty String title;
    @NonNull @NotNull String content;
    @CreatedDate Date createdAt;
    @LastModifiedDate Date updatedAt;
    List<Comment> comments = new ArrayList<>();
}
