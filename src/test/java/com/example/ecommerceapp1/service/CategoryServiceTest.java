package com.example.ecommerceapp1.service;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Repository.CategoryRepository;
import com.example.ecommerceapp1.Service.CategoryService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    // dummy data
    @Mock
    private CategoryRepository repository;

    // dummy service for injecting data
    @InjectMocks
    private CategoryService service;


    // before each test case, ready the mocked data
    @Before
    public void setup(){

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllCategory(){
        List<Category> employeeList = new ArrayList<Category>();
        employeeList.add(new Category(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10"));
        employeeList.add(new Category(new ObjectId("636dcf444b7e8832baeb2608"), "MI2", "2002-10-10"));
        employeeList.add(new Category(new ObjectId("636dcf444b7e8832baeb2609"), "MI3", "2002-02-20"));

        when(repository.findAll()).thenReturn(employeeList);

        List<Category> employeesResult = service.getAllCategories();

        assertEquals(3, employeesResult.size());
    }

    @Test
    public void getCategoryId(){

        Category category = new Category(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        when(repository.findById(new ObjectId("636dcf444b7e8832baeb2607"))).thenReturn(Optional.of(category));

        Category savedCategory = service.getCategory(String.valueOf(new ObjectId("636dcf444b7e8832baeb2607")));

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedCategory.getId());
        assertEquals("MI1", savedCategory.getName());
        assertEquals("2002-10-10", savedCategory.getDescription());

    }

    @Test
    public void saveCategory(){

        Category category = new Category(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        when(repository.save(category)).thenReturn(category);

        Category savedCategory = service.saveCategory(category);

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedCategory.getId());
        assertEquals("MI1", savedCategory.getName());
        assertEquals("2002-10-10", savedCategory.getDescription());
    }


    @Test
    public void deleteCategoryById(){

        Category category = new Category(new ObjectId("6581692f9a995a5d15fd70e3"), "MI1", "2002-10-10");
        when(repository.findById(category.getId())).thenReturn(Optional.of(category));

        service.removeCategoryById(String.valueOf(category.getId()));
       verify(repository, times(1)).deleteById(category.getId());
    }

}
