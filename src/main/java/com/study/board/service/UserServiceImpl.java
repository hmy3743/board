package com.study.board.service;

import com.study.board.model.Post;
import com.study.board.model.User;
import com.study.board.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            MongoTemplate mongoTemplate
    ) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<String> createUser(User user) {
        return Optional.ofNullable(userRepository.insert(user)).map(User::getId);
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.findById(new ObjectId(userId));
    }

    @Override
    public Optional<User> setUser(String userId, User user) {
        Update update = new Update();

        if(user.getName() != null) update.addToSet("name", user.getName());
        if(user.getEmail() != null) update.addToSet("email", user.getEmail());
        if(user.getPassword() != null) update.addToSet("password", user.getPassword());

        return Optional.ofNullable(
                mongoTemplate.findAndModify(
                        Query.query(Criteria.where("_id").is(new ObjectId(userId))),
                        update,
                        User.class
                )
        );
    }

    @Override
    public Optional<User> setUserName(String userId, String name) {
        return Optional.empty();
    }

    @Override
    public Optional<User> setUserPassword(String userId, String password) {
        return Optional.empty();
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
