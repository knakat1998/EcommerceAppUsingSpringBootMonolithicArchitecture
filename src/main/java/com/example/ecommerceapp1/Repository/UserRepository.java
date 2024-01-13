package com.example.ecommerceapp1.Repository;

import com.example.ecommerceapp1.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query("{username: \"?0\"}")
    List<User> getUserByUsername(String username);
}
