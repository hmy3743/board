package com.study.board.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @JsonView(View.Public.class)
    @Id String id;

    @JsonView(View.Public.class)
    @Indexed @NonNull @NotNull @NotEmpty String ownerId;

    @JsonView(View.Public.class)
    @NonNull @NotNull @NotEmpty String title;

    @JsonView(View.Public.class)
    @NonNull @NotNull String content;

    @JsonView(View.Public.class)
    @CreatedDate Date createdAt;

    @JsonView(View.Public.class)
    @LastModifiedDate Date updatedAt;

    @JsonView(View.Public.class)
    List<Comment> comments = new ArrayList<>();
}
