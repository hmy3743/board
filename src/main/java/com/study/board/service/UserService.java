package com.study.board.service;

import com.study.board.model.User;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService {
    @NonNull Optional<ObjectId> createUser(@NonNull User user);

    @NonNull Optional<User> getUser(@NonNull ObjectId userId);

    @NonNull Optional<User> setUser(@NonNull ObjectId userId, @NonNull User user);
    @NonNull Optional<User> setUserName(@NonNull ObjectId userId, @NonNull String name);
    @NonNull Optional<User> setUserPassword(@NonNull ObjectId userId, @NonNull String password);

    boolean login(@NonNull String email, @NonNull String password);
}
