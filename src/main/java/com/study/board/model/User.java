package com.study.board.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@RequiredArgsConstructor
@ToString
@Document(collection = "user")
public class User {
    @JsonView(View.Public.class)
    @Id private ObjectId id;

    @JsonView(View.Public.class)
    @NonNull @NotNull private String name;

    @JsonView(View.Public.class)
    @NonNull @NotNull @Indexed(unique = true) private String email;

    @JsonView(View.Private.class)
    @NonNull @NotNull private String password;

    @JsonView(View.Public.class)
    @CreatedDate private Date createdAt;

    @JsonView(View.Public.class)
    @LastModifiedDate private Date updatedAt;
}
