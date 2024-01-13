package com.example.ecommerceapp1.Repository;

import com.example.ecommerceapp1.Entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    @Query("{name : ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Category> getCategoriesByName(String name);
    @Query("{description : ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Category> getCategoryByDescription(String description);
}
