package com.example.ecommerceapp1.Controller;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.Product;
import com.example.ecommerceapp1.Service.CategoryService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @PostMapping("/add")
    public Category saveCategory(@RequestBody Category category){
        return  categoryService.saveCategory(category);
    }
    @PostMapping("update/{id}")
    public Category updateCategory(@RequestBody Category category,@PathVariable String id) {
        return categoryService.updateCategory(category,id);
    }
    @PostMapping("/del/{id}")
    public void removeCategoryById(@PathVariable String id) {
        categoryService.removeCategoryById(id);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/getCatById/{id}")
    public Category getcatById(@PathVariable String id) {
        return categoryService.getCategory(id);
    }


    @GetMapping("/getByFieldValue")
    public List<Category> getByFieldValue(@RequestBody Map<String,Object> map){
        return  categoryService.getCategoryfv(map.get("fieldname").toString(), map.get("value").toString());
    }



}
