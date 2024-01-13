package com.example.ecommerceapp1.Service;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.Product;
import com.example.ecommerceapp1.Repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    //---------------------ADD A CATEGORY-----------------------------
    public Category saveCategory(Category category){
        Category savedCat=categoryRepository.save(category);
        return savedCat;
    }
    //----------------UPDATE A CATEGORY---------------------------------
    public Category updateCategory(Category category, String id) {
        ObjectId objectId = new ObjectId(id);
        Optional<Category> temp = categoryRepository.findById(objectId);
        if (temp.isEmpty()) {
            throw new RuntimeException("Employee id doesn't exist");
        }
        var upCategory = temp.get();
        if (category.getName() != null) {
            upCategory.setName(category.getName());
        }
        if (category.getDescription() != null) {
            upCategory.setDescription(category.getDescription());
        }
        return categoryRepository.save(upCategory);
    }
    //-------------------------DELETE A CATEGORY WITH ID---------------------
    public void removeCategoryById(String id) {
        ObjectId id1 = new ObjectId(id);

        Optional<Category> tempcat = categoryRepository.findById(id1);
        if (tempcat.isEmpty()) {
            throw new RuntimeException("Category id: " + id + ",doesn't exist");
        }
        categoryRepository.deleteById(id1);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String id){
        ObjectId catobjectId = new ObjectId(id);
        return categoryRepository.findById(catobjectId).get();
    }
    public List<Category> getCategoryfv(String fieldname,String value){
        if(fieldname.equals("name")){
            return  categoryRepository.getCategoriesByName(value);
        }
        else{
        return categoryRepository.getCategoryByDescription(value);}

    }



}
