package com.study.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class CreateResult {
    boolean isSuccess;
    @Nullable String createdId;
    long executeTimeInMilli;
}
