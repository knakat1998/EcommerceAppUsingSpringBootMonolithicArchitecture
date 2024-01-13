package com.example.ecommerceapp1.service;
import com.example.ecommerceapp1.Entity.Product;
import com.example.ecommerceapp1.Repository.ProductRepository;
import com.example.ecommerceapp1.Service.ProductService;
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

public class ProductServiceTest {

    // dummy data
    @Mock
    private ProductRepository repository;

    // dummy service for injecting data
    @InjectMocks
    private ProductService service;


    // before each test case, ready the mocked data
    @Before
    public void setup(){

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllProduct(){
        List<Product> employeeList = new ArrayList<Product>() {
        };
        employeeList.add(new Product(new ObjectId("636dcf444b7e8832baeb2607"), "MI1",2300, "2002-10-10",null));
        employeeList.add(new Product(new ObjectId("636dcf444b7e8832baeb2608"), "MI2", 45,"2002-10-10",null));
        employeeList.add(new Product(new ObjectId("636dcf444b7e8832baeb2609"), "MI3",1000, "2002-02-20",null));

        when(repository.findAll()).thenReturn(employeeList);

        List<Product> employeesResult = service.getAllProducts();

        assertEquals(3, employeesResult.size());
    }

    @Test
    public void getCategoryId(){

        Product product = new Product(new ObjectId("636dcf444b7e8832baeb2607"), "MI1",2300,"abcd",null);

        when(repository.findById(new ObjectId("636dcf444b7e8832baeb2607"))).thenReturn(Optional.of(product));

        Product savedCategory = service.getProduct(String.valueOf(new ObjectId("636dcf444b7e8832baeb2607")));

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedCategory.getId());
        assertEquals("MI1", savedCategory.getName());
        assertEquals("2002-10-10", savedCategory.getDescription());

    }

    @Test
    public void saveCategory(){

        Product product = new Product(new ObjectId("636dcf444b7e8832baeb2607"), "MI1",456, "2002-10-10", null);

        when(repository.save(product)).thenReturn(product);

        Product savedCategory = service.saveProduct(product);

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedCategory.getId());
        assertEquals("MI1", savedCategory.getName());
        assertEquals("2002-10-10", savedCategory.getDescription());
    }


    @Test
    public void deleteCategoryById(){

        Product product = new Product(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", 465,"2002-10-10",null);
        when(repository.findById(product.getId())).thenReturn(Optional.of(product));

        service.removeProductById(String.valueOf(product.getId()));

        verify(repository, times(1)).deleteById(product.getId());
    }

}
