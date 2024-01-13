package com.example.ecommerceapp1.Repository;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    @Query("{name : ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Product> getProductByName(String name);
    @Query("{ description: ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Product> getProductByDescription(String description);
    @Query("{price: ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Product> getProductByPrice(int price);

}
