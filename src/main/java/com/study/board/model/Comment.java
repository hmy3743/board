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

import java.util.Date;

@Data
@RequiredArgsConstructor
@ToString
public class Comment {
    @JsonView(View.Public.class)
    @Id private String id;

    @JsonView(View.Public.class)
    @NonNull private String ownerId;

    @JsonView(View.Public.class)
    @NonNull private String content;

    @JsonView(View.Public.class)
    @CreatedDate private Date createdAt;

    @JsonView(View.Public.class)
    @LastModifiedDate private Date updatedAt;
}
