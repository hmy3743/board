package com.study.board.service;

import com.study.board.model.User;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService {
    @NonNull Optional<String> createUser(@NonNull User user);

    @NonNull Optional<User> getUser(@NonNull String userId);

    @NonNull Optional<User> setUser(@NonNull String userId, @NonNull User user);
    @NonNull Optional<User> setUserName(@NonNull String userId, @NonNull String name);
    @NonNull Optional<User> setUserPassword(@NonNull String userId, @NonNull String password);

    boolean login(@NonNull String email, @NonNull String password);
}
