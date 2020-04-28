package com.study.board.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.board.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class Response<T> {
    @JsonView(View.Public.class)
    boolean isSuccess;

    @JsonView(View.Public.class)
    @Nullable
    T result;

    @JsonView(View.Public.class)
    long executeTimeInMilli;
}
