package com.example.ecommerceapp1.Controller;
import com.example.ecommerceapp1.Entity.Category;
import com.example.ecommerceapp1.Entity.Product;
import com.example.ecommerceapp1.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product){
        return  productService.saveProduct(product);
    }
    @PostMapping("update/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable String id) {
        return productService.updateProduct(product,id);
    }
    @PostMapping("/del/{id}")
    public void removeProductById(@PathVariable String id) {
        productService.removeProductById(id);}
    @PostMapping("/link/{prodId}/{catId}")
    public Product linkProdwithCatId(@PathVariable String prodId, @PathVariable String catId){
        return productService.linkProductWithCategory(prodId,catId);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/getByFieldValue")
    public List<Product> getByFieldValue(@RequestBody Map<String,Object> map){
        return  productService.getProductfv(map.get("fieldname").toString(), map.get("value").toString());
    }
}
