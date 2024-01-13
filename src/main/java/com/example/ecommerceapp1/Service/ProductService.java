package com.example.ecommerceapp1.Service;

import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.Product;
import com.example.ecommerceapp1.Repository.CategoryRepository;
import com.example.ecommerceapp1.Repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    //-------ADD PRODUCT-------------------
    public Product saveProduct(Product product){
        Product savedprod=productRepository.save(product);
        return savedprod;
    }
    //----------------UPDATE A CATEGORY---------------------------------
    public Product updateProduct(Product product, String id) {
        ObjectId objectId = new ObjectId(id);
        Optional<Product> temp = productRepository.findById(objectId);
        if (temp.isEmpty()) {
            throw new RuntimeException("Employee id doesn't exist");
        }
        var upProduct = temp.get();
        if (product.getName() != null) {
            upProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            upProduct.setDescription(product.getDescription());
        }
        if(product.getPrice()!=0){
            upProduct.setPrice(product.getPrice());
        }
        return productRepository.save(upProduct);
    }
    //-------------------------DELETE A CATEGORY WITH ID---------------------
    public void removeProductById(String id) {
        ObjectId id1 = new ObjectId(id);
        Optional<Product> temp =productRepository.findById(id1);
        if (temp.isEmpty()) {
            throw new RuntimeException("Product id: " + id + ",doesn't exist");
        }
        productRepository.deleteById(id1);
    }

    //--------------linking product to category id------------
    public Product linkProductWithCategory(String productId, String categoryId) {
        // Find the product by ID
        ObjectId objectId = new ObjectId(productId);

        Product product = productRepository.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + objectId + " not found"));

        // Find the category by ID
        ObjectId catobjectId = new ObjectId(categoryId);

        Category category = categoryRepository.findById(catobjectId)
                .orElseThrow(() -> new RuntimeException("Category with ID " + catobjectId+ " not found"));

        // Link the product to the category
        product.setCategory(category);

        // Save the updated product
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //--------------------------GET PRODUCT------------------------------
    public Product getProduct( String id){
        ObjectId objectId = new ObjectId(id);
        return productRepository.findById(objectId).get();
    }
    public List<Product> getProductfv(String fieldname,String value){
        if(fieldname.equals("name")){
            return  productRepository.getProductByName(value);
        }
        else if(fieldname.equals("description")){
            return productRepository.getProductByDescription(value);}

        return productRepository.getProductByPrice(Integer.parseInt(value));
    }

}
