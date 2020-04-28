package com.study.board.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class CreateResult {
    @JsonView(View.Public.class)
    boolean isSuccess;

    @JsonView(View.Public.class)
    @Nullable String createdId;

    @JsonView(View.Public.class)
    long executeTimeInMilli;
}
