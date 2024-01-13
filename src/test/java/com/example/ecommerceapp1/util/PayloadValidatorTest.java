package com.example.ecommerceapp1.util;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.PayloadValidation;
//import com.springboot.movie.junitTesting.movieJunitTesting.model.Movie;
//import com.springboot.movie.junitTesting.movieJunitTesting.model.PayloadValidation;

import com.example.ecommerceapp1.Entity.Product;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayloadValidatorTest {

    // giving no id (which should not be present)
    @Test
    public void validatePayload1(){

        Category category = new Category(null, "MI1", "2002-10-10");

        assertEquals(true, PayloadValidation.createdPayloadValidation1(category));

    }




    //  giving valid id
    @Test
    public void validateInvalidPayload1(){
        ObjectId id = new ObjectId("507f191e810c19729de860ea");
        Category category = new Category(id, "MI1", "2002-10-10");

        assertEquals(false, PayloadValidation.createdPayloadValidation1(category));

    }

    @Test
    public void validatePayload(){

        Product product = new Product(null, "MI1", 45,"2002-10-10",null);

        assertEquals(true, PayloadValidation.createdPayloadValidation2(product));

    }




    //  giving valid id
    @Test
    public void validateInvalidPayload(){
        ObjectId id = new ObjectId("507f191e810c19729de860ea");
        Product product = new Product(id, "MI1",45, "2002-10-10",null);

        assertEquals(false, PayloadValidation.createdPayloadValidation2(product));

    }
}
