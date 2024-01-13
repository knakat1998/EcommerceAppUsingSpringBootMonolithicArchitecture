package com.example.ecommerceapp1.Entity;

public class PayloadValidation {


    public static boolean createdPayloadValidation1(Category category){


        if(category.getId()!=null){
            return false;
        }

        return true;
    }
    public static boolean createdPayloadValidation2(Product product){


        if(product.getId()!=null){
            return false;
        }

        return true;
    }
}
