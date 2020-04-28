package com.study.board.model;

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
    @Id private ObjectId id;
    @NonNull @NotNull private String name;
    @NonNull @NotNull @Indexed(unique = true) private String email;
    @NonNull @NotNull private String password;
    @CreatedDate private Date createdAt;
    @LastModifiedDate private Date updatedAt;
}
